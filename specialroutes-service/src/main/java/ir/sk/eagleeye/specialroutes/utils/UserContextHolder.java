package ir.sk.eagleeye.specialroutes.utils;

import org.springframework.util.Assert;

/**
 * is used to store the UserContext in a ThreadLocal
 * class. Once it’s stored in the ThreadLocal storage, any code that’s executed for a
 * request will use the UserContext object stored in the UserContextHolder
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
public class UserContextHolder {

    // The UserContext is stored in a static ThreadLocal variable
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();


    /**
     * The getContext() method will retrieve the UserContext object for consumption
     * @return
     */
    public static final UserContext getContext(){
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);

        }
        return userContext.get();
    }

    public static final void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    public static final UserContext createEmptyContext(){
        return new UserContext();
    }
}
