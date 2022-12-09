import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
public class SimpleInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) {

        System.out.println("SimpleInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );
        Object result = null;
        try{
            result = context.proceed();
        } catch (Exception e){

        }
        System.out.println("SimpleInterceptor - Logging AFTER calling method :"+context.getMethod().getName() );

        return result;
        }
}
