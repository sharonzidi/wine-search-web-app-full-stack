<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="findwines" method="post">
		<h1>Search for a Wine by Winery Name</h1>
		<p>
			<label for="wineryName">Winery Name</label>
			<input id="wineryName" name="wineryName" value="${fn:escapeXml(param.wineryName)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	
	<h1>Matching Wines</h1>
        <table border="1">
            <tr>
                <th>WineTitle</th>
                <th>WineId</th>
                <th>Country</th>
                <th>Price</th>
                <th>Designation</th>
                <th>Winery</th>
            </tr>
            <c:forEach items="${wine1}" var="w" >
                <tr>
                    <td><c:out value="${w.getWineTitle()}" /></td>
                    <td><c:out value="${w.getWineId()}" /></td>
                    <td><c:out value="${w.getCountry()}" /></td>
                    <td><c:out value="${w.getPrice()}" /></td>
                    <td><c:out value="${w.getDesignation()}" /></td>
                    <td><c:out value="${w.getWinery()}" /></td>
                    
                   
                    <%-- <td><a href="userblogposts?username=<c:out value="${blogUser.getUserName()}"/>">BlogPosts</a></td>
                    <td><a href="blogcomments?username=<c:out value="${blogUser.getUserName()}"/>">BlogComments</a></td>
                    <td><a href="userdelete?username=<c:out value="${blogUser.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${blogUser.getUserName()}"/>">Update</a></td> --%>
                </tr>
            </c:forEach>
       </table>
</body>
</html>