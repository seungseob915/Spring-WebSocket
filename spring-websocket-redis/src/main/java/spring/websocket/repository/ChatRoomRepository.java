package spring.websocket.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import spring.websocket.dto.ChatRoom;
import spring.websocket.service.RedisSubscriber;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;
    private static final String CHAT_ROOMS ="CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> hashOperationsChatRoom;
    private Map<String, ChannelTopic> topics;

    @PostConstruct
    private void init(){
        hashOperationsChatRoom=redisTemplate.opsForHash();
        topics=new HashMap<>();
    }

    public List<ChatRoom> findAllChatRoom(){
        return hashOperationsChatRoom.values(CHAT_ROOMS);
    }

    public ChatRoom findRoomById(String id){
        return hashOperationsChatRoom.get(CHAT_ROOMS, id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom=ChatRoom.create(name);
        hashOperationsChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public void enterChatRoom(String roomId){
        ChannelTopic topic=topics.get(roomId);
        if(topic==null){
            topic=new ChannelTopic(roomId);
            redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
            topics.put(roomId,topic);
        }
    }

    public ChannelTopic getTopic(String roomId){
        return topics.get(roomId);
    }
}
