package com.narxoz.rpg.observer;

public class AchievementTracker implements GameObserver {
    private int hits = 0;
    private int damageSum = 0;
    private boolean bossKilled = false;
    private boolean damageDone = false;
    private boolean comboDone = false;
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.ATTACK_LANDED) {
            hits++;
            damageSum += event.getValue();
            if (hits == 10 && !comboDone) {
                System.out.println("Achievement unlocked: Combo x10");
                comboDone = true;
            }
            if (damageSum > 180 && !damageDone) {
                System.out.println("Achievement unlocked: Big Damage");
                damageDone = true;
            }
        }
        if (event.getType() == GameEventType.HERO_DIED) {
            System.out.println("Achievement: Someone died...");
        }
        if (event.getType() == GameEventType.BOSS_DEFEATED) {
            if (!bossKilled) {
                System.out.println("Achievement: Boss down!");
                bossKilled = true;
            }
        }
    }
}