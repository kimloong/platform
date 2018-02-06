package me.kimloong.uaa.user.entity;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import me.kimloong.uaa.group.entity.Group;
import org.springframework.data.annotation.Id;

/**
 * 用户从属关系表
 *
 * @author KimLoong
 */
@Edge
public class BelongTo {

    @Id
    private String id;

    @From
    private Group group;

    @To
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
