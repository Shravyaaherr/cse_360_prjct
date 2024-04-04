package groupProject;


import java.io.File;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorPatientID extends Application{

	
    private TextField patientIDField;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Set background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        gridPane.setBackground(bg);

        Label titleLabel = new Label("Enter Patient ID:");
        titleLabel.setTextFill(Color.BLACK);

        patientIDField = new TextField();
        patientIDField.setPromptText("Patient ID");
        patientIDField.setPrefWidth(200);

        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> {
            String patientID = patientIDField.getText();
            if (patientExists(patientID)) {
                DoctorView doctorView = new DoctorView();
                doctorView.setPatientID(patientID);
                 
              
                doctorView.start(primaryStage);
            } else {
                showError("Patient ID doesn't exist.");
            }
        });

        gridPane.add(titleLabel, 0, 1);
        gridPane.add(patientIDField, 0, 2);
        gridPane.add(enterButton, 0, 3);

        Scene scene = new Scene(gridPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor - Patient ID");
        primaryStage.show();
    }
      private boolean patientExists(String patientID) {
        // Construct the file name based on the patient ID
        String fileName = "nurseview_data_" + patientID + ".txt";

        // Check if the file exists
        File file = new File(fileName);
        return file.exists();
    }

    private void showError(String errorMessage) {
        // Display error message in a dialog or label
    	showAlert(AlertType.ERROR, "Error", "Failed to find the Patient ID.");
    }
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }

    
}
