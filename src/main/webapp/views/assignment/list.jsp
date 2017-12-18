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

<security:authorize access="hasRole('TEACHER')">

	<div>
		<jstl:if test="${ own eq true }">
		<h2>
			<a href="assignment/create.do?subjectId=${subject.id}"> <spring:message
					code="assignment.create"></spring:message></a>
		</h2>
		</jstl:if>
	</div>
</security:authorize>

<display:table name="assignments" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="assignment.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="assignment.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" />

	<spring:message code="assignment.startDate" var="startDateHeader" />
	<display:column property="startDate" title="${startDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="assignment.endDate" var="endDateHeader" />
	<display:column property="endDate" title="${endDateHeader}" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="assignment.information" var="informationHeader" />
	<%-- <display:column property="information" title="${informationHeader}" /> --%>
	<display:column title="${informationHeader}">
		<jstl:if test="${row.information != null }">
			<a href="${row.information }"> ${row.information}</a>
		</jstl:if>
		<jstl:if test="${row.information == null }"></jstl:if>
	</display:column>

	<security:authorize access="hasRole('TEACHER')">

		<jstl:if test="${principal.id eq row.subject.teacher.id}">
			<display:column>
				<a href="assignment/edit.do?assignmentId=${row.id}"> <spring:message
						code="assignment.edit"></spring:message></a>
			</display:column>
		</jstl:if>
	</security:authorize>


</display:table>
