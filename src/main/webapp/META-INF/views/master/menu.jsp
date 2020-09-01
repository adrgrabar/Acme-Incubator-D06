<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.adrian-link" action="http://www.google.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.notice" action="/anonymous/notice/list"/>
			<acme:menu-suboption code="master.menu.anonymous.toolRecord" action="/anonymous/tool-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.technologyRecord" action="/anonymous/technology-record/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="!isAnonymous()">
			<acme:menu-suboption code="master.menu.authenticated.notice" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.authenticated.toolRecord" action="/authenticated/tool-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.technologyRecord" action="/authenticated/technology-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.inquiry" action="/authenticated/inquiry/list"/>
			<acme:menu-suboption code="master.menu.authenticated.overture" action="/authenticated/overture/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenge" action="/authenticated/challenge/list"/>
			<acme:menu-suboption code="master.menu.authenticated.investmentRound" action="/authenticated/investment-round/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.notice" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create"/>
			<acme:menu-suboption code="master.menu.administrator.toolRecord" action="/administrator/tool-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.toolRecord.create" action="/administrator/tool-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.technologyRecord" action="/administrator/technology-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.technologyRecord.create" action="/administrator/technology-record/create"/>
			<acme:menu-suboption code="master.menu.administrator.inquiry" action="/administrator/inquiry/list"/>
			<acme:menu-suboption code="master.menu.administrator.inquiry.create" action="/administrator/inquiry/create"/>
			<acme:menu-suboption code="master.menu.administrator.overture" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create"/>
			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound" action="/entrepreneur/investment-round/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.application" action="/entrepreneur/application/list_by_my_ir"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investmentRound.create" action="/entrepreneur/investment-round/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application" action="/investor/application/list_mine"/>
			<acme:menu-suboption code="master.menu.investor.investmentRound" action="/investor/investment-round/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

