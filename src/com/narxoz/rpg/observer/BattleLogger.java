package com.narxoz.rpg.observer;

public class BattleLogger  implements GameObserver {
    @Override
    public void onEvent(GameEvent event) {
        System.out.println(
                "[LOG] " + event.getType() +  " | " + event.getSourceName() + " | value=" + event.getValue());
    }
}
