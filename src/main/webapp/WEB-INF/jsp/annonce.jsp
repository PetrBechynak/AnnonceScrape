<%@ include file="header.jsp" %>

<div class="annonce">
    Annonce settings
    <form:form method="POST" action="/saveannoncesettings" modelAttribute="annosett">
        <table>
            <tr>
                <td><form:label path="url">url</form:label></td>
                <td><form:input path="url" size="160" value="${annonceSettings.url}"/></td>
                <td><form:errors path="url" cssclass="error">Url cannot be empty!</form:errors></td>
            </tr>
            <tr>
                <td><form:label path="nrToScrape">nr to Scrape</form:label></td>
                <td><form:input path="nrToScrape" size="4" value="${annonceSettings.nrToScrape}"/></td>
                <td><form:errors path="nrToScrape" cssclass="error">Must be from 1 to 1000</form:errors></td>
            </tr>
            <tr>
                <td><form:label path="startAtTab">start at tab</form:label></td>
                <td><form:input path="startAtTab" size="4" value="${annonceSettings.startAtTab}"/></td>
                <td><form:errors path="startAtTab" cssclass="error">Must be from 1 to 100</form:errors></td>

            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save settings"/>
                </td>
            </tr>
        </table>
    </form:form>


</div>

<form:form method="POST" action="/scrapeannonce">
    <input type="submit" name="scrapeIt" value="Scrape it!">
</form:form>

<div>
    <table>
        <tr>
            <th>date</th>
            <th>email</th>
            <th class="textdescr">text</th>
            <th>mobil</th>
            <th>profession</th>
            <th>domain</th>
            <th>region</th>
            <th>action</th>
        </tr>
        <c:forEach items="${contactList}" var="myContact" varStatus="status">
            <tr>
                <td>${myContact.date}</td>
                <td>${myContact.email}</td>
                <td class="textdescr">${myContact.text}</td>
                <td>${myContact.mobil}</td>
                <td>${myContact.profession}</td>
                <td>${myContact.domain}</td>
                <td>${myContact.region}</td>
                <td>
                    <form action="main.jsp" method="POST" target="_blank">
                        <input type="checkbox" name="maths" checked="checked"/> Accept
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="footer.jsp" %>