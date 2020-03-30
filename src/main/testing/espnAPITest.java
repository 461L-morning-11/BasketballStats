import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class espnAPITest {

    //This tester works in conjunction with a local intellij project called espnAPI which is where I developed
    //the functionality of the below-tested functions

    @org.junit.jupiter.api.Test
    void testGetIssues() throws Exception {
        int[] issues = espnAPI.getIssues("https://api.github.com/repos/461L-morning-11/BasketballStats/issues");
        assertArrayEquals(new int[]{2, 1, 0, 0, 0},issues);
    }

    @org.junit.jupiter.api.Test
    void testGetCommits() throws Exception {
        Long[] commits = espnAPI.getCommits("https://api.github.com/repos/461L-morning-11/BasketballStats/stats/contributors");
        assertArrayEquals(new Long[]{Long.valueOf(18), Long.valueOf(3), Long.valueOf(26), Long.valueOf(2), Long.valueOf(2)},commits);
    }

    @org.junit.jupiter.api.Test
    void testGamesConnection() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/games");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            assertFalse(true);
        else
            assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void testPlayersConnection() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/players");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            assertFalse(true);
        else
            assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void testTeamsConnection() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/teams");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            assertFalse(true);
        else
            assertTrue(true);
    }

    @org.junit.jupiter.api.Test
    void testGamesData() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/games");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        else
        {

            Scanner sc = new Scanner(url.openStream());

            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
            System.out.println("\nJSON data in string format");
            System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();

        JSONObject jobj = (JSONObject)parse.parse(inline);

        JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

        JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(0);

        JSONObject homeObj = (JSONObject)jsonobj_1.get("home_team");
        assertEquals("Celtics",homeObj.get("name"));
    }

    @org.junit.jupiter.api.Test
    void testPlayerData() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/players");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        else
        {

            Scanner sc = new Scanner(url.openStream());

            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
            System.out.println("\nJSON data in string format");
            System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();

        JSONObject jobj = (JSONObject)parse.parse(inline);

        JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

        JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(0);

       String lastName = (String)jsonobj_1.get("last_name");

        assertEquals("Anigbogu",lastName);
    }

    @org.junit.jupiter.api.Test
    void testTeamData() throws Exception{
        URL url = new URL("https://www.balldontlie.io/api/v1/teams");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if(responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        else
        {

            Scanner sc = new Scanner(url.openStream());

            while(sc.hasNext())
            {
                inline+=sc.nextLine();
            }
            System.out.println("\nJSON data in string format");
            System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();

        JSONObject jobj = (JSONObject)parse.parse(inline);

        JSONArray jsonarr_1 = (JSONArray) jobj.get("data");

        JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(0);

        String name = (String) jsonobj_1.get("name");

        assertEquals("Hawks",name);
    }

}