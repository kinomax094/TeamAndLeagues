package pl.com.b2bnetwork.football.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.com.b2bnetwork.football.domain.User;
import pl.com.b2bnetwork.football.service.impl.UserService;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserDatabaseAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userService.authenticate(username, password)) {
            User user = userService.findByUsername(username);
            String userRole = user.getRole().toString();

            return new
                    UsernamePasswordAuthenticationToken(username, password, Arrays.asList(new SimpleGrantedAuthority(userRole)));
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
