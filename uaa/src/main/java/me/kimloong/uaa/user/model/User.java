package me.kimloong.uaa.user.model;

import com.arangodb.entity.BaseDocument;

/**
 * Created by Zhang JinLong(150429) on 2017-09-18.
 */
/**
 * 用户实体
 * @author KimLoon
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
