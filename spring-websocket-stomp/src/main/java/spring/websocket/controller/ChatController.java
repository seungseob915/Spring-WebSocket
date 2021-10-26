package spring.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.websocket.dto.ChatRoom;
import spring.websocket.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return chatService.createChatRoom(name);
    }
}
