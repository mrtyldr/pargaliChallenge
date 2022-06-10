package li.parga.pargalichallenge.api;

import com.auth0.client.auth.AuthAPI;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import li.parga.pargalichallenge.repository.UserRepository;
import li.parga.pargalichallenge.service.AccountService;
import li.parga.pargalichallenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import li.parga.pargalichallenge.entities.User;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final AccountService accountService;


    @RequestMapping("/")
    public String loggedIn(Principal principal) throws UnirestException {
        HttpResponse<String> response1 = Unirest.post("https://dev-tqzxdnrv.us.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"Ek0IH8V30t5Pf7dBzM8sAbgrfkycE50R\",\"client_secret\":\"Akn9s8rl8MRiP-iOfV_07bdZ5Bscq6KaJx1DPFV1KpOY7Tqm-tu6WjBmrXrZwWnT\",\"audience\":\"https://dev-tqzxdnrv.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
                .asString();

        JSONObject json = new JSONObject(response1.getBody());
        String access_token = json.get("access_token").toString();
        HttpResponse<String> response = Unirest.get("https://dev-tqzxdnrv.us.auth0.com/api/v2/users/" + principal.getName())
                .header("authorization", "Bearer " +access_token)
                .asString();

        JSONObject userJson = new JSONObject(response.getBody());
        User user = new User(userJson.get("user_id").toString(),userJson.get("given_name").toString(),
                userJson.get("family_name").toString(),userJson.get("email").toString());
        if(!this.userService.existByEmail(user.getEmail()))
            this.userService.addUser(user);
        return user.getEmail();
    }

}
