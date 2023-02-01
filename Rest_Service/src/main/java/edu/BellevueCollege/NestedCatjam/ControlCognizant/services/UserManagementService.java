package edu.BellevueCollege.NestedCatjam.ControlCognizant.services;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.auth0.json.mgmt.users.User;
import com.auth0.net.TokenRequest;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    public UserManagementService() {

    }
    // TODO: fix this
    private ManagementAPI getApi() throws Auth0Exception{
        if (api == null) {
            final var api = new AuthAPI(domain, clientID, clientSecret);
            final var token = api.requestToken(audience).execute();
            this.api = new ManagementAPI(domain, token.getAccessToken());
        }
        return api;
    }
    // TODO: consider returning different type to better encapsulate the use of auth0 and reduce coupling
    public List<User> getUsers() throws Auth0Exception {
        return getApi().users().list(new UserFilter()).execute().getItems();
    }

}
