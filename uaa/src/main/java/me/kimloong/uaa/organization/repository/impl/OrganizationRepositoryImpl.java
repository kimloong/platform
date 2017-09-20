package me.kimloong.uaa.organization.repository.impl;

import me.kimloong.uaa.common.repository.impl.SimpleArangoRepository;
import me.kimloong.uaa.organization.model.Organization;
import me.kimloong.uaa.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Repository;

/**
 * 组织DAO实现
 *
 * @author KimLoong
 */
@Repository
public class OrganizationRepositoryImpl
        extends SimpleArangoRepository<Organization, String>
        implements OrganizationRepository {

    @Override
    protected String getCollectionName() {
        return "organization";
    }

    @Override
    protected Class<Organization> getEntityClass() {
        return Organization.class;
    }
}
