package games;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class GameOptions {

    static int answer;

    public static int display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //window needs to be attended to
        window.setTitle("Options");
        window.setWidth(400);
        window.setHeight(450);
        window.setResizable(false);
        window.getIcons().add(new Image("file:src/assets/images/icons/window_icon.png"));

        Image label = new Image("file:src/assets/images/icons/options_label.png");
        ImageView labelView = new ImageView();
        labelView.setImage(label);
        labelView.setFitWidth(300);
        labelView.setPreserveRatio(true);

        //Creating buttons
        Button returnButton = new Button("Return to Game");
        Button gamesButton = new Button("Games");
        Button mainMenuButton = new Button("Main Menu");

        returnButton.setMinSize(180, 75);
        gamesButton.setMinSize(180, 75);
        mainMenuButton.setMinSize(180, 75);

        returnButton.setStyle("-fx-font-size: 1.5em;");
        gamesButton.setStyle("-fx-font-size: 2em;");
        mainMenuButton.setStyle("-fx-font-size: 1.7em;");

        returnButton.setOnAction(e -> {
            answer = 0;
            window.close();
        });

        gamesButton.setOnAction(e -> {
            answer = 1;
            window.close();
        });

        mainMenuButton.setOnAction(e -> {
            answer = 2;
            window.close();
        });

        VBox layout = new VBox(20);
        layout.setStyle("-fx-background-color: #7cc9fc");
        layout.getChildren().addAll(labelView, returnButton, gamesButton, mainMenuButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //window needs to be closed before you can return

        return answer;
    }
}
