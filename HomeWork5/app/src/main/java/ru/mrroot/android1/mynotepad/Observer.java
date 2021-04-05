package ru.mrroot.android1.mynotepad;

import ru.mrroot.android1.mynotepad.data.CardData;

public interface Observer {
    void updateCardData(CardData cardData);
}
