package com.kafka.examples;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;




public class SampleProducer {
	private Properties props;
    private static KafkaProducer<String,String> producer;
    private static String topic="test";
    private static String key = "mykey";
    
    private void initializeProducer() {
    	producer = new KafkaProducer<String,String>(props);
    }

    private void initializeProducerProperties() {
    	props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
   }
    
    public static void main(String args[]) throws InterruptedException, ExecutionException {
    	SampleProducer tc = new SampleProducer();
 	    tc.initializeProducerProperties();
        tc.initializeProducer();
        boolean sync = false;
		
        
		String value = "testValue";
		ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic, key, value);
		if (sync) {
			producer.send(producerRecord).get();
		} else {
			producer.send(producerRecord);
		}
		
		
	    Random random = new Random();
	    for (long i = 0; i < 10; i++) {
	         Integer values = random.nextInt(255);
	         String msg = "Random values are " + values;
	         producer.send(new ProducerRecord<String, String>(topic, msg));
	    }

		producer.close();

    }
}
