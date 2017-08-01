package com.cvte.crud.kafka.util;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class KafkaProducer {
    private final Producer<String, String> producer;
    public final static String TOPIC = "Test";
    public KafkaProducer(){
        Properties props = new Properties();
        //配置kafka的端口
        props.put("metadata.broker.list", "localhost:9092");
        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks","-1");
        producer = new Producer<String, String>(new ProducerConfig(props));
    }
    public void produce(String JSON){
        if(JSON != null && "".equals(JSON)){
            producer.send(new KeyedMessage<String, String>(TOPIC ,JSON));
        }
    }
}
