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

<form:form action="subject/edit.do" modelAttribute="subject">

	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<form:hidden path="groups" />	
	<form:hidden path="bibliographyRecords" />	
	<form:hidden path="assignments" />	
	
	<form:hidden path="students" />	
	<form:hidden path="ticker" />	
	<form:hidden path="administrator" />	
	
	<jstl:choose>
		<jstl:when test="${subject.id == 0}">
			<acme:select items="${teachers}" itemLabel="name" code="subject.teacher" path="teacher"/>
		</jstl:when>
		<jstl:otherwise>
			<form:hidden path="teacher" />
		</jstl:otherwise>
	</jstl:choose>
	<br />
	
	<acme:textbox code="subject.title" path="title" mandatory="true"/>
	<br />

	<acme:textbox code="subject.syllabus" path="syllabus" mandatory="true"/>
	<br />

	<acme:textbox code="subject.seats" path="seats" mandatory="true"/>
	<br />

	<acme:submit name="save" code="subject.save" />
	
	<jstl:if test = "${subject.id != 0}">
	<input type="submit" name="delete"
		value="<spring:message code="subject.delete" />" 
		onclick="return confirm('<spring:message code = "subject.confirm.delete"/>')"/>
	</jstl:if>
	
	<acme:cancel code="subject.cancel" url="subject/list.do" />

</form:form>