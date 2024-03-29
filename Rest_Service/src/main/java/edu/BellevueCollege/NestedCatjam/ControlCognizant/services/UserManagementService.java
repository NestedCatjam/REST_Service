package edu.BellevueCollege.NestedCatjam.ControlCognizant.services;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.PageFilter;
import com.auth0.client.mgmt.filter.RolesFilter;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.Permission;
import com.auth0.json.mgmt.Role;
import com.auth0.json.mgmt.RolesPage;
import com.auth0.json.mgmt.organizations.*;
import com.auth0.json.mgmt.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserManagementService {
    private ManagementAPI api;


    @Value("https://${env.AUTH0_DOMAIN}/")
    public String domain;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_ID}")
    public String clientID;

    @Value("${env.AUTH0_CLIENT_ID}")
    public String invitationClientID;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_SECRET}")
    public String clientSecret;

    @Value("${env.AUTH0_MANAGEMENT_AUDIENCE}")
    public String audience;
    private AuthAPI authAPI;

    @Autowired
    public UserManagementService() {

    }

    // TODO: fix this
    private ManagementAPI getApi() throws Auth0Exception {
        if (null == authAPI) {
            authAPI = new AuthAPI(domain, clientID, clientSecret);
        }
        final var request = authAPI.requestToken(audience);
        final var token = request.execute();
        System.out.println(token);

        if (null == this.api) {
            this.api = new ManagementAPI(domain, token.getAccessToken());
        } else {
            this.api.setApiToken(token.getAccessToken());
        }

        return api;
    }

    // TODO: consider returning different type to better encapsulate the use of auth0 and reduce coupling
    public List<User> getUsers() throws Auth0Exception {
        return getApi().users().list(new UserFilter()).execute().getItems();
    }

    public void update(String userId, User user) throws Auth0Exception {
        final var result = getApi().users().update(userId, user).execute();
    }

    public void delete(String userId) throws Auth0Exception {
        getApi().users().delete(userId).execute();
    }

    public User addUser(User user) throws Auth0Exception {
        return getApi().users().create(user).execute();

    }

    public void assignRole(String id, List<String> role) throws Auth0Exception {
        getApi().users().addRoles(id, role).execute();
    }

    public List<Member> getMembers(Authentication authentication) throws Auth0Exception {
        final var api = getApi();
        final var organizations = api.users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems();
        return organizations.stream().flatMap(organization -> {
            try {
                return api.organizations().getMembers(organization.getId(), new PageFilter()).execute().getItems().stream();
            } catch (Auth0Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public List<Member> getMembers(String organizationID) throws Auth0Exception {
        final var api = getApi();
        return api.organizations().getMembers(organizationID, new PageFilter()).execute().getItems();
    }

    public void removeMember(String organizationID, String userID) throws Auth0Exception {
        getApi().organizations().deleteMembers(organizationID, new Members(List.of(userID))).execute();
    }

    public void addMember(String organizationID, String userID) throws Auth0Exception {
        getApi().organizations().addMembers(organizationID, new Members(List.of(userID))).execute();
    }

    public void assignRole(Authentication authentication, String userID, Roles roles) throws Auth0Exception {
        final var api = getApi();
        final var userOrganizations = api.users().getOrganizations(userID, new PageFilter()).execute().getItems().stream().map(userOrganization -> userOrganization.getId()).collect(Collectors.toCollection(HashSet::new));
        final var adminOrganizations = api.users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems();
        adminOrganizations.removeIf(organization -> !userOrganizations.contains(organization.getId()));
        final var organizations = adminOrganizations;
        for (final var organization : organizations) {
            api.organizations().addRoles(organization.getId(), userID, roles).execute();
        }
    }
    // TODO: break logic that user has permission into separate function later
    //admin assigning role to existing member in the organization
    public void assignRole(Authentication authentication, String organizationID,
                           String userID, Roles roles) throws Auth0Exception {
        //check whether user is signed in making requests to API is in org
        final var admin = authentication.getName();
        verifyInOrganization(authentication, organizationID);
        //verify role: user must be admin within the org
        final var api = getApi();
        //get roles for potential admin

        boolean userVerified = false;
        for (final var role : getRoles(authentication, organizationID,
                authentication.getName()).getItems()){
            //if at least one of the roles has the permission, continue, else throw exception

            boolean hasPermission = api.roles().listPermissions(role.getId(), new PageFilter())
                    .execute().getItems().stream().anyMatch(permission ->
                            permission.getName().equals("assign_role:users"));
            if (hasPermission){
                userVerified = true;
                break;
            }
        }
        if (!userVerified){
            throw new AccessDeniedException("User cannot assign roles");
        }
        //add the roles to the specified user
        api.organizations().addRoles(organizationID, userID, roles).execute();
    }

    public Organization createOrganization(Organization organization, Authentication authentication) throws Auth0Exception {
        final var api = getApi();
        final var result = api.organizations().create(organization).execute();
        getApi().organizations().addMembers(result.getId(), new Members(List.of(authentication.getName()))).execute();
        //add user as admin
        return result;
    }

    public OrganizationsPage getOrganizations(Authentication authentication) throws Auth0Exception {
        final var api = getApi();
        System.out.println(authentication.getName());
        return api.users().getOrganizations(authentication.getName(), new PageFilter()).execute();
    }

    public void addUser(Authentication authentication, String userID) throws Auth0Exception {
        final var api = getApi();
        final var organizations = api.users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems();
        for (final var organization : organizations) {
            api.organizations().addMembers(organization.getId(), new Members(List.of(userID)));
        }

    }



    public Invitation invite(Authentication authentication, String organizationID, String email)  throws Auth0Exception {
        final var api = getApi();
        verifyInOrganization(authentication, organizationID);

        return api.organizations().createInvitation(organizationID, new Invitation(new Inviter(authentication.getName()), new Invitee(email), invitationClientID)).execute();
    }

    private void verifyInOrganization(Authentication authentication, String organizationID) throws Auth0Exception {
        if (!getOrganizations(authentication).getItems().stream().anyMatch(organization -> organization.getId().equals(organizationID))) {
            throw new AccessDeniedException("The user creating the invitation is not a member of this organization.");
            // TODO: check for role
        }
    }


    public void removeMember(Authentication authentication, String organizationID, String userID) throws Auth0Exception {
        if (!getApi().users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems().stream()
                .anyMatch(organization -> organization.getId().equals(organizationID))) {
            throw new AccessDeniedException("The user is not a member of this organization.");
            // TODO: check for role
        }

        removeMember(organizationID, userID);


    }

    public RolesPage getRoles(Authentication authentication, String organizationID, String userID) throws Auth0Exception {
        final var api = getApi();

        if (getOrganizations(authentication).getItems().stream().allMatch(organization -> !organization.getId().equals(organizationID))) {
            throw new AccessDeniedException("The user is not a member of this organization.");
        }

        return api.organizations().getRoles(organizationID, userID, new PageFilter()).execute();
    }

    public Organization getOrganization(Authentication authentication, String id) throws Auth0Exception {


        return getOrganizations(authentication).getItems().stream()
                .filter(organization -> organization.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new AccessDeniedException("The user is not a member of this organization"));
    }
//    public Member getMember(Authentication authentication, String userID) throws Auth0Exception {
//        final var api = getApi();
//        final var organizations = api.users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems();
//        for (var organization : organizations) {
//            api.organizations().getMembers(organization.getId(), new PageFilter());
//        }
//
//    }
}
