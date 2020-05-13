package ir.sk.eagleeye.licenses.controllers;

import ir.sk.eagleeye.licenses.services.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "v1/tools")
public class ToolsController {
    @Autowired
    private DiscoveryService discoveryService;

    @GetMapping(value = "/eureka/services")
    public List<String> getEurekaServices() {

        return discoveryService.getEurekaServices();
    }
}
