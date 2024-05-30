package uts.model;

public class User {
    private Integer Id;
    private String Name;
    private String ClassName;
    public User(String name, String className, String section) {
        Name = name;
        ClassName = className;
        Section = section;
    }
    public User() {
    }
    public User(Integer id, String name, String className, String section) {
        Id = id;
        Name = name;
        ClassName = className;
        Section = section;
    }
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String class1) {
        ClassName = class1;
    }
    public String getSection() {
        return Section;
    }
    public void setSection(String section) {
        Section = section;
    }
    private String Section;
}
