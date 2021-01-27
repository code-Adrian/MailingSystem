package Project;

public class GroupModel {
    public GroupModel(String group) {
        Group = group;
    }

    private String Group;

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    @Override
    public String toString() {
        return "GroupModel{" +
                "Group='" + Group + '\'' +
                '}';
    }


}
