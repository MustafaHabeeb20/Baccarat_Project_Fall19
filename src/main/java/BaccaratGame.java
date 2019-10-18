
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage stage) throws Exception {
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
                //https://www.w3schools.com/java/java_try_catch.asp
                //https://www.geeksforgeeks.org/throwable-printstacktrace-method-in-java-with-examples/
                //notation for try catch

                try {
                    start(stage);
                }
                catch(Exception e){        //FIGURE OUT DIFF WAY
                    e.printStackTrace();
                }
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
//	      	Card test6 = new Card("Spades" , 6, "6H.png");
//	      	Card test2 = new Card("Spades" , 2, "2H.png");
////

		  // create the dealer object and create the deck of Cards
	      theDealer.generateDeck();
	      theDealer.shuffleDeck();

//	      // Give both the player and the banker their hands
//	      //playerHand = theDealer.dealHand();
//	      playerHand.add(test2);
//	      playerHand.add(test6);

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
	      ListView<String> listView = new ListView<String>();
	      ListView<String> trackBets = new ListView<String>();

	      listView.getItems().add("GamePlay");
	      trackBets.getItems().add("BETS");

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
	      setBottomPaneNodesToNotVisible( amtToBid, bidButton, playerButton, bankerButton, drawButton);
	      
	      // dont allow the bid button to be pressed until an option is selected
	      bidButton.setDisable(true);
	      
	      // When the start button is selected, set them all to visible (will also need to 
	      startButton.setOnAction(new EventHandler <ActionEvent> () {
	    	  public void handle(ActionEvent action) {
	    	      setBottomPaneNodesToVisible( amtToBid, bidButton, playerButton, bankerButton, drawButton);

	    	      // since the first set of cards have already been given out, we only want this to run the SECOND time, the 
	    	      // user presses the start button
	    	      if(forFirstRun > 0) {
	    	      // give them a new set of cards
	    		  playerHand = theDealer.dealHand();
	    	      bankerHand = theDealer.dealHand();
	    	      
	    	      pCard1.setVisible(false);
	    	      bCard1.setVisible(false);
	    	      pCard2.setVisible(false);
	    	      bCard2.setVisible(false);

//	    	      pCard3.setVisible(false);  //***********************//
	    	      
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
		playerButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				startButton.setDisable(true);

				bidButton.setDisable(false);

				//Disable other buttons once you chosen what to bid on
				bankerButton.setDisable(true);
				drawButton.setDisable(true);

			}
		});

		// or when the banker button is pressed...
		bankerButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				startButton.setDisable(true);

				bidButton.setDisable(false);

				//Disable other buttons once you chosen what to bid on
				playerButton.setDisable(true);
				drawButton.setDisable(true);
			}
		});

		// or when the draw button is pressed...
		drawButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				startButton.setDisable(true);

				bidButton.setDisable(false);

				//Disable other buttons once you chosen what to bid on
				playerButton.setDisable(true);
				bankerButton.setDisable(true);
			}
		});
	      
	      bidButton.setPadding(new Insets(20,20,20,20)); // ?? kinda weird	      
	      
	      // Hide the cards until the Bid Button is pressed, then show them
	      bidButton.setOnAction(new EventHandler<ActionEvent>() {
	    	  public void handle(ActionEvent action) {
	    		  
	    		  // check if the input is a number 
	    		  if(isNumeric(amtToBid.getText())) {

	    			  currentBet = Double.parseDouble(amtToBid.getText()); // save the amount of money that was bid
		    		  pCard1.setVisible(true);
		    		  pCard2.setVisible(true);
		    		
		    		  pause.setOnFinished(new EventHandler <ActionEvent>() {
		    			  public void handle(ActionEvent action) {
		    				  bCard1.setVisible(true);
		    				  bCard2.setVisible(true);
			    	    	  
		    				  // ******* Code on what happens to after the initial two cards are given out **********
				    	      if(bCard1.isVisible()) { // check the banker card because that means all the cards are for sure presented
				    	    	 int valueOfPlayerHand = gameLogic.handTotal(playerHand);
				    	    	 int valueOfBankerHand = gameLogic.handTotal(bankerHand);
				    	    	 listView.getItems().add("The player total: " + valueOfPlayerHand + "                   Banker Total: " + valueOfBankerHand );

				    	    	  //first scenario: either the player or the banker got 8 or 9, making it a "natural win"
//				    	    	  if(valueOfPlayerHand == 8 ||  valueOfPlayerHand == 9) {
//					    	    	  listView.getItems().add("Player Wins");
//					    	    	  if(optionSelected == "Player") {
//					    	    		  listView.getItems().add("Congrats, you bet Player, you win!");
//					    	    	  }
//					    	    	  else {
//					    	    		  listView.getItems().add("Sorry, you lost loser");
//					    	    	  }
//
//				    	    	  }

								  //listView.getItems(gameLogic.whoWon(playerHand,bankerHand));

//				    	    	  if(valueOfBankerHand == 8 || valueOfBankerHand == 9) {
//				    	    		  listView.getItems().add("The player total: " + valueOfPlayerHand + " Banker Total: " + valueOfBankerHand );
//					    	    	  listView.getItems().add("Banker Wins");
//					    	    	  if(optionSelected == "Banker") {
//					    	    		  listView.getItems().add("Congrats, you bet Banker, you win!");
//					    	    	  }
//					    	    	  else {
//					    	    		  listView.getItems().add("Sorry, you lost loser");
//					    	    	  }
//				    	    	  }

								  //resultPBD comes from the whoWon function
								  String resultPBD;
								  resultPBD = gameLogic.whoWon(playerHand,bankerHand);

								  //Options selected is the option the USER chooses of who to bet on ** WERE NOT UPDATING IT **

								  //***1) Check ass soon as cards are dealt if there is a natural win
								  if((valueOfPlayerHand == 8 || valueOfPlayerHand == 9) || (valueOfBankerHand == 8 || valueOfBankerHand == 9)){

								  	if(resultPBD == "Player"){

										listView.getItems().add("Player Wins");

										if(optionSelected == "Player") {
					    	    		  listView.getItems().add("Congrats, you bet Player, you win!");
					    	    	    }
										else {
											listView.getItems().add("Sorry, you lost loser");
										}
									}

								  	if(resultPBD == "Banker"){

										  listView.getItems().add("Banker Wins");

										  if(optionSelected == "Banker") {
											  listView.getItems().add("Congrats, you bet Banker, you win!");
										  }
										  else {
											  listView.getItems().add("Sorry, you lost loser");
										  }
								  	}

								  	if(resultPBD == "Draw"){
										listView.getItems().add("Draw Wins");

										if(optionSelected == "Draw") {
											listView.getItems().add("Congrats, you bet Draw, you win!");
										}
										else {
											listView.getItems().add("Sorry, you lost loser");
										}
								  	}
								  }

//								  //If no one won, than we check if the player will recieve a new card
//								  if(gameLogic.evaluatePlayerDraw(playerHand)){
//
//
//
//								  }

				    	      }

		    			  }
		    		  });
		    		  
		    		  pause.play();
		    		  
		    		  }
	    		  else {
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
	
	// Should prob change before/just make a different method before submitting**NOTE
	public static boolean isNumeric(String strNum) {
	    try {
	        Integer d = Integer.parseInt(strNum);
	    } catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }
	    return true;
	}
	
}
