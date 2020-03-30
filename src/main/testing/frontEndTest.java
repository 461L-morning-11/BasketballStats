import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class frontEndTest {

    //This tester works in conjunction with a local intellij project called espnAPI which is where I developed
    //the functionality of the below-tested functions

    @Test
    void testHomePage() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        String check = "Professional Basketball Statistics\n" +
                "Welcome to Basketball Stats!\n" +
                "Feel free to look around at all the info we have to offer!";
        assertEquals(check,title);
        wd.quit(); // close the browser window
    }

    @Test
    void testAboutPage() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/about");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        title = title.substring(0,14);
        String check = "About our Page";
        assertEquals(check,title);
        wd.quit(); // close the browser window
    }

    @Test
    void testPlayersPage() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/players");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        System.out.println(title);
        String check = "List of Players";
        assertEquals(check,title);
        wd.quit(); // close the browser window
    }

    @Test
    void testTeamsPage() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/teams");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        System.out.println(title);
        String shortTitle = title.substring(0,13);
        String check = "List of Teams";
        assertEquals(check,shortTitle);
        wd.quit(); // close the browser window
    }

    @Test
    void testGamesPage() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/games");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        System.out.println(title);
        String shortTitle = title.substring(0,13);
        String check = "List of Games";
        assertEquals(check,shortTitle);
        wd.quit(); // close the browser window
    }

    @Test
    void testSpecificGame() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/specificGame.jsp?gameId=47179");

        WebElement we = wd.findElement(By.className("main-content"));
        String title = we.getText();
        System.out.println(title);
        String shortTitle = title.substring(0,10);
        String check = "BOS vs CHA";
        assertEquals(check,shortTitle);
        wd.quit(); // close the browser window
    }

    @Test
    void testSpecificTeam() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/specificTeam.jsp?teamId=1");

        WebElement we = wd.findElement(By.className("center"));
        String title = we.getText();
        System.out.println(title);
        String shortTitle = title.substring(0,7);
        String check = "Atlanta";
        assertEquals(check,shortTitle);
        wd.quit(); // close the browser window
    }

    @Test
    void testSpecificPlayer() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\School\\geckodriver.exe");

        WebDriver wd = new FirefoxDriver(); // launch the browser
// edit the next line to enter the location of "min.html" on your file system
        wd.get("http://basketballstats-web.appspot.com/specificPlayer.jsp?playerId=14");

        WebElement we = wd.findElement(By.className("center"));
        String title = we.getText();
        System.out.println(title);
        String shortTitle = title.substring(0,3);
        String check = "Ike";
        assertEquals(check,shortTitle);
        wd.quit(); // close the browser window
    }
}