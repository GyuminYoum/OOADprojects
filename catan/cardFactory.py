from Card import *

class cardFactory:
    def __init__(self):
        self.deck = []

    def makeDeck(self):
        for x in range(14):
            card1 = Card("Knight")
            self.deck.append(card1)

        for x in range(5):
            card1 = Card("Victory Point")
            self.deck.append(card1)

        for x in range(2):
            card1 = Card("Road Building")
            self.deck.append(card1)

        for x in range(2):
            card1 = Card("Year of Plenty")
            self.deck.append(card1)

        for x in range(2):
            card1 = Card("Monopoly")
            self.deck.append(card1)

        return self.deck
