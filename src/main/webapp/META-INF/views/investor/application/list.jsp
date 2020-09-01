<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="35%"/>
	<acme:list-column code="investor.application.list.label.investmentRoundTitle" path="investmentRound.title" width="45%"/>
	<acme:list-column code="investor.application.list.label.status" path="status" width="20%"/>
</acme:list>