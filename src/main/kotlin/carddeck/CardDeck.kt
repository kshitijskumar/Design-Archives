package carddeck

class CardDeck {

    private var deckTopCardLink: CardLink? = null

    init {
        createDeck()
    }

    fun openDeck(): CardLink? {
        return deckTopCardLink
    }

    private fun createDeck() {
        listOf(CardSuit.CLUB, CardSuit.DIAMOND, CardSuit.HEART, CardSuit.SPADE).forEach {
            createCardsOfSuit(it)
        }
    }

    private fun createCardsOfSuit(cardSuit: CardSuit) {

        var mover: CardLink? = setupMover()

        (2..10).forEach {
            val card = Card(
                cardName = it.toString(),
                cardSuit = cardSuit,
                cardType = CardType.NUMBERED
            )

            val cardLink = CardLink(
                card = card,
                nextCardLink = null
            )

            if (deckTopCardLink == null) {
                deckTopCardLink = cardLink
                mover = deckTopCardLink
            } else {
                mover?.nextCardLink = cardLink
                mover = cardLink
            }
        }

        listOf(CardType.ACE, CardType.JACK, CardType.KING, CardType.QUEEN).forEach {
            val cardLink = CardLink(
                card = Card(
                    cardName = it.name,
                    cardSuit = cardSuit,
                    cardType = it
                ),
                nextCardLink = null
            )

            mover?.nextCardLink = cardLink
            mover = cardLink
        }
    }

    private fun setupMover(): CardLink? {
        return if (deckTopCardLink == null) {
            null
        } else {
            var temp = deckTopCardLink;
            while (temp?.nextCardLink != null) {
                temp = temp.nextCardLink
            }
            temp
        }
    }

}

data class CardLink(
    val card: Card,
    var nextCardLink: CardLink?
)