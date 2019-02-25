
package interceptor;

import java.util.Arrays;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
@Add
public class LoggingInterceptor {
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String name = context.getMethod().getName();
        String params = Arrays.toString(context.getParameters());
        System.out.println("Logging: name = " +name + " params = "+ params);
        return context.proceed();
    }
    
}
