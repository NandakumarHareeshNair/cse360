package asuHelloWorldJavaFX;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DoctorView extends Application {
    private TextArea messageTextArea;
    private TextField messageTextField;
    private TextField patientIdField;
    private Button sendButton;
    private Button submitButton; // Added submit button

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        Label titleLabel = new Label("Doctor View");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Patient ID input field
        Label patientIdLabel = new Label("Patient ID:");
        patientIdField = new TextField();
        patientIdField.setPromptText("Enter Patient ID Here");
        
        // Submit button
        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> displayMessages());
        
        HBox patientIdBox = new HBox(10, patientIdLabel, patientIdField, submitButton);

        // Messaging interface
        Label messagesLabel = new Label("Messages:");
        messageTextArea = new TextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setPrefHeight(200);
        messageTextField = new TextField();
        messageTextField.setPromptText("Type your reply...");
        sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendReply());

        HBox messageBox = new HBox(10, messageTextField, sendButton);
        VBox messagesLayout = new VBox(10, messagesLabel, messageTextArea, messageBox);

        vbox.getChildren().addAll(titleLabel, patientIdBox, messagesLayout);
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Doctor View");
        primaryStage.show();
    }

    private void displayMessages() {
        String patientId = patientIdField.getText().trim();
        if (!patientId.isEmpty()) {
            List<Message> messages = MessageManager.getInstance().getMessages(patientId);
            StringBuilder sb = new StringBuilder();
            for (Message message : messages) {
                sb.append(message.getSender()).append(": ").append(message.getContent()).append("\n");
            }
            messageTextArea.setText(sb.toString());
        }
    }

    private void sendReply() {
        String reply = messageTextField.getText();
        String patientId = patientIdField.getText().trim();
        if (!reply.isEmpty() && !patientId.isEmpty()) {
            MessageManager.getInstance().addMessage(patientId, new Message("Doctor", reply));
            displayMessages();
            messageTextField.clear();
        }
    }
}