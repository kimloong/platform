package me.kimloong.uaa.group.entity;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import org.springframework.data.annotation.Id;

/**
 * 群组关系表
 *
 * @author KimLoong
 */
@Edge
public class ChildOf {

    @Id
    private String id;

    @From
    private Group parent;

    @To
    private Group child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getChild() {
        return child;
    }

    public void setChild(Group child) {
        this.child = child;
    }

    public Group getParent() {
        return parent;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }
}
