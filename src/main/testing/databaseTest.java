import java.net.URL;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import java.net.HttpURLConnection;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Scanner;
import java.net.URI;
import java.sql.Connection;
import java.util.*;
//import com.google.cloud.sql.jdbc.Driver;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class databaseTest {



    @Test
    void testPlayersTable() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "AlexJaylenStevenBamDeVaughnLaMarcusRawleGraysonJarrett";
            ResultSet rs = statement.executeQuery("SELECT * FROM players LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("first_name");
            }
            assertEquals(expected,actual);

            }catch(Exception e2){
                e2.printStackTrace();
            }


        }


    @Test
    void testTeamsTable() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "HawksCelticsNetsHornetsBullsCavaliersMavericksNuggetsPistons";
            ResultSet rs = statement.executeQuery("SELECT * FROM teams LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("short_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }



    @Test
    void testGamesTable() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "PacersRocketsWizardsThunderWizardsHeatSpursWizardsClippers";
            ResultSet rs = statement.executeQuery("SELECT * FROM games LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("home_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }


    @Test
    void testPlayersTableByFirstName() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "A.J.A.J.A.J.AaronAaronAaronAaronAaronAaron";
            ResultSet rs = statement.executeQuery("SELECT * FROM players ORDER BY first_name LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("first_name");
            }
            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }


    @Test
    void testTeamsTableByConference() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "HawksCelticsNetsHornetsBullsCavaliersPistonsPacersHeat";
            ResultSet rs = statement.executeQuery("SELECT * FROM teams ORDER BY conference LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("short_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }



    @Test
    void dtestGamesTableBtDate() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "PacersKingsGrizzliesThunderRaptorsRaptorsHeatWizardsWizards";
            ResultSet rs = statement.executeQuery("SELECT * FROM games ORDER BY date LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("home_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }


    @Test
    void dtestPlayersTableByLastName() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "NeneAlaaTariqShareefAlexAlexMarkJaylenSteven";
            ResultSet rs = statement.executeQuery("SELECT * FROM players ORDER BY last_name LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("first_name");
            }
            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }


    @Test
    void testTeamsTableByName() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "76ersBucksBullsCavaliersCelticsClippersGrizzliesHawksHeat";
            ResultSet rs = statement.executeQuery("SELECT * FROM teams ORDER BY short_name LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("short_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }



    @Test
    void testGamesTableByHomeTeam() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "76ers76ers76ers76ers76ers76ers76ers76ers76ers";
            ResultSet rs = statement.executeQuery("SELECT * FROM games ORDER BY home_name LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("home_name");
            }

            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }

    @Test
    void dtestPlayersTableByTeam() throws Exception {
        String db = "basketball_web";
        String user = "root";
        String pass = "Sr4*8DNgZbvHqnee";
        String ip = "104.154.138.136";

        try {

            System.out.println("trying to query from sql database;");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String host = "jdbc:mysql://" + ip + ":3306/" + db;
            Connection c = null;
            try {
                c = DriverManager.getConnection(
                        host,
                        user,
                        pass
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Statement statement = c.createStatement();
            String actual = "";
            String expected = "DeAndre'ClintVinceJohnDewayneMarcusTreveonKevinDamian";
            ResultSet rs = statement.executeQuery("SELECT * FROM players ORDER BY team_name LIMIT " + 0 + ", " + 10);
            for (int i = 1; i < 10; i++) {
                rs.next();
                actual += rs.getString("first_name");
            }
            assertEquals(expected,actual);

        }catch(Exception e2){
            e2.printStackTrace();
        }


    }


    }


