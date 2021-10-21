package spring.websocket.repository;

import org.springframework.stereotype.Repository;
import spring.websocket.dto.MsgBrkChatRoom;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class MsgBrkChatRoomRepository {
    private Map<String, MsgBrkChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap=new LinkedHashMap<>();
    }

    public List<MsgBrkChatRoom> findAllRoom(){
        List chatRooms=new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms); // 최근 생성 순 정렬
        return chatRooms;
    }

    public MsgBrkChatRoom findRoomById(String id){
        return chatRoomMap.get(id);
    }

    public MsgBrkChatRoom createChatRoom(String name){
        MsgBrkChatRoom chatRoom=MsgBrkChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

}
