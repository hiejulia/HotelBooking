package com.project.hb.zuul.server;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableCircuitBreaker
@Configuration
@EnableFeignClients
@EnableResourceServer
public class EdgeApp {

    private static final Logger LOG = LoggerFactory.getLogger(EdgeApp.class);

    static {
        // for localhost testing only
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    @Value("${app.rabbitmq.host:localhost}")
    String rabbitMqHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        LOG.info("Create RabbitMqCF for host: {}", rabbitMqHost);
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqHost);
        return connectionFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(EdgeApp.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@Component
class DiscoveryClientSample implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(discoveryClient.description());
        discoveryClient.getInstances("hotel-service").forEach((ServiceInstance serviceInstance) -> {
            System.out.println("Instance --> " + serviceInstance.getServiceId()
                    + "\nServer: " + serviceInstance.getHost() + ":" + serviceInstance.getPort()
                    + "\nURI: " + serviceInstance.getUri() + "\n\n\n");
        });
    }
}

@Component
class RestTemplateExample implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... strings) throws Exception {

        ResponseEntity<Collection<Hotel>> exchange
                = this.restTemplate.exchange(
                        "http://hotel-service/v1/hotels?name=o",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Collection<Hotel>>() {
                },
                        (Object) "hotels");
        exchange.getBody().forEach((Hotel h) -> {
            System.out.println("\n\n\n[ " + h.getId() + " " + h.getName() + "]");
        });
    }
}

@FeignClient("hotel-service")
interface HotelClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/hotels")
    Collection<Hotel> getHotels(@RequestParam("name") String name);
}

@Component
class FeignSample implements CommandLineRunner {

    @Autowired
    private HotelClient hotelClient;

    @Override
    public void run(String... strings) throws Exception {
        this.hotelClient.getHotels("o").forEach((Hotel h) -> {
            System.out.println("\n\n\n[ " + h.getId() + " " + h.getName() + "]");
        });
    }
}

class Hotel {

    private List<Room> rooms = new ArrayList<>();
    private String id;
    private boolean isModified;
    private String name;

    public Hotel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsModified() {
        return isModified;
    }

    public void setIsModified(boolean isModified) {
        this.isModified = isModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel(String name, String id, List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}

class Room {

    private int numOfPeople;

    public Room(String name, BigInteger id, int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }
}
