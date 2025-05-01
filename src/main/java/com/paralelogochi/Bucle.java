package com.paralelogochi;

import java.util.Scanner;

public class Bucle {
    private int option = -1; 
    private Nekagochi nk;

    public Bucle(){
        nk = new Nekagochi();
    }
    /**
    - name: Tamagochi's name
    */
    public Bucle(String name){
        nk = new Nekagochi(name);
    }

    /** 0) Salir 1) Alimentar 2) Ejercitar 3) Mimar 4) Dormir */
    private void printMenu(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        +nk.getName()+" Salud: "+nk.getHealth()+" Comida: "+nk.getFood()+" Mente: "+nk.getMental()+" Estamina: "+nk.getStamina()
        +"\n0-Salir 1-Alimentar 2-Ejercitar 3-Mimar 4-Dormir --> ");
    }

    private void threadActions() throws Exception {
        while (true) {
            Thread.sleep(1000);
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
