package framework.aop.proxy;

import lombok.AllArgsConstructor;

/**
 *  创建代理对象的逻辑交给子类实现
 *  创建代理对象需要目标对象、切点、增强三部分
 *  这个抽象类依赖的AdvisedSupport正好提供了这三部分资源
 */
@AllArgsConstructor
public abstract class AbstractAopProxy implements AopProxy {
    //创建代理对象所需要的资源
    protected AdvisedSupport advisedSupport;
}
