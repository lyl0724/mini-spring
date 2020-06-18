package framework;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private String name;
    private int age;
    private boolean isBoy;
    private Double height;
    private School school;

    @Override
    public String toString() {
        return String.format("name=%s, age=%s, isBoy=%s, height=%s", name, age, isBoy, height);
    }

    public void broadcast() {
        System.out.println(name + "is in there " + school);
    }
}
