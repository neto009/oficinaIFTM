package br.edu.iftm.agent.controller;

import br.edu.iftm.agent.dto.request.ChatRequest;
import br.edu.iftm.agent.service.ChatService;
import br.edu.iftm.agent.service.FAQService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent/ecommerce")
public class EcommerceController {

    private final ChatService chatService;
    private final FAQService faqService;

    public EcommerceController(ChatService chatService, FAQService faqService) {
        this.chatService = chatService;
        this.faqService = faqService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatRequest request) {
        return chatService.ask(request.getQuery(), request.getDistance());
    }

    @PostMapping("/faq")
    public String searchFaq(@RequestBody ChatRequest request) {
        return faqService.ask(request.getQuery(), request.getDistance());
    }
}