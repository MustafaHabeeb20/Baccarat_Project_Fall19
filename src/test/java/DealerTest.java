import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DealerTest {

//    @BeforeEach
//        //before every of the test, create an instance of the new burgerOrder
//    void init(){
//
//    }

	/*CARD CLASS
	 1 method: Card(String theSuite, int theValue, String TheCardImageName);
	 2 data members : suite, value
	 */

	//test 1
    @Test
    void testCardConstructor(){
        Card King = new Card("Spades",13,"TheCardImageName");
        assertEquals("Card", King.getClass().getName(), "not the right class");
    }

    //test 2
    @Test
    void testCardDataValue(){
        Card King = new Card("Spades",13,"TheCardImageName");
        assertEquals(13, King.value, "not the right Value");
    }

    //test 3
    @Test
    void testCardDataSuite(){
        Card King = new Card("Spades",13,"TheCardImageName");
        assertEquals("Spades", King.suite, "not the right Suite");
    }


    /*BACCARATDEALER CLASS
	 5 method: void generateDeck(); --test size
	           int deckSize(); --test size ->1) deck is full 52, draw a card and see if 51
	           Card drawOne(); --test
	           void shuffleDeck();
	           ArrayList<Card> dealHand(); --test if deck decrease in size, if the array receive two cards.
     1 data memeber: ArrayList<Card> deck;
	 */

    //test 4
    @Test
    void testBDConstructor(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        assertEquals("BaccaratDealer", TheDealer.getClass().getName(), "not the right class");
    }


    //test 5
    @Test
    void testBDGenerateDeck1(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        BaccaratDealer ThePlayer = new BaccaratDealer();
        TheDealer.generateDeck();
        ThePlayer.generateDeck();
        if(TheDealer.deck.size() == ThePlayer.deck.size()){

            assertEquals(52,ThePlayer.deck.size(), "not the right size of the deck, GD1");
        }
    }

    //test 6
    @Test
    void testBDGenerateDeck2(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        assertEquals(52, TheDealer.deck.size(), "not the right size of the deck, GD2");
    }

    //test 7
    @Test
    void testBDdeckSize1(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        assertEquals(52, TheDealer.deckSize(), "not the right size of the deck, DS1");
    }

    //test 8
    @Test
    void testBDdeckSize2(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        TheDealer.drawOne();
        assertEquals(51, TheDealer.deckSize(), "not the right size of the deck, DS2");
    }

    //test 9
    @Test
    void testBDdrawOne1(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        TheDealer.drawOne();
        assertEquals(51, TheDealer.deckSize(), "not the right size of the deck, DO1");
    }

    //test 10
    @Test
    void testBDdrawOne2(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        Card aCard = TheDealer.drawOne();
        assertTrue((aCard.value < 14) && (aCard.value > 0));
    }

    //test 11
    @Test
    void testBDdrawOne3(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();
        Card aCard = TheDealer.drawOne();
        assertTrue(aCard.suite == "Spades" || aCard.suite == "Clubs" || aCard.suite == "Hearts" || aCard.suite == "Diamonds");
    }

    //test 12
    @Test
    void testBDshuffleDeck1(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();

        //shuffle deck then grab the first and second card
        TheDealer.shuffleDeck();
        Card beforeshuffleCard1 = TheDealer.deck.get(0);
       // Card beforeshuffleCard2 = TheDealer.deck.get(1);

        //shuffle deck again, and grab the first and second card
        TheDealer.shuffleDeck();
        Card aftershuffleCard1 = TheDealer.deck.get(0);
        //Card aftershuffleCard2 = TheDealer.deck.get(1);

        if((beforeshuffleCard1.value != aftershuffleCard1.value)){
                assertTrue(beforeshuffleCard1.value != aftershuffleCard1.value);
        }
    }

    //test 13
    @Test
    void testBDshuffleDeck2(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();

        //shuffle deck then grab the first and second card
        TheDealer.shuffleDeck();
        Card beforeshuffleCard1 = TheDealer.deck.get(0);

        assertEquals(52, TheDealer.deck.size(), "not the right size of the deck, SD2");

    }

    //test 14
    @Test
    void testBDdealHand1(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();

        TheDealer.dealHand(); //should draw two cards

        assertEquals(50, TheDealer.deck.size(), "not the right size for the deck, DH1");

    }

    //test 15
    @Test
    void testBDdealHand2(){
        BaccaratDealer TheDealer = new BaccaratDealer();
        TheDealer.generateDeck();

        ArrayList<Card> Hand = new ArrayList<Card>();

        Hand = TheDealer.dealHand(); //should draw two cards and put it in a new array

        assertEquals(2, Hand.size(), "not the right size for the deck, DH2");

    }


    /*BACCARATGAMELOGIC CLASS
	 4 method:   String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2);
	             int handTotal(ArrayList<Card> hand);
	             boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard);
	             boolean evaluatePlayerDraw(ArrayList<Card> hand);
	 */

    //test 16
    @Test
    void testBGLConstructor(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();
        assertEquals("BaccaratGameLogic", BGL.getClass().getName(), "not the right class");
    }

    //test 17
    @Test
    void testBGLhandTotal1(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card eightSpades = new Card("Spades", 8, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(aceSpades);
        Hand.add(eightSpades);

        int HAND = BGL.handTotal(Hand);

        //Winning HANDS
        assertEquals(9, HAND, "Incorrect total value for the hand, HT1 ");

    }

    //test 18
    @Test
    void testBGLhandTotal2(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card sevenSpades = new Card("Spades", 7, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(aceSpades);
        Hand.add(sevenSpades);

        int HAND = BGL.handTotal(Hand);

        //Winning HANDS
        assertEquals(8, HAND, "Incorrect total value for the hand, HT2 ");

    }

    //test 19
    @Test
    void testBGLhandTotal3(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card KingSpades = new Card("Spades", 0, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(aceSpades);
        Hand.add(KingSpades);

        int HAND = BGL.handTotal(Hand);

        //HAND with face cards
        assertEquals(1, HAND, "Incorrect total value for the hand, HT3 ");

    }

    //test 20
    @Test
    void testBGLhandTotal4(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card sixSpades = new Card("Spades", 6, "TheCardImageName");
        Card nineSpades = new Card("Spades", 9, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(sixSpades);
        Hand.add(nineSpades);

        int HAND = BGL.handTotal(Hand);

        //HAND with double digit value --> 9 + 6 = 15 so, 5
        assertEquals(5, HAND, "Incorrect total value for the hand, HT4 ");

    }

    //test 21
    @Test
    void testBGLevalPlayerDraw1(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card sixSpades = new Card("Spades", 6, "TheCardImageName");
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(sixSpades);
        Hand.add(aceSpades);

        //Should accept less than 7
        //6 + 1 = 7 FALSE
        assertFalse(BGL.evaluatePlayerDraw(Hand));

    }

    //test 22
    @Test
    void testBGLevalPlayerDraw2(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card sixSpades = new Card("Spades", 6, "TheCardImageName");
        Card nineSpades = new Card("Spades", 9, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(sixSpades);
        Hand.add(nineSpades);

        //Should accept less than 7
        //9 + 6 = 15 --> 5 TRUE
        assertTrue(BGL.evaluatePlayerDraw(Hand));

    }

    //test 23
    @Test
    void testBGLevalPlayerDraw3(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card KingSpades = new Card("Spades", 0, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(QueenHearts);
        Hand.add(KingSpades);

        //Should accept less than 7
        //0 + 0 = 0 TRUE
        assertTrue(BGL.evaluatePlayerDraw(Hand));

    }

    //test 24
    @Test
    void testBGLevalBankDraw1(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card KingSpades = new Card("Spades", 0, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(sevenHearts);
        Hand.add(KingSpades);

        //if Hand is 7 or more, banker recieves no more cards --> FALSE
        assertFalse(BGL.evaluateBankerDraw(Hand, QueenHearts));

    }

    //test 25
    @Test
    void testBGLevalBankDraw2(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card KingSpades = new Card("Spades", 0, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(twoHearts);
        Hand.add(KingSpades);

        //Hand is 2 or less, banker can draw a card --> TRUE
        assertTrue(BGL.evaluateBankerDraw(Hand, QueenHearts));

    }

    //test 26
    @Test
    void testBGLevalBankDraw3(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card aceClubs = new Card("Clubs", 1, "TheCardImageName");
        Card NoCard = new Card("\0", 0, "\0");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(aceClubs);
        Hand.add(aceSpades);

        //Hand is 2 or less, banker can draw a card --> TRUE
        //when the bank hand is 2
        assertTrue(BGL.evaluateBankerDraw(Hand, NoCard));

    }

    //test 27
    @Test
    void testBGLevalBankDraw4(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card KingSpades = new Card("Spades", 0, "TheCardImageName");
        Card KingClubs = new Card("Clubs", 0, "TheCardImageName");
        Card NoCard = new Card("\0", 0, "\0");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(KingClubs);
        Hand.add(KingSpades);

        //Hand is 5 or less, banker can draw a card --> TRUE
        //when the bank hand is 0
        assertTrue(BGL.evaluateBankerDraw(Hand, NoCard));

    }

    //test 28
    @Test
    void testBGLevalBankDraw5(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card fourClubs = new Card("Clubs", 4, "TheCardImageName");
        //Card NoCard = new Card("None", 0, "None");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(fourClubs);
        Hand.add(aceSpades);

        //Hand is 5 or less, banker can draw a card --> TRUE
        //when the bank hand is 5
        assertTrue(BGL.evaluateBankerDraw(Hand,null));

    }

    //test 29
    @Test
    void testBGLevalBankDraw6(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");

        //Card NoCard = new Card("\0", 0, "\0");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");

        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(twoClubs);
        Hand.add(aceSpades);

        //Hand is 3  && accept every value but 8 --> TRUE
        assertTrue(BGL.evaluateBankerDraw(Hand, null));
        assertTrue(BGL.evaluateBankerDraw(Hand, aceHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, twoHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, threeHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, fourHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, fiveHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sixHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sevenHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, nineHearts));


        assertFalse(BGL.evaluateBankerDraw(Hand, eightHearts));

    }

    //test 30
    @Test
    void testBGLevalBankDraw7(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");

        //Card NoCard = new Card("\0", 0, "\0");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");
        Card tenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card JackHearts = new Card("Hearts", 0, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card KingHearts = new Card("Hearts", 0, "TheCardImageName");


        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(fourHearts);
        Hand.add(tenHearts);

        //Hand is 4  && accept every value but 0,1,8, and 9  --> TRUE
        assertTrue(BGL.evaluateBankerDraw(Hand, null));
        assertTrue(BGL.evaluateBankerDraw(Hand, twoHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, threeHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, fourHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, fiveHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sixHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sevenHearts));

        assertFalse(BGL.evaluateBankerDraw(Hand, tenHearts));      //0
        assertFalse(BGL.evaluateBankerDraw(Hand, JackHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, QueenHearts));    //0
        assertFalse(BGL.evaluateBankerDraw(Hand, KingHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, aceHearts));     //1
        assertFalse(BGL.evaluateBankerDraw(Hand, eightHearts));   //8
        assertFalse(BGL.evaluateBankerDraw(Hand, nineHearts));    //9


    }

    //test 31
    @Test
    void testBGLevalBankDraw8(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");

        //Card NoCard = new Card("\0", 0, "\0");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");
        Card tenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card JackHearts = new Card("Hearts", 0, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card KingHearts = new Card("Hearts", 0, "TheCardImageName");


        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(fiveHearts);
        Hand.add(tenHearts);

        //Hand is 5  && accept values but 4,5,6, and 7  --> TRUE
        assertTrue(BGL.evaluateBankerDraw(Hand, null));
        assertTrue(BGL.evaluateBankerDraw(Hand, fourHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, fiveHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sixHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sevenHearts));

        assertFalse(BGL.evaluateBankerDraw(Hand, twoHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, threeHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, tenHearts));      //0
        assertFalse(BGL.evaluateBankerDraw(Hand, JackHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, QueenHearts));    //0
        assertFalse(BGL.evaluateBankerDraw(Hand, KingHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, aceHearts));     //1
        assertFalse(BGL.evaluateBankerDraw(Hand, eightHearts));   //8
        assertFalse(BGL.evaluateBankerDraw(Hand, nineHearts));    //9


    }

    //test 32
    @Test
    void testBGLevalBankDraw9(){
        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");

        //Card NoCard = new Card("\0", 0, "\0");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");
        Card tenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card JackHearts = new Card("Hearts", 0, "TheCardImageName");
        Card QueenHearts = new Card("Hearts", 0, "TheCardImageName");
        Card KingHearts = new Card("Hearts", 0, "TheCardImageName");


        //create a Hand for the cards to go into
        ArrayList<Card> Hand = new ArrayList<Card>();

        //add to the hand
        Hand.add(sixHearts);
        Hand.add(tenHearts);

        //Hand is 6 && accept values 6 and 7 --> TRUE
        assertTrue(BGL.evaluateBankerDraw(Hand, sixHearts));
        assertTrue(BGL.evaluateBankerDraw(Hand, sevenHearts));

        assertFalse(BGL.evaluateBankerDraw(Hand, null));
        assertFalse(BGL.evaluateBankerDraw(Hand, fourHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, fiveHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, twoHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, threeHearts));
        assertFalse(BGL.evaluateBankerDraw(Hand, tenHearts));      //0
        assertFalse(BGL.evaluateBankerDraw(Hand, JackHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, QueenHearts));    //0
        assertFalse(BGL.evaluateBankerDraw(Hand, KingHearts));     //0
        assertFalse(BGL.evaluateBankerDraw(Hand, aceHearts));     //1
        assertFalse(BGL.evaluateBankerDraw(Hand, eightHearts));   //8
        assertFalse(BGL.evaluateBankerDraw(Hand, nineHearts));    //9


    }

    //test 33
    @Test
    void BGLwhoWon1(){

        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");

        //create a Hands for the cards to go into
        ArrayList<Card> HandB = new ArrayList<Card>();
        ArrayList<Card> HandP = new ArrayList<Card>();

        //output is 9
        HandB.add(aceSpades);
        HandB.add(eightHearts);

        //output is 8
        HandP.add(sixHearts);
        HandP.add(twoClubs);

        //HandB wins cause its bigger than HandP
        assertEquals("Banker", BGL.whoWon(HandP,HandB), " Should have been BANKER, WW1");


    }

    //test 34
    @Test
    void BGLwhoWon2(){

        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");
        Card tenHearts = new Card("Hearts", 10, "TheCardImageName");

        //create a Hands for the cards to go into
        ArrayList<Card> HandB = new ArrayList<Card>();
        ArrayList<Card> HandP = new ArrayList<Card>();

        //output is 9
        HandB.add(tenHearts);
        HandB.add(nineHearts);

        //output is 8
        HandP.add(sixHearts);
        HandP.add(threeHearts);

        //HandB and HandP is the same, its a draw
        assertEquals("Draw", BGL.whoWon(HandP,HandB), " Should have been a DRAW, WW2");


    }

    //test 35
    @Test
    void BGLwhoWon3(){

        BaccaratGameLogic BGL = new BaccaratGameLogic();

        //create cards
        Card aceSpades = new Card("Spades", 1, "TheCardImageName");
        Card twoClubs = new Card("Clubs", 2, "TheCardImageName");
        Card aceHearts = new Card("Hearts", 1, "TheCardImageName");
        Card twoHearts = new Card("Hearts", 2, "TheCardImageName");
        Card threeHearts = new Card("Hearts", 3, "TheCardImageName");
        Card fourHearts = new Card("Hearts", 4, "TheCardImageName");
        Card fiveHearts = new Card("Hearts", 5, "TheCardImageName");
        Card sixHearts = new Card("Hearts", 6, "TheCardImageName");
        Card sevenHearts = new Card("Hearts", 7, "TheCardImageName");
        Card eightHearts = new Card("Hearts", 8, "TheCardImageName");
        Card nineHearts = new Card("Hearts", 9, "TheCardImageName");
        Card tenHearts = new Card("Hearts", 10, "TheCardImageName");

        //create a Hands for the cards to go into
        ArrayList<Card> HandB = new ArrayList<Card>();
        ArrayList<Card> HandP = new ArrayList<Card>();

        //output is 7
        HandP.add(sixHearts);
        HandP.add(aceHearts);

        //output is 6
        HandB.add(fourHearts);
        HandB.add(twoClubs);

        //HandB and HandP is the same, its a draw
        assertEquals("Player", BGL.whoWon(HandP,HandB), " Should have been PLAYER, WW2");


    }







}

