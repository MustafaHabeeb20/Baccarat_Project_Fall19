import java.util.ArrayList;

 /*Rules to determine who won:
            - https://wizardofodds.com/play/baccarat/
            - Number cards Ace - 9 have point values of 1 - 9
            - If the card is a 10 or Face card, it has a point value of 0
          Steps to play:
            - User bids on either Banker, Player to win, or a Tie.
            - Dealer than Deals two cards to each Player and Banker -> Player,Banker,Player,Banker
              player always goes first
            - If the Player or Bankers hand adds up to 8 OR 9 points, its a "Natural Win"
            - Else The Player will go first:
                if hand totals to 5 or less, The Player gets one more card.
                If the hand totals to 6 or 7 points, no more cards are given.
                EX: The Player will go first: if hand totals to 5 or less, The Player gets one more
                card. If the hand totals to 6 or 7 points, no more cards are given.
            - The Banker’s turn:
            if the bankers first two cards total 7 or more, no more cards are dealt.
            If The Banker’s cards total 2 or less, The Banker gets one additional card.
            If The Bankers first two cards total 3, 4, 5, or 6, it depends on if The Player drew
            another card and if so, the value of that card to determine if The Banker receives another card.
            look at PDF
 */

public class BaccaratGameLogic {
    //The method whoWon will evaluate two hands at the end of the game and return a string
    //depending on the winner: “Player”, “Banker”, “Draw”.
    //public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){

    //}

    //The method handTotal will take a
    //hand and return how many points that hand is worth.
    public int handTotal(ArrayList<Card> hand){
        int sum = 0;
        for(int i = 0; i < hand.size(); i++) {

            //Have to block out values of 10 & Face Cards
            if(hand.get(i).value < 10){
                sum += hand.get(i).value;
            }
        }
        return sum;
    }//End of handtotal

   //The methods evaluateBankerDraw and evaluatePlayerDraw will return true if
   //either one should be dealt a third card otherwise return false.
   //public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard){

   //}

    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
       //if hand totals to 5 or less, The Player gets one more card.
       //If the hand totals to 6 or 7 points, no more cards are given.
       //NOTE**use function handTotal to calculate point value of the hand
        if((handTotal(hand) < 6) || (handTotal(hand) < 7)){
            return false;
        }
        else {
            return true;
        }
    }//End of EPD
}
