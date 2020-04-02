
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSONObject;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.bson.Document;
import sun.net.www.http.HttpClient;

import java.net.URI;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import com.mongodb.MongoClient;


//import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;

public class espnAPI {

    public static void main(String[] args) throws Exception {

    }



    public static Long[] getCommits(String res) throws Exception{
        Long[] CorBarColHarChl = new Long[5];
        URL url = new URL(res);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }//System.out.println("\nJSON data in string format");//System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();

        JSONArray jarr = (JSONArray) parse.parse(inline);
        try {
            for (int j = 0; j < jarr.size(); j++) {
                JSONObject personObject = (JSONObject) jarr.get(j);
                System.out.println(personObject);
                Long totalCommits = (Long) personObject.get("total");
                JSONObject user = (JSONObject)  personObject.get("author");
                String curUser = (String) user.get("login");

                if (curUser.equals("coreykarnei")) {
                    CorBarColHarChl[0] = totalCommits;
                } else if (curUser.equals("colbyjanecka")) {
                    CorBarColHarChl[2] = totalCommits;
                } else if (curUser.equals("Barrett-S")) {
                    CorBarColHarChl[1] = totalCommits;
                } else if (curUser.equals("mikoyanhsch")) {
                    CorBarColHarChl[3] = totalCommits;
                } else if (curUser.equals("chloebryant")) {
                    CorBarColHarChl[4] = totalCommits;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CorBarColHarChl;

    }
    public static int[] getIssues(String res) throws Exception {

        int[] CorBarColHarChl = {0,0,0,0,0};
        URL url = new URL(res);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        String inline = "";
        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                inline += sc.nextLine();
            }//System.out.println("\nJSON data in string format");//System.out.println(inline);
            sc.close();
        }

        JSONParser parse = new JSONParser();

        JSONArray jarr = (JSONArray) parse.parse(inline);
        for(int i=0;i<jarr.size();i++) {
            JSONObject jobj = (JSONObject) jarr.get(i);
            JSONArray assignees = (JSONArray) jobj.get("assignees");
            for (int j = 0; j < assignees.size(); j++) {
                JSONObject user = (JSONObject) assignees.get(j);
                String curUser = (String) user.get("login");
                if(curUser.equals("coreykarnei")) {
                    CorBarColHarChl[0]++;
                }else if(curUser.equals("colbyjanecka")) {
                    CorBarColHarChl[2]++;
                }else if(curUser.equals("Barrett-S")) {
                    CorBarColHarChl[1]++;
                }else if(curUser.equals("mikoyanhsch")) {
                    CorBarColHarChl[3]++;
                }else if(curUser.equals("chloebryant")) {
                    CorBarColHarChl[4]++;
                }

            }

        }
        return CorBarColHarChl;
    }
}

