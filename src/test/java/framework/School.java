package framework;

import lombok.Data;

@Data
public class School {
    private String name;
    private User user;

    public void broadcast() {
        System.out.println(user + "is in school " + name);
    }

    @Override
    public String toString() {
        return "school name : " + name;
    }
}
