package carddeck

import java.lang.Exception

fun cardDeckMain(args: Array<String>) {
    println("welcome to card deck problem")
    val cardManager = CardManager()

    println(cardManager.presentAllCards())
//    println("Card popped: ${cardManager.popTopCardFromDeck()}")
    println("shuffle: ${cardManager.shuffleDeck(3)}")
    println(cardManager.presentAllCards())
//    cardManager.shuffleDeck(3)

    try {
        cardManager.distributeCardsInPlayers(5).forEachIndexed { index, list ->
            println("player: $index and cards: $list")
        }
    } catch (e: Exception) {
        print("error: $e")
    }

//    (0..51).forEach {
//        try {
//            cardManager.popTopCardFromDeck()
//        } catch (e: Exception) {
//            println("error: $e")
//        }
//    }
//    println("shuffle: ${cardManager.shuffleDeck(3)}")

}