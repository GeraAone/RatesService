package com.geraAone.cryptoRates.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return mapper;
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return  new TimedAspect(meterRegistry);
    }

    @Bean
    public NewTopic topic() {
        //scalable
        return new NewTopic("rates-topic", 1, (short) 1);
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("Kafka Logger");
    }
}
