package Project.UserPrivateData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsertypeFunction {

    Map<String,String> Usertype = new HashMap<String,String>();

    public UsertypeFunction(){

    }


    public void AddType(String Email,String UserType){

        Usertype.put(Email, UserType);
    }

    public void RemoveType(String Email){
        Usertype.remove(Email);
    }

    public boolean checkIfAdmin(String Email){

        for(Map.Entry<String,String> entry : Usertype.entrySet()){
            if(entry.getKey().equals(Email) && entry.getValue().equals("Admin")){
                 return true;
            }
        }

        return false;
    }


    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("UsertypeData.xml"));
        Usertype = (Map)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("UsertypeData.xml"));
        out.writeObject(Usertype);
        out.close();
    }

}
