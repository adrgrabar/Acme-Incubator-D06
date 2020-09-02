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

<acme:form readonly="${published}">
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.description" path="description"/>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" placeholder="XXX-00-000000"/>
	<jstl:if test="${command!='create'}">
		<acme:form-moment code="entrepreneur.investmentRound.form.label.date" path="date" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.type" path="type"/>
	<acme:form-money code="entrepreneur.investmentRound.form.label.moneyAmount" path="moneyAmount"/>
	<acme:form-url code="entrepreneur.investmentRound.form.label.moreInformation" path="moreInformation"/>
	<jstl:if test="${command!='create'}">
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.published" path="published" readonly="true"/>
	</jstl:if>
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
	<jstl:if test="${command!='create'}">
		<acme:form-submit method="get" code="entrepreneur.investmentRound.form.button.activities" action="../activity/list_by_ir?irId=${id}"/>
		<jstl:if test="${!published}">
			<acme:form-submit code="entrepreneur.investmentRound.form.button.publish" action="../investment-round/publish"/>
			<acme:form-submit method="get" code="entrepreneur.investmentRound.form.button.activities.create" action="../activity/create?irId=${id}"/>
		</jstl:if>
		<acme:form-submit method="get" code="entrepreneur.investmentRound.form.button.applications" action="../application/list_by_ir?irId=${id}"/>
	</jstl:if>
	<acme:form-submit test="${command=='show' && !published}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command=='show' && !published}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command=='create'}" code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment-round/create"/>
	<acme:form-submit test="${command=='update'}" code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${command=='delete'}" code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
</acme:form>
