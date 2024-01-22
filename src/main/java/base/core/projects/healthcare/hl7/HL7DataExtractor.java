package base.core.projects.healthcare.hl7;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.app.SimpleServer;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;

public class HL7DataExtractor {

    public static void hl7Parse(String[] args) {
        HapiContext context = new DefaultHapiContext();
        int serverPort = 12345; // 你的HL7服务端口
        String serverHost = "localhost"; // 你的HL7服务主机名或IP地址

        try {
            // 启动HL7服务器
            SimpleServer server = new SimpleServer(context, serverPort, false);
            server.start();

            // 创建HL7连接
            Connection connection = context.newClient(serverHost, serverPort, false);

            // 创建HL7消息解析器
            Parser parser = context.getPipeParser();

            // 创建HL7消息发送器
            Initiator initiator = connection.getInitiator();

            // 构建HL7消息请求
            String messageRequest = "Your HL7 message request here"; // 替换成你的HL7请求消息

            // 发送HL7消息请求并接收响应
            Message response = initiator.sendAndReceive(parser.parse(messageRequest));

            // 处理HL7响应消息
            String responseMessage = parser.encode(response);
            System.out.println("Received HL7 response:\n" + responseMessage);

            // 关闭HL7连接
            connection.close();

            // 关闭HL7服务器
            server.stopAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
