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

<acme:form>
	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.overture.form.label.description" path="description"/>
	<acme:form-textbox code="administrator.overture.form.label.email" path="email"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.overture.form.label.date" path="date" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.overture.form.label.deadline" path="deadline"/>
	<acme:form-money code="administrator.overture.form.label.moneyStart" path="moneyStart"/>
	<acme:form-money code="administrator.overture.form.label.moneyEnd" path="moneyEnd"/>
	<acme:form-return code="administrator.overture.form.button.return"/>
	<acme:form-submit test="${command=='show'}" code="administrator.overture.form.button.update" action="/administrator/overture/update"/>
	<acme:form-submit test="${command=='show'}" code="administrator.overture.form.button.delete" action="/administrator/overture/delete"/>
	<acme:form-submit test="${command=='create'}" code="administrator.overture.form.button.create" action="/administrator/overture/create"/>
	<acme:form-submit test="${command=='update'}" code="administrator.overture.form.button.update" action="/administrator/overture/update"/>
	<acme:form-submit test="${command=='delete'}" code="administrator.overture.form.button.delete" action="/administrator/overture/delete"/>
</acme:form>
