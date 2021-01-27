package Project;

import Project.UserMessageModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GroupModelFunction {
    ObservableList<GroupModel> group = FXCollections.observableArrayList();


    public GroupModelFunction(){

    }





//This will add a group name, this method is used for User Controller or Admin Controller to add various groups
    //that will be initialised in the controllers and will populate choicebox that the users can choose for.
public void addGroup(GroupModel groupName){
        group.add(groupName);

}

public void checkIfNotEmpty() throws Exception {
    if(group.size() <= 0){
        group.add(new GroupModel("Games"));
        group.add(new GroupModel("Prices"));
        group.add(new GroupModel("Updates"));
        save();
      load();
    }

}

public void DeleteGroup(String groupname) throws Exception {
if(group.size() > 0) {
    for (int i = 0; i < group.size(); i++) {

        if (group.get(i).getGroup().equals(groupname)) {
            group.remove(i);

        }

    }
}
}

public void UpdateGroup(String groupname, String newgroupname){
if(group.size() > 0) {
    for (int i = 0; i < group.size(); i++) {

        if (group.get(i).getGroup().equals(groupname)) {
            group.get(i).setGroup(newgroupname);

        }

    }
}
}


public void Populate(ObservableList<String> OList){


    for(int i = 0; i < group.size();i++){

     OList.add(String.valueOf(group.get(i).getGroup()));

    }
}


//Test method
public void test(){
        InitialiseDefaultGroups();

for(int i = 0; i < group.size(); i++){
    System.out.println(group.get(i));


}
}


public void InitialiseDefaultGroups(){
    group.add(new GroupModel("Games"));
    group.add(new GroupModel("Prices"));
    group.add(new GroupModel("Updates"));

}



    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Groups.xml"));

        ArrayList<GroupModel> TemporaryArrayList = new ArrayList<>();

        TemporaryArrayList = (ArrayList<GroupModel>) is.readObject();
        group = FXCollections.observableArrayList(TemporaryArrayList);
        is.close();

    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Groups.xml"));

        out.writeObject(new ArrayList<GroupModel>(group));
        out.close();
    }


}
