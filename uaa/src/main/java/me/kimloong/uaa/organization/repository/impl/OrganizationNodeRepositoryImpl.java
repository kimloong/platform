package me.kimloong.uaa.organization.repository.impl;

import me.kimloong.uaa.common.repository.impl.SimpleArangoRepository;
import me.kimloong.uaa.organization.model.OrganizationNode;
import me.kimloong.uaa.organization.repository.OrganizationNodeRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织节点DAO实现
 *
 * @author KimLoong
 */
@Repository
public class OrganizationNodeRepositoryImpl
        extends SimpleArangoRepository<OrganizationNode, String>
        implements OrganizationNodeRepository {

    @Override
    protected String getCollectionName() {
        return "organization_node";
    }

    @Override
    protected Class<OrganizationNode> getEntityClass() {
        return OrganizationNode.class;
    }
}
