package br.edu.iftm.agent.controller;

import br.edu.iftm.agent.dto.request.ChatRequest;
import br.edu.iftm.agent.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent/ecommerce")
public class EcommerceController {

    private final ChatService chatService;

    public EcommerceController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return chatService.ask(request.getQuery(), request.getDistance());
    }
}

