package li.parga.pargalichallenge.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/logout")
public class LogoutHandler extends SecurityContextLogoutHandler {

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        super.logout(request, response, authentication);


        String issuer = (String) getClientRegistration().getProviderDetails().getConfigurationMetadata().get("issuer");
        String clientId = getClientRegistration().getClientId();
        String returnTo = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();

        String logoutUrl = UriComponentsBuilder
                .fromHttpUrl(issuer + "v2/logout?client_id={clientId}&returnTo={returnTo}")
                .encode()
                .buildAndExpand(clientId, returnTo)
                .toUriString();

        try {
            response.sendRedirect(logoutUrl);
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(),ioe);
        }
    }

    @Bean
    private ClientRegistration getClientRegistration() {
        return this.clientRegistrationRepository.findByRegistrationId("auth0");
    }
}
