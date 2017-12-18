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

<form:form action="activityRecord/edit.do" modelAttribute="activityRecord">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="actor" />
	<form:hidden path="moment" />

	<acme:textarea code="activityRecord.description" path="description" />
	<br />

	<acme:textbox code="activityRecord.attachments" path="attachments" />
	<br />

	<jstl:choose>
		<jstl:when test="${activityRecord.id == 0}">
			<acme:submit name="save" code="activityRecord.save" />
			<acme:cancel code="activityRecord.cancel" url="activityRecord/myList.do" />
		</jstl:when>
		<jstl:otherwise>
			<acme:submit name="save" code="activityRecord.save" />
			<jstl:if test = "${subject.id != 0}">
				<input type="submit" name="delete"
					value="<spring:message code="activityRecord.delete" />" 
					onclick="return confirm('<spring:message code = "activityRecord.confirm.delete"/>')"/>
			</jstl:if>
			<acme:cancel code="activityRecord.cancel" url="activityRecord/myList.do" />
		</jstl:otherwise>
	</jstl:choose>

</form:form>