<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1">
<title>Billing - Sunrise Dental</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<main class="container">
  <div class="page-head">
    <div><h1>Patient Bill / Receipt</h1><div class="muted">Treatment price is taken directly from the selected treatment. No hidden consultation fee is added.</div></div>
    <a class="btn btn-light" href="${pageContext.request.contextPath}/appointments">Back</a>
  </div>
  <jsp:include page="includes/flash.jsp"/>
  <c:if test="${not empty error}"><div class="alert alert-error"><c:out value="${error}"/></div></c:if>

  <c:if test="${not empty bill}">
    <section class="panel receipt" style="max-width:760px;margin-left:auto;margin-right:auto">
      <div class="receipt-head">
        <div><h2>Sunrise Dental Clinic</h2><div class="muted">Official treatment receipt</div></div>
        <div class="receipt-number"><span class="muted">Bill No.</span><strong>#${bill.billId}</strong></div>
      </div>

      <c:if test="${not empty appointment}">
        <div class="receipt-info grid grid-3">
          <div><span class="muted">Patient</span><strong><c:out value="${appointment.patient.fullName}"/></strong></div>
          <div><span class="muted">Appointment</span><strong><c:out value="${appointment.appointmentNumber}"/></strong></div>
          <div><span class="muted">Treatment</span><strong><c:out value="${appointment.treatment.treatmentName}"/></strong></div>
        </div>
      </c:if>

      <div class="table-wrap">
        <table style="min-width:0">
          <thead><tr><th>Description</th><th style="text-align:right">Amount</th></tr></thead>
          <tbody>
            <tr><td>Treatment Cost</td><td style="text-align:right">Rs. <fmt:formatNumber value="${bill.treatmentCost}" minFractionDigits="2" maxFractionDigits="2"/></td></tr>
            <c:if test="${bill.consultationFee gt 0}"><tr><td>Additional Consultation Fee</td><td style="text-align:right">Rs. <fmt:formatNumber value="${bill.consultationFee}" minFractionDigits="2" maxFractionDigits="2"/></td></tr></c:if>
            <c:if test="${bill.discount gt 0}"><tr><td>Discount</td><td style="text-align:right">- Rs. <fmt:formatNumber value="${bill.discount}" minFractionDigits="2" maxFractionDigits="2"/></td></tr></c:if>
            <tr class="total-row"><th>Total Payable</th><th style="text-align:right">Rs. <fmt:formatNumber value="${bill.totalAmount}" minFractionDigits="2" maxFractionDigits="2"/></th></tr>
          </tbody>
        </table>
      </div>
      <div class="receipt-footer"><span>Pricing: <c:out value="${bill.pricingStrategy}"/></span><button type="button" onclick="window.print()">Print Receipt</button></div>
    </section>
  </c:if>
</main>
</body>
</html>
