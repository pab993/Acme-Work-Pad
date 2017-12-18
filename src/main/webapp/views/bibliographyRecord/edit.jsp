<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="bibliographyRecord/edit.do" modelAttribute="bibliographyRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="subject" />

	<acme:textbox code="bibliographyRecord.title" path="title" />
	<br />
	
	<acme:textbox code="bibliographyRecord.authors" path="authors" />
	<br />
	
	<acme:textbox code="bibliographyRecord.locator" path="locator" />
	<br />
	
	

	
	<jstl:choose>
		<jstl:when test="${bibliographyRecord.id == 0}">
			<acme:submit name="save" code="bibliographyRecord.submit" />
			<acme:cancel code="bibliographyRecord.cancel" url="bibliographyRecord/list.do?subjectId=${bibliographyRecord.subject.id}" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="bibliographyRecord.submit" />
			<acme:submit name="delete" code="bibliographyRecord.delete" />
			<acme:cancel code="bibliographyRecord.cancel" url="bibliographyRecord/list.do?subjectId=${bibliographyRecord.subject.id}" />
		</jstl:otherwise>
	</jstl:choose>

</form:form>