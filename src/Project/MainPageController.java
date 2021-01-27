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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    private LoggingFunction Logging = new LoggingFunction();
    private GroupFunction Group = new GroupFunction();
    private UsernameFunction Username = new UsernameFunction();
    private UsertypeFunction UserType = new UsertypeFunction();

    ObservableList<String> usertype = FXCollections.observableArrayList("Normal","Admin");
    @FXML
    private ChoiceBox usertypebox;

    @FXML
    ImageView iv;

    @FXML
    private void Image() {
        String Location = getClass().getResource("/Img/main.jpg").toString();
        Image image = new Image(Location);
        iv.setImage(image);
    }



    public void UserLogin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    public void AdminLogin(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }



    public void ReturnMainPage(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }



    ObservableList<String> Groupschoose = FXCollections.observableArrayList("Games","Prices","Updates");

    @FXML
    private ChoiceBox GroupChoice;


    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField userame;







    @FXML
    Label ErrorChecker;


    public void RegisterUser(){

        try {

            if(Logging.CheckEmail(email.getText()) == true){
                System.out.println("That email already exists, try another One");



                ErrorChecker.setText("That email already exists, try another One");
                ErrorChecker.setTextFill(Color.web("#FF0000", 1));
            }else if(Username.CheckUserName(userame.getText()) == true){
                ErrorChecker.setTextFill(Color.web("#FF0000", 1));
                ErrorChecker.setText("That username already exists, try another One");
                System.out.println("That username already exists, try another One");
            }else if(userame.getText().isEmpty() == true || password.getText().isEmpty() == true || email.getText().isEmpty() == true){
                ErrorChecker.setTextFill(Color.web("#FF0000", 1));
                ErrorChecker.setText("One of the fields is empty, try again");
                System.out.println("One of the fileds is empty, try again");
            }
            else {

                Logging.AddLogging(email.getText(), password.getText());
                Group.AddGroup(email.getText(), GroupChoice.getValue().toString());
                Username.AddUsername(email.getText(), userame.getText());
                UserType.AddType(email.getText(), usertypebox.getSelectionModel().getSelectedItem().toString());
                ErrorChecker.setTextFill(Color.web("#00FF00", 1));
                ErrorChecker.setText("Success");
                System.out.println("Success");
            }






            Logging.save();
            Group.save();
            Username.save();
            UserType.save();
        }catch(Exception e){
            System.out.println(e);

        }


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GroupChoice.setItems(Groupschoose);
        GroupChoice.setValue("Games");
        usertypebox.setItems(usertype);
        usertypebox.setValue("Normal");


        try {
            Logging.load();
            Username.load();
            Group.load();
            UserType.load();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Image();
    }
}
