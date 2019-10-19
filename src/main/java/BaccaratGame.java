import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class BaccaratGame extends Application {

    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BaccaratDealer theDealer;
    BaccaratGameLogic gameLogic;
    double currentBet;
    double totalWinnings;
    boolean gameDone;
    int forFirstRun = 0;
    String optionSelected;
    MenuBar menu;
    ImageView pCardG;
    ImageView pCardG2;

    ListView<String> listView;
    ListView<String> trackBets;

    Card playerCardGenerated = null; //have  null card created until the played actually draws a card //***************//

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    //feel free to remove the starter code from this method
    @Override
    public void start(Stage stage) {  //got rid of (throws Exception)
        //System.out.print("yooo");

        // set title for the stage
        stage.setTitle("creating MenuBar");

        //Create a Menu to have Options and withing options have EXIT & FRESH START
        menu = new MenuBar();

        Menu Options = new Menu("Options");
        MenuItem exit = new MenuItem("Exit");


        Options.getItems().add(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });


        MenuItem freshStart = new MenuItem("Fresh Start");

        Options.getItems().add(freshStart);

        freshStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(stage);
            }
        });

        menu.getMenus().addAll(Options);

		/*
		|
		|  menu above
		|
		|
		 */


        //Instantiating the BorderPane along with the other objects
        BorderPane bPane = new BorderPane();

        //added for the menu
        bPane.setTop(menu);

        //for the bottom pane options
        theDealer = new BaccaratDealer();
        gameLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        PauseTransition pause = new PauseTransition(Duration.seconds(2)); //5 secs
//
////	     // USED FOR TESTING IF PLAYER GETS W THEN WHAT HAPPENS
//        Card test1 = new Card("Spades", 1, "1H.png");
//        Card test4 = new Card("Spades", 4, "4H.png");
//
//        Card test1_1 = new Card("Spades", 1, "1H.png");
//        Card test6 = new Card("Spades", 5, "6H.png");
////

        // create the dealer object and create the deck of Cards
        theDealer.generateDeck();
        theDealer.shuffleDeck();

//	    // Give both the player and the banker their hands
//	    //playerHand = theDealer.dealHand();
//		bankerHand.add(test1);
//		bankerHand.add(test6);

