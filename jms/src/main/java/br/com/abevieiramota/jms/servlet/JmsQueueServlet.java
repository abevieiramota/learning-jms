package br.com.abevieiramota.jms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/sendQueue")
public class JmsQueueServlet extends HttpServlet {

	private static final boolean TRANSACTED = false;

	@Resource(lookup = "java:/myJmsTest/MyConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/myJmsTest/MyQueue")
	private Destination destination;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String message = req.getParameter("message");
		if (message == null) {
			throw new ServletException("message should be given");
		}

		PrintWriter writer = resp.getWriter();

		try (QueueConnection connection = (QueueConnection) this.connectionFactory.createConnection("usuario",
				"usuarioPass");
				QueueSession session = connection.createQueueSession(TRANSACTED, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(this.destination)) {
			
			TextMessage textMessage = session.createTextMessage(message);
			
			producer.send(textMessage);
			
			writer.println("Message sent!");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
