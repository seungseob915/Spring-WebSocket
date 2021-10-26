package spring.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MsgBrkChatRoom {
    private String roomId;
    private String name;
    // 메시지큐에서 구독자를 관리하여, 웹 소켓 세션관리가 필요 없음

    public static MsgBrkChatRoom create(String name){
        MsgBrkChatRoom chatRoom=new MsgBrkChatRoom();
        chatRoom.roomId= UUID.randomUUID().toString();
        chatRoom.name=name;
        return chatRoom;
    }
}
