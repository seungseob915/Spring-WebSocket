package spring.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import spring.websocket.dto.ChatRoom;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms=new LinkedHashMap<>();
    }

    // 모든 채팅방 검색
    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    // roomId로 채팅방 검색
    public ChatRoom findChatRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    // 채팅방 생성
    public ChatRoom createChatRoom(String name){
        String randomId= UUID.randomUUID().toString();
        ChatRoom chatRoom=ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId,chatRoom);
        return chatRoom;
    }

    // 메세지 전송
    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }

}
