package com.paralelogochi.websocket;

import java.net.URI;
import javax.websocket.*;

import org.json.JSONObject;

@ClientEndpoint
/** Se conecta al websocket donde se esta ejecutando el LLM */
public class WebsocketeCliente {
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Conectado");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Conexión cerrada: " + reason);
        session = null;
    }

    public void sendMessage(String msg){
        if (session != null && session.isOpen()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("answ", "llm:"+msg);
            session.getAsyncRemote().sendText(mensaje.toString());
        }
    }

    public WebsocketeCliente() throws Exception{
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, new URI("ws://localhost:3069/ws"));
    }
}
