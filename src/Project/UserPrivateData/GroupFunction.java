package Project.UserPrivateData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GroupFunction {

    Map<String,String> Group = new HashMap<String,String>();

    public GroupFunction(){

    }


    public void AddGroup(String Email,String Groups){
        Group.put(Email,Groups);
    }

    public void RemoveGroup(String Email){

        Group.remove(Email);
    }


    public String getInitialGroupByEmail(String Email){
        String group = "";
        for(Map.Entry<String,String> entry : Group.entrySet()){
            if(entry.getKey().equals(Email)){
                group = entry.getValue();
            }
        }
        return group;
    }


    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("GroupData.xml"));
        Group = (Map)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("GroupData.xml"));
        out.writeObject(Group);
        out.close();
    }
}
