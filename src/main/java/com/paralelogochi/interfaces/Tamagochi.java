package com.paralelogochi.interfaces;

public interface Tamagochi{
    /** Evento que aumenta Food del tamagochi */
    public void alimentar();
    /** Evento que aumenta Mental del tamagochi */
    public void mimar();
    /** Evento que reduce Stamina del tamagochi pero mejora salud en general*/
    public void ejercitar();
    /** Evento que aumenta Mental del tamagochi */
    public void dormir();
    /** Retorna si el tamagochi sigue con vida */
    public boolean isAlive();
}
