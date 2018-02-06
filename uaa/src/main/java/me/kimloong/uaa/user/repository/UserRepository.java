package me.kimloong.uaa.user.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import me.kimloong.uaa.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO接口
 *
 * @author KimLoong
 */
@Repository
public interface UserRepository extends ArangoRepository<User> {

    /**
     * 查找某群组下的某用户名的用户
     *
     * @param username  用户名
     * @param groupName 群组名
     * @return 用户信息
     */
    User findByNameAndGroupsName(String username, String groupName);

    /**
     * 查找某群组下的某电话号码的用户
     *
     * @param mobilePhone 手机号码
     * @param groupName   群组名
     * @return 用户信息
     */
    User findByMobilePhoneAndGroupsName(String mobilePhone, String groupName);

    /**
     * 查找某群组下的某邮箱的用户
     *
     * @param email     邮箱
     * @param groupName 群组名
     * @return 用户信息
     */
    User findByEmailAndGroupsName(String email, String groupName);
}
