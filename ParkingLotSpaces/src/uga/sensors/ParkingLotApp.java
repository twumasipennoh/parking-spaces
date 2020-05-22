package uga.sensors;

/**
 * A GUI application that displays a gallery of images based on
 * the results of a search query to the iTunes Search API. This 
 * class is the driver class for the GUI application. There are
 * different helper methods in the class to help set up the stage
 * for the application. 
 *
 * @author Twumasi Pennoh
 * @author Al Ameen Akindele
 * @author Mohammed Mohammed
 * @author David Combs
 */

import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.ToolBar;
import javafx.application.Platform;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.ProgressBar;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.text.FontWeight;
import java.lang.Math;
import java.lang.NullPointerException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;

public class ParkingLotApp extends Application{
	private SerialTest arduinoOutput;
	private Rectangle parkingLot1 = new Rectangle (150, 250);
	private Rectangle parkingLot2 = new Rectangle (150, 250);
	Rectangle parkingLot3 = new Rectangle (150, 250);
	Rectangle parkingLot4 = new Rectangle (150, 250);
	StackPane stack1 = new StackPane();
	StackPane stack2 = new StackPane();
	StackPane stack3 = new StackPane();
	StackPane stack4 = new StackPane();
	int space1FreeCount = 0;
	int space2FreeCount = 0;
	int space3FreeCount = 0;
	int space4FreeCount = 0;
	int space1TakenCount = 0;
	int space2TakenCount = 0;
	int space3TakenCount = 0;
	int space4TakenCount = 0;
	VBox appLayout = new VBox(20);
	Text title = new Text("Parking Lot Spaces");
	HBox background = new HBox(20);
	
	public void changeParkingLotColors(String input) {
		
		if(input.equals("Space 1 is taken")) {
			if(space1TakenCount > 0) {
				space1FreeCount = 0;
				parkingLot1.setFill(Color.TRANSPARENT);
				parkingLot1.setFill(Color.RED);
			}else {
				space1TakenCount++;
			}
			
		}else if (input.equals("Space 1 is free")){
			if(space1FreeCount > 0) {
				space1TakenCount = 0;
				parkingLot1.setFill(Color.TRANSPARENT);
				parkingLot1.setFill(Color.GREEN);
			}else {
				space1FreeCount++;
			}
		}
		
		if(input.equals("Space 2 is taken")) {
			if(space2TakenCount > 0) {
				
				space2FreeCount = 0;
				parkingLot2.setFill(Color.TRANSPARENT);
				parkingLot2.setFill(Color.RED);
			}else {
				space2TakenCount++;
			}
			
		}else if (input.equals("Space 2 is free")){
			if(space2FreeCount > 0) {
				space2TakenCount = 0;
				parkingLot2.setFill(Color.TRANSPARENT);
				parkingLot2.setFill(Color.GREEN);
			}else {
				space2FreeCount++;
			}
		}
		
		if(input.equals("Space 3 is taken")) {
			if(space3TakenCount > 1) {
				space3FreeCount = 0;
				parkingLot3.setFill(Color.TRANSPARENT);
				parkingLot3.setFill(Color.RED);
			}else {
				space3TakenCount++;
			}
			
		}else if (input.equals("Space 3 is free")){
			if(space3FreeCount > 1) {
				space3TakenCount = 0;
				parkingLot3.setFill(Color.TRANSPARENT);
				parkingLot3.setFill(Color.GREEN);
			}else {
				space3FreeCount++;
			}
		}
		
		if(input.equals("Space 4 is taken")) {
			if(space4TakenCount > 1) {
				space4FreeCount = 0;
				parkingLot4.setFill(Color.TRANSPARENT);
				parkingLot4.setFill(Color.RED);
			}else {
				space3TakenCount++;
			}
			
		}else if (input.equals("Space 4 is free")){
			if(space4FreeCount > 1) {
				space4TakenCount = 0;
				parkingLot4.setFill(Color.TRANSPARENT);
				parkingLot4.setFill(Color.GREEN);
			}else {
				space4FreeCount++;
			}
		}
		
	}
	
    @Override
    public void start(Stage stage) {
    	
    	arduinoOutput = new SerialTest();
    	Thread t = new Thread() {
    		public void run() {
    			arduinoOutput.initialize();
    			Runnable updater = new Runnable() {
    				
    				public void run() {
    					changeParkingLotColors(arduinoOutput.lineReturn());
    				}
    			};
    			
    			while(true) {
    				try {
    					Thread.sleep(25);
    				}
    				catch(InterruptedException ex) {
    				}
    				Platform.runLater(updater);
    			}
			}
		};
		t.setDaemon(true);
		t.start();
    	
    	Text title = new Text("Parking Lot Spaces");
		title.setFont(new Font(20));
		HBox background = new HBox(20);
		 
		parkingLot1.setFill(Color.GREEN);
		parkingLot2.setFill(Color.GREEN);
		parkingLot3.setFill(Color.GREEN);
		parkingLot4.setFill(Color.GREEN);
		
		Text parkingLot1Text = new Text("1");
		parkingLot1Text.setFont(Font.font(null, FontWeight.BOLD, 17));
		parkingLot1Text.setFill(Color.WHITE);
		stack1.getChildren().addAll(parkingLot1, parkingLot1Text);
		
		Text parkingLot2Text = new Text("2");
		parkingLot2Text.setFont(Font.font(null, FontWeight.BOLD, 17));
		parkingLot2Text.setFill(Color.WHITE);
		stack2.getChildren().addAll(parkingLot2, parkingLot2Text);
		
		Text parkingLot3Text = new Text("3");
		parkingLot3Text.setFont(Font.font(null, FontWeight.BOLD, 17));
		parkingLot3Text.setFill(Color.WHITE);
		stack3.getChildren().addAll(parkingLot3, parkingLot3Text);
		
		Text parkingLot4Text = new Text("4");
		parkingLot4Text.setFont(Font.font(null, FontWeight.BOLD, 17));
		parkingLot4Text.setFill(Color.WHITE);
		stack4.getChildren().addAll(parkingLot4, parkingLot4Text);
	
		background.getChildren().addAll(stack1, stack2, stack3, stack4);
		appLayout.getChildren().addAll(title, background);
		appLayout.setAlignment(Pos.CENTER);
		appLayout.setPadding(new Insets(10));
		
    	Scene scene = new Scene(appLayout);
	    stage.setTitle("Parking Lot Spaces");
	    stage.setScene(scene);
		stage.sizeToScene();
	    stage.show();
    } // start

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // GalleryApp
