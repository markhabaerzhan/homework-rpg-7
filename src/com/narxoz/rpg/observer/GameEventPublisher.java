package com.narxoz.rpg.observer;
import java.util.ArrayList;
import java.util.List;

public class GameEventPublisher {
    private List<GameObserver> observers = new ArrayList<>();
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }
    public void notifyObservers(GameEvent event) {
        for (GameObserver obs : observers) {
            obs.onEvent(event);
        }
    }
}