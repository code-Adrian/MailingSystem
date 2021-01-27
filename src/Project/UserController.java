package Project;

import Project.UserPrivateData.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {


    GroupFunction group = new GroupFunction();
    private LoggingFunction LoggingFunc = new LoggingFunction();
    private UsernameFunction UsernameFunc = new UsernameFunction();
    private UsertypeFunction UserTypeFunc = new UsertypeFunction();
    private UserMessageModel Model = new UserMessageModel();
private LoginController UserNameGet = new LoginController();
    GroupModelFunction groups = new GroupModelFunction();
    ObservableList<UserMessageModel> List = FXCollections.observableArrayList();


    public void ReturnMainPage(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
@FXML
TableView<UserMessageModel> tableView;

@FXML
Label UserName ;

private String UserInitizalGroup;

 private TableColumn<UserMessageModel,String> User = new TableColumn<UserMessageModel,String>("UserName");
 private TableColumn<UserMessageModel,String> Title = new TableColumn<>("Title");
 private TableColumn<UserMessageModel,String> Message = new TableColumn<>("Message");
 private TableColumn<UserMessageModel,String> Date = new TableColumn<>("Date & Time");
private TableColumn<UserMessageModel,String> Priority = new TableColumn<>("Priority");
 private TableColumn<UserMessageModel,String> Group = new TableColumn<>("Group");

    ObservableList<String> GroupschooseJoin = FXCollections.observableArrayList(); // Here is Choices for user to filter
    ObservableList<String> GroupschooseFilter = FXCollections.observableArrayList();
    ObservableList<String> SendMail = FXCollections.observableArrayList();
    ObservableList<String> SendPriority = FXCollections.observableArrayList("Priority","Urgent","Normal");
    @FXML
    private ChoiceBox SendMailBox;

    @FXML
    private ChoiceBox GroupChoiceJoin;

    @FXML
    private ChoiceBox GroupChoiceFilter;

    @FXML
    private ChoiceBox SendPriorityBox;

    public void Populate(){

        groups.Populate(GroupschooseJoin);
        GroupChoiceJoin.setItems(GroupschooseJoin);
GroupChoiceJoin.setValue("Games");

    }

    @FXML
    Label ErrorCheckerPriority;
    @FXML
    Label ErrorCheckerMail;
@FXML
   Label ErrorCheckerTitle;



    @FXML
    private TextField MessageTitle;

@FXML
private TextArea MessageBody;

    public void WriteMessage() throws Exception {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
       String Date = date.getDayOfMonth() + "." + date.getMonth().getValue() + "." + date.getYear();
       String Time = time.getHour() + ":" + time.getMinute();

        String DateAndTime = Date + " " + Time;
        String Username = UserName.getText();

        if(MessageTitle.getText().isEmpty() || MessageTitle.getText().isBlank()){
            ErrorCheckerTitle.setTextFill(Color.web("#FF0000", 1));
ErrorCheckerTitle.setText("You need a title to write a message");
        }else if(MessageBody.getText().isEmpty() || MessageBody.getText().isBlank()){
ErrorCheckerTitle.setTextFill(Color.web("FF0000",1));
ErrorCheckerTitle.setText("You need to write a message!");
        }
        else {
            ErrorCheckerTitle.setText("");
            List.add(new UserMessageModel(Username, MessageTitle.getText(), MessageBody.getText(), DateAndTime, SendPriorityBox.getSelectionModel().getSelectedItem().toString(), SendMailBox.getSelectionModel().getSelectedItem().toString()));
            save();
            ErrorCheckerTitle.setTextFill(Color.web("#00FF00", 1));
            ErrorCheckerTitle.setText("You wrote a message to the "+SendMailBox.getSelectionModel().getSelectedItem().toString()+" group, at " + time.getHour() + ":" + time.getMinute());
        }


    }

    public boolean checkIfGroupExistsInFilter(){
        for(int i = 0; i < GroupschooseFilter.size();i++){
            if(GroupChoiceJoin.getSelectionModel().getSelectedItem().toString().equals(GroupschooseFilter.get(i))){

                return true;
            }

        }
        return false;
    }


@FXML
Label ErrorCheckerJoin;
    public void JoinGroup(){
        String Username = UserName.getText();
        String Email = UsernameFunc.getEmailByUsername(Username);
        String UserInitialGroup = group.getInitialGroupByEmail(Email);




        if(checkIfGroupExistsInFilter() == false) {
            GroupschooseFilter.add(GroupChoiceJoin.getSelectionModel().getSelectedItem().toString());
            ErrorCheckerJoin.setTextFill(Color.web("#00FF00", 1));
            ErrorCheckerJoin.setText("Your now part of the "+GroupChoiceJoin.getSelectionModel().getSelectedItem().toString()+ " Group!");
            SendMail.add(GroupChoiceJoin.getSelectionModel().getSelectedItem().toString());
            SendMailBox.setItems(SendMail);

        }else{
            ErrorCheckerJoin.setTextFill(Color.web("#FF0000", 1));
            ErrorCheckerJoin.setText("You're already a part of that group!");
            System.out.println("Your already a part of that group");
        }
    }

public void setColumnDimensions(){

        User.setMinWidth(70);
Title.setMinWidth(80);
Message.setMinWidth(250);
Date.setMinWidth(130);
}

public ObservableList<UserMessageModel> getMessages() throws Exception {


    try {
       load();
    } catch (Exception e) {
        e.printStackTrace();
    }


    return List;

}

public void ViewMessage(){
    tableView.getSelectionModel().setCellSelectionEnabled(true);
    ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();

    selectedCells.addListener(new ListChangeListener() {
        @Override
        public void onChanged(ListChangeListener.Change c) {
            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
            Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Cell Information");
            alert.setContentText(val.toString());

            alert.showAndWait();
        }
    });
}

@FXML
TextField filterField;


public void WordSearchFilter() throws Exception {

    String Username = UserName.getText();
    String Email = UsernameFunc.getEmailByUsername(Username);
    String UserInitialGroup = group.getInitialGroupByEmail(Email);
    GroupschooseFilter.add("All");
        GroupschooseFilter.add(UserInitialGroup);




    GroupChoiceFilter.setItems(GroupschooseFilter);
    GroupChoiceFilter.setValue("All");






    FilteredList<UserMessageModel> filterData = new FilteredList<>(List, b -> true);
    GroupChoiceFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, Groups) -> {
            filterData.setPredicate(Model -> {


                //Filtering starts here

                String GroupFilter = (String) Groups;

if(GroupChoiceFilter.getSelectionModel().getSelectedItem().equals("All")){

    return true;
}

                if (Model.getGroup().indexOf(GroupFilter) != -1) {
                    return true;
                }
                else
                    return false; // Does not match.
            });
    });


    SortedList<UserMessageModel> sortedInfo1 = new SortedList<>(filterData);

    sortedInfo1.comparatorProperty().bind(tableView.comparatorProperty());

    tableView.setItems(sortedInfo1);



    SortedList<UserMessageModel> sortedInfo2 = new SortedList<>(filterData);

    sortedInfo2.comparatorProperty().bind(tableView.comparatorProperty());
    tableView.setItems(sortedInfo2);

    //When filtered with group it allows to filter words as well
    GroupChoiceFilter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, Groups) -> {
                filterField.textProperty().addListener((observable1, oldValue1, Words) -> {
                    filterData.setPredicate(Model -> {



                        //Filtering starts here
                        String GroupFilter = (String) Groups;
                        String WordFilter = Words.toLowerCase();


                        if (Model.getUsername().toLowerCase().indexOf(WordFilter) != -1 && Model.getGroup().indexOf(GroupFilter) != -1) {
                            return true;
                        } else if (Model.getBody().toLowerCase().indexOf(WordFilter) != -1 && Model.getGroup().indexOf(GroupFilter) != -1) {
                            return true;
                        } else if (Model.getDate().toLowerCase().indexOf(WordFilter) != -1 && Model.getGroup().indexOf(GroupFilter) != -1 ) {
                            return true;
                        } else if (Model.getTitle().toLowerCase().indexOf(WordFilter) != -1 && Model.getGroup().indexOf(GroupFilter) != -1 ) {
                            return true;
                        } else if (Model.getPriority().toLowerCase().indexOf(WordFilter) != -1 && Model.getGroup().indexOf(GroupFilter) != -1) {
                            return true;
                        }

                         if (Model.getUsername().toLowerCase().indexOf(WordFilter) != -1 && GroupFilter.equals("All")) {
                         return true;
                         } else if (Model.getBody().toLowerCase().indexOf(WordFilter) != -1 && GroupFilter.equals("All")) {
                             return true;
                         } else if (Model.getDate().toLowerCase().indexOf(WordFilter) != -1 && GroupFilter.equals("All")) {
                             return true;
                        } else if (Model.getTitle().toLowerCase().indexOf(WordFilter) != -1 && GroupFilter.equals("All")) {
                             return true;
                         } else if (Model.getPriority().toLowerCase().indexOf(WordFilter) != -1 && GroupFilter.equals("All")) {
                             return true;
                         }


                            else
                            return false; // Does not match.
                    });
                });
            });




    SortedList<UserMessageModel> sortedInfo = new SortedList<>(filterData);

    sortedInfo.comparatorProperty().bind(tableView.comparatorProperty());


            tableView.setItems(sortedInfo);


}





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setColumnDimensions();
        try {

            groups.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        User.setCellValueFactory(new PropertyValueFactory("username"));

        Title.setCellValueFactory(new PropertyValueFactory("title"));

        Message.setCellValueFactory(new PropertyValueFactory("body"));


        Date.setCellValueFactory(new PropertyValueFactory("date"));

        Priority.setCellValueFactory(new PropertyValueFactory("priority"));

        Group.setCellValueFactory(new PropertyValueFactory("group"));

        Populate();

        try {
            LoggingFunc.load();
            UsernameFunc.load();
            UserTypeFunc.load();
            group.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserName.setText(UserNameGet.getUserName());

        String Username = UserName.getText();
        String Email = UsernameFunc.getEmailByUsername(Username);
        String UserInitialGroup = group.getInitialGroupByEmail(Email);

        UserInitizalGroup = UserInitialGroup;
        tableView.getColumns().addAll(User,Title,Message,Priority,Date,Group);
        try {
            tableView.setItems(getMessages());

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            WordSearchFilter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SendMail.add(UserInitialGroup);
        SendMailBox.setItems(SendMail);
        SendMailBox.setValue(UserInitialGroup);
        SendMailBox.getSelectionModel().selectFirst();
SendPriorityBox.setItems(SendPriority);
SendPriorityBox.setValue("Normal");

try {
    ViewMessage();
}catch(Exception e){

}

    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Table.xml"));

        ArrayList<UserMessageModel> TemporaryArrayList = new ArrayList<>();

        TemporaryArrayList = (ArrayList<UserMessageModel>) is.readObject();
         List = FXCollections.observableArrayList(TemporaryArrayList);
        is.close();

    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Table.xml"));

        out.writeObject(new ArrayList<UserMessageModel>(List));
        out.close();
    }

}
