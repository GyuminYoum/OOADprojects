from Card import *


class cardFactory:
    def __init__(self):
        self.deck = []

    def makeDeck(self):
        for _ in range(14):
            card1 = Card("Knight")
            self.deck.append(card1)

        for _ in range(2):
            card1 = Card("Road Building")
            self.deck.append(card1)

        for _ in range(2):
            card1 = Card("Year of Plenty")
            self.deck.append(card1)

        for _ in range(2):
            card1 = Card("Monopoly")
            self.deck.append(card1)

        for _ in range(5):
            card1 = Card("Victory Point")
            card1.used=True
            self.deck.append(card1)

        return self.deck
