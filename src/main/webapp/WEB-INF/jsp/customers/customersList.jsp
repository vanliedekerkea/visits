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
    <h2>Customers</h2>
    
    <datatables:table id="customers" data="${selections}" row="customer" theme="bootstrap2" 
                      cssClass="table table-striped" pageable="false" info="false" export="pdf">
        <datatables:column title="Name" cssStyle="width: 150px;" display="html">
            <spring:url value="/customers/{customerId}.html" var="customerUrl">
                <spring:param name="customerId" value="${customer.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(customerUrl)}"><c:out value="${customer.firstName} ${customer.lastName}"/></a>
        </datatables:column>
        <datatables:column title="Name" display="pdf">
            <c:out value="${customer.firstName} ${customer.lastName}"/>
        </datatables:column>
        <datatables:column title="Address" property="address" cssStyle="width: 200px;"/>
        <datatables:column title="City" property="city"/>
        <datatables:column title="Telephone" property="telephone"/>
        <datatables:column title="Pets" cssStyle="width: 100px;">
            <c:forEach var="pet" items="${customer.pets}">
                <c:out value="${pet.name}"/>
            </c:forEach>
        </datatables:column>
        <datatables:export type="pdf" cssClass="btn" cssStyle="height: 25px;" />
    </datatables:table>
    
    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
