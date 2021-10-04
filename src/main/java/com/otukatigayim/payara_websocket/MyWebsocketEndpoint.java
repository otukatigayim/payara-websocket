package com.otukatigayim.payara_websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websocket")
public class MyWebsocketEndpoint {

    @OnOpen
    public void onOpen(Session client, EndpointConfig config) {
        client.setMaxIdleTimeout(5000);
        System.out.println(client.getId() + " was connected.");
    }

    @OnClose
    public void onClose(Session client, CloseReason reason) {
        System.out.println(client.getId() + " was closed by " + reason.getCloseCode() + "[" + reason.getCloseCode().getCode()+"]");
    }

    @OnError
    public void onError(Session client, Throwable t) {
        System.out.println(client.getId() + " was error.");
        t.printStackTrace();
    }

    @OnMessage
    public void onMessage(String text, Session client)  throws IOException {
        for(Session other : client.getOpenSessions()) {
            other.getBasicRemote().sendText(text);
        }
    }

}
