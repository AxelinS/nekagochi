package com.paralelogochi;

import com.paralelogochi.interfaces.Events;
import com.paralelogochi.interfaces.Stats;
import com.paralelogochi.interfaces.Tamagochi;

public class Nekagochi implements Stats, Tamagochi, Events{    
    private String name;

    private int health;
    private int food;
    private int stamina;
    private int mental;
    private int confidence;

    public Nekagochi(){
        setName("Tama");
    }
    public Nekagochi(String name){
        setName(name);
        setConfidence(MAXCONFIDENCE);
        setFood(MAXFOOD);
        setHealth(MAXHEALTH);
        setMental(MAXMENTAL);
        setStamina(MAXSTAMINA);
    }

    @Override
    public void tick() {
        int currentFood = getFood();
        int currentMental = getMental();
        int currentConfidence = getConfidence();
        int currentStamina = getStamina();

        int newFood = currentFood-1;
        int newMental = currentMental-1;
        int newConfidence = currentConfidence-1;
        int newStamina = currentStamina+1;
        
        if (newFood>=0){setFood(newFood);}
        if (newConfidence>=0){setConfidence(newConfidence);}
        if (newMental>=0){setMental(newMental);}
        if (newStamina<=100){setStamina(newStamina);}
    }

    @Override
    public void alimentar() {
        int currentFood = getFood();
        int newFood = currentFood + 10;

        // Comer de mas no es sano asi que penaliza
        if(newFood>100){
            int currentHealth = getHealth();
            int currentConfidence = getConfidence();
            int currentMental = getMental();
            setHealth(currentHealth-5);
            setConfidence(currentConfidence+2); // Es como si lo mimaran dandole de comer mas
            setMental(currentMental-2); // Lo hace sentir mal mentalmente
            setFood(MAXFOOD);
            // aqui deberia llamar al llm para que diga algo
            return;
        }

        setFood(newFood);
    }

    @Override
    public void mimar() {
        int currentMental = getMental();
        int newMental = currentMental + 1;

        // Mimar de mas hace que se vuelva mas malcriado
        if(newMental>100){
            int currentConfidence = getConfidence();
            setConfidence(currentConfidence+10);
            setMental(MAXMENTAL);
            // aqui deberia llamar al llm para que diga algo
            return;
        }

        setMental(newMental);
    }

    @Override
    public void ejercitar() {
        int currentStamina = getStamina();
        int currentHealth = getHealth();
        int currentMental = getMental();
        int newStamina = currentStamina - 10;
        int currentFood = getFood();
        setFood(currentFood-5); // Le da hambre al ejercitar

        // Ejercitar de mas puede detereorar la salud
        if(newStamina<=0){
            int currentConfidence = getConfidence();    
            setConfidence(currentConfidence-1);
            setHealth(currentHealth-2);
            setMental(currentMental-2);
            setStamina(0);
            // aqui deberia llamar al llm para que diga algo
            return;
        }
        if(currentHealth<100){setHealth(currentHealth+1);}
        if(currentMental<100){setMental(currentMental+1);}
        setStamina(newStamina);
    }
    
    @Override
    public void dormir() {
        int currentStamina = getStamina();
        int currentMental = getMental();
        int newMental = currentMental + 10;
        int newStamina = currentStamina + 10;

        if(newMental>100){
            newMental = 100;
        }
        if(newStamina>100){
            newMental = currentMental-5;
            newStamina = 100;
        }
        setStamina(newStamina);
        setMental(newMental);
    }
    
    @Override
    public boolean isAlive() {
        int currentHealth = getHealth();
        if (currentHealth <= 0) {
            return false;
        }
        return true;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void setHealth(int health){
        this.health = health;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
    
    @Override
    public void setStamina(int stamina){
        this.stamina = stamina;
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }
    
    @Override
    public void setMental(int mental){
        this.mental = mental;
    }

    @Override
    public int getMental() {
        return this.mental;
    }

    @Override
    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    @Override
    public int getConfidence() {
        return this.confidence;
    }

    @Override
    public void setFood(int food) {
        this.food = food;
    }

    @Override
    public int getFood() {
        return food;
    }
}
