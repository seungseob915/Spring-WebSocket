package spring.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.websocket.dto.ChatMessage;
import spring.websocket.dto.ChatRoom;
import spring.websocket.repository.ChatRoomRepository;
import spring.websocket.service.RedisPublisher;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if(ChatMessage.MessageType.ENTER.equals(message.getType())){
            chatRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장했습니다.");
        }
        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}
