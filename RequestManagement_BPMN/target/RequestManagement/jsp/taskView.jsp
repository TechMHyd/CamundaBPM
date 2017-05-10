<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="completeTask" method="post" modelAttribute="user">
<c:if test="${ not empty userList }">
<h2 align="center">Task Assigned to you are:</h2>
<table class="table" align="center">
				<thead>
				  <tr>				 					
					<th >Employee Name: </th>
					<th>Salary Advance</th>
					<th></th>					
				  </tr>
				</thead>
				<tbody>				
				 <c:forEach items="${userList}" var="user">
				 <input type="hidden" name="taskId" value="<c:out value="${user.taskId}"/>"/>	
		         <tr>
		         	<%-- <td><input type="checkbox" name="taskList" value="<c:out value='${task.taskId}'/>"/></td> --%>		         	
		            <td><c:out value="${user.username}"/></td>	
		            <td><c:out value="${user.salaryAdvance}"/></td>	            
		        </tr>
		        <input type="hidden" name="executionId"  value="<c:out value="${user.executionId}"/>"/>	
    			</c:forEach>
    			<tr>
    			
    			</tr>    			
				</tbody>
			  </table>
<div align="center" style=" padding-top: 25px;">
<input type="submit" value="Approve" style=" height: 25px; width: 100px;">

</div>	
</c:if>

<c:if test="${ empty userList }">
There are no pending approvals
</c:if>

</form>
</body>
</html>