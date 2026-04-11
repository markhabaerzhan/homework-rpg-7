package com.narxoz.rpg.combatant;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;

public class DungeonBoss implements GameObserver {

    private int hp;
    private int maxHp;
    private int baseDamage = 50;
    private int baseDefense = 20;
    private int phase = 1;
    private CombatStrategy strategy;
    private GameEventPublisher publisher;
    public DungeonBoss(int hp, GameEventPublisher publisher) {
        this.hp = hp;
        this.maxHp = hp;
        this.publisher = publisher;
        this.strategy = new BossPhaseOneStrategy();
    }
    public void takeDamage(int damage) {
        int defense = strategy.calculateDefense(baseDefense);
        int realDamage = Math.max(0, damage - defense);

        hp -= realDamage;

        int percent = (hp * 100) / maxHp;
        if (percent <= 60 && phase == 1) {
            phase = 2;

            publisher.notifyObservers(
                    new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 2)
            );
        }
        if (percent <= 30 && phase == 2) {
            phase = 3;

            publisher.notifyObservers(
                    new GameEvent(GameEventType.BOSS_PHASE_CHANGED, "Boss", 3)
            );
        }
        if (hp <= 0) {
            hp = 0;

            publisher.notifyObservers(
                    new GameEvent(GameEventType.BOSS_DEFEATED, "Boss", 0)
            );
        }
    }
    public int attack() {
        return strategy.calculateDamage(baseDamage);
    }
    public boolean isDead() {
        return hp <= 0;
    }
    @Override
    public void onEvent(GameEvent event) {
        if (event.getType() == GameEventType.BOSS_PHASE_CHANGED) {
            if (event.getValue() == 2) {
                strategy = new BossPhaseTwoStrategy();
            }
            if (event.getValue() == 3) {
                strategy = new BossPhaseThreeStrategy();
            }
            System.out.println("Boss switched to " + strategy.getName());
        }
    }
}