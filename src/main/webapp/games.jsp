<%@ page import="java.net.URL" %>
<%@ page import="org.xml.sax.SAXException" %> 
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.net.URI"%>
<%@ page import="java.time.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*" %>
<%@ page import="com.google.cloud.sql.jdbc.Driver" %>
<%@ page import="java.sql.*" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="../img/basketball.png">

    <title>Coaches</title>

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
			<h1>List of Games</h1>
        		
        		<%
		    	// pagination ------------------------
		       	String pageNumber = request.getParameter("page");
		  	    if(pageNumber == null) {
		  	    	pageNumber = "1";
		  	    }
		  	    int pageInt = Integer.parseInt(pageNumber);
		  		pageContext.setAttribute("page", pageInt);
		  		
		  		
		  		// sorting ---------------------------
		  		int startInt = (pageInt * 27) - 26;
		  		int endInt = pageInt * 27;
		       
		       String sortBy = request.getParameter("sortBy");
		  	    if(sortBy == null) {
		  	    	sortBy = "id";
		  	    }
		  		pageContext.setAttribute("sortBy", sortBy);
		  		
		       
		       %>
		       <!-- Sorting Setup -->
				<div class="btn-group">
	 				<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	   					Sort By
	 				</button>
					<div class="dropdown-menu">
		  				<a class="dropdown-item" href="games.jsp?sortBy=id&page=${page}">Default</a>
		  				<a class="dropdown-item" href="games.jsp?sortBy=first_name&page=${page}">First Name</a>
		  				<a class="dropdown-item" href="games.jsp?sortBy=last_name&page=${page}">Last Name</a>
	  					<a class="dropdown-item" href="games.jsp?sortBy=team_name&page=${page}">Team</a>
					</div>
				</div>
				<hr>
        
		        
		<% 
		
			String db="basketball_web";
			String user = "root";
			String pass="Sr4*8DNgZbvHqnee";
			String ip="104.154.138.136";
			
	   		try {
	   			
	   			System.out.println("trying to query from sql database;");
			   	Class.forName("com.mysql.cj.jdbc.Driver");
			   	String host = "jdbc:mysql://" + ip + ":3306/" + db;
			   	Connection c = DriverManager.getConnection(
		        	host,
		        	user,
		        	pass
		        );
			   	
		
		   		Statement statement = c.createStatement();
		   		
		   		ResultSet rs = statement.executeQuery("SELECT * FROM games LIMIT " + startInt + ", " + endInt);
		   		
		   		%>
				<div class="container">
			<div class="row">
				<% 
				for(int i=0;i<25;i++)
				{
		   			rs.next();
		

					pageContext.setAttribute("game_home_team", rs.getString("home_name"));
					pageContext.setAttribute("game_visitor_team", rs.getString("visitor_name"));
					pageContext.setAttribute("home_id", rs.getString("home_team_id"));
					pageContext.setAttribute("visitor_id", rs.getString("visitor_team_id"));
					
					
			   		System.out.println("getting data for game " + rs.getString("id"));
			   		
			        String shortDate = (String) rs.getString("date");
				    
				    pageContext.setAttribute("game_date", shortDate.substring(5, 10));


					pageContext.setAttribute("game_home_score", rs.getString("home_team_score"));
					
					pageContext.setAttribute("game_visitor_score", rs.getString("visitor_team_score"));
					
					
					pageContext.setAttribute("game_ID", rs.getString("id"));
					pageContext.setAttribute("home_logo", "../img/logos/" + rs.getString("home_name") + ".png");
					pageContext.setAttribute("visitor_logo", "../img/logos/" + rs.getString("visitor_name") + ".png");
			   		
		   		
				
					
					
				%>
					<div class="col-md-4">
						<div class="card mb-4 shadow-sm">
						<a class="itemCardLink" href="specificGame.jsp?gameId=${game_ID}">
								<img src="${fn:escapeXml(visitor_logo)}" class="img-fluid img-thumbnail" alt="Responsive image">
								<img src="${fn:escapeXml(home_logo)}" class="img-fluid img-thumbnail" alt="Responsive image">
								<div class="card-body">
									<p class="boldP"> ${fn:escapeXml(game_visitor_team)} @ ${fn:escapeXml(game_home_team)}</p>
									<p class="card-text"> ${fn:escapeXml(game_visitor_score)} - ${fn:escapeXml(game_home_score)}</p>
									<div class="d-flex justify-content-between align-items-center">
									</div>
								</div>
							</a>
						</div>
					</div>
				<% 
				}
				
			   	
	   		}
	   		
	   		catch(Exception e) {
	   			e.printStackTrace();
	   		}%>
			
		
		
        
      </div>
		</div>
	</div>
      
      <nav aria-label="Page navigation">
		<ul class="pagination justify-content-center">
			
			<li class="page-item <% if(pageInt == 1){ %> disabled <% } %>">
				<a class="page-link" href="games.jsp?page=${page-1}" tabindex="-1" aria-disabled="false">Previous</a>
			</li>
			<% if(pageInt > 3){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page-3}">${page-3}</a></li>
			<% } %>
			<% if(pageInt > 2){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page-2}">${page-2}</a></li>
			<% } %>
			<% if(pageInt > 1){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page-1}">${page-1}</a></li>
			<% } %>
	 		<li class="page-item disabled"><a class="page-link" href="#">${page}</a></li>
			<% if(pageInt < 488){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page+1}">${page+1}</a></li>
			<% } %>
			<% if(pageInt < 487){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page+2}">${page+2}</a></li>
			<% } %>
			<% if(pageInt < 486){ %>
			<li class="page-item"><a class="page-link" href="games.jsp?page=${page+3}">${page+3}</a></li>
			<% } %>
			<li class="page-item <% if(pageInt == 488){ %> disabled <% } %>">
				<a class="page-link" href="games.jsp?page=${page+1}">Next</a>
			</li>
		</ul>
	</nav>

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
