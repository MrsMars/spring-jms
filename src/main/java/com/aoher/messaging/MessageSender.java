package com.aoher.messaging;

import com.aoher.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Order order) {
        jmsTemplate.send(new MessageCreator() {
            @NotNull
            @Override
            public Message createMessage(@NotNull Session session) throws JMSException {
                return session.createObjectMessage(order);
            }
        });
    }
}
