package com.project.hb.api.service.user;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class UserServiceAPI {
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/users/{user-id}")
    @HystrixCommand(fallbackMethod = "defaultUser")
    public ResponseEntity<User> getUser(
            @PathVariable("user-id") int id) {
        String url ="http://user-service/v1/users/" + id;
        ResponseEntity<User> res = restTemplate.getForEntity(url, User.class);
        return new ResponseEntity<>(res.getBody(), HttpStatus.OK);

    }


    @HystrixCommand(fallbackMethod = "defaultUsers")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> findByName(@RequestParam("name") String name) {

        String url = "http://user-service/v1/users?name=".concat(name);
        Collection<User> users;
        ResponseEntity<Collection> result = restTemplate.getForEntity(url, Collection.class);
        return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
    }




    public ResponseEntity<User> defaultUser(
            @PathVariable int id) {
        return new ResponseEntity<>( HttpStatus.BAD_GATEWAY);
    }


    public ResponseEntity<Collection<User>> defaultUsers(String input) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
