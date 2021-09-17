package org.springinincheon.chat.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


/**
 *
 *   WebSocket 사용을 위한 핸들러 클래스 정의 (TextWebSocketHandler 상속)
 *
 *   [오버라이드 메소드]
 *   1) handlerTextMessage() : 웹소켓 서버로 메세지가 도착할 때 처리해야할 작업을 정의하는 메소드
 *   2) afterConnectionClosed() : 웹소켓 연결이 종료된 뒤, 서버에서 실행해야할 작업을 정의하는 메소드
 *   3) afterConnectionEstablished() : 연결 성공 후 처리해야할 작업
 *
 * <p>
 *
 * <pre>
 * 개정이력(Modification Information)·
 * 수정일   수정자    수정내용
 * ------------------------------------
 *
 * </pre>
 *
 * @author Seobi
 * @since 2021. 9. 18.
 * @version 0.0.1
 * @see
 *
 */

@Slf4j
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload=message.getPayload();    // .getPayload() : Message 내 텍스트 값 리턴
        log.info("Text :  {}", payload);
        System.out.println(payload);

        TextMessage textMessage=new TextMessage("채팅 참여를 시작합니다.");
        session.sendMessage(textMessage);
    }
}
