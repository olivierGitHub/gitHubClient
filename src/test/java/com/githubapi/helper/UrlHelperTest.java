package com.githubapi.helper;


import com.githubapi.util.helper.UrlHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.HttpURLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlHelperTest {

    private final String USER_AGENT = "Mozilla/5.0";
    private final String ACCEPT = "application/vnd.github.inertia-preview+json";

    @Autowired
    UrlHelper urlHelper;


    @Test
    public void getConnection_should_return_a_HttpURLConnection_object_with_right_parameters() throws Exception {
        // GIVEN
        String url = "http://an/url";

        // THEN
        HttpURLConnection connection = urlHelper.getConnection(url);

        // ASSERT
        Assert.assertNotNull(connection);
        Assert.assertEquals(connection.getRequestProperty("User-Agent"), USER_AGENT);
        Assert.assertEquals(connection.getRequestProperty("Accept"), ACCEPT);
        Assert.assertEquals(connection.getRequestMethod(), "GET");
    }

}
