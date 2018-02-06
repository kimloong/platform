package me.kimloong.uaa;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Arangodb数据库配置
 *
 * @author KimLoong
 */
@Configuration
@EnableArangoRepositories
public class ArangodbConfig extends AbstractArangoConfiguration {

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB.Builder()
                .host("127.0.0.1", 8529)
                .user("root")
                .password("root");
    }

    @Override
    public String database() {
        return "uaa";
    }
}
