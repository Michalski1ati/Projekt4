package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PeselPaneController implements Initializable {

    @FXML
    private Button clearButton;

    @FXML
    private TextField inputPesel;

    @FXML
    private TextArea outputText;

    @FXML
    private Button confirmButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        clearButton.setOnAction(x -> clear());
        checkingPesel();
    }

    private void clear() {
        inputPesel.clear();
        outputText.clear();
    }

    private void checkingPesel() {
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int checksum = 0, counter = 0;
                char[] digitChar = inputPesel.getText().toCharArray();

                if (digitChar.length == 11) {
                    for (int position = 1; position <= 11; position++) {
                        if (position % 2 == 0) {
                            if ((digitChar[counter] - '0') >= 0 && (digitChar[counter] - '0') <= 9) {
                                checksum += doubleDigitValue(digitChar[counter] - '0');
                            } else {
                                outputText.setText("Niepoprawny nr PESEL");
                                return;
                            }
                        } else {
                            if ((digitChar[counter] - '0') >= 0 && (digitChar[counter] - '0') <= 9) {
                                checksum += digitChar[counter] - '0';
                            } else {
                                outputText.setText("Niepoprawny nr PESEL");
                                return;
                            }
                        }
                        counter++;
                    }
                    if (checksum % 10 == 0) {
                        outputText.setText("Prawidlowy nr PESEL");
                        String fileName = "PESEL.txt";
                        String code = "\n> " + inputPesel.getText();

                        try (
                                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));) {
                            writer.write(code + "\r\n");
                        } catch (IOException e) {
                            System.err.println("Blad zapisu do pliku");
                        }
                    } else {
                        outputText.setText("Niepoprawny nr PESEL");
                    }
                } else {
                    outputText.setText("Niepoprawny nr PESEL");
                }
            }
        });
    }

    public static int doubleDigitValue(int digit) {
        int doubledDigit = digit * 2;
        int sum;
        if (doubledDigit >= 10) {
            sum = 1 + doubledDigit % 10;
        } else {
            sum = doubledDigit;
        }
        return sum;
    }
}
