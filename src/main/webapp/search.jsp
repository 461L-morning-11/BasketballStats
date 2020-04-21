<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" href="../img/basketball.png">

    <title>Search Results</title>

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
    
    <%
    String db="basketball_web";
	String user = "root";
	String pass="Sr4*8DNgZbvHqnee";
	String ip="104.154.138.136";
	
    try{
    	System.out.println("trying to query from sql database;");
	   	Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	   	String host = "jdbc:mysql://" + ip + ":3306/" + db;
	   	Connection c = DriverManager.getConnection(
        	host,
        	user,
        	pass
        );
	   	
	   	String Query = "SELECT * FROM players WHERE MATCH(first_name, last_name, team_name, team_conference, position) AGAINST( '"+request.getParameter("search")+"')";
	   	Statement stm = c.createStatement();
	   	ResultSet rs = stm.executeQuery(Query);
	   	%>
	   	<table  class="table table-hover table-sm">
	   		<thead>
	   			<tr>
	   				<th scope="col">Players</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   	<% 
	   	while(rs.next()){
	   		pageContext.setAttribute("player_id", rs.getString("id"));
	   		%>
	   			<tr onclick="window.location='specificPlayer.jsp?playerId=${player_id}';">
	   				<td><%=rs.getString("first_name") %></td>
	   				<td><%=rs.getString("last_name") %></td>	   			
	   			</tr>
	   		<%} %>
	   		</tbody>
		</table>
		<br>
		
		<table  class="table table-hover table-sm">
	   		<thead>
	   			<tr>
	   				<th scope="col">Teams</th>
	   			</tr>
	   		</thead>
	   		<tbody>
		
	    <% 
	   	String Query2 = "SELECT * FROM teams WHERE MATCH(abbreviation, city, conference, division, full_name, short_name) AGAINST('"+request.getParameter("search")+"')";
	   	ResultSet rs2 = stm.executeQuery(Query2);
	   	while(rs2.next()){
	   		pageContext.setAttribute("team_ID", rs2.getString("id"));
	   	%>
	   		<tr onclick="window.location='specificTeam.jsp?teamId=${team_ID}';">
	   			<td><%=rs2.getString("full_name") %></td>	   			
	   		</tr>
	   		<%} %>
	   		</tbody>
		</table>
		<br>
	   	
	   	<table  class="table table-hover table-sm">
	   		<thead>
	   			<tr>
	   				<th scope="col">Games</th>
	   			</tr>
	   		</thead>
	   		<tbody>
	   	<% 
	   	String Query3 = "SELECT * FROM games WHERE MATCH(home_name, visitor_name) AGAINST ('"+request.getParameter("search")+"')";
	   	ResultSet rs3 = stm.executeQuery(Query3);
	   	while(rs3.next()){
	   		pageContext.setAttribute("game_ID", rs3.getString("id"));
	   		%>
	   	
				<tr onclick="window.location='specificGame.jsp?gameId=${game_ID}';">
   					<td><%=rs3.getDate("date") %></td>		  				<td><%=rs3.getString("home_name") %></td>
		  			<td><%=rs3.getInt("home_team_score") %></td>
		  			<td><%=rs3.getString("visitor_name") %></td>
		  			<td><%=rs3.getInt("visitor_team_score") %></td>
				</tr>
	   		<%} %>
	   		</tbody>
		</table>
	  <%  
    }
    catch(Exception ex){
    	ex.printStackTrace();
    }
    %>
    
    
   
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