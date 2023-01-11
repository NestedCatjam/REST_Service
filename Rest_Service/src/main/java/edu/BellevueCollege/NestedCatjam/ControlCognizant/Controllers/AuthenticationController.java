package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Security.LoginRequest;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Security.WebSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    WebSecurityConfiguration webSecurityConfiguration;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LoginRequest loginRequest) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest loginRequest) {
        return null;
    }
}

