package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class asuHelloWorldJavaFX extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showMainView();
    }

    private void showMainView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20; -fx-border-style: solid inside;" + 
                      "-fx-border-width: 2; -fx-border-insets: 5;" + 
                      "-fx-border-radius: 5; -fx-border-color: black;");

        Label welcomeLabel = new Label("Welcome to Heart Health Imaging and Recording System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-padding: 20;");

        Button btnPatient = new Button("Patient");
        btnPatient.setPrefWidth(200);
        btnPatient.setStyle("-fx-font-size: 14px;");
        btnPatient.setOnAction(event -> showPatientLoginView());

        Button btnOfficeStaff = new Button("Office Staff");
        btnOfficeStaff.setPrefWidth(200);
        btnOfficeStaff.setStyle("-fx-font-size: 14px;");
        btnOfficeStaff.setOnAction(event -> showOfficeStaffView());

        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white;";
        btnPatient.setStyle(buttonStyle);
        btnOfficeStaff.setStyle(buttonStyle);

        root.getChildren().addAll(welcomeLabel, btnPatient, btnOfficeStaff);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Main UI of the System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showPatientLoginView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");

        Label titleLabel = new Label("Patient");
        titleLabel.setStyle("-fx-font-size: 24px;");

        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setPrefWidth(200);
        btnCreateAccount.setOnAction(event -> showReceptionistView());

        Button btnLogin = new Button("Login");
        btnLogin.setPrefWidth(200);
        btnLogin.setOnAction(event -> showPatientView());

        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white;";
        btnCreateAccount.setStyle(buttonStyle);
        btnLogin.setStyle(buttonStyle);

        root.getChildren().addAll(titleLabel, btnCreateAccount, btnLogin);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Patient");
        primaryStage.setScene(scene);
    }

    private void showOfficeStaffView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");

        Label titleLabel = new Label("Office Staff");
        titleLabel.setStyle("-fx-font-size: 24px;");

        Button btnTechnicianView = new Button("Technician View");
        btnTechnicianView.setPrefWidth(200);
        btnTechnicianView.setOnAction(event -> showTechnicianView());

        Button btnDoctorView = new Button("Doctor View");
        btnDoctorView.setPrefWidth(200);
        btnDoctorView.setOnAction(event -> showDoctorView());

        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white;";
        btnTechnicianView.setStyle(buttonStyle);
        btnDoctorView.setStyle(buttonStyle);

        root.getChildren().addAll(titleLabel, btnTechnicianView, btnDoctorView);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Office Staff");
        primaryStage.setScene(scene);
    }

    private void showReceptionistView() {
        ReceptionistView receptionistView = new ReceptionistView();
        receptionistView.start(new Stage());
    }

    private void showTechnicianView() {
        TechnicianView technicianView = new TechnicianView();
        technicianView.start(new Stage());
    }

    private void showPatientView() {
        PatientView patientView = new PatientView();
        patientView.start(new Stage());
    }

    private void showDoctorView() {
        DoctorView doctorView = new DoctorView();
        try {
            doctorView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
