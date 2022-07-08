package carddeck

enum class CardSuit {
    CLUB, SPADE, HEART, DIAMOND
}

enum class FaceType {
    KING, QUEEN, ACE, JACK
}

sealed class Card(cardSuit: CardSuit) {
    data class FaceCard(val faceType: FaceType, val cardSuit: CardSuit): Card(cardSuit)
    data class NumberedCard(val number: Int, val cardSuit: CardSuit): Card(cardSuit)
}