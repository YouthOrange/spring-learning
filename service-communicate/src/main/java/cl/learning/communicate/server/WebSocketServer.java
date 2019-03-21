package cl.learning.communicate.server;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @Author : 常亮
 * @Date : 14:43 2019-01-15
 * @Description :
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{sid}")
@Component
@EnableWebSocket
public class WebSocketServer {
    private static volatile int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    private Session session;
    private static StringBuilder historyMessage = new StringBuilder();

    public String getSid() {
        String sid = "";
        return sid;
    }

    private static List<String> messages = new ArrayList<>();

    @Override
    public int hashCode() {
        return this.session.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) throws IOException {
        this.session = session;
        webSocketServers.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
        if (historyMessage.length() > 0) {
            this.sendMessage(historyMessage.toString() + "以上是历史消息");
        }
    }

    @OnClose
    public void onClose() {
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("sid") String sid) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        String[] messages = message.split(",");
        String content = sid + ":" + message + "(" + getCurrentTime() + ")";
        historyMessage.append(content).append("<br>");
        webSocketServers.forEach(webSocketServer -> {
            try {
                webSocketServer.sendMessage(content);
            } catch (Exception e) {
                log.warn("sendMessage error,cause by:{}", Throwables.getStackTraceAsString(e));
            }
        });
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendInfo(String message, String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        Map<String, WebSocketServer> webSocketServerMap = webSocketServers.stream().collect(Collectors.toMap(WebSocketServer::getSid, w -> w));
        WebSocketServer webSocketServer;
        if ((webSocketServer = webSocketServerMap.get(sid)) != null) {
            webSocketServer.sendMessage(message);
        }
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    public static void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static String getCurrentTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

    }


}
