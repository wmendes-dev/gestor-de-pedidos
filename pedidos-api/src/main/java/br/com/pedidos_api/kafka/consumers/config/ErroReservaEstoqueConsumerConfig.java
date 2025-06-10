package br.com.pedidos_api.kafka.consumers.config;

import br.com.pedidos_api.dtos.event.ErroReservaEstoqueEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ErroReservaEstoqueConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servidoresBootstrap;

    @Bean
    public ConsumerFactory<String, ErroReservaEstoqueEvent> erroReservaEstoqueConsumerFactory() {
        JsonDeserializer<ErroReservaEstoqueEvent> deserializer = new JsonDeserializer<>(ErroReservaEstoqueEvent.class);
        deserializer.setRemoveTypeHeaders(true);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servidoresBootstrap);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-pedidos");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ErroReservaEstoqueEvent> erroReservaEstoqueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ErroReservaEstoqueEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(erroReservaEstoqueConsumerFactory());
        return factory;
    }

}
