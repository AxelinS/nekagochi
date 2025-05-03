package com.paralelogochi.ai.prompt;

import java.util.ArrayList;

public class LLama extends PromptManager{

    @Override
    public String user(String msg) {
        msg = "<|start_header_id|>user<|end_header_id|>\n\n"+msg+"<|eot_id|>";
        return msg;
    }

    @Override
    public String bot(String msg) {
        msg = "<|start_header_id|>assistant<|end_header_id|>\n\n"+msg+"<|eot_id|>";
        return msg;
    }
    
    @Override
    public String system(String msg) {
        msg = "<|start_header_id|>system<|end_header_id|>\n\n"+msg+"<|eot_id|>";
        return msg;
    }

    @Override
    public String getPrompt() {
        String prompt = "<|start_header_id|>system<|end_header_id|>\n\n"+identity+"<|eot_id|>";

        ArrayList<String> mensajes = getMessages();
        for (String msg : mensajes) {
            prompt += msg;
        }

        prompt += "<|start_header_id|>assistant<|end_header_id|>\n\n";

        if (prompt.length() > PROMPTMAXLEN){
            try {
                popMessage(2);
            } catch (Exception e) {e.printStackTrace();}
            return getPrompt();
        }
        
        return prompt;
    }
    
}
