package com.sdust.controller;

import com.sdust.service.ChatService;
import com.sdust.utils.JsonResponse;
import com.sdust.utils.Result;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@CrossOrigin
public class ChatController {
    @Autowired
    ChatService chatService;


}
