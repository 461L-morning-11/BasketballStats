<%@ page import="java.net.*" %>
<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="java.net.URI"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="../img/basketball.png">

    <title>About</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/custom-site.css" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="/">Basketball Stats</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Models</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="/teams">Teams</a>
              <a class="dropdown-item" href="/players">Players</a>
              <a class="dropdown-item" href="/games">Games</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/about">About</a>
          </li>
        </ul>
    	<form class="form-inline my-2 my-lg-0" method="post" action="search.jsp">
          <input class="form-control mr-sm-2" name="search" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

      <div class="main-content">
        <h1>About our Page</h1>
      </div>
      
      <h3>Welcome to Basketball Stats! This purpose of this site is to provide statistics on all players, teams, and games in the history of the NBA. This information is intended for people of all ages with any level of interest in professional basketball. </h3>
      <br>
      <h2>Meet the developers</h2>
      <br>
      <%!
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
      
      
      
      %> <%
      String commitsURL = "https://api.github.com/repos/461L-morning-11/BasketballStats/stats/contributors";
      String issuesURL = "https://api.github.com/repos/461L-morning-11/BasketballStats/issues";
      
      Long[] commits = new Long[5];
	  int[] issues = {0,0,0,0,0};
	  
      try{
      //both arrays VV have the following indecies: corey-0 barrett-1 colby-2 harry-3 chloe-4
     commits = getCommits(commitsURL);
     issues = getIssues(issuesURL);
      }catch(Exception e){
    	  e.printStackTrace();
    	  //if github returns a "forbidden" error on api call, default to saved values
    	  commits[2] = new Long(52); 
    	  commits[0] = new Long(47); 
    	  commits[1] = new Long(7); 
    	  commits[4] = new Long(14); 
    	  commits[3] = new Long(3);
    	  issues[2] = 0; 
    	  issues[0] = 2; 
    	  issues[1] = 1; 
    	  issues[4] = 0; 
    	  issues[3] = 0;
      }
      
      //String newSHA= getNewSHA(res);
      //pageContext.setAttribute("total_commits",getTotalCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/compare/98284fc9903dacfc123c6bb131d3d500ad75dad7..."+newSHA)));
      pageContext.setAttribute("colby_commits", commits[2]);
      pageContext.setAttribute("corey_commits",commits[0]);
      pageContext.setAttribute("barrett_commits",commits[1]);
      pageContext.setAttribute("chloe_commits",commits[4]);
      pageContext.setAttribute("harry_commits",commits[3]);
     //pageContext.setAttribute("total_issues",getTotalIssues("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats"));
      pageContext.setAttribute("colby_issues",issues[2]);
      pageContext.setAttribute("corey_issues",issues[0]);
      pageContext.setAttribute("barrett_issues",issues[1]);
      pageContext.setAttribute("chloe_issues",issues[4]);
      pageContext.setAttribute("harry_issues",issues[3]);
      pageContext.setAttribute("total_issues",issues[0]+issues[1]+issues[2]+issues[3]+issues[4]);
      pageContext.setAttribute("total_commits",commits[0]+ commits[1]+commits[2]+commits[3]+commits[4]);
      %>
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Barrett.jpg" alt="Picture of Barrett">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Barrett Stricklin</h4>
    		<p class="card-text">Barrett is a junior studying Software Engineering at The University of Texas at Austin with experience in cybersecurity. He enjoys the outdoors and spending money on his home pc/audio setup. <br> Commits: ${fn:escapeXml(barrett_commits)} <br>Assigned Issues: ${fn:escapeXml(barrett_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/chloe.jpg" class="img-responsive" alt="Picture of Chloe">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Chloe Bryant</h4>
    		<p class="card-text">Chloe is a third year ECE major at UT from Houston, TX with a technical core in software engineering. In her free time, she enjoys playing guitar and being outdoors.<br> Commits: ${fn:escapeXml(chloe_commits)} <br>Assigned Issues: ${fn:escapeXml(chloe_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Colby.jpg" class="img-responsive" alt="Picture of Colby">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Colby Janecka</h4>
    		<p class="card-text">Colby is an ECE major with a focus in Data Science, from Waco, TX, and enjoys making music. <br> Commits: ${fn:escapeXml(colby_commits)} <br>Assigned Issues: ${fn:escapeXml(colby_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Corey.jpg" class="img-responsive" alt="Picture of Corey">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Corey Karnei</h4>
    		<p class="card-text">Corey is an electrical engineering major at UT with a minor in business. Born and Raised in Waco, TX, he enjoys competition and the outdoors.<br>Commits: ${fn:escapeXml(corey_commits)} <br>Assigned Issues: ${fn:escapeXml(corey_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Harry.jpg" class="img-responsive" alt="Picture of Harry">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Harry Schneider</h4>
    		<p class="card-text">Harry is a senior Computer Engineering major at UT with a specialty towards software and network security. He is currently employed at Cisco Systems and his hobbies include building ship models and guns. <br>Commits: ${fn:escapeXml(harry_commits)} <br>Assigned Issues: ${fn:escapeXml(harry_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <a href="https://github.com/461L-morning-11/BasketballStats.git"> Github repo</a>
      <br>
      <br>
 	  <h2>Team Stats</h2>
	  <h3>Total Commits: ${fn:escapeXml(total_commits)}</h3>
	  <h3>Total Issues Assigned: ${fn:escapeXml(total_issues)}</h3>
	  <h3>Total Unit Tests: 16</h3>
	  
	  <br>
	  
	  <h2>Data</h2>
	  <p><a href= "https://www.balldontlie.io/#get-a-specific-team"> Ball Don't Lie API</a>
	  <br>We used the API linked above to gather data about players, teams, and games. A call to this API enabled us to extract and display specific information regarding each player, team, and game in the NBA's past and present. We store this data in our database where it is accessed when a user loads a page.
	  		We collected stats about player' season average and teams' runs at the NBA Championships in addition to more basic info like name, position, team, ect.</p>
	  
	  <br>
	  
	  <h2>Tools</h2>
	  <p>We used the BallDon'tLie API and the Github statistics API to dynamically source content for our site. We also added json-simple and apache-commons JAR files to facilitate our desired functionality. We used the built-in google SQL service to store our data and the MySQL Workbench to see and alter talbes and columns during development. The website is hosted on an AWS Beanstalk environment generated from a .war file. As for the appearance, we used built in functions made available through Bootstrap to organize and display the acquired data throughout the site.</p>
	  
	  <br>
      
      
      <h3></h3>
		
    </main><!-- /.container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>
