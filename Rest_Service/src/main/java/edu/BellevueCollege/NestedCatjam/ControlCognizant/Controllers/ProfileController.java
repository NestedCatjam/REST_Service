package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils.ProfileDaoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    //implement rest controller for profile
    public ProfileDaoUtil profileDaoUtil;

    public ProfileController(ProfileDaoUtil profileDaoUtil) {
        this.profileDaoUtil = profileDaoUtil;
    }
}
