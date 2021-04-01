package ru.mrroot.android1.mynotepad;

import java.util.ArrayList;
import java.util.List;

import ru.mrroot.android1.mynotepad.data.CardData;

public class Publisher {

    private List<Observer> observers;

    public Publisher() {
        observers = new ArrayList<>();
    }


    public void subscribe(Observer observer) {
        observers.add(observer);
    }


    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }


    public void notifySingle(CardData cardData) {
        for (Observer observer : observers) {
            observer.updateCardData(cardData);
            unsubscribe(observer);
        }
    }

}

