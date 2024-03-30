package com.sbip.ch08websocket.controller;

import com.sbip.ch08websocket.model.InputMessage;
import com.sbip.ch08websocket.model.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.Clock;
import java.time.Instant;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 30 Mar, 2024
 */

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage message(InputMessage message){
        log.info("Input Message {}", message);
        return OutputMessage
                .builder().time(Instant.now(Clock.systemDefaultZone()))
                .content(message.getContent())
                .build();
    }
}
