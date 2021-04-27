package screens.shop;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import store.MoneyManager;
import store.avatar.Avatar;
import store.avatar.AvatarManager;

import java.io.IOException;

public class FXMLShopController {

    private AvatarManager avatarManager;
    private MoneyManager moneyManager;

    @FXML
    private GridPane gridPane;

    public FXMLShopController(AvatarManager avatarManager,
                              MoneyManager moneyManager) {
        this.avatarManager = avatarManager;
        this.moneyManager = moneyManager;
    }

    @FXML
    public void initialize() throws IOException {
        int i = 0;
        int width = 3;

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for(Avatar avatar : Avatar.values()) {

            GridPane buttonPane = new GridPane();
            buttonPane.setAlignment(Pos.CENTER);
            buttonPane.setHgap(20);

            int x = i % width;
            int y = i / width;
            Button button = new Button();
            button.setText("Purchase\nCost: " + avatar.getCost());
            button.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
            button.setStyle("-fx-background-color: #7cfca7");


            if(moneyManager.get() < avatar.getCost()) {
                button.setText("Can't afford\nCost: " + avatar.getCost());
                button.setStyle("-fx-background-color: #fc7c7c");
            } else {
                button.setOnAction(e -> {
                    moneyManager.rem(avatar.getCost());
                    moneyManager.saveToFile();
                    avatarManager.selectAvatar(avatar);
                    avatarManager.saveToFile();

                    try {
                        backButtonPushed(e);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
            }

            ImageView avatarImage = new ImageView();
            avatarImage.setImage(avatar.getImage());
            avatarImage.setFitHeight(100);
            avatarImage.setFitWidth(100);

            buttonPane.add(avatarImage, 0, 0);
            if(!avatarManager.getAvatars().contains(avatar)) {
                buttonPane.add(button, 1, 0);
            }

            gridPane.add(buttonPane, x, y);
            i++;
        }
        gridPane.setAlignment(Pos.CENTER);
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLMainscreen.fxml"));
        Scene mainScene = new Scene(root);
        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.setResizable(false);
        window.show();
    }
}
