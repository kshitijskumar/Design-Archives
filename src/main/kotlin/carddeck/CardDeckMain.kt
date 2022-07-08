package carddeck

import java.lang.Exception

/**
 * Problem statement: Design a system for deck of cards, expose functions like
 * 1. pop top card of the deck
 * 2. shuffle the deck
 * 3. distribute cards in players
 *
 * Approach:
 * Created a CardManager contract for the manager that will contain implementation of all these functions
 * This manager will take a cardDeck in its constructor. This CardDeck is not of very much significance, but wanted to
 * free the manager from creating the deck of card. Rather the manager simply opens the deck and gets the access of the cards.
 *
 * Thought1: Just like CardManager, thought of creating CardDeck as an abstract class or interface, but while writing functions of
 * the deck, realised that most of the functions implementation were supposed to be in the abstract class itself, and the child class woul
 * have been empty, plus I dont think so deck pattern would change deck to deck basis (Uno is exception lets say :p )
 *
 * For the cards, decided to create a linked list of the cards, where the head of the linked list is the top most card.
 *
 * Thought2: For cards, considered Linked list because of the shuffle function, if that would have been a simple list,
 * then on shuffle would have to either create a new list where second half of first list would take 1st half in the new list and 1st half
 * in the original list to the 2nd half of the new list. Or would have to move the list elements.
 * With linked list, simply break the link and join the last node of the second half to head and change head.
 *
 * eg.
 * deck: 1-2-3-4-5
 *
 * shuffle (lets say we are splitting from the first 2 cards)
 *
 * 1-2-   and   3-4-5
 *
 *  3-4-5-1-2-null
 *
 *
 */



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