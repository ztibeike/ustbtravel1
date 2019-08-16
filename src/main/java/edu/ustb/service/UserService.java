package edu.ustb.service;

import edu.ustb.domain.User;

public interface UserService {
    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    boolean register(User user);

    boolean active(String code);

    boolean login(User user);

    User findByUsernameAndPassword(String username, String password);
}
