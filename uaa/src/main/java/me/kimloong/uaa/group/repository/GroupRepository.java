package me.kimloong.uaa.group.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import me.kimloong.uaa.group.entity.Group;
import org.springframework.stereotype.Repository;

/**
 * 群组DAO
 *
 * @author KimLoong
 */
@Repository
public interface GroupRepository extends ArangoRepository<Group> {

    Group findByName(String common);
}
