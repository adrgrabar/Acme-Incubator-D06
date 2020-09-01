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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.date" path="date" readonly="true"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.type" path="type"/>
	<acme:form-money code="authenticated.investmentRound.form.label.moneyAmount" path="moneyAmount"/>
	<acme:form-url code="authenticated.investmentRound.form.label.moreInformation" path="moreInformation"/>
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
	<acme:form-submit method="get" code="authenticated.investmentRound.form.button.activities" action="../activity/list_by_ir?irId=${id}"/>
</acme:form>
