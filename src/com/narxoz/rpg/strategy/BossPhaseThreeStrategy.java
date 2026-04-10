package com.narxoz.rpg.strategy;

public class BossPhaseThreeStrategy implements CombatStrategy {
    @Override
    public int calculateDamage(int basePower) {
        return (int)(basePower * 2.0);
    }
    @Override
    public int calculateDefense(int baseDefense) {
        return 0;
    }
    @Override
    public String getName() {
        return "Phase 3";
    }
}