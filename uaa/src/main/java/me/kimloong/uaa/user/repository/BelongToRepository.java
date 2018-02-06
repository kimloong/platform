package me.kimloong.uaa.user.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import me.kimloong.uaa.user.entity.BelongTo;

/**
 * 组织/用户从属关系DAO
 *
 * @author KimLoong
 */
public interface BelongToRepository extends ArangoRepository<BelongTo> {

//    boolean isBelongTo(String userId, String organizationCode);
}
