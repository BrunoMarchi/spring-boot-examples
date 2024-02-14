package com.example.springactivemqjms.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@Configuration
public class JmsConfiguration {
    @Value("${spring.activemq.broker-url}")
    private String url;

    @Value("${spring.activemq.username}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        if(username == null || "".equals(username)) {
            return new ActiveMQConnectionFactory(url);
        }
        return new ActiveMQConnectionFactory(username, password, url);
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");

        factory.setMessageConverter(jacksonJmsMessageConverter());

        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTypeIdPropertyName("_type");
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
}
