package com.project.hb.hotel.resources.docker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(DockerIT.class)
public class HotelAppDockerIT {

    // Hotel
    @Test
    public void testConnectionHotel() throws IOException {
        String baseUrl= System.getProperty("service.url");
        URL serviceUrl = new URL(baseUrl+"v1/hotels/1");
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        int responseCode = connection.getResponseCode();
        assertEquals(200,responseCode);
    }

    // User
    @Test
    public void testConnectionUser() throws IOException {
        String baseUrl= System.getProperty("service.url");
        URL serviceUrl = new URL(baseUrl+"v1/users/1");
        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        int responseCode = connection.getResponseCode();
        assertEquals(200,responseCode);
    }


}
