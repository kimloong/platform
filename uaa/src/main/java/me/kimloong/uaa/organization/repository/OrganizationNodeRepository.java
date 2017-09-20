package me.kimloong.uaa.organization.repository;

import me.kimloong.uaa.common.repository.CrudRepository;
import me.kimloong.uaa.organization.model.OrganizationNode;
import org.springframework.stereotype.Repository;

/**
 * 组织节点DAO
 *
 * @author KimLoong
 */
@Repository
public interface OrganizationNodeRepository extends CrudRepository<OrganizationNode, String> {
}
