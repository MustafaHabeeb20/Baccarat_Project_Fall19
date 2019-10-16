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
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2){

        if(handTotal(hand1) > handTotal(hand2)){
            return "Player";
        }
        else if (handTotal(hand1) < handTotal(hand2)){
            return "Banker";
        }
        else{
            return "Draw";
        }
    }

    //The method handTotal will take a
    //hand and return how many points that hand is worth.
    public int handTotal(ArrayList<Card> hand){
        int sum = 0;
        for(int i = 0; i < hand.size(); i++) {

            //Have to block out values of 10 & Face Cards
            //Have to block out double digits numbers
            if(hand.get(i).value < 10){
                sum += hand.get(i).value;
            }
        }

        sum = sum % 10;   //9 + 6 = 15 % 10 = 5

        return sum;
    }//End of handtotal

   //The methods evaluateBankerDraw and evaluatePlayerDraw will return true if
   //either one should be dealt a third card otherwise return false.
   public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
       //if the bankers first two cards total 7 or more, no more cards are dealt.
       if (handTotal(hand) >= 7) {
           return false;
       }
       //If The Banker’s cards total 2 or less, The Banker gets one additional card.
       if (handTotal(hand) <= 2) {
           return true;
       }
       else {
           //If The Bankers first two cards total 3, 4, 5, or 6, it depends on if The Player drew
           // another card and if so, the value of that card to determine if The Banker receives another card. look at PDF

           //if the Player does not draw a card,
           if ((handTotal(hand) <= 5) && (playerCard == null)) {
               return true;
           }

           //if the bankers point value is 3 && playerCard is not 8
           if ((handTotal(hand) == 3)) {
               switch (playerCard.value) {

                   case 8:
                       return false;
                   default:
                       return true;
               }
           }

           //if the bankers point value is 4 && playerCard that is not 0, 1, 8 or 9
           if ((handTotal(hand) == 4)) {
               switch (playerCard.value) {

                   case 0:
                       return false;
                   case 1:
                       return false;
                   case 8:
                       return false;
                   case 9:
                       return false;
                   default:
                       return true;
               }
           }

           //if the bankers point value is 5 && the playerCard either DNE (null or > 0), or values 4-7 including 4 and 7.
           //NOTE**cant do playerCard.value == null because not an int
           if ((handTotal(hand) == 5)) {
               switch (playerCard.value) {

                   case 4:
                       return true;
                   case 5:
                       return true;
                   case 6:
                       return true;
                   case 7:
                       return true;
                   default:
                       return false;
               }
           }

           //if the bankers point value is 6  && playerCard == 6 || 7
           if ((handTotal(hand) == 6) && playerCard != null) {
               switch (playerCard.value) {

                   case 6:
                       return true;
                   case 7:
                       return true;
                   default:
                       return false;
               }
           }
       }
       return false;
   }//END of EBD


    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
       //if hand totals to 5 or less, The Player gets one more card.
       //If the hand totals to 6 or 7 points, no more cards are given.
       //NOTE**use function handTotal to calculate point value of the hand
        if((handTotal(hand) < 6) || (handTotal(hand) < 7)){
            return true;
        }
        return false;
    }//End of EPD
}
