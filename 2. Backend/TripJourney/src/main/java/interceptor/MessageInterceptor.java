package interceptor;

import domain.Message;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Add
public class MessageInterceptor {
    @AroundInvoke
    public Object replaceMethod(InvocationContext context) throws Exception {
        Object[] objects = context.getParameters();
        Message comment = (Message) objects[0];
        String message = comment.getComment();

        String message1 = message.replaceAll("vet", "hard");
        message = message.replaceAll("cool", "dik");
        System.out.println("Logging: name = " +message1 );
        comment.setComment(message1);

        objects[0] = comment;

        context.setParameters(objects);

        return context.proceed();
    }
}
