<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<jstl:set var="isPublished" value="${investmentRound.getPublished()}" />
<acme:form readonly="${isPublished}">
	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.activity.form.label.startDate" path="startDate"/>
	<acme:form-moment code="entrepreneur.activity.form.label.endDate" path="endDate"/>
	<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget"/>
	<acme:form-return code="entrepreneur.activity.form.button.return"/>
	<acme:form-submit test="${command=='show' && !isPublished}" code="entrepreneur.activity.form.button.update" action="/entrepreneur/activity/update"/>
	<acme:form-submit test="${command=='show' && !isPublished}" code="entrepreneur.activity.form.button.delete" action="/entrepreneur/activity/delete"/>
	<acme:form-submit test="${command=='create'}" code="entrepreneur.activity.form.button.create" action="/entrepreneur/activity/create?irId=${irId}"/>
	<acme:form-submit test="${command=='update'}" code="entrepreneur.activity.form.button.update" action="/entrepreneur/activity/update"/>
	<acme:form-submit test="${command=='delete'}" code="entrepreneur.activity.form.button.delete" action="/entrepreneur/activity/delete"/>
</acme:form>
