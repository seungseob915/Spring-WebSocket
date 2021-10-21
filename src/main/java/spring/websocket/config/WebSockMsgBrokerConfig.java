package spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 *
 *   Websocket Stomp 사용을 위한 Config
 *
 */

@Configuration
@EnableWebSocketMessageBroker       // Stomp 사용을 위한 선언
public class WebSockMsgBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/sub");  // 메시지 구독하는 요청은 sub으로 시작
        config.setApplicationDestinationPrefixes("/pub");   // 메시징 구현을 위한 메시지 발행 요청은 pub으로 시작
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*").withSockJS();
    }
}
