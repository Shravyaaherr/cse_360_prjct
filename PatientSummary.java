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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PatientSummary extends Application {
    private TextField date1;
    private TextField date2;
    private TextField date3;
    private TextField notes1;
    private TextField notes2;
    private TextField notes3;
    
    
    
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
        
        Label titleLabel = new Label("Patient Visit Summaries");
        titleLabel.setFont(Font.font(20));
        titleLabel.setTextFill(Color.BLACK);

        Label patientInfoLabel = new Label("Patient Visit Summaries");
        patientInfoLabel.setStyle("-fx-font-weight: bold");
        patientInfoLabel.setAlignment(Pos.CENTER);

        date1 = new TextField();
        date2 = new TextField();
        date3 = new TextField();
        notes1 = new TextField();
        notes2 = new TextField();
        notes3 = new TextField();
        
        // Set preferred width for text fields
        date1.setPrefWidth(200);
        date1.setPrefHeight(50);
        date2.setPrefWidth(250);
        date2.setPrefHeight(50);
        date3.setPrefWidth(200);
        date3.setPrefHeight(50);
        notes1.setPrefWidth(200);
        notes1.setPrefHeight(150);
        notes2.setPrefWidth(200);
        notes2.setPrefHeight(150); 
        notes3.setPrefWidth(200);
        notes3.setPrefHeight(150);
        
        
        // Create a grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        

        // Add elements to the grid pane
        gridPane.add(patientInfoLabel, 1, 0);
        gridPane.add(new Label("Date:"), 0, 1);
        gridPane.add(date1, 1, 1);
        gridPane.add(new Label("Notes:"), 0, 2);
        gridPane.add(notes1, 1, 2);
        gridPane.add(new Label("Date:"), 0, 3);
        gridPane.add(date2, 1, 3);
        gridPane.add(new Label("Notes:"), 0, 5);
        gridPane.add(notes2, 1, 5);
        gridPane.add(new Label("Date:"), 0, 6);
        gridPane.add(date3, 1, 6);
        gridPane.add(new Label("Notes:"), 0, 7);
        gridPane.add(notes3, 1, 7);
        
        gridPane.setBackground(bg);
     // Create a Save button
        Button homePage = new Button("Home");
        
        homePage.setOnAction(e -> {
            savePatientInfo();
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });

        // Set the button to span two columns and align it to the right
        gridPane.add(homePage, 1, 9);
        GridPane.setColumnSpan(homePage, 2);
        GridPane.setHalignment(homePage, javafx.geometry.HPos.RIGHT);

        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        
        Button contactUs = new Button("Contact Us");
        contactUs.setOnAction(e -> {
        	PatientChat PatientChat = new PatientChat();
            PatientChat.start(primaryStage);
        });
        
        Label sentenceLabel2 = new Label("Have any inquiries or health related questions?");
        // Add the exit button to the same row as the save button
        gridPane.add(exitButton, 0, 9);
        
        
        gridPane.add(contactUs, 5, 5);
        gridPane.add(sentenceLabel2, 5, 3);
        GridPane.setHalignment(contactUs, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(exitButton, javafx.geometry.HPos.LEFT);
        // Create a scene
        Scene scene = new Scene(gridPane, 800, 400, Color.WHITE);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
        
 
    }
    
    private String savePatientInfo() {
        // Store patient information in a string
        String patientInfo = 
                "Date: " + date1.getText() + "\n"
                + "Notes: " + notes1.getText() + "\n"
                + "Date: " + date2.getText() + "\n"
                + "Notes: " + notes2.getText() + "\n"
                + "Date: " + date3.getText() + "\n"
                + "Notes: " + notes3.getText();


        // Perform saving operation 
        String patientID = generatePatientID();
        System.out.println("Saving patient information:");
        System.out.println(patientInfo);
        System.out.println("Generated Patient ID: " + patientID);

        String fileName = patientID + "_PatientInfo.txt";
        System.out.println("Saving patient information to file: " + fileName);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(patientInfo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return patientID;
    }
    private String generatePatientID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}