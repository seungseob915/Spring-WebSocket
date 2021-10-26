package spring.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Profile("local")
@Configuration
public class WebSockEmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedisServer(){
        redisServer=new RedisServer(redisPort);
        System.out.println("local");
        redisServer.start();
    }

    @PreDestroy
    public void stopRedisServer(){
        if(redisServer!=null){
            redisServer.stop();
        }
    }
}
