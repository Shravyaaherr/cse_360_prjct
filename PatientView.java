package groupProject;

import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PatientView extends Application {
    private TextField patientfirstNameField;
    private TextField patientlastNameField;
    private TextField emailField;
    private TextField phoneNumberField;
    private TextField InsuranceIdField;
    private TextField addressField;
    private TextField pharmacyField;
    private String username;
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void start(Stage primaryStage) {
        // Create labels and text fields
    	
    	Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        
        Label titleLabel = new Label("Patient Contact Information");
        titleLabel.setFont(Font.font(20));
        titleLabel.setTextFill(Color.BLACK);

        Label patientInfoLabel = new Label("Patient Contact Information");
        patientInfoLabel.setStyle("-fx-font-weight: bold");
        patientInfoLabel.setAlignment(Pos.CENTER);

        patientfirstNameField = new TextField();
        patientlastNameField = new TextField();
        emailField = new TextField();
        phoneNumberField = new TextField();
        InsuranceIdField = new TextField();
        addressField = new TextField();
        pharmacyField = new TextField();
        
        // Set preferred width for text fields
        patientfirstNameField.setPrefWidth(200);
        patientlastNameField.setPrefWidth(200);
        emailField.setPrefWidth(200);
        phoneNumberField.setPrefWidth(200);
        InsuranceIdField.setPrefWidth(200);
        addressField.setPrefWidth(200);
        pharmacyField.setPrefWidth(200);
        
        // Create a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        

        // Add elements to the grid pane
        gridPane.add(patientInfoLabel, 1, 0);
        gridPane.add(new Label("First Name:"), 0, 1);
        gridPane.add(patientfirstNameField, 1, 1);
        gridPane.add(new Label("Last Name:"), 0, 2);
        gridPane.add(patientlastNameField, 1, 2);
        gridPane.add(new Label("Email:"), 0, 3);
        gridPane.add(emailField, 1, 3);
        gridPane.add(new Label("Phone Number:"), 0, 5);
        gridPane.add(phoneNumberField, 1, 5);
        gridPane.add(new Label("Insurance Information:"), 0, 6);
        gridPane.add(InsuranceIdField, 1, 6);
        gridPane.add(new Label("Home Address:"), 0, 7);
        gridPane.add(addressField, 1, 7);
        gridPane.add(new Label("Pharmacy Information:"), 0, 8);
        gridPane.add(pharmacyField, 1, 8);
        gridPane.setBackground(bg);
        fillNamesFromFile("nurseview_data_" + username + ".txt");
        
     // Create a Save button
        Button saveButton = new Button("Save");
        
        saveButton.setOnAction(e -> {
        	 // Assuming you have a method to retrieve the patient ID (username)
        	   savePatientInfo(username);
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });

        // Set the button to span two columns and align it to the right
        gridPane.add(saveButton, 1, 9);
        GridPane.setColumnSpan(saveButton, 2);
        GridPane.setHalignment(saveButton, javafx.geometry.HPos.RIGHT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        Button summary = new Button("Summaries");
        summary.setOnAction(e -> {
            PatientSummary summaryPage = new PatientSummary();
            summaryPage.start(primaryStage);
        });
        Button contactUs = new Button("Contact Us");
        contactUs.setOnAction(e -> {
            PatientChat PatientChat = new PatientChat();
            PatientChat.start(primaryStage);
        });
        Label sentenceLabel = new Label("Looking for previous visits?");
        Label sentenceLabel2 = new Label("Have any inquiries or health related questions?");
        // Add the exit button to the same row as the save button
        gridPane.add(exitButton, 0, 9);
        gridPane.add(summary, 5, 3);
        gridPane.add(sentenceLabel, 5, 2);
        gridPane.add(contactUs, 5, 7);
        gridPane.add(sentenceLabel2, 5, 6);
        GridPane.setHalignment(sentenceLabel, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(sentenceLabel2, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(contactUs, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(summary, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(exitButton, javafx.geometry.HPos.LEFT);
        // Create a scene
        Scene scene = new Scene(gridPane, 800, 400, Color.WHITE);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
        
 
    }
    private void fillNamesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the name
                if (line.startsWith("Name:")) {
                    // Split the line by spaces
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 2) { // Ensure there are at least two parts (first name and last name)
                        // Extract the first and last name
                        String firstName = parts[1];
                        String lastName = parts[parts.length - 1];
                        // Set the first and last name fields
                        patientfirstNameField.setText(firstName);
                        patientlastNameField.setText(lastName);
                    } else if (parts.length == 1) { // If only one part, set it as the first name
                        patientfirstNameField.setText(parts[0]);
                    }
                    // Break the loop after finding the name
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void savePatientInfo(String patientID) {
        // Store patient information in a string
        String patientInfo = 
                "First Name: " + patientfirstNameField.getText() + "\n"
                + "Last Name: " + patientlastNameField.getText() + "\n"
                + "Email: " + emailField.getText() + "\n"
                + "Phone Number: " + phoneNumberField.getText() + "\n"
                + "Insurance ID: " + InsuranceIdField.getText() + "\n"
                + "Address: " + addressField.getText() + "\n"
                + "Pharmacy Location: " + pharmacyField.getText();


        // Perform saving operation 
        System.out.println("Saving patient information:");
        System.out.println(patientInfo);
        System.out.println("Patient ID: " + patientID);

        String fileName = "patient_data_" + patientID + ".txt";
        System.out.println("Saving patient information to file: " + fileName);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(patientInfo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}