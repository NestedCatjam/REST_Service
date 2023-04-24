package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.organizations.Member;
import com.auth0.json.mgmt.organizations.Organization;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {
    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/organizations/")
    public Organization createOrganization(@RequestBody Organization organization) throws Auth0Exception {
        return userManagementService.createOrganization(organization);
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
