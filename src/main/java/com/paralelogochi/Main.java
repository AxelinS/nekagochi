package com.paralelogochi;

import com.paralelogochi.websocket.WebsocketeCliente;

public class Main{
    public static void main(String[] args) throws Exception {
        boolean ai = true;
        try{
            WebsocketeCliente wsCliente = new WebsocketeCliente();
            wsCliente.sendMessage("Hola como estas?");
        } catch (Exception error){
            ai = false;
            System.out.println("El servicio de IA no se encuentra disponible");
        }
        
        Bucle bucle = new Bucle("Neka");
        bucle.start();
    }
}