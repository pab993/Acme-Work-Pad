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

<div>
	<a href="socialIdentity/create.do">
		<spring:message code="socialIdentity.create" />
	</a>
</div>
<br />

<display:table name="socialIdentities" pagesize="5" class="displaytag" requestURI="${requestURI}" id="row">
	
	<spring:message code="socialIdentity.photo" var="headerTag" />
	<display:column style="width:48px" title="${headerTag}"><img height="48" width="48" src="${row.photo}"></display:column>
	
	<spring:message code="socialIdentity.nick" var="headerTag" />
	<display:column property="nick" title="${headerTag}"/>
	
	<spring:message code="socialIdentity.socialNetwork" var="headerTag" />
	<display:column property="socialNetwork" title="${headerTag}"/>
	
	<spring:message code="socialIdentity.link" var="informationTag" />
	<display:column title="${informationHeader}">
		<jstl:if test="${row.link != null }">
			<a href="${row.link }"> ${row.link}</a>
		</jstl:if>
		<jstl:if test="${row.link == null }"></jstl:if>
	</display:column>
	
	<spring:message code="socialIdentity.edit" var="headerTag" />
	<display:column title="${headerTag}">
			<a href="socialIdentity/edit.do?socialIdentityId=${row.id}">
				<spring:message code="socialIdentity.edit" />
			</a>
	</display:column>
	
	<spring:message code="socialIdentity.delete" var="headerTag" />
	<display:column title="${headerTag}">
			<a href="socialIdentity/delete.do?socialIdentityId=${row.id}">
				<input type="submit" name="delete"
				value="<spring:message code="socialIdentity.delete" />"
				onclick="return confirm('<spring:message code="socialIdentity.confirm.delete" />')" />&nbsp;
			</a>
	</display:column>
		
</display:table>