package com.kafka.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class SampleConsumer {
	private Properties consumerProperties;
    private static KafkaConsumer<String, String> kafkaConsumer;

    public SampleConsumer() {
        
    }

    private void initializeConsumer() {
        kafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList("test"));
    }

    private void initializeConsumerProperties() {
        consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG ,"test");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG ,"org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG ,"org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG ,true);
        consumerProperties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG , "1000");
        consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG ,"30000");
   }


    public static void main(String args[]) throws InterruptedException, ExecutionException {
    	SampleConsumer tc = new SampleConsumer();
    	tc.initializeConsumerProperties();
        tc.initializeConsumer();
    	
        int commitInterval = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
               /* if (buffer.size() >= commitInterval) {
                    //insertIntoDb(buffer);
                	System.out.println("buffer value.."+buffer);
                    kafkaConsumer.commitSync();
                    buffer.clear();
                }*/
//                System.out.println("Topic ="+record.topic()+", partition ="+record.partition()+"offset = "+record.offset()+", key = "+record.key()+", value = "+record.value());
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
            }
        }
    }
}
