package games;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class directions {
    public static void display(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //window needs to be attended to
        window.setTitle("Options");
        window.setWidth(400);
        window.setHeight(450);
        window.setResizable(false);
    }
}
