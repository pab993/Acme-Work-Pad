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

<div>
	<jstl:if test="${errorMessage != null}">
		<br />
		<span class="message" style="color:red"><spring:message code="${errorMessage}" /></span>
	</jstl:if>
</div>

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

	<security:authorize access="hasRole('STUDENT')">
	<display:column>
		<jstl:if test="${!studentSeminary.contains(row)}">
		
			<a href="seminary/student/register.do?seminaryId=${row.id}"> <spring:message
					code="seminary.register"></spring:message></a>
		
		</jstl:if>
		</display:column>
		<display:column>
		<jstl:if test="${studentSeminary.contains(row)}">
		
			<a href="seminary/student/unregister.do?seminaryId=${row.id}"> <spring:message
					code="seminary.unregister"></spring:message></a>
		</jstl:if>
		</display:column>
	</security:authorize>

</display:table>