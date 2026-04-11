package com.narxoz.rpg.observer;
import com.narxoz.rpg.combatant.Hero;
import java.util.List;
import java.util.Random;

public class PartySupport implements GameObserver {
    private List<Hero> heroes;
    private Random random = new Random();
    public PartySupport(List<Hero> heroes) {
        this.heroes = heroes;
    }
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.HERO_LOW_HP) {
            Hero target = heroes.get(random.nextInt(heroes.size()));
            if (target.isAlive()) {
                target.heal(25);
                System.out.println(" PartySupport healed " + target.getName());
            }
        }
    }
}
