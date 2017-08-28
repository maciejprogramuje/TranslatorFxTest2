package pl.facebook.maciejprogramuje.translator.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.facebook.maciejprogramuje.translator.Translator;

/**
 * Created by m.szymczyk on 2017-08-28.
 */
public class MainController {
    @FXML
    private TextField textField;

    @FXML
    private Label label;

    public void initialize() {
        Translator translator = new Translator();

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(translator.findKey(newValue));
        });
    }
}
