<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset>

	<spring:message code="teacher.name" />
	:
	<jstl:out value="${teacher.name}"></jstl:out>
	<br>
	<spring:message code="teacher.surname" />
	:
	<jstl:out value="${teacher.surname}"></jstl:out>
	<br>
	<spring:message code="teacher.email" />
	:
	<jstl:out value="${teacher.email}"></jstl:out>
	<br>
	<spring:message code="teacher.phone" />
	:
	<jstl:out value="${teacher.phone}"></jstl:out>
	<br>
	<spring:message code="teacher.postalAddress" />
	:
	<jstl:out value="${teacher.postalAddress}"></jstl:out>
</fieldset>


	<%-- <security:authorize access="isAuthenticated()">
		<display:table name="activityRecords" id="row" requestURI="${requestURI}"
			pagesize="5" class="displaytag">
		
			<spring:message code="activityRecord.moment" var="momentHeader" />
			<display:column property="moment" title="${momentHeader}" />
		
			<spring:message code="activityRecord.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
		
			<spring:message code="activityRecord.attachments" var="attachmentsHeader" />
			<display:column property="attachments" title="${attachmentsHeader}" />
		</display:table>
	</security:authorize> --%>
	
	
	<security:authorize access="isAuthenticated()">
	<h3>	
		<spring:message code="teacher.activityRecords" />
	</h3>
	
	<display:table name="activityRecords" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="activityRecord.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="activityRecord.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="activityRecord.attachments" var="attachmentsHeader" />
	<display:column property="attachments" title="${attachmentsHeader}" />

	<security:authorize access="isAuthenticated()">
		<jstl:if test="${principal.id eq row.actor.id}">
			<display:column>
				<a href="activityRecord/edit.do?activityRecordId=${row.id}"> <spring:message
						code="activityRecord.edit"></spring:message></a>
			</display:column>
		</jstl:if>
	</security:authorize>
	</display:table>
	
	</security:authorize>