//		playerHand.add(test1_1);
//		playerHand.add(test4);

        //change to both player and banker geting random cards
        playerHand = theDealer.dealHand();
        bankerHand = theDealer.dealHand();


        // NODES IN THE BOTTOM PANE
        Button startButton = new Button("Start the Round!");
        TextField amtToBid = new TextField();
        Button bidButton = new Button("Bid!");
        Button playerButton = new Button("Player");
        Button bankerButton = new Button("Banker");
        Button drawButton = new Button("Draw");

        // NODE IN THE CENTER PANE
        listView = new ListView<String>();
        trackBets = new ListView<String>();

        listView.getItems().add("GamePlay");
        trackBets.getItems().add("CURRENT WINNINGS");

        listView.setPrefWidth(20);
        listView.setPrefHeight(200);

        // NODES IN RIGHT AND LEFT PANE, and set to invisible for now
        Image bankerCard1 = new Image(bankerHand.get(0).cardImageName);
        ImageView bCard1 = new ImageView(bankerCard1);
        bCard1.setVisible(false);

        Image bankerCard2 = new Image(bankerHand.get(1).cardImageName);
        ImageView bCard2 = new ImageView(bankerCard2);
        bCard2.setVisible(false);

        Image playerCard1 = new Image(playerHand.get(0).cardImageName);
        ImageView pCard1 = new ImageView(playerCard1);
        pCard1.setVisible(false);

        Image playerCard2 = new Image(playerHand.get(1).cardImageName);
        ImageView pCard2 = new ImageView(playerCard2);
        pCard2.setVisible(false);


        // **** ALL THE RESPECTIVE VERTICAL AND HORIZONTAL BOXES IN THE BORDERPANE ****
        VBox vBoxCenter = new VBox();
        vBoxCenter.setStyle("-fx-background-color: #389800");
        vBoxCenter.getChildren().add(listView);
        vBoxCenter.getChildren().add(trackBets);

        VBox vBoxRight = new VBox(); // this vertical box will hold the cards in the right box
        vBoxRight.setStyle("-fx-background-color: #5CB529;");

        VBox vBoxLeft = new VBox();
        vBoxLeft.setStyle("-fx-background-color: #5CB529;");


        // create a horizontal box which will hold all the nodes in the bottom pane
        HBox hBox = new HBox(startButton, playerButton, bankerButton, drawButton, amtToBid, bidButton);
        hBox.setStyle("-fx-background-color: #38661D;");

        hBox.setSpacing(30);


        // Set all the buttons to NOT visible until the start button is pressed
        setBottomPaneNodesToNotVisible(amtToBid, bidButton, playerButton, bankerButton, drawButton);

        // dont allow the bid button to be pressed until an option is selected
        bidButton.setDisable(true);

        // When the start button is selected, set them all to visible (will also need to
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action) {
                setBottomPaneNodesToVisible(amtToBid, bidButton, playerButton, bankerButton, drawButton);

                // since the first set of cards have already been given out, we only want this to run the SECOND time, the
                // user presses the start button
                if (forFirstRun > 0) {
                    // give them a new set of cards
                    playerHand = theDealer.dealHand();
                    bankerHand = theDealer.dealHand();

                    pCard1.setVisible(false);
                    bCard1.setVisible(false);
                    pCard2.setVisible(false);
                    bCard2.setVisible(false);
                    pCardG.setVisible(false);
                    pCardG2.setVisible(false);


                    Image bankerCard1New = new Image(bankerHand.get(0).cardImageName);
                    bCard1.setImage(bankerCard1New);

                    Image bankerCard2New = new Image(bankerHand.get(1).cardImageName);
                    bCard2.setImage(bankerCard2New);

                    Image playerCard1New = new Image(playerHand.get(0).cardImageName);
                    pCard1.setImage(playerCard1New);

                    Image playerCard2New = new Image(playerHand.get(1).cardImageName);
                    pCard2.setImage(playerCard2New);
                    bidButton.setDisable(true);

                }
                forFirstRun++;
            }
        });

        // add the cards into the respective vertical boxes, but keep them hidden.
        vBoxLeft.getChildren().add(pCard1);
        vBoxLeft.getChildren().add(pCard2);
        vBoxRight.getChildren().add(bCard1);
        vBoxRight.getChildren().add(bCard2);

        // When either Player, Banker, or Draw is selected then allow the Bid to be pressed

        // when the player button is pressed...
        playerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action) {
                optionSelected = playerButton.getText();
                bidButton.setDisable(false);
            }
        });

        // or when the banker button is pressed...
        bankerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action) {
                optionSelected = bankerButton.getText();
                bidButton.setDisable(false);
            }
        });

        // or when the draw button is pressed...
        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action) {
                optionSelected = drawButton.getText();
                bidButton.setDisable(false);
            }
        });

        bidButton.setPadding(new Insets(20, 20, 20, 20)); // ?? kinda weird

        // Hide the cards until the Bid Button is pressed, then show them
        bidButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent action) {

                // check if the input is a number
                if (isNumeric(amtToBid.getText())) {

                    currentBet = Double.parseDouble(amtToBid.getText()); // save the amount of money that was bid
                    pCard1.setVisible(true);
                    pCard2.setVisible(true);

                    pause.setOnFinished(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent action) {
                            bCard1.setVisible(true);
                            bCard2.setVisible(true);

                            // ******* Code on what happens to after the initial two cards are given out **********
                            if (bCard1.isVisible()) { // check the banker card because that means all the cards are for sure presented
                                int valueOfPlayerHand = gameLogic.handTotal(playerHand);
                                int valueOfBankerHand = gameLogic.handTotal(bankerHand);

                                //resultPBD comes from the whoWon function
                                String resultPBD;
                                resultPBD = gameLogic.whoWon(playerHand, bankerHand);

                                //***1) Check as soon as cards are dealt if there is a natural win
                                if ((valueOfPlayerHand == 8 || valueOfPlayerHand == 9) || (valueOfBankerHand == 8 || valueOfBankerHand == 9)) {

                                    if (resultPBD == "Player") {

                                        listView.getItems().add("Player Wins");

                                        if (optionSelected == "Player") {
                                            listView.getItems().add("Congrats, you bet Player, you win!");
                                        } else {
                                            listView.getItems().add("Sorry, you bet on Player and lost loser");
                                        }
                                    }

                                    if (resultPBD == "Banker") {

                                        listView.getItems().add("Banker Wins");

                                        if (optionSelected == "Banker") {
                                            listView.getItems().add("Congrats, you bet Banker, you win!");
                                        } else {
                                            listView.getItems().add("Sorry, you bet on Banker and lost loser");
                                        }
                                    }

                                    if (resultPBD == "Draw") {
                                        listView.getItems().add("Draw Wins");

                                        if (optionSelected == "Draw") {
                                            listView.getItems().add("Congrats, you bet Draw, you win!");
                                        } else {
                                            listView.getItems().add("Sorry, you bet on Draw and lost loser");
                                        }
                                    }
                                } else {
                                    pause.setOnFinished(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {
                                            //If no one won, than we check if the player will recieve a new card
                                            if (gameLogic.evaluatePlayerDraw(playerHand)) {

                                                playerHand.add(theDealer.drawOne());

                                                playerCardGenerated = playerHand.get(2);

                                                Image playerCardNew = new Image(playerHand.get(2).cardImageName);
                                                pCardG = new ImageView(playerCardNew);
                                                pCardG.setImage(playerCardNew);
                                                vBoxLeft.getChildren().add(pCardG);


                                                bidButton.setDisable(false);


                                            }

                                            if (gameLogic.evaluateBankerDraw(bankerHand, playerCardGenerated)) {

                                                bankerHand.add(theDealer.drawOne());

                                                Image bankerCardNew = new Image(bankerHand.get(2).cardImageName);
                                                pCardG2 = new ImageView(bankerCardNew);
                                                pCardG2.setImage(bankerCardNew);
                                                vBoxRight.getChildren().add(pCardG2);

                                                bidButton.setDisable(false);
                                            }

                                            // The portentnial for both the player and the banker to have a third card is done
                                            listView.getItems().add("Player Total: " + gameLogic.handTotal(playerHand) + "           Banker Total: " + gameLogic.handTotal(bankerHand));

                                            if (gameLogic.whoWon(playerHand, bankerHand) == "Player") {
                                                listView.getItems().add("Player Wins");
                                            } else if (gameLogic.whoWon(playerHand, bankerHand) == "Banker") {
                                                listView.getItems().add("Banker Wins");
                                            } else {
                                                listView.getItems().add("Draw Wins");
                                            }

                                            if (optionSelected == gameLogic.whoWon(playerHand, bankerHand)) {
                                                listView.getItems().add("Congrats! you bet " + optionSelected + "! You win!");
                                            } else {
                                                listView.getItems().add("Sorry, you bet " + optionSelected + "! You lost your bet!");
                                            }

                                            trackBets.getItems().add(Double.toString(evaluateWinnings()));
                                        }
                                    });
                                    pause.play();

                                }//end of else
                            }
                        }
                    });

                    pause.play();

                } else {
                    amtToBid.setText("Input a Number!");
                }
            }
        });

        bPane.setCenter(vBoxCenter);
        bPane.setLeft(vBoxLeft); // player hand
        bPane.setRight(vBoxRight); // Banker Hand
        bPane.setBottom(hBox); // Game Controls

        //Creating a scene object
        Scene scene = new Scene(bPane, 700, 700);

        //Setting title to the Stage
        stage.setTitle("Baccarat Game");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    void setBottomPaneNodesToNotVisible(
            TextField amtToBid,
            Button bidButton,
            Button playerButton,
            Button bankerButton,
            Button drawButton) {
        amtToBid.setVisible(false);
        bidButton.setVisible(false);
        playerButton.setVisible(false);
        bankerButton.setVisible(false);
        drawButton.setVisible(false);
    }

    void setBottomPaneNodesToVisible(
            TextField amtToBid,
            Button bidButton,
            Button playerButton,
            Button bankerButton,
            Button drawButton) {
        amtToBid.setVisible(true);
        bidButton.setVisible(true);
        playerButton.setVisible(true);
        bankerButton.setVisible(true);
        drawButton.setVisible(true);
    }

    // Checks the bid amount put in by the user to see if they put a number and word/string
    //https://www.tutorialspoint.com/java/character_isdigit.htm
    public static boolean isNumeric(String strNum) {
        boolean result = true;

        for (Character a : strNum.toCharArray()) {
            if (Character.isDigit(a)) {
                result = true;
            } else {
                return false;
            }
        }

        return result;
    }

    //determine if the user won or loss their bet
    public double evaluateWinnings() {
        if (optionSelected == gameLogic.whoWon(playerHand, bankerHand)) {
            totalWinnings = totalWinnings + currentBet * 2;
        } else {
            totalWinnings -= currentBet * 2;
        }
        return totalWinnings;

    }
}