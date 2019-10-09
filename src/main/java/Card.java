import java.lang.String;
public class Card {

    /*
    -4 Suits:   Spades, Clubs, Hearts, and Diamonds

    -13 Cards/Suit:         Points          Face Value

                Ace           1                  1
                2             2                  2
                3             3                  3
                4             4                  4
                5             5                  5
                6             6                  6
                7             7                  7
                8             8                  8
                9             9                  9
                10            0                  10
                Joker         0                  11
                Queen         0                  12
                King          0                  13
     */

    String suite;   //4 suits
    int value;      //Face value of each card 1-13

     Card(String theSuite, int theValue){
        this.suite = theSuite;        //(this.) is used to reference to the current object
        this.value = theValue;
    }
}
