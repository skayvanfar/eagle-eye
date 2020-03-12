package ir.sk.eagleeye.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import ir.sk.eagleeye.licenses.clients.OrganizationDiscoveryClient;
import ir.sk.eagleeye.licenses.clients.OrganizationFeignClient;
import ir.sk.eagleeye.licenses.clients.OrganizationRestTemplateClient;
import ir.sk.eagleeye.licenses.config.ServiceConfig;
import ir.sk.eagleeye.licenses.model.License;
import ir.sk.eagleeye.licenses.model.Organization;
import ir.sk.eagleeye.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    ServiceConfig config;


    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    private Organization retrieveOrgInfo(String organizationId, String clientType){
        Organization organization = null;
        randomlyRunLong();

        switch (clientType) {
            case "feign":
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                System.out.println("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
                break;
            case "discovery":
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    @HystrixCommand(commandProperties= // The commandProperties attribute lets you provide additional properties to customize Hystrix
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds", // The execution.isolation.thread.timeoutInMilliseconds is used to set the length of the timeout (in milliseconds) of the circuit breaker
                            value="3000")})
    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        Organization org = retrieveOrgInfo(organizationId, clientType);

        return license
                .withOrganizationName( org.getName())
                .withContactName( org.getContactName())
                .withContactEmail( org.getContactEmail() )
                .withContactPhone( org.getContactPhone() )
                .withComment(config.getExampleProperty());
    }

    /**
     * gives you a one in three chance
     * of a database call running long
     */
    private void randomlyRunLong() {
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
           /* You sleep for 11,000 milliseconds
              (11 seconds). Default Hystrix behavior
              is to time a call out after 1 second.*/
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* @HystrixCommand annotation is used to
    wrapper the getLicenseByOrg() method
    with a Hystrix circuit breaker */
    @HystrixCommand(fallbackMethod = "buildFallbackLicenseList", // The fallbackMethod attribute defines a single function in your class that will be called if the call from Hystrix fails
            threadPoolKey = "licenseByOrgThreadPool", // The threadPoolKey attribute defines the unique name of thread pool
            threadPoolProperties = // The threadPoolProperties attribute lets you define and customize the behavior of the threadPool.
                    {@HystrixProperty(name = "coreSize",value="30"), // The coreSize attribute lets you define the maximum number of threads in the thread pool
                            @HystrixProperty(name="maxQueueSize", value="10")}, // The maxQueueSize lets you define a queue that sits in front of your thread pool and that can queue incoming requests
            commandProperties= // The commandProperties attribute lets you provide additional properties to customize Hystrix
                    {@HystrixProperty(
                            name="execution.isolation.thread.timeoutInMilliseconds", // The execution.isolation.thread.timeoutInMilliseconds is used to set the length of the timeout (in milliseconds) of the circuit breaker
                            value="3000")})
    public List<License> getLicensesByOrg(String organizationId){
        randomlyRunLong();
        return licenseRepository.findByOrganizationId( organizationId );
    }

    /**
     * In the fallback method you return a hard-coded value
     * @param organizationId
     * @return
     */
    private List<License> buildFallbackLicenseList(String organizationId){
        List<License> fallbackList = new ArrayList<>();
        License license = new License()
                .withId("0000000-00-00000")
                .withOrganizationId( organizationId )
                .withProductName("Sorry no licensing information currently available");

        fallbackList.add(license);
        return fallbackList;
    }

    public void saveLicense(License license){
        license.withId( UUID.randomUUID().toString());

        licenseRepository.save(license);

    }

    public void updateLicense(License license){
        licenseRepository.save(license);
    }

    public void deleteLicense(License license){
        licenseRepository.delete( license.getLicenseId());
    }

}
