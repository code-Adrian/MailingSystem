package Project;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UserMessageModel {


public UserMessageModel(){
    this.username = "";
    this.title = "";
    this.body = "";
    this.date = "";
    this.priority = "";
    this.group = "";
}



    public UserMessageModel(String username, String title, String body, String date, String priority, String group) {
        this.username = username;
        this.title = title;
        this.body = body;
        this.date = date;
        this.priority = priority;
        this.group = group;
    }


    private String username;
    private String title;
    private String body;
    private String date;
    private String priority;
    private String group;



    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String mailingList) {
        this.group = group;
    }


    @Override
    public String toString() {
        return "UserMessageModel{" +
                "Title='" + title + '\'' +
                ", Body='" + body + '\'' +
                ", Date='" + date + '\'' +
                ", Priority='" + priority + '\'' +
                ", MailingList='" + group + '\'' +
                '}';
    }






}
