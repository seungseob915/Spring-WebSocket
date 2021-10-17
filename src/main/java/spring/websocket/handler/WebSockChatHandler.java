package spring.websocket.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import spring.websocket.dto.ChatMessage;
import spring.websocket.dto.ChatRoom;
import spring.websocket.service.ChatService;

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
 *
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload=message.getPayload();    // .getPayload() : Message 내 텍스트 값 리턴
        log.info("Text :  {}", payload);
        System.out.println(payload);

//        TextMessage textMessage=new TextMessage("채팅 참여를 시작합니다.");
//        session.sendMessage(textMessage);

        ChatMessage chatMessage=objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom chatRoom=chatService.findChatRoomById(chatMessage.getRoomId());
        chatRoom.handleActions(session,chatMessage,chatService);
    }
}
