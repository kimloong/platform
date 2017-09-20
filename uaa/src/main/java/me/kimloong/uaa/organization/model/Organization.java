package me.kimloong.uaa.organization.model;

import com.arangodb.entity.BaseDocument;

/**
 * 组织实体
 *
 * @author KimLoong
 */
public class Organization extends BaseDocument {

    /*组织名*/
    private String name;

    /*是否虚拟组织*/
    private boolean virtual;

    public String getCode() {
        return getKey();
    }

    public void setCode(String code) {
        setKey(code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }
}
