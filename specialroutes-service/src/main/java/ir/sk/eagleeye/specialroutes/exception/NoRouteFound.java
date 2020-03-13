package ir.sk.eagleeye.specialroutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRouteFound extends RuntimeException {
}
