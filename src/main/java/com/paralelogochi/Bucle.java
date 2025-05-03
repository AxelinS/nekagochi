package com.paralelogochi;

import java.util.Scanner;

import com.paralelogochi.ai.Inference;

public class Bucle {
    private int option = -1; 
    private Nekagochi nk;
    private Inference inf;

    public Bucle(){
        nk = new Nekagochi();
    }
    public Bucle(String name){
        try {
            inf = new Inference();
            nk = new Nekagochi(name, inf);
            String msg = inf.llamaLLM.system("EVENT: HEALTH="+nk.getHealth()+", MENTAL="+nk.getMental()+", CONFIDENCE="+nk.getConfidence()+", FOOD="+nk.getFood()+", STAMINA="+nk.getStamina()+". Has nacido!");
            inf.llamaLLM.addMessage(msg); // ---
            String answ = inf.llmInference(inf.llamaLLM.getPrompt()); // Agregar metodo para cuando falle la inferencia borre el mensaje anteriormente enviado
            inf.llamaLLM.addMessage(inf.llamaLLM.bot(answ)); // --
            System.out.println(nk.getName()+" dice: "+answ);
        } catch (Exception e) {
            e.printStackTrace();
            nk = new Nekagochi(name);
        }
    }

    /** 0) Salir 1) Alimentar 2) Ejercitar 3) Mimar 4) Dormir */
    private void printMenu(){
        System.out.print("\n\n"
        +nk.getName()+" Salud: "+nk.getHealth()+" Comida: "+nk.getFood()+" Mente: "+nk.getMental()+" Estamina: "+nk.getStamina()
        +"\n0-Salir 1-Alimentar 2-Ejercitar 3-Mimar 4-Dormir --> ");
    }

    private void threadActions() throws Exception {
        while (true) {
            Thread.sleep(2000);
            if(!nk.isAlive()){
                System.out.println(nk.getName()+" Ha muerto :( -> Aqui tienes uno nuevo...");
                nk = new Nekagochi(nk.getName());
            }

            printMenu();
            nk.tick();
        }
    }

    private void menuActions(){
        Scanner sc = new Scanner(System.in);
        try{
            while(option != 0){
                if (sc.hasNextInt()) {
                    option = sc.nextInt();
                    switch (option) {
                        case 0 -> System.out.println("Nos vemos!");
                        case 1 -> nk.alimentar();
                        case 2 -> nk.ejercitar();
                        case 3 -> nk.mimar();
                        case 4 -> nk.dormir();
                        default -> System.out.println("Opcion invalida");
                    }
                }else{
                    System.out.println("Entrada invalida. Escribe un numero.");
                    sc.next();
                }
            }
        } catch (Exception e){
            System.out.println("Error en el menuActions: "+e);
        } finally {
            sc.close();
        }
    }

    public void start(){
        // Bucle de eventos en paralelo
        Thread.startVirtualThread(()->{
            try {
                threadActions();
            } catch (Exception e) {e.printStackTrace();}
        });
        
        // Bucle de eventos del menu
        menuActions();
    }
}
