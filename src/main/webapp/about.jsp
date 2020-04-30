<%@ page import="java.net.*"%>
<%@ page import="java.net.URL"%>
<%@ page import="org.xml.sax.SAXException"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="org.json.simple.parser.JSONParser"%>
<%@ page import="org.json.simple.JSONObject"%>
<%@ page import="org.json.simple.JSONArray"%>
<%@ page import="java.util.Scanner"%>
<%@ page import="org.apache.commons.codec.binary.Base64"%>
<%@ page import="java.net.URI"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">

<%@ include file="../WEB-INF/generalHeader.jspf"%>

<body>

	<%@ include file="../WEB-INF/navbar.jspf"%>

	<main role="main" class="container">

		<div class="main-content">
			<h1>About our Page</h1>
		</div>

		<h3>Welcome to Basketball Stats! This purpose of this site is to
			provide statistics on all players, teams, and games in the history of
			the NBA. This information is intended for people of all ages with any
			level of interest in professional basketball.</h3>
		<br>

		<%@ include file="../WEB-INF/about/bios.jspf"%>

		<br> <a
			href="https://github.com/461L-morning-11/BasketballStats.git">
			Github repo</a> <br> <br>
		<h2>Team Stats</h2>
		<h3>Total Commits: ${fn:escapeXml(total_commits)}</h3>
		<h3>Total Issues Assigned: ${fn:escapeXml(total_issues)}</h3>
		<h3>Total Unit Tests: 16</h3>

		<br>

		<%@ include file="../WEB-INF/about/tools.jspf"%>


		<h3></h3>

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
