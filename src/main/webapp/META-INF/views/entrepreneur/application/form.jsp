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
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.date" path="date" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	<acme:form-money code="entrepreneur.application.form.label.offer" path="offer" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investmentRoundTicker" path="investmentRound.ticker" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investmentRoundTitle" path="investmentRound.title" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.status" path="status" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.justification" path="justification"/>
	<acme:form-return code="entrepreneur.application.form.button.return"/>
	<jstl:if test="${status=='PENDING'}">
		<acme:form-submit code="entrepreneur.application.form.button.accept" action="../application/update_status?newStatus=ACCEPTED"/>
		<acme:form-submit code="entrepreneur.application.form.button.reject" action="../application/update_status?newStatus=REJECTED"/>
	</jstl:if>
</acme:form>
