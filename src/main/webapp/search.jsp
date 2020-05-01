<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!doctype html>
<html lang="en">

<%@ include file="../WEB-INF/generalHeader.jspf"%>

<body>

	<%@ include file="../WEB-INF/navbar.jspf"%>

	<main role="main" class="container">

		<%@ include file="../WEB-INF/search/searchPlayers.jspf"%>

		<%@ include file="../WEB-INF/search/searchTeams.jspf"%>

		<%@ include file="../WEB-INF/search/searchGames.jspf"%>

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