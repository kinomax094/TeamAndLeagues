package pl.com.b2bnetwork.football.service.impl;

import pl.com.b2bnetwork.football.domain.User;

public interface UserService {

    User findByUsername(String name);

    boolean authenticate(String username, String password);
}
