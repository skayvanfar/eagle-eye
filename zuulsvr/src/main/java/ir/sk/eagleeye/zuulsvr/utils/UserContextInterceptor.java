package ir.sk.eagleeye.zuulsvr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * used to inject the correlation ID into any outgoing HTTP-based service
 * requests being executed from a RestTemplate instance. This is done to ensure
 * that you can establish a linkage between service calls
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
public class UserContextInterceptor implements ClientHttpRequestInterceptor { // The UserContextIntercept implements the Spring frameworks ClientHttpRequestInterceptor
    private static final Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);

    /**
     * The intercept() method is invoked before the actual HTTP service call occurs by the RestTemplate
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        /*You take the HTTP request header
        that’s being prepared for the outgoing
        service call and add the correlation
        ID stored in the UserContext*/
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());

        return execution.execute(request, body);
    }
}
