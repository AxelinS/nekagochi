package com.paralelogochi.ai.prompt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;

public abstract class PromptManager implements PromptFormatter{
    private ArrayList<String> mensajes = new ArrayList<>();
    protected String identity = getIdentity();
    // Agregar un metodo para guardar en archivo los mensajes

    public String getIdentity(){
        File identityFile = new File("./src/main/java/com/paralelogochi/ai/prompt/identity/identity.txt");
        String identity = "";
        String personalidad = "";
        
        try {
            String identContenido = Files.readString(Paths.get("./src/main/java/com/paralelogochi/ai/prompt/identity/identities.json"));
            JSONObject json = new JSONObject(identContenido);
            json = json.getJSONObject("identidades"); // Cambiar a ser dinamico
            personalidad = json.getString("perezozo");// y random
        } catch (IOException e) {e.printStackTrace();}
        
        try {            
            Scanner lector = new Scanner(identityFile);
            while (lector.hasNextLine()) {
                identity += lector.nextLine();
            }
            lector.close();
        } catch (FileNotFoundException e) {e.printStackTrace();}

        identity = identity.replace("{{PERSONALITY}}", personalidad);
        identity = identity.replace("{{TAMAGOTCHI_NAME}}", "Neka");
        identity = identity.replace("{{OWNER_NAME}}", "Axelin");
        identity = identity.replace("{{MAXHEALTH}}", "100");
        identity = identity.replace("{{MAXMENTAL}}", "100");
        identity = identity.replace("{{MAXCONFIDENCE}}", "200");
        identity = identity.replace("{{MAXFOOD}}", "100");
        identity = identity.replace("{{MAXSTAMINA}}", "100");
        System.out.println("IDENTIDAD DEL TAMAGOTCHI: "+identity+"\n==========\n"); // Para debug
        return identity;
    }

    public void addMessage(String msg){
        mensajes.add(msg);
    }

    public void popMessage(){
        mensajes.remove(0);
    }
    /** Elimina mensajes desde 1 a mensajes.size() */
    public void popMessage(int cuantity) throws Exception {
        if (cuantity <=0 && cuantity > mensajes.size()){throw new IndexOutOfBoundsException("Selecciona un numero valido para eliminar mensajes");}
        for(int i=0;i<cuantity;i++){
            mensajes.remove(0);
        }
    }

    public ArrayList<String> getMessages(){
        return mensajes;
    }
}
