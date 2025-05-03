package com.paralelogochi.ai;

import com.paralelogochi.ai.prompt.ChatML;
import com.paralelogochi.ai.prompt.LLama;
import com.paralelogochi.websocket.WebsocketeCliente;

public class Inference {
    public WebsocketeCliente ws;
    public LLama llamaLLM;
    public ChatML chatmlLLM;

    public Inference() throws Exception {
        ws = new WebsocketeCliente("ws://localhost:3069/ws");
        llamaLLM = new LLama();
        chatmlLLM = new ChatML();
    }

    public String llmInference(String prompt){
        String answ = "";
        try {
            answ = ws.sendLLMMessage(prompt);
        } catch (InterruptedException e) {e.printStackTrace();}
        return answ;
    }
}
