import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {

    ArrayList<Card> deck;

    String Spades = "Spades";
    String Clubs = "Clubs";
    String Hearts = "Hearts";
    String Diamonds = "Diamonds";

    //generate the deck using the Card class
    public void generateDeck(){

        deck = new ArrayList<Card>();

        for(int i = 1; i <= 13; i++){

            //Create 13 cards for each suite.
            deck.add(new Card(Spades , i));
            deck.add(new Card(Clubs, i));
            deck.add(new Card(Hearts, i));
            deck.add(new Card(Diamonds, i));
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
