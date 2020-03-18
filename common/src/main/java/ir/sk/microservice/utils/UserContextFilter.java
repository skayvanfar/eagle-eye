package ir.sk.microservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * intercept every call into your REST service and retrieve this information from
 * the incoming HTTP request and store this contextual information in a custom User-
 * Context object
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */

@Component
public class UserContextFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;


        UserContextHolder.getContext().setAuthorization(httpServletRequest.getHeader(UserContext.AUTHORIZATION) );

        // Retrieving values set in the HTTP header of the call into a UserContext, which is stored in UserContextHolder
        UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrgId(httpServletRequest.getHeader(UserContext.ORG_ID));

        logger.debug("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        logger.debug("I am entering the licensing service id with auth token: {}" , httpServletRequest.getHeader(UserContext.AUTHORIZATION));

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
