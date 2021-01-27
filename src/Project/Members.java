package Project;

public class Members {



    public Members(String email, String password, String username, String userType, String group) {
        Email = email;
        Password = password;
        Username = username;
        UserType = userType;
        Group = group;
    }


    private String Email;
    private String Password;
    private String Username;
    private String UserType;
    private String Group;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }


    @Override
    public String toString() {
        return "Members{" +
                "Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Username='" + Username + '\'' +
                ", UserType='" + UserType + '\'' +
                ", Group='" + Group + '\'' +
                '}';
    }



}
