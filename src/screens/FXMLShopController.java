package screens;

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

    @FXML
    public void initialize() throws IOException {
        int i = 0;
        int width = 3, height = 3;

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

            button.setOnAction(e -> {
                System.out.println(moneyManager.get() + " - " + avatar.getCost());
                if(moneyManager.get() < avatar.getCost()) {
                        return;
                }

                moneyManager.rem(avatar.getCost());
                moneyManager.saveToFile();

                System.out.println("Money left: " + moneyManager.get());
                avatarManager.selectAvatar(avatar);
                System.out.println("Adding " + avatar);
                for (Avatar a : avatarManager.getAvatars()) {
                    System.out.println(a.name());
                }

                try {
                    backButtonPushed(e);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

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

    public FXMLShopController() {
        avatarManager = new AvatarManager();
        moneyManager = new MoneyManager();
    }

    public EventHandler<ActionEvent> interactAvatar(Avatar avatar) throws IOException {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!avatarManager.getAvatars().contains(avatar)) {
                    if(moneyManager.get() < avatar.getCost()) {
                        return;
                    }

                    moneyManager.rem(avatar.getCost());
                }

                avatarManager.getAvatars().remove(avatar);
                avatarManager.getAvatars().addFirst(avatar);
                avatarManager.saveToFile();
                moneyManager.saveToFile();

                // Try to return back to main menu
                try {
                    backButtonPushed(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void backButtonPushed(ActionEvent event) throws IOException {
        Parent aboutParent = FXMLLoader.load(getClass().getResource("FXMLMainscreen.fxml"));
        Scene mainScene = new Scene(aboutParent);
        // getting stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.setResizable(false);
        window.show();
    }
}
