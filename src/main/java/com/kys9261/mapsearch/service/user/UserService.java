package com.kys9261.mapsearch.service.user;

import com.kys9261.mapsearch.exception.NotFoundException;
import com.kys9261.mapsearch.model.user.User;
import com.kys9261.mapsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUserId(String userId) {
        checkNotNull(userId, "userId must be provided.");

        return userRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException());
    }

    public User login(String userId, String password) {
        checkArgument(StringUtils.hasText(userId), "userId must be provided.");
        checkArgument(StringUtils.hasText(password), "password must be provided.");

        User user = getUserByUserId(userId);
        user.login(passwordEncoder, password);
        return user;
    }
}
