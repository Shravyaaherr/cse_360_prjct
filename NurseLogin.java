package groupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NurseLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Nurse Login");

        // Create a GridPane for login components
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

       
        
        
        Image image = new Image("https://www.transparentpng.com/thumb/human/black-human-user-profile-png-icon-free-fsR5FT.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Set the width of the image
        imageView.setPreserveRatio(true);
        GridPane.setConstraints(imageView, 1, 0, 2, 1); // Center the image horizontally
        grid.getChildren().add(imageView);

        // Username Label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 1);

        // Username Input
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter your username");
        GridPane.setConstraints(usernameInput, 1, 1);

        // Password Label
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 2);

        // Password Input
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        GridPane.setConstraints(passwordInput, 1, 2);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 3); // Span 1 column
        loginButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            // Hardcoded login credentials for demonstration
            String hardcodedUsername = "nurse";
            String hardcodedPassword = "password";

            if (username.equals(hardcodedUsername) && password.equals(hardcodedPassword)) {
                // If login is successful, navigate to Nurse Portal
                NurseView nurseView = new NurseView();
                nurseView.start(primaryStage);
            } else {
                // If login fails, display an error message (you can modify this part as needed)
                System.out.println("Invalid username or password. Please try again.");
            }
        });
        // Back Button
        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 0, 3); // Span 1 column
        backButton.setMaxWidth(Double.MAX_VALUE); // Make the button fill the width
        backButton.setOnAction(e -> {
            // Implement back button action here (e.g., navigate to a previous screen)
            // For demonstration, let's close the current window and open the home screen
            GroupProject home2 = new GroupProject();
            primaryStage.close();
            home2.start(primaryStage);
        });
        
        // Add components to the grid
        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton, backButton);

        // Create a BorderPane for layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);

        // Set up the scene
        Scene scene = new Scene(borderPane, 800, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
