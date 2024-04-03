package groupProject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NurseView extends Application {

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

    @Override
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

        // Creating UI components
        patientNameField = new TextField();
        ageField = new TextField();
        dobField = new TextField();
        weightField = new TextField();
        heightField = new TextField();
        bodyTempField = new TextField();
        bloodPressureField = new TextField();
        healthIssuesArea = new TextArea();
        healthIssuesArea.setPrefSize(250, 100);
        allergiesArea = new TextArea();
        allergiesArea.setPrefSize(250, 100);
        purposeOfVisitArea = new TextArea();
        purposeOfVisitArea.setPrefSize(250, 100);
        // Creating labels
        Label patientInfoLabel = new Label("Patient Information");
        patientInfoLabel.setStyle("-fx-font-weight: bold");
        Label vitalsLabel = new Label("Vitals");
        vitalsLabel.setStyle("-fx-font-weight: bold");
        Label nurseInputLabel = new Label("Nurse Input");
        nurseInputLabel.setStyle("-fx-font-weight: bold");

        // Creating buttons
        Button exitButton = new Button("Back");
        exitButton.setOnAction(e -> {
            GroupProject Main = new GroupProject();
            Main.start(primaryStage);
        });
        
        Button historyButton = new Button("History");
        historyButton.setOnAction(e -> {
            // Go back to the PatientHistory scene
            PatientHistory patient = new PatientHistory();
            patient.start(primaryStage);
        });
        Button chatButton = new Button("Patient Chat");
        chatButton.setOnAction(e -> {
            NurseChat nurseChat = new NurseChat();
            nurseChat.start(primaryStage);
        });
       
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save data
            saveData();
        });
        
        // Creating the layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Adding components to the layout
        VBox nameBox = new VBox();
        nameBox.getChildren().addAll(patientInfoLabel,new Label("Name:"), patientNameField);
        nameBox.setSpacing(5);

        VBox ageBox = new VBox();
        ageBox.getChildren().addAll(new Label("Age:"), ageField);
        ageBox.setSpacing(5);

        VBox dobBox = new VBox();
        dobBox.getChildren().addAll(new Label("Date of Birth:"), dobField);
        dobBox.setSpacing(5);

        // Add the VBox containers to the GridPane
        gridPane.add(nameBox, 0, 1);
        gridPane.add(ageBox, 0, 2);
        gridPane.add(dobBox, 0, 3);

        gridPane.add(vitalsLabel, 1, 4);
        gridPane.add(new Label("Weight:"), 0, 5);
        gridPane.add(weightField, 1, 5);
        gridPane.add(new Label("Height:"), 0, 6);
        gridPane.add(heightField, 1, 6);
        gridPane.add(new Label("Body Temperature:"), 0, 7);
        gridPane.add(bodyTempField, 1, 7);
        gridPane.add(new Label("Blood Pressure:"), 0, 8);
        gridPane.add(bloodPressureField, 1, 8);

        
        VBox healthIssuesBox = new VBox();
        healthIssuesBox.getChildren().addAll(nurseInputLabel, new Label("Health Issues:"), healthIssuesArea);
        healthIssuesBox.setSpacing(5);

        VBox allergiesBox = new VBox();
        allergiesBox.getChildren().addAll(new Label("Allergies:"), allergiesArea);
        allergiesBox.setSpacing(5);

        VBox purposeOfVisitBox = new VBox();
        purposeOfVisitBox.getChildren().addAll(new Label("Purpose of Visit:"), purposeOfVisitArea);
        purposeOfVisitBox.setSpacing(5);

        // Add the VBox containers to the GridPane
        gridPane.add(healthIssuesBox, 2, 1);
        gridPane.add(allergiesBox, 2, 2);
        gridPane.add(purposeOfVisitBox, 2, 3);


        // Adding buttons to the layout
        gridPane.add(exitButton, 0, 9);
        gridPane.add(historyButton, 1, 9);
        gridPane.add(saveButton, 2, 9);
        gridPane.add(chatButton, 3, 9);
       
        // Setting background
        gridPane.setBackground(bg);

        // Creating and showing the scene
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nurse View");
        primaryStage.show();
    }

    private void saveData() {
        // Generate a random patient ID
        Random random = new Random();
        int patientID = random.nextInt(10000); // Adjust the range as needed

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

        // Perform saving operation to a text file with patient ID in the file name
        String fileName = "nurseview_data_" + patientID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Patient ID: " + patientID);
            writer.newLine();
            writer.write("Patient Data:");
            writer.newLine();
            writer.write("Name: " + patientName);
            writer.newLine();
            writer.write("Age: " + age);
            writer.newLine();
            writer.write("Date of Birth: " + dob);
            writer.newLine();
            writer.newLine();
            writer.write("Vitals:");
            writer.newLine();
            writer.write("Weight: " + weight);
            writer.newLine();
            writer.write("Height: " + height);
            writer.newLine();
            writer.write("Body Temperature: " + bodyTemp);
            writer.newLine();
            writer.write("Blood Pressure: " + bloodPressure);
            writer.newLine();
            writer.newLine();
            writer.write("Nurse Input:");
            writer.newLine();
            writer.write("Health Issues: " + healthIssues);
            writer.newLine();
            writer.write("Allergies: " + allergies);
            writer.newLine();
            writer.write("Purpose of Visit: " + purposeOfVisit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
