package me.kimloong.uaa.group.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import me.kimloong.uaa.group.entity.ChildOf;

/**
 * 群组关系 DAO
 *
 * @author KimLoong
 */
public interface ChildOfRepository extends ArangoRepository<ChildOf> {

    void deleteByParentIdAndChildId(String parentId, String childId);
}
