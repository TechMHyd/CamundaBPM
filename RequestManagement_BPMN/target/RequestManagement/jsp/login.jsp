<html>
<body>
<h2 align="center">LogIn</h2>

<form method="post" action="loginCheck" modelAttribute="user">
<div align="center"> 
  <div style="padding-bottom: 5px;">
    <input type="text" name="username" placeholder="Username" style=" border-radius: 5px;
    height: 42px;
    width: 327px;" f>
   </div>
  <div>
  <input type="password" name="password" placeholder="Password" style="border-radius: 5px;height: 42px;width: 327px;">
  </div>
</div>
<div align="center" style=" padding-top: 25px;">
<input type="submit" value="LogOn" style=" height: 25px; width: 100px;">
<input type="reset" value="Reset" style=" height: 25px; width: 100px;">
</div>

</form>
</body>
</html>
