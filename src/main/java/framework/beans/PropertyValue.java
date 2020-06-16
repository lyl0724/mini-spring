package framework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  Bean的基本类型和String类型的依赖
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PropertyValue {
    private String name;
    private String value;
}
