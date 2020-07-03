package framework.aop.target;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  创建代理对象，目标对象自然是必不可少的。
 *  TargetSource封装了目标对象及其信息。
 */
@Getter
@AllArgsConstructor
public class TargetSource {
    private final Object target;

    private final Class<?> targetClass;

    //jdk动态代理是基于接口的，创建代理对象时，需要目标对象的所有接口作为参数
    private final Class<?>[] interfaces;
}
