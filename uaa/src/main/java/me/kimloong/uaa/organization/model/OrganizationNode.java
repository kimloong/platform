package me.kimloong.uaa.organization.model;

import com.arangodb.entity.BaseDocument;

/**
 * 组织节点实体
 *
 * @author KimLoong
 */
public class OrganizationNode extends BaseDocument {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
