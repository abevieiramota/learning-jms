package br.com.abevieiramota.jms.consumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/myJmsTest/MyQueue") })
public class QueueListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;

			try {
				System.out.println(String.format("A message was found in queue MyQueue! '%s'", textMessage.getText()));
			} catch (JMSException ex) {
				ex.printStackTrace();
			}
		}
	}
}
