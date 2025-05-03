package com.paralelogochi.ai.prompt;

public interface PromptFormatter {
    /** Determina que tan largo puede llegar a ser el prompt */
    public final int PROMPTMAXLEN = 8000;
    
    public String user(String msg);
    
    public String bot(String msg);

    public String system(String msg);
    
    /** Devuelve el prompt formateado con los mensajes guardados en el ArrayList mensajes del PromptManager */
    public String getPrompt();
}
