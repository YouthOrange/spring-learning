package cl.learning.communicate.server;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author : 常亮
 * @Date : 14:43 2019-01-15
 * @Description :
 */
@Slf4j
@ServerEndpoint(value = "/WebSocket/${sid}")
@Component
public class WebSocketServer {
    private static volatile int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<>();
    private Session session;
    private String sid = "";


    @OnOpen
    public void onOpen(Session session, String sid) {
        this.session = session;
        webSocketServers.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketServers.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        webSocketServers.forEach(webSocketServer -> {
            try {
                webSocketServer.sendMessage(message);
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

    public void sendInfo(String message, String sid) {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        webSocketServers.forEach(webSocketServer -> {
            try {
                if (sid == null) {
                    webSocketServer.sendMessage(message);
                } else if (webSocketServer.sid.equals(sid)) {
                    webSocketServer.sendMessage(message);
                }
            } catch (IOException e) {
                log.warn("sendMessage error,cause by:{}", Throwables.getStackTraceAsString(e));
            }
        });
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


}
