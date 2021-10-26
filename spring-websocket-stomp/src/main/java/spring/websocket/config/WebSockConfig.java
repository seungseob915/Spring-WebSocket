package spring.websocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


/**
 *
 *   Websocket 활성화를 위한 Config
 *   
 *   - WebSocket 핸들러를 사용할 수 있도록 등록 설정
 *   - WebSocketConfigurer 인터페이스 구현을 통해 추가 설정 가능
 *
 *
 */

@RequiredArgsConstructor
@Configuration
@EnableWebSocket    // WebSocket 활성화
public class WebSockConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){   // WebSocketHandlerRegistry: Handler의 요청(URL 경로 등)에 대한 설정을 구성할 수 있는 메소드를 제공하는 I/F
        registry.addHandler(webSocketHandler, "/chat/ws").setAllowedOrigins("*");      // setAllowedOrigins : Origin 내 총 3가지 요소(protocol, host, port)가 동일한 허용된 Origin 요청만 수락. "*" 사용시 모든 Origin 허용      //  .withSockJS() 추가 시, SockJS 사용 가능
    }
}
