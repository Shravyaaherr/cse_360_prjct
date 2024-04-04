package groupProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientHistory extends Application {

    private static final int BODY_FONT = 16, TITLE_FONT = 20;

    private Label title, lastName, age, DOB, blank, status, medLab, immLab, pharmLab, blank2, allergLab, healLab, visLab, blank3;
    private VBox col1, col2, col3;
    private Button back, exit, confirm;
    private TextArea patName, patAge, patDOB, med, immunize, pharma, allerg, health, visits;
    private Image image;
    private Background bg;
    private BackgroundImage bgImage;

    @Override
    public void start(Stage primaryStage) {
        // Instance variables initialization
        col1 = new VBox();
        col2 = new VBox();
        col3 = new VBox();
        title = new Label();
        lastName = new Label();
        age = new Label();
        DOB = new Label();
        blank = new Label();
        status = new Label();
        medLab = new Label();
        immLab = new Label();
        pharmLab = new Label();
        blank2 = new Label();
        allergLab = new Label();
        healLab = new Label();
        visLab = new Label();
        blank3 = new Label();
        back = new Button("Back");
        exit = new Button("Exit");
        confirm = new Button("Search");
        patName = new TextArea();
        patAge = new TextArea();
        patDOB = new TextArea();
        med = new TextArea();
        immunize = new TextArea();
        pharma = new TextArea();
        allerg = new TextArea();
        health = new TextArea();
        visits = new TextArea();

        // Column 1 initialization
        col1.setStyle("-fx-border-color: black");
        col1.setAlignment(Pos.TOP_CENTER);
        col1.setMinSize(400, 375);
        col1.setMaxSize(400, 375);
        title.setText("Patient History");
        title.setFont(Font.font(null, TITLE_FONT));
        title.setPadding(new Insets(5, 0, 30, 0));
        lastName.setFont(Font.font(null, BODY_FONT));
        age.setFont(Font.font(null, BODY_FONT));
        DOB.setFont(Font.font(null, BODY_FONT));
        patName.setMaxSize(200, 40);
        patName.setMinSize(200, 40);
        patAge.setMaxSize(200, 40);
        patAge.setMinSize(200, 40);
        patDOB.setMaxSize(200, 40);
        patDOB.setMinSize(200, 40);
        confirm.setFont(Font.font(null, BODY_FONT));
        status.setFont(Font.font(null, BODY_FONT));
        status.setPadding(new Insets(10, 0, 0, 0));
        col1.getChildren().addAll(title, lastName, patName, age, patAge, DOB, patDOB, blank, confirm, status);

        // Column 2 initialization
        col2.setAlignment(Pos.TOP_CENTER);
        col2.setMinSize(175, 375);
        col2.setMaxSize(175, 375);
        col2.setPadding(new Insets(10, 0, 0, 10));
        medLab.setFont(Font.font(null, BODY_FONT));
        med.setEditable(false);
        med.setMouseTransparent(true);
        immLab.setFont(Font.font(null, BODY_FONT));
        immunize.setEditable(false);
        immunize.setMouseTransparent(true);
        healLab.setFont(Font.font(null, BODY_FONT));
        health.setEditable(false);
        health.setMouseTransparent(true);
        back.setFont(Font.font(null, BODY_FONT));
        back.setOnAction(e -> {
            // Go back to the NurseView scene
        	NurseView nurseView = new NurseView(); // Create an instance of NurseView
            nurseView.start(primaryStage);
        });
        col2.getChildren().addAll(medLab, med, immLab, immunize, healLab, health, blank2, back);

        // Column 3 initialization
        col3.setAlignment(Pos.TOP_CENTER);
        col3.setMinSize(175, 375);
        col3.setMaxSize(175, 375);
        col3.setPadding(new Insets(10, 0, 0, 10));
        pharmLab.setFont(Font.font(null, BODY_FONT));
        pharma.setEditable(false);
        pharma.setMouseTransparent(true);
        allergLab.setFont(Font.font(null, BODY_FONT));
        allerg.setEditable(false);
        allerg.setMouseTransparent(true);
        visLab.setFont(Font.font(null, BODY_FONT));
        visits.setEditable(false);
        visits.setMouseTransparent(true);
        exit.setFont(Font.font(null, BODY_FONT));
        exit.setOnAction(e -> primaryStage.close());
        col3.getChildren().addAll(pharmLab, pharma, allergLab, allerg, visLab, visits, blank3, exit);

        // Set background
        image = new Image("https://healthimpact.org/wp-content/uploads/2020/12/17767809.jpg");
        bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        bg = new Background(bgImage);

        // Populate scene
        HBox root = new HBox();
        root.setPadding(new Insets(0, 0, 0, 5));
        root.setBackground(bg);
        root.getChildren().addAll(col1, col2, col3);

        // Show scene
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.setTitle("Patient History");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
