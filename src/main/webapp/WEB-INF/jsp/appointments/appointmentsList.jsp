<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h2>Appointments</h2>
    
    <datatables:table id="appointments" data="${selections}" row="appointment" theme="bootstrap2" 
                      cssClass="table table-striped" pageable="false" info="false">
        <datatables:column title="Description" cssStyle="width: 150px;" display="html">
        	<p>${appointment.description}</p>
        </datatables:column>
        <datatables:column title="Date" cssStyle="width: 150px;" display="html">
        	<p>${appointment.dateTime}</p>
        </datatables:column>
    </datatables:table>    
    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
