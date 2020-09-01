<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.inquiry.list.label.title" path="title" width="35%"/>
	<acme:list-column code="administrator.inquiry.list.label.description" path="description" width="65%"/>
</acme:list>