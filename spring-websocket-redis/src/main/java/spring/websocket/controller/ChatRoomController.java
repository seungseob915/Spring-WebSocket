package spring.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.websocket.dto.ChatRoom;
import spring.websocket.repository.ChatRoomRepository;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;

    // 채팅리스트
    @GetMapping("/room")
    public String rooms(Model model){
        return "/chat/room";
    }

    // 모든 채팅 리스트
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room(){
        return chatRoomRepository.findAllChatRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }

    // 채팅방 입장
    @GetMapping("/room/enter/{roomId}")
    public String roomEnter(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "/chat/enter";
    }

    // 특정 채팅방 검색
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom findRoom(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }
}
