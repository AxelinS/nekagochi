package com.paralelogochi.interfaces;

public interface Stats {
    /** Health es un valor para determinar si el tamagochi vive */
    public final int MAXHEALTH = 100;
    /** Mental determina la salud mental */
    public final int MAXMENTAL = 100;
    /** Confidence cambia el comportamiento del tamagochi */
    public final int MAXCONFIDENCE = 200;
    /** Determina el hambre del tamagochi */
    public final int MAXFOOD = 100;
    /** Salud fisica del tamagochi */
    public final int MAXSTAMINA = 100;

    public void setHealth(int health);

    public int getHealth();

    public void setStamina(int stamina);

    public int getStamina();

    public void setMental(int mental);

    public int getMental();

    public void setConfidence(int confidence);

    public int getConfidence();
    
    public void setFood(int food);

    public int getFood();
}
