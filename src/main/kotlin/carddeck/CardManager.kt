package carddeck

import java.lang.IllegalStateException

interface ICardManager {

    fun popTopCardFromDeck(): Card

    fun shuffleDeck(numberOfTimes: Int): Boolean

    fun distributeCardsInPlayers(numberOfPlayers: Int): List<List<Card>>

    fun presentAllCards()

}

class CardManager(
    cardDeck: CardDeck = CardDeck()
) : ICardManager {

    private var deckTopCardLink = cardDeck.openDeck()

    override fun popTopCardFromDeck(): Card {
        val topCardLink = deckTopCardLink

        deckTopCardLink = topCardLink?.nextCardLink

        return topCardLink?.card ?: throw IllegalAccessException("Deck is empty!")
    }

    override fun shuffleDeck(numberOfTimes: Int): Boolean {
        val currentDeckSize = getCurrentDeckSize()
        if (currentDeckSize == 0) return false

        (0 until numberOfTimes).forEach { _ ->
            val randomNumberToSplitDeck = (0 until currentDeckSize).random()

            var tempCount = 0
            var mover = deckTopCardLink
            var splitCardLink: CardLink? = null

            while (mover?.nextCardLink != null) {
                tempCount += 1
                if (tempCount == randomNumberToSplitDeck) {
                    splitCardLink = mover
                }
                mover = mover.nextCardLink
            }

            val newHead = splitCardLink?.nextCardLink
            splitCardLink?.nextCardLink = null
            mover?.nextCardLink = deckTopCardLink
            deckTopCardLink = newHead

        }

        return true
    }

    override fun distributeCardsInPlayers(numberOfPlayers: Int): List<List<Card>> {

        if (numberOfPlayers == 0) throw IllegalStateException("No players to distribute")
        var currentDeckSize = getCurrentDeckSize()
        if (numberOfPlayers > currentDeckSize) throw IllegalStateException("Number of players more than total number of cards")

        val distribution = mutableListOf<MutableList<Card>>()
        // slot preparation
        (0 until numberOfPlayers).forEach {
            distribution.add(mutableListOf())
        }

        var playerNumber: Int = 0

        while (currentDeckSize >= numberOfPlayers - playerNumber) {
            val currentCard = popTopCardFromDeck()
            distribution[playerNumber].add(currentCard)
            currentDeckSize -= 1
            playerNumber = (playerNumber + 1) % numberOfPlayers
        }

        return distribution
    }

    override fun presentAllCards() {
        var firstCard: CardLink? = deckTopCardLink

        while (firstCard != null) {
            println(firstCard.card)
            firstCard = firstCard.nextCardLink
        }
    }

    private fun getCurrentDeckSize(): Int {
        var firstCard: CardLink? = deckTopCardLink
        var deckCount = 0

        while (firstCard != null) {
            deckCount += 1
            firstCard = firstCard.nextCardLink
        }

        return deckCount
    }
}