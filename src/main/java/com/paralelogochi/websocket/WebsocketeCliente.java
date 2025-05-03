package com.paralelogochi.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.websocket.*;

import org.json.JSONObject;

@ClientEndpoint
/** Se conecta al websocket donde se esta ejecutando el LLM */
public class WebsocketeCliente {
    private String uri;
    private Session session;
    private final BlockingQueue<String> responses = new ArrayBlockingQueue<>(1);

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Conectado");
    }

    @OnMessage
    public void onMessage(String message) {
        responses.offer(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Conexión cerrada: " + reason);
        while(true){
            try {
                Thread.sleep(1000);
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                container.connectToServer(this, new URI(uri));
                break;
            } catch (DeploymentException | IOException | InterruptedException | URISyntaxException e) {
                System.out.println("Conexion con websocket perdida.");;
            }
        }
    }

    public String sendLLMMessage(String msg) throws InterruptedException{
        if (session != null && session.isOpen()) {
            JSONObject mensaje = new JSONObject();
            mensaje.put("answ", "llm:"+msg);
            session.getAsyncRemote().sendText(mensaje.toString());
            String answ = responses.take();
            JSONObject json = new JSONObject(answ);
            answ = json.getString("answ");
            return answ;
        }
        return "";
    }

    // Agregar metodo para MoodNet

    public WebsocketeCliente(String uri) throws Exception{
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, new URI(uri));
        this.uri = uri;
    }
}
