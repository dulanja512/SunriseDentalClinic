<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Staff Management - Sunrise Dental</title></head><body>
<jsp:include page="includes/header.jsp"/>
<main class="container">
  <div class="page-head"><div><h1>Staff Management</h1><div class="muted">Create accounts, control access and reset staff passwords.</div></div></div>
  <jsp:include page="includes/flash.jsp"/>
  <section class="panel">
    <div class="section-title"><h2>Create Staff Account</h2><span class="muted">Admin only</span></div>
    <form method="post" action="${pageContext.request.contextPath}/staff" autocomplete="off">
      <input type="hidden" name="action" value="create">
      <div class="grid grid-3">
        <div class="field"><label>Full name</label><input name="fullName" maxlength="100" required placeholder="e.g. Nimal Perera"></div>
        <div class="field"><label>Username</label><input name="username" maxlength="30" pattern="[A-Za-z0-9._-]{3,30}" required placeholder="e.g. nimal.perera"><div class="helper">3-30 letters, numbers, dot, dash or underscore.</div></div>
        <div class="field"><label>Email</label><input type="email" name="email" maxlength="120" placeholder="name@example.com"></div>
        <div class="field"><label>Role</label><select name="role" required><option value="STAFF">Staff</option><option value="ADMIN">Administrator</option></select></div>
        <div class="field"><label>Temporary password</label><input type="password" name="password" pattern=".{8,}" title="Password must contain at least 8 characters" required placeholder="Minimum 8 characters"><div class="helper">Give this password securely to the staff member.</div></div>
        <div class="field" style="align-self:end"><button type="submit">Create Staff Account</button></div>
      </div>
    </form>
  </section>

  <section class="panel">
    <div class="section-title"><h2>Existing Staff</h2><span class="muted">${fn:length(staff)} account(s)</span></div>
    <div class="table-wrap"><table><thead><tr><th>Name</th><th>Username</th><th>Role</th><th>Email</th><th>Status</th><th>Account actions</th></tr></thead><tbody>
    <c:forEach var="s" items="${staff}"><tr>
      <td><strong><c:out value="${s.fullName}"/></strong><c:if test="${s.userId eq sessionScope.user.userId}"> <span class="muted">(you)</span></c:if></td>
      <td><c:out value="${s.username}"/></td>
      <td><span class="badge ${s.role eq 'ADMIN' ? 'badge-blue' : 'badge-amber'}">${s.role}</span></td>
      <td><c:out value="${empty s.email ? '-' : s.email}"/></td>
      <td><span class="badge ${s.active ? 'badge-green' : 'badge-red'}">${s.active ? 'Active' : 'Inactive'}</span></td>
      <td><div class="actions">
        <c:if test="${s.userId ne sessionScope.user.userId}"><form method="post" action="${pageContext.request.contextPath}/staff"><input type="hidden" name="action" value="toggle"><input type="hidden" name="id" value="${s.userId}"><input type="hidden" name="active" value="${not s.active}"><button class="btn-sm ${s.active ? 'btn-danger' : ''}" type="submit">${s.active ? 'Deactivate' : 'Activate'}</button></form></c:if>
        <form class="inline-form" method="post" action="${pageContext.request.contextPath}/staff"><input type="hidden" name="action" value="resetPassword"><input type="hidden" name="id" value="${s.userId}"><input type="password" name="newPassword" pattern=".{8,}" title="Password must contain at least 8 characters" required placeholder="New password"><button class="btn-secondary btn-sm" type="submit">Reset</button></form>
      </div></td>
    </tr></c:forEach>
    </tbody></table></div>
  </section>
</main></body></html>
