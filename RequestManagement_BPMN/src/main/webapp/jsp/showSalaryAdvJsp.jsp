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

<form action="applySalaryAdv" method="post" modelAttribute="userData">
<div align="right"><a href="<c:url value="/logout" />" >Logout</a></div>

<input type="hidden" name="username" value="${empName}">
<div align="left"> 
  <div style="padding-bottom: 5px;">
    <input type="text" name="salaryAdvance" placeholder="Salary advance" style=" border-radius: 5px;height: 42px; width: 327px;" >
   </div>
  <div>
  <select name="designation" style="border-radius: 5px;height: 42px;width: 327px;">
  <option value="manager">Manager</option>
  <option value="assistant">Assistant</option>
  </select>
  </div>
</div>
<div align="center" style=" padding-top: 25px;">
<input type="submit" value="Submit" style=" height: 25px; width: 100px;">
<input type="reset" value="Reset" style=" height: 25px; width: 100px;">
</div>
</form>
</body>
</html>