package spring.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.websocket.dto.ChatRoom;
import spring.websocket.dto.MsgBrkChatRoom;
import spring.websocket.repository.MsgBrkChatRoomRepository;

import java.util.List;

/**
 *
 *   Stomp - 구독자(Subscriber) 구현 컨트롤러
 *
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("/chatstp")
public class MsgBrkChatRoomController {
    private final MsgBrkChatRoomRepository chatRoomRepository;

    // 채팅리스트
    @GetMapping("/room")
    public String rooms(Model model){
        return "/chatstp/room";
    }

    // 모든 채팅 리스트
    @GetMapping("/rooms")
    @ResponseBody
    public List<MsgBrkChatRoom> room(){
        return chatRoomRepository.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public MsgBrkChatRoom createRoom(@RequestParam String name){
        return chatRoomRepository.createChatRoom(name);
    }

    // 채팅방 입장
    @GetMapping("/room/enter/{roomId}")
    public String roomEnter(Model model, @PathVariable String roomId){
        model.addAttribute("roomId", roomId);
        return "/chatstp/enter";
    }

    // 특정 채팅방 검색
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public MsgBrkChatRoom findRoom(@PathVariable String roomId){
        return chatRoomRepository.findRoomById(roomId);
    }
}
