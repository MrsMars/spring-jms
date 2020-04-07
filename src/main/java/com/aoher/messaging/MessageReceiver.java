package com.aoher.messaging;

import com.aoher.model.InventoryResponse;
import com.aoher.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private Environment env;

    @Autowired
    OrderService orderService;

    @JmsListener(destination = "${myApp.jms.outbound.queue.name}")
    public void receiveMessage(final Message<InventoryResponse> message) {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

        MessageHeaders headers =  message.getHeaders();
        log.info("Application : headers received : {}", headers);

        InventoryResponse response = message.getPayload();
        log.info("Application : response received : {}",response);

        orderService.updateOrder(response);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
