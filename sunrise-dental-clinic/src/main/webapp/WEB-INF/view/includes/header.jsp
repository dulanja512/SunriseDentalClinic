<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${ctx}/assets/app.css">
<div class="topbar">
  <div class="nav-wrap">
    <div class="brand">Sunrise Dental Clinic</div>
    <nav class="nav-links" aria-label="Main navigation">
      <a href="${ctx}/dashboard">Dashboard</a>
      <a href="${ctx}/appointments">Appointments</a>
      <a href="${ctx}/reports">Reports</a>
      <a href="${ctx}/help">Help</a>
      <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <a href="${ctx}/staff">Staff</a>
        <a href="${ctx}/logs">Audit Logs</a>
      </c:if>
    </nav>
    <div class="user-chip">
      <span>${sessionScope.user.fullName}</span>
      <span class="role-pill">${sessionScope.user.role}</span>
      <form class="logout-form" method="post" action="${ctx}/auth">
        <input type="hidden" name="action" value="logout">
        <button class="btn-ghost btn-sm" type="submit">Logout</button>
      </form>
    </div>
  </div>
</div>
