<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Dashboard - Sunrise Dental</title></head><body>
<jsp:include page="includes/header.jsp"/>
<main class="container">
  <div class="page-head"><div><h1>Welcome, <c:out value="${sessionScope.user.fullName}"/></h1><div class="muted">Here is today’s clinic overview.</div></div><a class="btn" href="${pageContext.request.contextPath}/appointments">+ New Appointment</a></div>
  <jsp:include page="includes/flash.jsp"/>
  <div class="grid ${sessionScope.user.role eq 'ADMIN' ? 'grid-3' : 'grid-2'}">
    <div class="stat"><div class="label">Today’s appointments</div><div class="num">${stats.todayAppointments}</div></div>
    <div class="stat"><div class="label">Total appointments</div><div class="num">${stats.totalAppointments}</div></div>
    <c:if test="${sessionScope.user.role eq 'ADMIN'}"><div class="stat"><div class="label">Active staff accounts</div><div class="num">${activeStaff}</div></div></c:if>
  </div>

  <section class="panel" style="margin-top:20px"><div class="section-title"><h2>Quick Actions</h2></div><div class="quick-links">
    <a class="quick-link" href="${pageContext.request.contextPath}/appointments"><strong>Register appointment</strong><span class="helper">Add a patient and reserve a dentist time slot.</span></a>
    <a class="quick-link" href="${pageContext.request.contextPath}/reports"><strong>View reports</strong><span class="helper">Review clinic appointment activity.</span></a>
    <c:choose><c:when test="${sessionScope.user.role eq 'ADMIN'}"><a class="quick-link" href="${pageContext.request.contextPath}/staff"><strong>Manage staff</strong><span class="helper">Create, activate and maintain staff accounts.</span></a></c:when><c:otherwise><a class="quick-link" href="${pageContext.request.contextPath}/help"><strong>Staff help</strong><span class="helper">Open the workflow and usage guide.</span></a></c:otherwise></c:choose>
  </div></section>

  <section class="panel"><div class="section-title"><h2>Upcoming / Recent Appointments</h2><a class="btn btn-light btn-sm" href="${pageContext.request.contextPath}/appointments">View all</a></div>
    <c:choose><c:when test="${empty recentAppointments}"><div class="empty">No appointments have been registered yet.</div></c:when><c:otherwise><div class="table-wrap"><table><thead><tr><th>Appointment</th><th>Patient</th><th>Dentist</th><th>Date &amp; Time</th><th>Status</th></tr></thead><tbody><c:forEach var="a" items="${recentAppointments}"><tr><td><c:out value="${a.appointmentNumber}"/></td><td><c:out value="${a.patient.fullName}"/></td><td><c:out value="${a.dentist.fullName}"/></td><td>${a.appointmentDate} ${a.appointmentTime}</td><td><span class="badge ${a.status eq 'BOOKED' ? 'badge-blue' : (a.status eq 'COMPLETED' ? 'badge-green' : 'badge-red')}">${a.status}</span></td></tr></c:forEach></tbody></table></div></c:otherwise></c:choose>
  </section>
</main></body></html>
