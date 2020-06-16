package framework;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private String name;
    private int age;
    private boolean isBoy;
    private Double height;

    @Override
    public String toString() {
        return String.format("name=%s, age=%s, isBoy=%s, height=%s", name, age, isBoy, height);
    }
}
