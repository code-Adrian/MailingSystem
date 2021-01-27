package Project.UserPrivateData;

import com.sun.javafx.scene.control.Logging;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UsernameFunction {

    Map<String,String> Username = new HashMap<String,String>();

    public UsernameFunction(){

    }


    public void AddUsername(String Email,String UserName){
        Username.put(Email,UserName);
    }

    public void RemoveUsername(String Email){
        Username.remove(Email);
    }

    public boolean CheckUserName(String UserName){

        for(Map.Entry<String,String> entry : Username.entrySet()){
            if(entry.getValue().equals(UserName)){
                return true;
            }
        }
        return false;
    }


    public String getUserNameByEmail(String Email){
        String username = "";
        for(Map.Entry<String,String> entry : Username.entrySet()){
            if(entry.getKey().equals(Email)){
                username = entry.getValue();
            }
        }
        return username;
    }

    public String getEmailByUsername(String username){
        String user = "";
        for(Map.Entry<String,String> entry : Username.entrySet()){
            if(entry.getValue().equals(username)){
                user = entry.getKey();
            }
        }
        return user;
    }


    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("UsernameData.xml"));
        Username = (Map)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("UsernameData.xml"));
        out.writeObject(Username);
        out.close();
    }
}
