<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>Login - Sunrise Dental Clinic</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/app.css">
</head>
  <body>
    <div class="login-shell">
    <div class="login-card">
    <div class="login-mark">✚</div><h1>Sunrise Dental Clinic</h1>
     <p class="muted">Secure staff portal</p>
      <c:if test="${not empty error}">
    <div class="alert alert-error">
      <c:out value="${error}"/></div></c:if>
   <form method="post" action="${pageContext.request.contextPath}/auth"><input type="hidden" name="action" value="login">
    <div class="field"><label>Username</label>
    <input name="username" autocomplete="username" required autofocus placeholder="Enter username"></div>
    <div class="field"><label>Password</label>
<input type="password" name="password" autocomplete="current-password" required placeholder="Enter password">
</div><button style="width:100%" type="submit">Sign in</button></form>
<p class="helper" style="margin-top:18px">Demo administrator: <strong>admin</strong> / <strong>admin123</strong>
</p>
</div>
</div></body></html>
