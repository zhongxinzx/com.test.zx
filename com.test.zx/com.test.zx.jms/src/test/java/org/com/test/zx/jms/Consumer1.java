package org.com.test.zx.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Consumer1 extends AbstractConsumer implements MessageListener {
	
	public Consumer1() {
		super();
		setMessageLisenter(this);
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage text = (TextMessage)message;
				printMessage(text.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Consumer1();
	}

}
