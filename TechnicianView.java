package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TechnicianView extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Title and fields
        Label titleLabel = new Label("Technician View (Entering Data):");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        vbox.getChildren().add(titleLabel);

        // Patient ID field
        grid.add(new Label("Patient ID:"), 0, 1);
        TextField patientIdField = new TextField();
        patientIdField.setPrefWidth(300);
        grid.add(patientIdField, 1, 1);

        // The total Agatston CAC score field
        grid.add(new Label("The total Agatston CAC score"), 0, 2);
        TextField totalAgatstonField = new TextField();
        grid.add(totalAgatstonField, 1, 2);

        // Vessel level Agatston CAC score fields
        grid.add(new Label("Vessel level Agatston CAC score"), 0, 3);
        grid.add(new Label("LM:"), 0, 4);
        TextField lmField = new TextField();
        grid.add(lmField, 1, 4);

        grid.add(new Label("LAD:"), 0, 5);
        TextField ladField = new TextField();
        grid.add(ladField, 1, 5);

        grid.add(new Label("LCX:"), 0, 6);
        TextField lcxField = new TextField();
        grid.add(lcxField, 1, 6);

        grid.add(new Label("RCA:"), 0, 7);
        TextField rcaField = new TextField();
        grid.add(rcaField, 1, 7);

        grid.add(new Label("PDA:"), 0, 8);
        TextField pdaField = new TextField();
        grid.add(pdaField, 1, 8);

        vbox.getChildren().add(grid);

        // Save button
        Button btnSave = new Button("Save");
        btnSave.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white; -fx-font-weight: bold;");
        btnSave.setPrefWidth(80);
        btnSave.setOnAction(e -> saveData(patientIdField.getText(),
                totalAgatstonField.getText(),
                lmField.getText(),
                ladField.getText(),
                lcxField.getText(),
                rcaField.getText(),
                pdaField.getText()));

        HBox hBoxSave = new HBox();
        hBoxSave.setAlignment(Pos.CENTER_RIGHT);
        hBoxSave.getChildren().add(btnSave);
        vbox.getChildren().add(hBoxSave);

        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Technician View");
        primaryStage.show();
    }


    private void saveData(String patientId, String totalAgatston, String lm, String lad, String lcx, String rca, String pda) {
        if (patientId.isEmpty() || totalAgatston.isEmpty() || lm.isEmpty() || lad.isEmpty() || lcx.isEmpty() || rca.isEmpty() || pda.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled out.");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientId + "CTResults.txt"))) {
                writer.write("Patient ID: " + patientId + "\n");
                writer.write("The total Agatston CAC score: " + totalAgatston + "\n");
                writer.write("Vessel level Agatston CAC score\n");
                writer.write("LM: " + lm + "\n");
                writer.write("LAD: " + lad + "\n");
                writer.write("LCX: " + lcx + "\n");
                writer.write("RCA: " + rca + "\n");
                writer.write("PDA: " + pda + "\n");
            } catch (IOException ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while saving the data.");
                ex.printStackTrace();
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}