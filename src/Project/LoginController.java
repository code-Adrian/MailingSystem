package Project;


import Project.UserPrivateData.GroupFunction;
import Project.UserPrivateData.LoggingFunction;
import Project.UserPrivateData.UsernameFunction;
import Project.UserPrivateData.UsertypeFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private LoggingFunction Logging = new LoggingFunction();
    private GroupFunction Group = new GroupFunction();
    private UsernameFunction Username = new UsernameFunction();
    private UsertypeFunction UserType = new UsertypeFunction();





public LoginController(){

}



    @FXML
  Label ErrorChecker;

    @FXML
    Label ErrorChecker2;

    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;


    @FXML
    private TextField EmailAdmin;
    @FXML
    private PasswordField PasswordAdmin;


    private static String UserName;

    public void Login(ActionEvent event) throws IOException {
        if(Email.getText().isEmpty() == true || Password.getText().isEmpty()){
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("One of the fields is empty");
        }else if(Logging.CheckEmail(Email.getText()) == true && Logging.CheckPassword(Password.getText()) == true){
            ErrorChecker.setTextFill(Color.web("#00FF00", 1));

System.out.println(Username.getUserNameByEmail(Email.getText()));
            UserName = Username.getUserNameByEmail(Email.getText());

            Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();


System.out.println("Success");
        }else{
            ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            ErrorChecker.setText("Incorrect Logging");
            System.out.println("Incorrect Logging");
        }


    }

public void AdminLogin(ActionEvent event) throws IOException {

    if(EmailAdmin.getText().isEmpty() == true || PasswordAdmin.getText().isEmpty() || UserType.checkIfAdmin(EmailAdmin.getText()) == false){
        System.out.println(UserType.checkIfAdmin(EmailAdmin.getText()));
        ErrorChecker2.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker2.setText("One of the fields is empty or the user is not admin");
    }else if(Logging.CheckEmail(EmailAdmin.getText()) == true && Logging.CheckPassword(PasswordAdmin.getText()) == true ){
        ErrorChecker2.setTextFill(Color.web("#00FF00", 1));

        System.out.println(Username.getUserNameByEmail(EmailAdmin.getText()));
        UserName = Username.getUserNameByEmail(EmailAdmin.getText());

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


        System.out.println("Success");
    }

    else{
        ErrorChecker2.setTextFill(Color.web("#FF0000", 1));
        ErrorChecker2.setText("Incorrect Logging");
        System.out.println("Incorrect Logging");
    }


}

    public static String getUserName() {
        return UserName;
    }

    public void ReturnMainPage(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            Logging.load();
            Username.load();
            Group.load();
            UserType.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
