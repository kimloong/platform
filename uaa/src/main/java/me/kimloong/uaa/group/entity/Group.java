package me.kimloong.uaa.group.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.Collection;

/**
 * 群组实体
 *
 * @author KimLoong
 */
@Document
@HashIndex(fields = {"name"}, unique = true)
public class Group {

    @Id
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 显示名
     */
    private String displayName;

    /**
     * 类型
     */
    private GroupType type;

    /**
     * 子群组
     */
    @JsonIgnore
    @Relations(edges = ChildOf.class, lazy = true)
    private Collection<Group> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

    public Collection<Group> getChildren() {
        return children;
    }

    public void setChildren(Collection<Group> children) {
        this.children = children;
    }
}
