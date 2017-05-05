package org.com.test.zx.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AbstractConsumer {
	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private MessageConsumer consumer;
	
	public AbstractConsumer() {
		connectionFactory = new ActiveMQConnectionFactory("system", "manager", "tcp://127.0.0.1:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// consumer = session.createConsumer(session.createQueue("myQueue"));
			consumer = session.createConsumer(session.createTopic("myBroadcast"));
			// consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

	protected void setMessageLisenter(MessageListener messageLisenter) {
		try {
			consumer.setMessageListener(messageLisenter);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	protected void printMessage(String message) {
		System.out.println(this.getClass().getSimpleName() + " recevied message:" + message);
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

}
