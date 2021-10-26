package spring.websocket.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import spring.websocket.service.ChatService;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions=new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name){
        this.roomId=roomId;
        this.name=name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
            sessions.add(session);
//            System.out.println(session.getLocalAddress());
//            System.out.println(session.getRemoteAddress());
//            System.out.println(session.getUri());
//            System.out.println(session.getId());
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하였습니다.");
        }
        else if(chatMessage.getType().equals(ChatMessage.MessageType.LEAVE)){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 나갔습니다.");
        }
        sendMessage(chatMessage, chatService);
    }
    
    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session->chatService.sendMessage(session, message));
    }
}
