package spring.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import spring.websocket.dto.ChatMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern){
        try{
            String publishMessage=(String) redisTemplate.getStringSerializer().deserialize(message.getBody());  // 발행된 메시지를 받아 deserialize
            ChatMessage roomMessage=objectMapper.readValue(publishMessage, ChatMessage.class);  // ChatMessage 객체 타입으로 저장
            messageSendingOperations.convertAndSend("/sub/chat/room/"+roomMessage.getRoomId(), roomMessage);    // 채팅방 해당 구독자에게 메시지 전달
        }catch (Exception e){
            //log.error(e.getMessage());
        }
    }
}
