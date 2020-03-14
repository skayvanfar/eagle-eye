package ir.sk.eagleeye.organization.hystrix;

import ir.sk.eagleeye.organization.utils.UserContext;
import ir.sk.eagleeye.organization.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * used to set the UserContext from the parent thread executing
 * the user’s REST service call to the Hystrix command thread
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
public class DelegatingUserContextCallable<V> implements Callable<V> {

    private static final Logger logger = LoggerFactory.getLogger(DelegatingUserContextCallable.class);

    private final Callable<V> delegate;
    private UserContext originalUserContext;

    /**
     * Custom Callable class will be passed the original Callable
     * class that will invoke your Hystrix protected code and
     * UserContext coming in from the parent thread
     * @param delegate
     * @param userContext
     */
    public DelegatingUserContextCallable(Callable<V> delegate,
                                         UserContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    /**
     * The UserContext is set. The ThreadLocal variable that stores
     * the UserContext is associated with the thread running the Hystrix protected method
     * @return
     * @throws Exception
     */
    public V call() throws Exception {
        UserContextHolder.setContext( originalUserContext );

        try {
            // Once the UserContext is set invoke the
            // call() method on the Hystrix protected
            // method; for instance, your
            // LicenseServer.getLicenseByOrg() method.
            return delegate.call();
        }
        finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
