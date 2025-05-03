package com.paralelogochi.ai.prompt;

import java.util.ArrayList;

public class ChatML extends PromptManager{
    @Override
    public String user(String msg) {
        msg = "\n<|im_start|>user\n"+msg+".\n<|im_end|>";
        return msg;
    }

    @Override
    public String bot(String msg) {
        msg = "\n<|im_start|>assistant\n"+msg+".\n<|im_end|>";
        return msg;
    }
    
    @Override
    public String system(String msg) {
        msg = "\n<|im_start|>system\n"+msg+".\n<|im_end|>";
        return msg;
    }

    @Override
    public String getPrompt() {
        String prompt = "<|im_start|>system\n"+identity+"\n<|im_end|>";

        ArrayList<String> mensajes = getMessages();
        for (String msg : mensajes) {
            prompt += msg;
        }

        prompt += "\n<|im_start|>assistant\n";
        
        if (prompt.length() > PROMPTMAXLEN){
            try {
                popMessage(2);
            } catch (Exception e) {e.printStackTrace();}
            return getPrompt();
        }

        return prompt;
    }
}
