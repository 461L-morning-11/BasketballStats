<%@ page import="java.net.URL"%>
<%@ page import="org.xml.sax.SAXException"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="java.net.URI"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.cloud.sql.jdbc.Driver"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="icon" type="image/png" href="../img/basketball.png">
<!-- Bootstrap core CSS -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/custom-site.css" rel="stylesheet">

<title>Players</title>


</head>

<body>

	<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
		<a class="navbar-brand" href="/">Basketball Stats</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="http://example.com"
					id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Models</a>
					<div class="dropdown-menu" aria-labelledby="dropdown01">
						<a class="dropdown-item" href="/teams">Teams</a> <a
							class="dropdown-item" href="/players">Players</a> <a
							class="dropdown-item" href="/games">Games</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="/about">About</a>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0" method="post"
				action="search.jsp">
				<input class="form-control mr-sm-2" name="search" type="text"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<main role="main" class="container">


		<div class="main-content">
			<h1 class="title">List of Players</h1>

			<c:set var="pageNumber" value="${param.page}" />
			<c:if test="${pageNumber == null || pageNumber < 1}">
				<c:set var="pageNumber" value="1" />
			</c:if>

			<fmt:parseNumber var="pageInt" type="number" value="${pageNumber}" />

			<c:set var="startInt" value="${(pageInt * 27) - 27 }" />
			<c:set var="endInt" value="${(pageInt * 27)}" />

			<c:set var="sortBy" value="${param.sortBy}" />
			<c:if test="${sortBy == null}">
				<c:set var="sortBy" value="id" />
			</c:if>


			<!-- Sorting Setup -->
			<div class="btn-group">
				<button type="button" class="btn btn-success dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					Sort By</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="players.jsp?sortBy=id&page=${page}">Default</a>
					<a class="dropdown-item"
						href="players.jsp?sortBy=first_name&page=${page}">First Name</a> <a
						class="dropdown-item"
						href="players.jsp?sortBy=last_name&page=${page}">Last Name</a> <a
						class="dropdown-item"
						href="players.jsp?sortBy=team_name&page=${page}">Team</a>
				</div>
			</div>
			<hr>

			<sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver"
				url="jdbc:mysql://104.154.138.136/basketball_web" user="root"
				password="Sr4*8DNgZbvHqnee" />

			<sql:query dataSource="${dataSource}" var="result" maxRows="27">
  					SELECT * FROM players ORDER BY ${sortBy} LIMIT ${startInt}, ${endInt};
				</sql:query>


			<div class="container">
				<div class="row">

					<c:forEach var="row" items="${result.rows}">
						<div class="col-md-4">
							<div class="card mb-4 shadow-sm text-white bg-dark">
								<a class="itemCardLink"
									href="specificPlayer.jsp?playerId=${row.id}">
									<div class="card-body">
										<h5 class="card-text">
											<c:out value="${row.first_name}" />
											<c:out value="${row.last_name}" />
										</h5>
										<h6 class="card-text">
											<c:out value="${row.team_name}" />
										</h6>
										<div class="d-flex justify-content-between align-items-center">
										</div>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>


			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">

					<li
						class="page-item <c:if test = "${pageInt == 1}"> disabled </c:if>">
						<a class="page-link" href="players.jsp?page=${page-1}"
						tabindex="-1" aria-disabled="false">Previous</a>
					</li>
					<c:if test="${pageInt > 3}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt-3}">${pageInt-3}</a></li>
					</c:if>
					<c:if test="${pageInt > 2}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt-2}">${pageInt-2}</a></li>
					</c:if>
					<c:if test="${pageInt > 1}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt-1}">${pageInt-1}</a></li>
					</c:if>
					<li class="page-item disabled"><a class="page-link" href="#">${pageNumber}</a></li>
					<c:if test="${pageInt < 33}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt+1}">${pageInt+1}</a></li>
					</c:if>
					<c:if test="${pageInt < 32}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt+2}">${pageInt+2}</a></li>
					</c:if>
					<c:if test="${pageInt < 31}">
						<li class="page-item"><a class="page-link"
							href="players.jsp?sortBy=${sortBy}&page=${pageInt+3}">${pageInt+3}</a></li>
					</c:if>
					<li
						class="page-item <c:if test = "${pageInt == 33}"> disabled </c:if>">
						<a class="page-link" href="players.jsp?page=${page+1}">Next</a>
					</li>
				</ul>
			</nav>
		</div>

	</main>
	<!-- /.container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>
