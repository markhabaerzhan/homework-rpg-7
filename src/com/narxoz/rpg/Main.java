package com.narxoz.rpg;
import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.engine.*;
import com.narxoz.rpg.observer.*;
import com.narxoz.rpg.strategy.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GameEventPublisher publisher = new GameEventPublisher();

        Hero h1 = new Hero("Knight", 120, 40, 20, new AggressiveStrategy());
        Hero h2 = new Hero("Tank", 180, 35, 40, new DefensiveStrategy());
        Hero h3 = new Hero("Mage", 100, 50, 10, new BalancedStrategy());

        List<Hero> heroes = Arrays.asList(h1, h2, h3);

        DungeonBoss boss = new DungeonBoss(300, publisher);

        publisher.addObserver(new BattleLogger());
        publisher.addObserver(new AchievementTracker());
        publisher.addObserver(new PartySupport(heroes));
        publisher.addObserver(new HeroStatusMonitor(heroes));
        publisher.addObserver(new LootDropper());

        publisher.addObserver(boss);

        DungeonEngine engine = new DungeonEngine();
        EncounterResult result = engine.run(heroes, boss, publisher);

        System.out.println("\n=== FINAL RESULT ===");
        System.out.println("Heroes won: " + result.isHeroesWon());
        System.out.println("Rounds played: " + result.getRoundsPlayed());
        System.out.println("Surviving heroes: " + result.getSurvivingHeroes());
    }
}