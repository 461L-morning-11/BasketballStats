package basketballstats;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class playerFill extends HttpServlet {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private Players[] players;

    public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException {
        // GoogleSQL Connection Setup
        String instance = "basketball-db";
        String db="basketball_web";
        String user = "root";
        String pass="Sr4*8DNgZbvHqnee";
        String ip="104.154.138.136";
        String host = "jdbc:mysql://" + ip + ":3306/" + db;

        try {
            // Attempt connection to SQL server via JDBC. Ironically this does not require forName import (Deprecated)
            Connection c = DriverManager.getConnection(host, user, pass);

            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("show tables");

            while(r.next()) {
                System.out.println(r.getString(1));
            }

            for (int i = 1; i <= 33; i++) {
                apiRequest("https://www.balldontlie.io/api/v1/players?per_page=100&page=" + i);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apiRequest(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = EntityUtils.toString(entity);

                players = mapper.readValue(jsonInString, Players[].class);
            }
        }
    }
}
