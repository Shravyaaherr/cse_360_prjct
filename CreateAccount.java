package groupProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateAccount {
	 private TextField patientfirstNameField;
	    private TextField patientlastNameField;
	    private TextField emailField;
	    private TextField phoneNumberField;
	 

	    public void start(Stage primaryStage) {
	        // Create labels and text fields
	        Label titleLabel = new Label("Create Account");
	        titleLabel.setFont(Font.font(20));
	        titleLabel.setTextFill(Color.BLACK);

	        

	        patientfirstNameField = new TextField();
	        patientfirstNameField.setPromptText("First Name");
	        patientlastNameField = new TextField();
	        patientlastNameField.setPromptText("Last Name");
	        emailField = new TextField();
	        emailField.setPromptText("Email Address");
	        phoneNumberField = new TextField();
	        phoneNumberField.setPromptText("Phone Number");


	        // Set preferred width for text fields
	        patientfirstNameField.setPrefWidth(100);
	        patientlastNameField.setPrefWidth(100);
	        emailField.setPrefWidth(100);
	        phoneNumberField.setPrefWidth(100);
	        

	        // Create a grid pane
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        Image image = new Image("https://www.transparentpng.com/thumb/human/black-human-user-profile-png-icon-free-fsR5FT.png");
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(130); // Set the width of the image
	        imageView.setPreserveRatio(true);
	        GridPane.setConstraints(imageView, 1, 0, 3, 1); // Center the image horizontally
	        gridPane.getChildren().add(imageView);

	        // Add elements to the grid pane
	        
	        
	        gridPane.add(patientfirstNameField, 1, 1);
	        
	        gridPane.add(patientlastNameField, 1, 2);
	        
	        gridPane.add(emailField, 1, 3);
	        
	        gridPane.add(phoneNumberField, 1, 5);

	        // Center the text fields
	        gridPane.setAlignment(Pos.CENTER);
	        
	        
	     // Create a Save button
	        Button saveButton = new Button("Create Account");
	        
	        saveButton.setOnAction(e -> {
	        
	        	if (savePatientInfo()) {
	                // If data is successfully saved, show success message
	                showAlert(AlertType.INFORMATION, "Success", "Data saved successfully.");
	                GroupProject Main = new GroupProject();
	    	        
	                // Close the current stage
	                primaryStage.close();
	                Main.start(primaryStage);
	                // You can add code here to open another stage or do other actions
	            } else {
	                // If data saving failed, show error message
	                showAlert(AlertType.ERROR, "Error", "Failed to save data. Please fill in all fields.");
	            }
	        		
	        		
	        		
	        }
	        		);
	       

	        // Set the button to span two columns and align it to the right
	        gridPane.add(saveButton, 0, 8, 2, 1);
	        GridPane.setHalignment(saveButton, javafx.geometry.HPos.RIGHT);
	        saveButton.setMinWidth(230);
	        // Create a scene
	        Scene scene = new Scene(gridPane, 800, 400, Color.WHITE);

	        // Set the scene to the stage
	        primaryStage.setScene(scene);

	        // Show the stage
	        primaryStage.show();
	        
	 
	    }
	    
	    private boolean savePatientInfo() {
	        // Store patient information in a string
	        String patientInfo = 
	                "First Name: " + patientfirstNameField.getText() + "\n"
	                + "Last Name: " + patientlastNameField.getText() + "\n"
	                + "Email: " + emailField.getText() + "\n"
	                + "Phone Number: " + phoneNumberField.getText() + "\n";
	               
	        if (patientfirstNameField.getText().isEmpty() || patientlastNameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
	            showAlert(AlertType.ERROR, "Error", "Please fill in all fields.");
	            return false; // Exit the method if any field is empty
	        }

	        // Perform saving operation 
	        String patientID = generatePatientID();
	        System.out.println("Saving patient information:");
	        System.out.println(patientInfo);
	        System.out.println("Generated Patient ID: " + patientID);

	        String fileName = patientID + "_PatientInfo.txt";
	        System.out.println("Saving patient information to file: " + fileName);
	        try (FileWriter writer = new FileWriter(fileName)) {
	            writer.write(patientInfo);
	            return true;
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            return false;
	        }
	        
	    }

	    private String generatePatientID() {
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 5; i++) {
	            sb.append(random.nextInt(10));
	        }
	        return sb.toString();
	    }
	    
	    private void showAlert(AlertType type, String title, String message) {
	        Alert alert = new Alert(type);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	   
	
}
