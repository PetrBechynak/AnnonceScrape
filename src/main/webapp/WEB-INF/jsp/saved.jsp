<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link type="text/css" rel="stylesheet" href="<c:url value="/globalCSS.css" />" />
<html>
<head>
    <title>Contact Scraper</title>

</head>
<body>
<h1>Contact scraper</h1>
<ul class="navigation">
    <li class="navigation"><a href="/index">Home</a></li>
    <li class="navigation"><a class="active" href="/annonce">Scrape it!</a></li>
    <li class="navigation"><a href="/about">About</a></li>
</ul>

New url saved: ${annonceSettings.url} <br>
New number of desired records saved: ${annonceSettings.nrToScrape} <br>
New start at tab saved: ${annonceSettings.startAtTab} <br>
<a href ="/annonce">Go back</a>

</body>
</html>