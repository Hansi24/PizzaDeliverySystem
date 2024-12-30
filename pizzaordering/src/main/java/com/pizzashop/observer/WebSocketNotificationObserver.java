package com.pizzashop.observer;

import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Component
public class WebSocketNotificationObserver implements OrderObserver{
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationObserver(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void update(String message) {
        // Send message to WebSocket topic
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
