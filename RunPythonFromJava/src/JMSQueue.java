import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSQueue {

    public static void main(String[] args) throws Exception {

        putMessage(
                "ENQUIRY.SELECT,,AUTHOR/123456//US0010001,%ACCOUNT,CUSTOMER.MNEMONIC:EQ=A300131");
    }

    public static String putMessage(String st) throws Exception {
        Session session = null;
        Connection connection = null;
        String result = "";
        try {
            Context namingContext = null;
            Properties prop = new Properties();
            prop.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
            prop.put("java.naming.provider.url", "http-remoting://" + "localhost" + ":" + "9089");
            namingContext = new InitialContext(prop);
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext
                    .lookup("jms/RemoteConnectionFactory");

            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession();
            Queue queue = session.createQueue("t24OFSQueue");
            MessageProducer producer = session.createProducer(queue);

            TextMessage txtMsg = session.createTextMessage(st);
            String jmscor = UUID.randomUUID().toString().replace("-", "");
            txtMsg.setJMSCorrelationID(jmscor);
            producer.send(txtMsg);
            System.out.println(LocalDateTime.now() + "Message sent");
            Queue queue1 = session.createQueue("t24OFSReplyQueue");

            String jmscor1 = "JMSCorrelationID='" + jmscor + "'";
            MessageConsumer consumer = session.createConsumer(queue1, jmscor1);

            int MAX_RETRIES = 3;
            long lTimeout = 60000L;

            int nTry = 0;
            TextMessage textMsg = null;
            while ((textMsg == null) && (nTry < MAX_RETRIES)) {
                textMsg = (TextMessage) consumer.receive(lTimeout);
                if (textMsg != null) {
                    break;
                }
                nTry++;
            }
            if (textMsg == null) {
                throw new Exception("Error");
            }
            System.out.println(LocalDateTime.now() + ", Received: " + textMsg.getText());
            result = textMsg.getText();

            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }
        return result;
    }
}
