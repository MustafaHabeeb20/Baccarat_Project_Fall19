import java.util.*;
import java.util.Arrays;
import java.util.Collections;

public class BaccaratDealer {

    ArrayList<Card> deck;

    // These are the different suits
    String Spades = "Spades";
    String Clubs = "Clubs";
    String Hearts = "Hearts";
    String Diamonds = "Diamonds";


    // Names of all the images of the cards
    ArrayList<String> cards = new ArrayList<>(Arrays.asList(
            "1S.png", "1C.png", "1H.png", "1D.png",
            "2S.png", "2C.png", "2H.png", "2D.png",
            "3S.png", "3C.png", "3H.png", "3D.png",
            "4S.png", "4C.png", "4H.png", "4D.png",
            "5S.png", "5C.png", "5H.png", "5D.png",
            "6S.png", "6C.png", "6H.png", "6D.png",
            "7S.png", "7C.png", "7H.png", "7D.png",
            "8S.png", "8C.png", "8H.png", "8D.png",
            "9S.png", "9C.png", "9H.png", "9D.png",
            "10S.png", "10C.png", "10H.png", "10D.png",
            "11S.png", "11C.png", "11H.png", "11D.png",
            "12S.png", "12C.png", "12H.png", "12D.png",
            "13S.png", "13C.png", "13H.png", "13D.png"
    ));

    //generate the deck using the Card class
    public void generateDeck(){

        deck = new ArrayList<Card>();
        int counter =0;
        for(int i = 1; i <= 13; i++){

            //Create 13 cards for each suite.
            deck.add(new Card(Spades , i, cards.get(i-1 + counter)));
            deck.add(new Card(Clubs, i,cards.get(i-1+1+ counter)));
            deck.add(new Card(Hearts, i,cards.get(i-1+2+ counter)));
            deck.add(new Card(Diamonds, i, cards.get(i-1+3+ counter)));
            counter = counter+3;
        }
    }//end of generateDeck

    //deckSize will just return how many cards are in this.deck at any given time.
    public int deckSize(){
        int size = deck.size();

        return size;
    }//End of DeckSize

    //drawOne will deal a single card and return it.
    //NOTE** We can use this too draw two cards for dealHand();
    public Card drawOne(){
        /*cases:
            //case1 : No deck
                    -generate the deck
                    -shuffle the deck
           //case2: Else just draw A card
        */

        if(deck.isEmpty()){
            generateDeck();
            shuffleDeck();
        }

        //https://howtodoinjava.com/java/collections/arraylist/arraylist-remove-example/
        //create a variable to draw the card.
        //remove will automatically push contents in array to the left.
        Card DrawCard = deck.remove(0);

        //return the card that is drawn
        return DrawCard;
    }//End of drawOne


    //https://www.geeksforgeeks.org/shuffle-or-randomize-a-list-in-java/
    //shuffle the deck, in this case the array, in a random order
    public void shuffleDeck(){

        Collections.shuffle(deck);
    }//End of ShuffleDeck

    //dealHand will deal two cards and return them in an ArrayList<Card>.
    public ArrayList<Card> dealHand(){

    	/*cases:
            //case1 : No deck
                    -generate the deck
                    -shuffle the deck
            //case2 : if there is 1 card left in the deck
                    -should we account for this case of should we always
                     generate and shuffle the deck every time?
                     answer: taken care of cause, drawOne
           //case3: Else just draw 2 cards
        */

        ArrayList<Card>  DealerorPlayer = new ArrayList<Card>();

        //get the first card
        DealerorPlayer.add(drawOne());

        //get the second card
        DealerorPlayer.add(drawOne());

        return DealerorPlayer;
    }//End of dealHand
}