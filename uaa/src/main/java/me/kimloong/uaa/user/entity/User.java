package me.kimloong.uaa.user.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import me.kimloong.uaa.group.entity.Group;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Pattern;
import java.util.Collection;

/**
 * 用户实体
 *
 * @author KimLoong
 */
@Document("user")
public class User {

    @Id
    private String id;

    @Pattern(regexp = "[a-zA-Z]+[\\w]+", message = "用户名只能是以字母开头的字母数字中划线及下划线")
    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Pattern(regexp = "[\\w]+@[\\w]+.[\\w]+", message = "邮箱不符合格式")
    private String email;

    @Pattern(regexp = "[\\d]{11}", message = "电话号码只能是长度11位的数字")
    private String mobilePhone;

    @Relations(edges = BelongTo.class, lazy = true)
    private Collection<Group> groups;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Collection<Group> getGroups() {
        return groups;
    }

    public void setGroups(Collection<Group> groups) {
        this.groups = groups;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
