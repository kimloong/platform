package me.kimloong.uaa.user.repository;

import me.kimloong.uaa.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO接口
 * @author KimLoong
 */
@Repository
public interface UserRepository {

    User findOne(String username);

    User add(User user);
}
