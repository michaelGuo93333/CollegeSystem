package loginapp;

import Admin.AdminController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginstatus;

    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("connected");
        }else{
                this.dbstatus.setText("Not Connected To Database");
    }

    this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void Login(ActionEvent event){
        try {

            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), this.combobox.getValue().toString())){
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option)this.combobox.getValue()).toString()){

                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            }
            else {
                this.loginstatus.setText("Wrong creditials");
            }

        }catch (Exception lcoalException){


        }

    }

    public void studentLogin(){
        try{
            Stage userstage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane )loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());

            StudentsController studentsController = (StudentsController)loader.getController();

            Scene scene = new Scene(root);
            userstage.setTitle("Student Dashbboard");
            userstage.setResizable(false);
            userstage.show();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }



    public void adminLogin(){
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
            AdminController adminController = (AdminController)adminLoader.getController();

            Scene scene = new Scene(adminroot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
