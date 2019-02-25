package interceptor;

import domain.Comment;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Add
public class MessageInterceptor {
    @AroundInvoke
    public Object replaceMethod(InvocationContext context) throws Exception {
        Object[] objects = context.getParameters();
        Comment comment = (Comment) objects[0];
        String message = comment.getComment();

        message = message.replaceAll("vet", "hard");
        message = message.replaceAll("cool", "dik");

        comment.setComment(message);

        objects[0] = comment;

        context.setParameters(objects);

        return context.proceed();
    }
}
