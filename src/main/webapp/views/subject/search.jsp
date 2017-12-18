<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<jstl:if test="${withSeats}">
	<form:form action="subject/searchWithSeats.do">

	<input type="text" name="keyword" />
	<input type="submit" name="searchWithSeats"
		value="<spring:message code = "subject.search" />" />
	</form:form>
</jstl:if>

<jstl:if test="${!withSeats}">
	<form:form action="subject/searchWithoutSeats.do">

	<input type="text" name="keyword" />
	<input type="submit" name="searchWithoutSeats"
		value="<spring:message code = "subject.search" />" />
	</form:form>
</jstl:if>

<jstl:if test="${!firstTime}">
		
	<display:table name = "subjects" id = "row" requestURI="subject/searchForm.do" pagesize = "10" class = "displaytag" >

		<spring:message code="subject.title" var="headerTag" />
		<display:column property="title" title="${headerTag}" />
	
		<spring:message code="subject.ticker" var="headerTag" />
		<display:column property="ticker" title="${headerTag}" />
	
		<spring:message code="subject.syllabus" var="headerTag" />
		<display:column property="syllabus" title="${headerTag}" />
	
		<spring:message code="subject.seats" var="headerTag" />
		<display:column property="seats" title="${headerTag}" />
			
	</display:table>
</jstl:if>