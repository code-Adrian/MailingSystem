package Project.UserPrivateData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoggingFunction {

    Map<String,String> Logging = new HashMap<>();

    public LoggingFunction(){

    }


    public void AddLogging(String Email,String Password){
        Logging.put(Email,Password);
    }



    public void RemoveLogging(String Email){
        Logging.remove(Email);
    }

    public boolean CheckEmail(String Email){

        for(Map.Entry<String,String> entry : Logging.entrySet()){
            if(entry.getKey().equals(Email)){
                return true;
            }
        }
        return false;
    }

    public boolean CheckPassword(String Email){

        for(Map.Entry<String,String> entry : Logging.entrySet()){
            if(entry.getValue().equals(Email)){
                return true;
            }
        }
        return false;
    }




    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("LoggingData.xml"));
        Logging = (Map)is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("LoggingData.xml"));
        out.writeObject(Logging);
        out.close();
    }
}
