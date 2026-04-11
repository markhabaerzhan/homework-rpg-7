package com.narxoz.rpg.engine;
import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.observer.*;
import java.util.List;

public class DungeonEngine {
    public EncounterResult run(List<Hero> heroes, DungeonBoss boss, GameEventPublisher publisher) {
        int round = 0;
        while (true) {
            round++;
            System.out.println("\n--- Round " + round + " ---");
            for (Hero h : heroes) {
                if (!h.isAlive()) continue;
                int dmg = h.attack();
                boss.takeDamage(dmg);
                publisher.notifyObservers(
                        new GameEvent(GameEventType.ATTACK_LANDED, h.getName(), dmg)
                );
                if (boss.isDead()) break;
            }
            if (boss.isDead()) break;
            for (Hero h : heroes) {
                if (!h.isAlive()) continue;

                int dmg = boss.attack();
                h.takeDamage(dmg);

                if (h.isAlive() && h.getHp() <= h.getMaxHp() * 0.3) {
                    publisher.notifyObservers(
                            new GameEvent(GameEventType.HERO_LOW_HP, h.getName(), h.getHp())
                    );
                }
                if (!h.isAlive()) {
                    publisher.notifyObservers(
                            new GameEvent(GameEventType.HERO_DIED, h.getName(), 0)
                    );
                }
            }
            boolean allDead = true;
            for (Hero h : heroes) {
                if (h.isAlive()) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) break;
            if (round >= 20) break;
        }
        int aliveCount = 0;
        for (Hero h : heroes) {
            if (h.isAlive()) {
                aliveCount++;
            }
        }
        return new EncounterResult(!boss.isDead(), round, aliveCount);
    }
}