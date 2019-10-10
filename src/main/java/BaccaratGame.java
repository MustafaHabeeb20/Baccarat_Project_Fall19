import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BaccaratGame extends Application {

	/*// add data members
	ArrayList<Card> playerHand
	ArrayList<Card> bankerHand
	BaccaratDealer theDealer
	BaccaratGameLogic gameLogic
	double currentBet
	double totalWinnings

	*/


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		
		
		  //Instantiating the BorderPane class  
	      BorderPane bPane = new BorderPane(); 

	      // All of the nodes that are in the bottom pane
	      Button startButton = new Button("Start the Round!");
	      TextField amtToBid = new TextField();
	      Button bidButton = new Button("Bid!");
	      Button playerButton = new Button("Player");
	      Button bankerButton = new Button("Banker");
	      Button drawButton = new Button("Draw");
	      
	      // Eventually will need to have all the cards in a some sort of String array so testing it rn
	      String cards[] = {
	    		"2C.png", "2D.png","2H.png"
	      };
	      
	      
	      Image bankerCard1 = new Image(cards[0]);
		  ImageView bCard1 = new ImageView(bankerCard1);
		  
		  Image bankerCard2 = new Image(cards[1]);
		  ImageView bCard2 = new ImageView(bankerCard2);
		  
	      VBox vBoxRight = new VBox(); // this vertical box will hold the cards in the right box

	      // Set all the buttons to NOT visible until the start button is pressed 
	      setBottomPaneNodesToNotVisible( amtToBid, bidButton, playerButton, bankerButton, drawButton);
	      
	      // dont allow the bid button to be pressed until an option is selected
	      bidButton.setDisable(true);
	      
	      // When the start button is selected, set them all to visible
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
	    		  vBoxRight.getChildren().add(bCard1);	    		  
	    		  vBoxRight.getChildren().add(bCard2);

	    	  }
	      });
		  
	      
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

}
