package pl.com.b2bnetwork.football.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import pl.com.b2bnetwork.football.domain.User;
import pl.com.b2bnetwork.football.repository.UserRepository;
import pl.com.b2bnetwork.football.service.impl.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsername(final String name) {

        User result = new User();
        Optional<User> optionalUser = userRepository.findOneByUsername(name);

        if (optionalUser.isPresent()) {
            result = optionalUser.get();
        }
        return result;
    }

    @Override
    public boolean authenticate(final String username, final String password) {
        User user = userRepository.findOneByUsernameAndPassword(username, DigestUtils.md5Hex(password));
        return user != null ? true : false;
    }
}
