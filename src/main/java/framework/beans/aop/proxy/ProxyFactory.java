package framework.beans.aop.proxy;

public class ProxyFactory extends AdvisedSupport implements  AopProxy {

    @Override
    public Object getProxy() {
        return createCglibAopProxy().getProxy();
    }

    protected final AopProxy createCglibAopProxy() {
        return new Cglib2AopProxy(this);
    }
}
