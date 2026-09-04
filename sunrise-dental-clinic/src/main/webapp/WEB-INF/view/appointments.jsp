```````<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Appointments - Sunrise Dental</title></head><body>
<jsp:include page="includes/header.jsp"/>
<main class="container">
  <div class="page-head"><div><h1>Appointments</h1><div class="muted">Register patients, manage bookings and find existing appointment records.</div></div></div>
  <jsp:include page="includes/flash.jsp"/>

  <section class="panel"><div class="section-title"><h2>Register New Appointment</h2><span class="muted">All required fields are marked *</span></div>
    <form method="post" action="${pageContext.request.contextPath}/appointments">
      <input type="hidden" name="action" value="create">
      <div class="grid grid-3">
        <div><div class="field"><label>Patient name *</label><input name="name" maxlength="100" required placeholder="Full name"></div><div class="field"><label>Address *</label><input name="address" maxlength="255" required placeholder="Home address"></div><div class="field"><label>Contact number *</label><input name="contact" maxlength="20" pattern="[0-9]{10}" inputmode="numeric" required placeholder="10 digits"></div><div class="field"><label>Email</label><input type="email" name="email" maxlength="120" placeholder="Optional"></div></div>
        <div><div class="field"><label>Dentist *</label><select name="dentistId" required><option value="">Choose dentist</option><c:forEach var="d" items="${dentists}"><option value="${d.dentistId}"><c:out value="${d.fullName}"/> - <c:out value="${d.specialization}"/></option></c:forEach></select></div><div class="field"><label>Treatment *</label><select name="treatmentId" required><option value="">Choose treatment</option><c:forEach var="t" items="${treatments}"><option value="${t.treatmentId}"><c:out value="${t.treatmentName}"/> - Rs. ${t.baseCost}</option></c:forEach></select></div></div>
        <div><div class="field"><label>Date *</label><input type="date" name="date" min="${today}" required></div><div class="field"><label>Time *</label><input type="time" name="time" required></div><button type="submit">Create Appointment</button><div class="helper">The system checks past dates and dentist double-booking automatically.</div></div>
      </div>
    </form>
  </section>

  <section class="panel"><div class="section-title"><h2>Find Appointment</h2></div><form class="grid grid-3" method="post" action="${pageContext.request.contextPath}/appointment-search"><div class="field" style="grid-column:span 2"><label>Appointment number</label><input name="appointmentNumber" maxlength="30" required placeholder="APT-YYYYMMDD-XXXXX"></div><div class="field" style="align-self:end"><button type="submit">Search Record</button></div></form></section>

  <section class="panel"><div class="section-title"><h2>All Appointments</h2><input id="appointmentFilter" style="max-width:280px" placeholder="Filter table..." aria-label="Filter appointments"></div>
    <c:choose><c:when test="${empty appointments}"><div class="empty">No appointments found. Create the first appointment above.</div></c:when><c:otherwise><div class="table-wrap"><table id="appointmentTable"><thead><tr><th>No.</th><th>Patient</th><th>Dentist</th><th>Treatment</th><th>Date</th><th>Time</th><th>Status</th><th>Actions</th></tr></thead><tbody>
      <c:forEach var="a" items="${appointments}"><tr><td><strong><c:out value="${a.appointmentNumber}"/></strong></td><td><c:out value="${a.patient.fullName}"/></td><td><c:out value="${a.dentist.fullName}"/></td><td><c:out value="${a.treatment.treatmentName}"/></td><td>${a.appointmentDate}</td><td>${a.appointmentTime}</td><td><span class="badge ${a.status eq 'BOOKED' ? 'badge-blue' : (a.status eq 'COMPLETED' ? 'badge-green' : 'badge-red')}">${a.status}</span></td><td><div class="actions"><form method="post" action="${pageContext.request.contextPath}/appointment-search"><input type="hidden" name="appointmentNumber" value="${a.appointmentNumber}"><button class="btn-light btn-sm" type="submit">View</button></form><c:if test="${a.status eq 'BOOKED'}"><form method="post" action="${pageContext.request.contextPath}/appointments"><input type="hidden" name="id" value="${a.appointmentId}"><button class="btn-sm" name="action" value="complete" type="submit">Complete</button><button class="btn-danger btn-sm" name="action" value="cancel" type="submit">Cancel</button></form></c:if></div></td></tr></c:forEach>
    </tbody></table></div></c:otherwise></c:choose>
  </section>
</main>
<script>const f=document.getElementById('appointmentFilter');const t=document.getElementById('appointmentTable');if(f&&t){f.addEventListener('input',()=>{const q=f.value.toLowerCase();t.querySelectorAll('tbody tr').forEach(r=>r.style.display=r.innerText.toLowerCase().includes(q)?'':'none')})}</script>
</body></html>
