package xiong.user.edge.service.annotation;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.thrift.transport.TTransportException;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Configuration(proxyBeanMethods = false)
public class RetryConfiguration extends AbstractPointcutAdvisor {

    Pointcut pointcut;
    Advice advice;

    @PostConstruct
    public void init() {
        pointcut = new AnnotationMethodPoint();
        advice = new RetryAdvice();
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    private final class AnnotationMethodPoint extends StaticMethodMatcherPointcut {

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            RetryInit retryInit = method.getAnnotation(RetryInit.class);
            return retryInit != null;
        }
    }

    private final class RetryAdvice implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                return invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());

            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof TTransportException) {
                    e.printStackTrace();
                    Method initMethod = invocation.getThis().getClass().getMethod("init");
                    initMethod.invoke(invocation.getThis());
                    return invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
                }
                throw e;
            }
        }
    }
}
