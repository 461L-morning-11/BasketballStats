<%@ page import="java.net.*" %>
<%@ page import="java.net.http.HttpClient" %>
<%@ page import="java.net.http.HttpRequest" %>
<%@ page import="java.net.http.HttpResponse" %>
<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.net.URI"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

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
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
      </div>
    </nav>

    <main role="main" class="container">

      <div class="main-content">
        <h1>About our Page</h1>
      </div>
      
      <h3>Welcome to Basketball Stats! This purpose of this site is to provide statistics on all players, coaches, and teams in the history of the NBA and WNBA. (ADD INTENDED USERS)  </h3>
      <br>
      <h2>Meet the developers</h2>
      <br>
      <%!
      public static String get(String uri) throws Exception {
          HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create(uri))
                  .GET()
                  .build();

          HttpResponse<String> response = client.send(request,
                  HttpResponse.BodyHandlers.ofString());
          return response.body();


      }
      public static String getNewSHA(String res) throws Exception {
          JSONParser parse = new JSONParser();

          JSONObject jobj = (JSONObject)parse.parse(res);
          JSONObject obj=(JSONObject)jobj.get("object");
          Thread.sleep(100);
		  String ret=""+obj.get("sha");
          return ret;
      }
      public static int getTotalCommits(String res) throws Exception{
          /*JSONParser parse = new JSONParser();

          JSONObject jobj = (JSONObject)parse.parse(res);
          Long total=(Long) (jobj.get("total_commits"));
          return total.intValue() +1;
          */
          return getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=colbyjanecka"))+getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=barrett-s"))+getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=chloebryant"))+getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=coreykarnei"))+getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=mikoyanhsch"));

      }
      public static int getUserCommits(String res) throws Exception{
    	  JSONParser parse = new JSONParser();
          //JSONObject jsonarr_1 = (JSONObject)parse.parse(res);
          JSONArray jsonarr_1 = (JSONArray) parse.parse(res);
          int userCommits=0;
          for(int i=0;i<jsonarr_1.size();i++) {
            userCommits++;
          }
          return userCommits;
      }
      public static int getTotalIssues(String res) throws Exception {
          
    	  JSONParser parse = new JSONParser();

          JSONObject jobj = (JSONObject)parse.parse(res);
          Long total=(Long) (jobj.get("total_count"));
          if(total!=null)
          return total.intValue();
          else
        	  return 0;
          
          
      }
      %> <% 
      String res= get("https://api.github.com/repos/461L-morning-11/BasketballStats/git/refs/heads/master");
      //String newSHA= getNewSHA(res);
      //pageContext.setAttribute("total_commits",getTotalCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/compare/98284fc9903dacfc123c6bb131d3d500ad75dad7..."+newSHA)));
      pageContext.setAttribute("colby_commits",getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=colbyjanecka")));
      pageContext.setAttribute("corey_commits",getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=coreykarnei")));
      pageContext.setAttribute("barrett_commits",getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=barrett-s")));
      pageContext.setAttribute("chloe_commits",getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=chloebryant")));
      pageContext.setAttribute("harry_commits",getUserCommits(get("https://api.github.com/repos/461L-morning-11/BasketballStats/commits?author=mikoyanhsch")));
      pageContext.setAttribute("total_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats")));
      pageContext.setAttribute("colby_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats+author:colbyjanecka")));
      pageContext.setAttribute("corey_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats+author:coreykarnei")));
      pageContext.setAttribute("barrett_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats+author:barrett-s")));
      pageContext.setAttribute("chloe_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats+author:chloebryant")));
      pageContext.setAttribute("harry_issues",getTotalIssues(get("https://api.github.com/search/issues?q=repo:461L-morning-11/BasketballStats+author:mikoyanhsch")));
      
      %>
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Barrett.jpg" alt="Picture of Barrett">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Barrett Stricklin</h4>
    		<p class="card-text">Commits: ${fn:escapeXml(barrett_commits)} <br>Issues:${fn:escapeXml(barrett_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/chloe.jpg" class="img-responsive" alt="Picture of Chloe">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Chloe Bryant</h4>
    		<p class="card-text">Commits: ${fn:escapeXml(chloe_commits)} <br>Issues:${fn:escapeXml(chloe_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Colby.jpg" class="img-responsive" alt="Picture of Colby">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Colby Janecka</h4>
    		<p class="card-text">Commits: ${fn:escapeXml(colby_commits)} <br>Issues:${fn:escapeXml(colby_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Harry.jpg" class="img-responsive" alt="Picture of Harry">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Harry Schneider</h4>
    		<p class="card-text">Commits: ${fn:escapeXml(harry_commits)} <br>Issues:${fn:escapeXml(harry_issues)}</p>
      	</div>
      </div>
      
      <br>
      
      <div class="card" style="width:400px">
      	<div class="embed-responsive embed-responsive-1by1">
      		<img class="card-img-top embed-responsive-item" src="../img/Corey.jpg" class="img-responsive" alt="Picture of Corey">
      	</div>
      	<div class="card-body">
      		<h4 class="card-title">Corey Karnei</h4>
    		<p class="card-text">Commits: ${fn:escapeXml(corey_commits)} <br>Issues:${fn:escapeXml(corey_issues)}</p>
      	</div>
      </div>
      
      <br>
 	  <h2>Team Stats</h2>
	  <h3>Total Commits:${fn:escapeXml(total_commits)}</h3>
	  <h3>Total Issues:${fn:escapeXml(total_issues)}</h3>
	  <h3>Total Unit Tests: 0</h3>
	  
	  <br>
	  
	  <h2>Data</h2>
	  <h3>add link to api and expain how it was scraped</h3>
	  
	  <br>
	  
	  <h2>Tools</h2>
	  <h3>desribe tools and how they were used</h3>
	  
	  <br>
      <a href="https://github.com/461L-morning-11/BasketballStats.git"> Link to Github repo</a>
		
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
