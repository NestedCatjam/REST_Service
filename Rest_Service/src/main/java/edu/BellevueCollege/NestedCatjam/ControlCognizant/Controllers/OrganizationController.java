package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.Role;
import com.auth0.json.mgmt.organizations.Invitation;
import com.auth0.json.mgmt.organizations.Member;
import com.auth0.json.mgmt.organizations.Organization;
import com.auth0.json.mgmt.organizations.Roles;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrganizationController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/organizations/")
    public Organization createOrganization(Authentication authentication, @RequestBody Organization organization) throws Auth0Exception {
        return userManagementService.createOrganization(organization, authentication);
    }

    @GetMapping("/organizations/")
    public List<Organization> getOrganizations(Authentication authentication) throws Auth0Exception {
        return userManagementService.getOrganizations(authentication).getItems();
    }


    @GetMapping("/organizations/{id}")
    public Organization getOrganization(Authentication authentication, @PathVariable("id") String id) throws Auth0Exception {
        return userManagementService.getOrganization(authentication, id);
    }


    @GetMapping("/organizations/{id}/members/")
    public List<Member> getMembers(@PathVariable("id") String id) throws Auth0Exception {
        return userManagementService.getMembers(id);
    }

    @PostMapping("/organizations/{organizationID}/members/")
    public void addMember(@PathVariable("organizationID") String organizationID, @RequestBody Map<String, List<String>> userIDsObject) throws Auth0Exception {
        System.out.println("Add " + userIDsObject.toString() + " to " + organizationID);
        for (final var userIDs : userIDsObject.values()) {
            userManagementService.addMember(organizationID, userIDs);
        }
    }
    @PostMapping("/organizations/{organizationID}/invitations/")
    public Invitation invite(Authentication authentication, @PathVariable("organizationID")
    String organizationID, @RequestBody String email) throws Auth0Exception {
        return userManagementService.invite(authentication, organizationID, email);
    }

    @DeleteMapping("/organizations/{organizationID}/members/{userID}")
    public void removeMember(Authentication authentication, @PathVariable("organizationID")
    String organizationID, @PathVariable("userID") String userID) throws Auth0Exception {
        userManagementService.removeMember(authentication, organizationID, userID);
    }

    @GetMapping("/organizations/{organizationID}/members/me/roles")
    public List<Role> getMyRolesIn(Authentication authentication, @PathVariable("organizationID")
    String organizationID) throws Auth0Exception {
        return getRoles(authentication, organizationID, authentication.getName());
    }

    @GetMapping("/organizations/{organizationID}/members/{userID}/roles")
    public List<Role> getRoles(Authentication authentication, @PathVariable("organizationID")
    String organizationID, @PathVariable("userID") String userID) throws Auth0Exception {
        return userManagementService.getRoles(authentication, organizationID, userID).getItems();
    }

    /**
     * @param authentication
     * @param organizationID
     * @param userID
     * @param roles: list of Auth0 role IDs
     * @throws Auth0Exception
     */
    @PostMapping("/organizations/{organizationID}/members/{userID}/roles")
    public void addRoles(Authentication authentication, @PathVariable("organizationID")
    String organizationID, @PathVariable("userID") String userID, @RequestBody List<String> roles) throws Auth0Exception {
        userManagementService.assignRole(authentication, organizationID, userID, new Roles(roles));
    }
}
