import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BaccaratGame extends Application {

	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage stage) throws Exception {


		//Instantiating the BorderPane along with the other objects
		BorderPane bPane = new BorderPane();
		theDealer = new BaccaratDealer();
		playerHand = new ArrayList<>();
		bankerHand = new ArrayList<>();

		VBox vBoxRight = new VBox(); // this vertical box will hold the cards in the right box
		VBox vBoxLeft = new VBox();

		// All of the nodes that are in the bottom pane
		Button startButton = new Button("Start the Round!");
		TextField amtToBid = new TextField();
		Button bidButton = new Button("Bid!");
		Button playerButton = new Button("Player");
		Button bankerButton = new Button("Banker");
		Button drawButton = new Button("Draw");

		// create the dealer object and create the deck of Cards
		theDealer.generateDeck();
		theDealer.shuffleDeck();

		// Give both the player and the banker their hands
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();

		// Connect the banker's and player's respective cards to the image of that card
		Image bankerCard1 = new Image(bankerHand.get(0).cardImageName);
		ImageView bCard1 = new ImageView(bankerCard1);

		Image bankerCard2 = new Image(bankerHand.get(1).cardImageName);
		ImageView bCard2 = new ImageView(bankerCard2);

		Image playerCard1 = new Image(playerHand.get(0).cardImageName);
		ImageView pCard1 = new ImageView(playerCard1);

		Image playerCard2 = new Image(playerHand.get(1).cardImageName);
		ImageView pCard2 = new ImageView(playerCard2);



		// Set all the buttons to NOT visible until the start button is pressed
		setBottomPaneNodesToNotVisible( amtToBid, bidButton, playerButton, bankerButton, drawButton);

		// dont allow the bid button to be pressed until an option is selected
		bidButton.setDisable(true);

		// When the start button is selected, set them all to visible (will also need to
		startButton.setOnAction(new EventHandler <ActionEvent> () {
			public void handle(ActionEvent action) {
				setBottomPaneNodesToVisible( amtToBid, bidButton, playerButton, bankerButton, drawButton);
			}

		});

		// When either Player, Banker, or Draw is selected then allow the Bid to be pressed

		// when the player button is pressed...
		playerButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				bidButton.setDisable(false);
			}
		});

		// or when the banker button is pressed...
		bankerButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				bidButton.setDisable(false);
			}
		});

		// or when the draw button is pressed...
		drawButton.setOnAction(new EventHandler <ActionEvent>() {
			public void handle(ActionEvent action) {
				bidButton.setDisable(false);
			}
		});

		// create a horizontal box which will hold all the nodes in the bottom pane
		HBox hBox = new HBox(startButton, playerButton, bankerButton, drawButton, amtToBid, bidButton);

		hBox.setSpacing(30);
		bidButton.setPadding(new Insets(20,20,20,20)); // ?? kinda weird

		// Hide the cards until the Bid Button is pressed, then show them
		bidButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent action) {
				if(isNumeric(amtToBid.getText())) {
					vBoxLeft.getChildren().add(pCard1);
					vBoxLeft.getChildren().add(pCard2);

					vBoxRight.getChildren().add(bCard1);
					vBoxRight.getChildren().add(bCard2);

				}
				else {
					amtToBid.setText("Input a Number!");
				}


			}
		});

		bPane.setLeft(vBoxLeft);
		bPane.setRight(vBoxRight); // Banker Hand
		bPane.setBottom(hBox); // Game Controls

		//Creating a scene object
		Scene scene = new Scene(bPane, 700, 700);

		//Setting title to the Stage
		stage.setTitle("BorderPane Example");

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

	// Should prob change before/just make a different method before submitting
	public static boolean isNumeric(String strNum) {
		try {
			Integer d = Integer.parseInt(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

}