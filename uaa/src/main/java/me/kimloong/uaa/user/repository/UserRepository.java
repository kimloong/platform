package me.kimloong.uaa.user.repository;

import me.kimloong.uaa.common.repository.CrudRepository;
import me.kimloong.uaa.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO接口
 * @author KimLoong
 */
@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
