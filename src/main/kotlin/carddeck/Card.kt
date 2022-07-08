package carddeck

data class Card(
    val cardName: String,
    val cardSuit: CardSuit,
    val cardType: CardType
)

enum class CardSuit {
    CLUB, SPADE, HEART, DIAMOND
}

enum class CardType {
    KING, QUEEN, ACE, JACK, NUMBERED
}