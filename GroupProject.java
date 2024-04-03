package groupProject;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class GroupProject extends Application{

    public void start(Stage primaryStage) {
        // Create buttons

        
        
        Label titleLabel = new Label("Welcome to the Medical Portal");
        titleLabel.setFont(Font.font(16));
        titleLabel.setTextFill(Color.BLACK);
        
        Button button3 = new Button("Patient Portal");
        Button button1 = new Button("Nurse Portal");
        Button button2 = new Button("Doctor Portal");
        

        // Set button colors
        String buttonStyle = "-fx-background-color: #6d0000; -fx-text-fill: white; -fx-font-size: 14pt;";
        button1.setStyle(buttonStyle + "-fx-text-fill: white;");
        button2.setStyle(buttonStyle + "-fx-text-fill: white;");
        button3.setStyle(buttonStyle + "-fx-text-fill: white;");

        button1.setPrefSize(200, 50);
        button2.setPrefSize(200, 50);
        button3.setPrefSize(200, 50);

        // Arrange buttons vertically
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(titleLabel,button1, button2, button3);
        Image image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        BackgroundImage bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        vbox.setBackground(bg);
        
        // Create a scene
        Scene scene = new Scene(vbox, 800, 400, Color.WHITE);

        // Set the stage title
        primaryStage.setTitle("Medical Portal");

        button1.setOnAction(e -> {
        	NurseLogin nurseView = new NurseLogin(); // Create an instance of NurseView
            nurseView.start(primaryStage);
        });
        button2.setOnAction(e -> {
            DoctorLogin doctor = new DoctorLogin();
            doctor.start(primaryStage);
        });
        button3.setOnAction(e -> {
            PatientLogin patientLogin = new PatientLogin();
            patientLogin.start(primaryStage);
        });
        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}


