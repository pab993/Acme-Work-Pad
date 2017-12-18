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
		<h2>
			<a href="seminary/create.do"> <spring:message code="seminary.create"></spring:message></a>
		</h2>
	</div>
</security:authorize>

<display:table name="seminaries" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="seminary.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="seminary.abstractSeminary" var="abstractSeminaryHeader" />
	<display:column property="abstractSeminary" title="${abstractSeminaryHeader}" />

	<spring:message code="seminary.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" format="{0,date,dd/MM/yyyy HH:mm}"  />

	<spring:message code="seminary.duration" var="durationHeader" />
	<display:column property="duration" title="${durationHeader}" />

	<spring:message code="seminary.hall" var="hallHeader" />
	<display:column property="hall" title="${hallHeader}" />

	<spring:message code="seminary.seats" var="seatsHeader" />
	<display:column property="seats" title="${seatsHeader}" />

	<security:authorize access="hasRole('TEACHER')">

		<display:column>
			<a href="seminary/edit.do?seminaryId=${row.id}"> <spring:message
					code="seminary.edit"></spring:message></a>
		</display:column>

	</security:authorize>

</display:table>
