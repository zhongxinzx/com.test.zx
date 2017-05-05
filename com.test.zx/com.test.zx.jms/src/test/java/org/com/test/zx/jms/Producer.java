package org.com.test.zx.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private MessageProducer producer;
	
	public Producer() {
		connectionFactory = new ActiveMQConnectionFactory("system", "manager", "tcp://127.0.0.1:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			// point 2 point
			// producer = session.createProducer(session.createQueue("myQueue"));
			// broadcast
			producer = session.createProducer(session.createTopic("myBroadcast"));
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			// System.out.println(sendMessage("Hello ActiveMQ!") ? "消息发送成功..." : "消息发送成功...");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean sendMessage(String message) {
		try {
			producer.send(session.createTextMessage(message));
			session.commit();
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		new Producer().sendMessage("Hello ActiveMQ!");
	}
}
