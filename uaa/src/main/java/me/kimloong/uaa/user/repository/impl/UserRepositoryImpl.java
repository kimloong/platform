package me.kimloong.uaa.user.repository.impl;

import com.arangodb.ArangoDatabase;
import me.kimloong.uaa.user.model.User;
import me.kimloong.uaa.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Queue;

/**
 * 用户DAO实现
 * @author KimLoong
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Queue<ArangoDatabase> queue;

    public User findOne(String username) {
        ArangoDatabase database = queue.peek();
        try {
            return database.getDocument("user/" + username, User.class);
        } finally {
            queue.add(database);
        }
    }

    public User add(User user) {
        ArangoDatabase database = queue.peek();
        try {
            return database.collection("user").insertDocument(user).getNew();
        } finally {
            queue.add(database);
        }
    }
}
