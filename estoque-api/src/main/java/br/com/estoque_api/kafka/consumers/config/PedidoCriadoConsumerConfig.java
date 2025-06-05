package br.com.estoque_api.kafka.consumers.config;

import br.com.estoque_api.dtos.event.PedidoCriadoEvent;
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
public class PedidoCriadoConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servidoresBootstrap;

    @Bean
    public ConsumerFactory<String, PedidoCriadoEvent> pedidoCriadoConsumerFactory() {
        JsonDeserializer<PedidoCriadoEvent> deserializer = new JsonDeserializer<>(PedidoCriadoEvent.class);
        deserializer.setRemoveTypeHeaders(true);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(false);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servidoresBootstrap);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-estoque");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PedidoCriadoEvent> pedidoCriadoKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PedidoCriadoEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pedidoCriadoConsumerFactory());
        return factory;
    }

}
