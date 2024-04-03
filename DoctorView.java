package groupProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DoctorView extends Application  {
    private TextField patientNameField;
    private TextField ageField;
    private TextField dobField;
    private TextField weightField;
    private TextField heightField;
    private TextField bodyTempField;
    private TextField bloodPressureField;

    private TextArea healthIssuesArea;
    private TextArea allergiesArea;
    private TextArea purposeOfVisitArea;
    private String patientID;
    
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void start(Stage primaryStage) {
        Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image, 
                BackgroundRepeat.NO_REPEAT, 
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        
        patientNameField = new TextField();
        patientNameField.setPrefSize(200, 20);
        ageField = new TextField();
        ageField.setPrefSize(200, 20);
        dobField = new TextField();
        weightField = new TextField();
        weightField.setPrefSize(100, 20);
        heightField = new TextField();
        heightField .setPrefSize(100, 20);
        bodyTempField = new TextField();
        bloodPressureField = new TextField();
        healthIssuesArea = new TextArea();
        healthIssuesArea.setPrefSize(150, 100);
        allergiesArea = new TextArea();
        allergiesArea.setPrefSize(150, 100);
        purposeOfVisitArea = new TextArea();

        // Creating labels
       
        Label vitalsLabel = new Label("Vitals");
        vitalsLabel.setStyle("-fx-font-weight: bold");
        Label nurseInputLabel = new Label("Nurse Input");
        nurseInputLabel.setStyle("-fx-font-weight: bold");

        
        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        exitButton.setPrefWidth(70); // Set preferred width
        exitButton.setPrefHeight(30); // Set preferred height

        Button historyButton = new Button("History");
        historyButton.setOnAction(e -> {
            // Go back to the PatientHistory scene
            PatientHistory patient = new PatientHistory();
            patient.start(primaryStage);
        });
        historyButton.setPrefWidth(70); // Set preferred width
        historyButton.setPrefHeight(30); // Set preferred height

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save data
            saveData();
        });
        Button chatButton = new Button("Patient Chat");
        chatButton.setOnAction(e -> {
            DoctorChat doctorChat = new DoctorChat();
            doctorChat.start(primaryStage);
        });
        
        saveButton.setPrefWidth(70); // Set preferred width
        saveButton.setPrefHeight(30); // Set preferred height
        setPatientID(patientID);
        // Read data from file and fill in the fields
        fillVitalsFromFile("nurseview_data_" + patientID + ".txt");

        VBox nameBox = new VBox();
        nameBox.getChildren().addAll(new Label("Name:"), patientNameField);
        nameBox.setSpacing(5);

        VBox ageBox = new VBox();
        ageBox.getChildren().addAll(new Label("Age:"), ageField);
        ageBox.setSpacing(5);

        gridPane.add(nameBox, 0, 1);
        gridPane.add(ageBox, 5, 1);
        VBox vitals = new VBox();
        vitals.getChildren().addAll(vitalsLabel);
        vitals.setSpacing(5);
        VBox vitalsBox = new VBox();
        vitalsBox.getChildren().addAll(new Label("Weight:"), weightField);
        vitalsBox.setSpacing(5);
        VBox heightBox = new VBox();
        heightBox.getChildren().addAll(new Label("Height:"), heightField);
        heightBox.setSpacing(5);
        VBox bodyBox = new VBox();
        bodyBox.getChildren().addAll(new Label("Body Temperature:"), bodyTempField);
        bodyBox.setSpacing(5);
        VBox bloodBox = new VBox();
        bloodBox.getChildren().addAll(new Label("Blood Pressure:"), bloodPressureField);
        bloodBox.setSpacing(5);
        gridPane.add(vitals, 0, 2);
        gridPane.add(vitalsBox, 0, 3);
        gridPane.add(heightBox, 1, 3);
        gridPane.add(bodyBox, 0, 4);
        gridPane.add(bloodBox, 1, 4);
        
        VBox healthIssuesBox = new VBox();
        Label recommendationsLabel = new Label("Recommendations:");
        recommendationsLabel.setAlignment(Pos.CENTER);
        healthIssuesBox.getChildren().addAll(recommendationsLabel, healthIssuesArea);
        healthIssuesBox.setSpacing(5); // Adjust spacing between label and text area

        VBox allergiesBox = new VBox();
        Label prescriptionsLabel = new Label("Prescriptions:");
        prescriptionsLabel.setAlignment(Pos.CENTER);
        allergiesBox.getChildren().addAll(prescriptionsLabel, allergiesArea);
        allergiesBox.setSpacing(5); // Adjust spacing between label and text area

        gridPane.add(healthIssuesBox, 5, 3);
        gridPane.add(allergiesBox, 6, 3);

        VBox purposeOfVisitBox = new VBox();
        Label physicalExaminationLabel = new Label("Physical Examination:");
        physicalExaminationLabel.setAlignment(Pos.CENTER);
        purposeOfVisitBox.getChildren().addAll(physicalExaminationLabel, purposeOfVisitArea);
        purposeOfVisitBox.setSpacing(5); 

        gridPane.add(purposeOfVisitBox, 5, 4);
        
        chatButton.setPrefWidth(100); // Set preferred width
        chatButton.setPrefHeight(30); // Set preferred height
        gridPane.add(chatButton, 6, 11);
        
        GridPane.setColumnSpan(purposeOfVisitBox, 2);
 // Span 2 columns
        

        // Adding buttons to the layout
        gridPane.add(exitButton, 0, 11);
        gridPane.add(historyButton, 1, 11);
        gridPane.add(saveButton, 3, 11);
        
       
        
      
        gridPane.setBackground(bg);
        
        
        
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor View");
        primaryStage.show();
        
    }
    
    // Method to fill vitals from file
    private void fillVitalsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean startVitals = false;
            while ((line = reader.readLine()) != null) {
                // Check if we reached the vitals section
                if (line.startsWith("Vitals:")) {
                    startVitals = true;
                    continue; // Skip this line
                }
                // Check if we reached the nurse input section
                if (line.startsWith("Nurse Input:")) {
                    break; // Stop reading if we reached the nurse input section
                }
                // If we are in the vitals section, parse the data
                if (startVitals) {
                    String[] parts = line.split(": ");
                    switch (parts[0]) {
                        case "Name":
                            patientNameField.setText(parts[1]);
                            break;
                        case "Age":
                            ageField.setText(parts[1]);
                            break;
                        case "Weight":
                            weightField.setText(parts[1]);
                            break;
                        case "Height":
                            heightField.setText(parts[1]);
                            break;
                        case "Body Temperature":
                            bodyTempField.setText(parts[1]);
                            break;
                        case "Blood Pressure":
                            bloodPressureField.setText(parts[1]);
                            break;
                    }
                } else if (line.startsWith("Name:")) { // Check if the line contains patient name
                    patientNameField.setText(line.substring(6)); // Skip "Name: " to get the name
                } else if (line.startsWith("Age:")) { // Check if the line contains patient age
                    ageField.setText(line.substring(5)); // Skip "Age: " to get the age
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void saveData() {
        // Retrieve and save data
        String patientName = patientNameField.getText();
        String age = ageField.getText();
        String dob = dobField.getText();
        String weight = weightField.getText();
        String height = heightField.getText();
        String bodyTemp = bodyTempField.getText();
        String bloodPressure = bloodPressureField.getText();
        String healthIssues = healthIssuesArea.getText();
        String allergies = allergiesArea.getText();
        String purposeOfVisit = purposeOfVisitArea.getText();

        // Perform saving operation (printing to console in this example)
        System.out.println("Saving patient data:");
        System.out.println("Name: " + patientName);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Saving patient vitals:");
        System.out.println("Weight: " + weight);
        System.out.println("Height: " + height);
        System.out.println("Body Temperature: " + bodyTemp);
        System.out.println("Blood Pressure: " + bloodPressure);
        System.out.println("Saving nurse input:");
        System.out.println("Health Issues: " + healthIssues);
        System.out.println("Allergies: " + allergies);
        System.out.println("Purpose of Visit: " + purposeOfVisit);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
