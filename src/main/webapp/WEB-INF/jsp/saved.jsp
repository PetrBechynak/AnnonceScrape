<%@ include file="header.jsp" %>

<h1>Saved contacts</h1>
<div>
    <form:form method="POST" action="/deleteAllSaved">
        <input type="submit" name="Delete all" value="Delete all records">
    </form:form>

    <form:form method="POST" action="/deleteSaved" modelAttribute="contactList">
        <input type="submit" name="Delete marked records" value="Delete marked records">
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
                <c:forEach items="${contactList.list}" var="dbContact" varStatus="status">
                    <tr>
                        <td>${dbContact.date}a</td>
                        <td>${dbContact.email}s</td>
                        <td class="textdescr">${dbContact.text}d</td>
                        <td>${dbContact.mobil}</td>
                        <td>${dbContact.profession}</td>
                        <td>${dbContact.domain}</td>
                        <td>${dbContact.region}</td>
                        <td>
                                <%--<form:checkbox path="contactList.list" />--%>
                            <form:checkbox path="contactList.list[${status.index}].toBeDeleted"/>
                                <%----%><input type="checkbox" id="items${dbContact.index}" name="maths"
                                               checked="checked" value="${dbContact.toBeDeleted}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </form:form>
</div>


<%@ include file="footer.jsp" %>