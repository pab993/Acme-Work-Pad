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

<%-- <div>
	<jstl:if test="${errorMessage != null}">
		<br />
		<span class="message" style="color:red"><spring:message code="${errorMessage}" /></span>
	</jstl:if>
</div> --%>

<security:authorize access="hasRole('TEACHER')">

	<div>
		<jstl:if test="${ own eq true }">
		<h2>
			<a href="bibliographyRecord/create.do?subjectId=${subject.id}"> <spring:message
					code="bibliographyRecord.create"></spring:message></a>
		</h2>
		</jstl:if>
	</div>
</security:authorize>

<display:table name="bibliographyRecords" id="row"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="bibliographyRecord.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="bibliographyRecord.authors" var="authorsHeader" />
	<display:column property="authors" title="${authorsHeader}" />

	<spring:message code="bibliographyRecord.locator" var="locatorHeader" />
	<display:column property="locator" title="${locatorHeader}" />

	<security:authorize access="hasRole('TEACHER')">
	
	<jstl:if test="${ own eq true }">
		<display:column>
			<a href="bibliographyRecord/edit.do?bibliographyRecordId=${row.id}"> <spring:message
					code="bibliographyRecord.edit"></spring:message></a>
		</display:column>
</jstl:if>
	</security:authorize>

</display:table>