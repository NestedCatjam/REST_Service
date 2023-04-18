package edu.BellevueCollege.NestedCatjam.ControlCognizant.services;

import com.auth0.client.HttpOptions;
import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.PageFilter;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.Role;
import com.auth0.json.mgmt.organizations.Member;
import com.auth0.json.mgmt.organizations.Members;
import com.auth0.json.mgmt.organizations.Organization;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.TokenRequest;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.config.ApplicationProperties;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserManagementService {
    private ManagementAPI api;


    @Value("https://${env.AUTH0_DOMAIN}/")
    public String domain;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_ID}")
    public String clientID;

    @Value("${env.AUTH0_MANAGEMENT_CLIENT_SECRET}")
    public String clientSecret;

    @Value("${env.AUTH0_MANAGEMENT_AUDIENCE}") public String audience;
    private AuthAPI authAPI;

    @Autowired
    public UserManagementService() {

    }
    // TODO: fix this
    private ManagementAPI getApi() throws Auth0Exception{
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

    public List<Member> getMembers(Authentication authentication) throws Auth0Exception{
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

    public void removeMember(String organizationID, String userID) throws Auth0Exception {
        getApi().organizations().deleteMembers(organizationID, new Members(List.of(userID))).execute();
    }

    public Member getMember(Authentication authentication, String userID) throws Auth0Exception {
        final var api = getApi();
        final var organizations = api.users().getOrganizations(authentication.getName(), new PageFilter()).execute().getItems();
        for (var organization : organizations) {
            api.organizations().getMembers(organization.getId(), new PageFilter())
        }

    }
}
