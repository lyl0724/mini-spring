package framework.beans;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *  Bean依赖的所有基本类型和引用类型
 */
@Data
public class PropertyValues {
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public void addProperty(PropertyValue propertyValue) {
        if (!propertyValues.contains(propertyValue)) {
            propertyValues.add(propertyValue);
        }
    }
}
