package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Profile;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileDaoUtil {
   private static final List<Profile> PROFILE_LIST = new ArrayList<>();
    private static int profileCount;
    public List<Profile> findAll() {
        return PROFILE_LIST;
    }

    public Profile save(Profile Profile) {
        if (Profile.getId() == 0) {
            Profile.setId(profileCount++);
        }
        PROFILE_LIST.add(Profile);
        return Profile;
    }
    public Profile findProfile(int id) {
        for(Profile Profile : PROFILE_LIST) {
            if(Profile.getId() == id) {
                return Profile;
            }
        }
        return null;
    }

    public Profile deleteById(int id) {
        for(Profile Profile : PROFILE_LIST) {
            if(Profile.getId() == id) {
                PROFILE_LIST.remove(Profile);
                return Profile;
            }
        }
        return null;
    }

}
