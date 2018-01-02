package br.com.abevieiramota.jms.consumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/myJmsTest/MyTopic"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")})
public class TopicListener2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;

			try {
				System.out.println(String.format("2 A message was found in topic MyTopic! - '%s'", textMessage.getText()));
			} catch (JMSException ex) {
				ex.printStackTrace();
			}
		}

	}

}
