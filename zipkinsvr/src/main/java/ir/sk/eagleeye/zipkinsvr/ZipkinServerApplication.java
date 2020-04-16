package ir.sk.eagleeye.zipkinsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 4/16/2020.
 */
@SpringBootApplication
// allows you to quickly start Zipkin as a Spring Boot project
@EnableZipkinServer
// TODO: 4/16/2020 use @EnableZipkinStreamServer annotation is that you can continue to collect trace data even if the Zipkin server is unavailable 
public class ZipkinServerApplication {
    // TODO: 4/16/2020 config Zipkin with an non-memory data
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
