package games;

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
        window.setHeight(400);
        window.setResizable(false);

        Label label = new Label();
        label.setText("Options Menu");
        label.setScaleX(3);
        label.setScaleY(3);

        //Creating buttons
        Button returnButton = new Button("Return to Game");
        Button gamesButton = new Button("Games");
        Button mainMenuButton = new Button("Main Menu");

        returnButton.setMinSize(150, 75);
        gamesButton.setMinSize(150, 75);
        mainMenuButton.setMinSize(150, 75);

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
        layout.getChildren().addAll(label, returnButton, gamesButton, mainMenuButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //window needs to be closed before you can return

        return answer;
    }
}
