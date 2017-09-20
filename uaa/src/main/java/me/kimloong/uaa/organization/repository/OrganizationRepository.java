package me.kimloong.uaa.organization.repository;

import me.kimloong.uaa.common.repository.CrudRepository;
import me.kimloong.uaa.organization.model.Organization;
import org.springframework.stereotype.Repository;

/**
 * 组织DAO
 *
 * @author KimLoong
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
