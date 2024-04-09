package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PatientView extends Application {

    private TextField tfPatientID;
    private Label lblPatientName;
    private TextField tfTotalAgatstonScore;
    private TextField tfLMScore;
    private TextField tfLADScore;
    private TextField tfLCXScore;
    private TextField tfRCAScore;
    private TextField tfPDAScore;
    
    private TextArea messageTextArea;
    private TextField messageTextField;
    private Button sendButton;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Title and Patient ID field
        Label lblPatientID = new Label("Patient ID:");
        tfPatientID = new TextField();
        tfPatientID.setPrefWidth(200);
        HBox hBoxPatientID = new HBox(10, lblPatientID, tfPatientID);
        hBoxPatientID.setAlignment(Pos.CENTER_LEFT);

        // Scores fields
        tfTotalAgatstonScore = new TextField();
        tfLMScore = new TextField();
        tfLADScore = new TextField();
        tfLCXScore = new TextField();
        tfRCAScore = new TextField();
        tfPDAScore = new TextField();

        grid.add(new Label("The total Agatston CAC score"), 0, 1);
        grid.add(tfTotalAgatstonScore, 1, 1);
        grid.add(new Label("LM:"), 0, 2);
        grid.add(tfLMScore, 1, 2);
        grid.add(new Label("LAD:"), 0, 3);
        grid.add(tfLADScore, 1, 3);
        grid.add(new Label("LCX:"), 0, 4);
        grid.add(tfLCXScore, 1, 4);
        grid.add(new Label("RCA:"), 0, 5);
        grid.add(tfRCAScore, 1, 5);
        grid.add(new Label("PDA:"), 0, 6);
        grid.add(tfPDAScore, 1, 6);

        Button btnViewResults = new Button("View Results");
        btnViewResults.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        btnViewResults.setOnAction(e -> viewResults());

        HBox hBoxButton = new HBox(btnViewResults);
        hBoxButton.setAlignment(Pos.CENTER_RIGHT);
        
        lblPatientName = new Label("Hello <Patient Name>");

        VBox vbox = new VBox(20, lblPatientName, hBoxPatientID, grid, hBoxButton);
        
        // Messaging interface
        Label messagesLabel = new Label("Messages:");
        messageTextArea = new TextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setPrefHeight(200);

        messageTextField = new TextField();
        messageTextField.setPromptText("Type your message...");

        sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        HBox messageBox = new HBox(10, messageTextField, sendButton);
        VBox messagesLayout = new VBox(10, messagesLabel, messageTextArea, messageBox);

        vbox.getChildren().add(messagesLayout);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient View");
        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageTextField.getText();
        if (!message.isEmpty()) {
            String patientId = tfPatientID.getText().trim();
            MessageManager.getInstance().addMessage(patientId, new Message("Patient", message));
            displayMessages(patientId);
            messageTextField.clear();
        }
    }

    private void displayMessages(String patientId) {
        List<Message> messages = MessageManager.getInstance().getMessages(patientId);
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append(message.getSender()).append(": ").append(message.getContent()).append("\n");
        }
        messageTextArea.setText(sb.toString());
    }

	private void viewResults() {
        clearResultsFields();
        String patientId = tfPatientID.getText().trim();

        // Validate the patient ID format
        if (patientId.isEmpty() || !patientId.matches("\\d+")) {
            showAlert("Invalid patient ID. Please enter a numeric ID.");
            return;
        }

        // Construct file names based on patient ID
        String patientInfoFileName = patientId + "_PatientInfo.txt";
        String ctResultsFileName = patientId + "CTResults.txt";
        
        // Extract the patient's name and update the greeting label
        lblPatientName.setText("Hello " + extractPatientName(patientInfoFileName));

        // Check for the existence of the patient info file
        File patientInfoFile = new File(patientInfoFileName);
        if (!patientInfoFile.exists()) {
            showAlert("Wrong patient ID or no such patient is available.");
            return;
        }

        // Check for the existence of the CT scan results file
        File ctResultsFile = new File(ctResultsFileName);
        if (!ctResultsFile.exists()) {
            showAlert("CT scan data is not available yet for this patient ID.");
            return;
        }

        // If both files exist, read and display the CT scan results
        String ctResults = readDataFromFile(ctResultsFileName);

        // Assuming the CT results file has each value on a new line in the order specified in your requirement
        String[] ctValues = ctResults.split("\\r?\\n");
        if (ctValues.length >= 6) {
            tfTotalAgatstonScore.setText(ctValues[1].split(": ")[1].trim());
            tfLMScore.setText(ctValues[3].split(": ")[1].trim());
            tfLADScore.setText(ctValues[4].split(": ")[1].trim());
            tfLCXScore.setText(ctValues[5].split(": ")[1].trim());
            tfRCAScore.setText(ctValues[6].split(": ")[1].trim());
            tfPDAScore.setText(ctValues[7].split(": ")[1].trim());
        }
    }
    
    
    private String extractPatientName(String patientInfoFileName) {
        // Assumes the second line of the patient info file contains the patient's name in the format: "Name: John Doe"
        File patientInfoFile = new File(patientInfoFileName);
        if (!patientInfoFile.exists()) {
            return "<File not found>";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(patientInfoFile))) {
            reader.readLine(); // Skip the first line
            String nameLine = reader.readLine(); // Read the second line which has the name
            if (nameLine != null) {
                String[] parts = nameLine.split(":");
                if (parts.length > 1) {
                    return parts[1].trim(); // Return the name part
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle this as appropriate for your application
        }
        return "<Name not found>"; // Fallback if name cannot be extracted
    }




    private void clearResultsFields() {
        tfTotalAgatstonScore.clear();
        tfLMScore.clear();
        tfLADScore.clear();
        tfLCXScore.clear();
        tfRCAScore.clear();
        tfPDAScore.clear();
    }

    private String readDataFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return sb.toString();
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}