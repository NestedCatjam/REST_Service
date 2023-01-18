//package edu.BellevueCollege.NestedCatjam.ControlCognizant.Security;
//
//import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
//import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
//import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
//        if (null == user) {
//            throw new UserNotFoundException("User not found");
//        }
//        return new CustomUserDetails(user);
//    }
//}
