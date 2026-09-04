<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty sessionScope.flashMessage}">
  <div class="alert ${sessionScope.flashType eq 'error' ? 'alert-error' : 'alert-success'}">
    <c:out value="${sessionScope.flashMessage}" />
  </div>
  <c:remove var="flashMessage" scope="session"/>
  <c:remove var="flashType" scope="session"/>
</c:if>
<c:if test="${not empty error}"><div class="alert alert-error"><c:out value="${error}" /></div></c:if>
<c:if test="${not empty message}"><div class="alert alert-success"><c:out value="${message}" /></div></c:if>
