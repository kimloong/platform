package me.kimloong.uaa.organization.service;

import me.kimloong.uaa.organization.model.Organization;
import me.kimloong.uaa.organization.model.OrganizationNode;
import me.kimloong.uaa.organization.repository.OrganizationNodeRepository;
import me.kimloong.uaa.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 组织/组织节点 Service
 *
 * @author KimLoong
 */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Autowired
    private OrganizationNodeRepository nodeRepository;

    public Organization add(Organization organization){
        return repository.add(organization);
    }

    public Organization addNode(String organizationCode, OrganizationNode node) {
        return null;
    }
}
