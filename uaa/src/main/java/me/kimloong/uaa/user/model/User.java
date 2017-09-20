package me.kimloong.uaa.user.model;

import com.arangodb.entity.BaseDocument;

/**
 * 用户实体
 * @author KimLoong
 */
public class User extends BaseDocument {

    private String password;

    public void setUsername(String username) {
        setKey(username);
    }

    public String getUsername() {
        return getKey();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
