package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Import statement for Label
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import javafx.application.Platform; // Import statement for Platform

public class asuHelloWorldJavaFX extends Application {

    private Label patientIDLabel; // New addition to display the patient ID

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20; -fx-border-style: solid inside;" + 
                      "-fx-border-width: 2; -fx-border-insets: 5;" + 
                      "-fx-border-radius: 5; -fx-border-color: black;");

        Label welcomeLabel = new Label("Welcome to Heart Health Imaging and Recording System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-padding: 20;");

        // Initialize the label to display the patient ID
        patientIDLabel = new Label("No Patient ID Generated");
        patientIDLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10;");

        Button btnPatientIntake = new Button("Patient Intake");
        btnPatientIntake.setPrefWidth(200);
        btnPatientIntake.setStyle("-fx-font-size: 14px;");
        btnPatientIntake.setOnAction(event -> showReceptionistView());

        Button btnTechView = new Button("CT Scan Tech View");
        btnTechView.setPrefWidth(200);
        btnTechView.setStyle("-fx-font-size: 14px;");
        btnTechView.setOnAction(event -> showTechnicianView());

        Button btnPatientView = new Button("Patient View");
        btnPatientView.setPrefWidth(200);
        btnPatientView.setStyle("-fx-font-size: 14px;");
        btnPatientView.setOnAction(event -> showPatientView());
        
        Button btnDoctorView = new Button("Doctor View");
        btnDoctorView.setPrefWidth(200);
        btnDoctorView.setStyle("-fx-font-size: 14px;");
        btnDoctorView.setOnAction(event -> showDoctorView());

        // Set the style of the button to a blue background
        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white;";

        btnPatientIntake.setStyle(buttonStyle);
        btnTechView.setStyle(buttonStyle);
        btnPatientView.setStyle(buttonStyle);
        btnDoctorView.setStyle(buttonStyle);
        
        // Set the style of the button to a blue background
        String buttonStyle1 = "-fx-background-color: #007bff; -fx-text-fill: white;";

        btnPatientIntake.setStyle(buttonStyle1);
        btnTechView.setStyle(buttonStyle1);
        btnPatientView.setStyle(buttonStyle1);

        root.getChildren().addAll(welcomeLabel, btnPatientIntake, btnTechView, btnPatientView, btnDoctorView);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Main UI of the System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    private void showReceptionistView() {
    	ReceptionistView receptionistView = new ReceptionistView();
        Stage stage = new Stage();
        receptionistView.start(new Stage());
    }
    
    private void showTechnicianView() {
    	TechnicianView TechnicianView = new TechnicianView();
        Stage stage = new Stage();
        TechnicianView.start(stage);
    }


    private void showPatientView() {
        PatientView patientView = new PatientView();
        Stage stage = new Stage();
        patientView.start(stage);
    }
    
    
    private void showDoctorView() {
        DoctorView doctorView = new DoctorView();
        Stage stage = new Stage();
        try {
            doctorView.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    public static void main(String[] args) {
        launch(args);
    }
}