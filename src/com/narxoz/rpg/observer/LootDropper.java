package com.narxoz.rpg.observer;
import java.util.Random;

public class LootDropper implements GameObserver {
    private String[] loot = {"Sword", "Armor", "Ring", "Potion"};
    private Random random = new Random();
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED || event.getType() == GameEventType.BOSS_DEFEATED) {
            String item = loot[random.nextInt(loot.length)];
            System.out.println(" Loot dropped: " + item);
        }
    }
}
