package me.kimloong.uaa.organization.controller;

import me.kimloong.uaa.organization.model.Organization;
import me.kimloong.uaa.organization.model.OrganizationNode;
import me.kimloong.uaa.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 组织/节点 Controller
 *
 * @author KimLoong
 */
@RestController
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @RequestMapping(value = "/organizations", method = RequestMethod.POST)
    public Organization add(
            @RequestBody Organization organization) {
        return service.add(organization);
    }

    @RequestMapping(value = "/organizations/{organizationCode}/", method = RequestMethod.POST)
    public Organization addNode(
            @PathVariable String organizationCode,
            @RequestBody OrganizationNode node) {
        return service.addNode(organizationCode, node);
    }
}
