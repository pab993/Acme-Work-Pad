<%--
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->

<security:authorize access="isAuthenticated()">

	<div>
		<h2>
			<a href="activityRecord/create.do"> <spring:message
					code="activityRecord.create"></spring:message></a>
		</h2>
	</div>
</security:authorize>

<display:table name="activityRecords" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

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
