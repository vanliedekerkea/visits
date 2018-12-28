<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../../fragments/bodyHeader.jsp"/>
    <h2>Appointments</h2>
    
    <c:if test="${not empty daysOfWeek}">
		<ul>
			<c:forEach var="listValue" items="${daysOfWeek}">
				<li><fmt:formatDate value="${listValue}" pattern="dd-MM-yyyy"/></li>
			</c:forEach>
		</ul>
	</c:if>
    <jsp:include page="../../fragments/footer.jsp"/>

</div>
</body>

</html>
