package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.organizations.Member;
import com.auth0.json.mgmt.organizations.Organization;
import com.auth0.json.mgmt.organizations.OrganizationsPage;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/organizations/")
    public Organization createOrganization(Authentication authentication, @RequestBody Organization organization) throws Auth0Exception {
        return userManagementService.createOrganization(organization, authentication);
    }

    @GetMapping("/organizations/")
    public OrganizationsPage getOrganizations(Authentication authentication) throws Auth0Exception {
        return userManagementService.getOrganizations(authentication);
    }

    @GetMapping("/organizations/{id}/members/")
    public List<Member> getMembers(@PathVariable("id") String id) throws Auth0Exception {
        return userManagementService.getMembers(id);
    }

    @PostMapping("/organizations/{organizationID}/members")
    public void addMember(@PathVariable("organizationID") String organizationID, @RequestBody String userID) throws Auth0Exception {
        userManagementService.addMember(organizationID, userID);
    }
}
