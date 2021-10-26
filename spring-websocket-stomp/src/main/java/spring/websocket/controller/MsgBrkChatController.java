package spring.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import spring.websocket.dto.ChatMessage;

/**
 *
 *   Stomp - pub(Publisher) 구현 컨트롤러
 *
 */

@RequiredArgsConstructor
@Controller
public class MsgBrkChatController {
    private final SimpMessageSendingOperations messageSendingOperation;

    @MessageMapping("/chatstp/message")    // WebSocket으로 들어오는 메시지 발행을 처리. Clinet측에서 /pub/chat/message로 발행 요청하면, Controller가 받아서 처리함.
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.ENTER.equals(message.getType())){
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }
        messageSendingOperation.convertAndSend("/sub/chatstp/room/"+message.getRoomId(),message);  // 메시지가 발행되면, Send처리를 해서, 구독하고 있는 Client에게 전송
    }
}
