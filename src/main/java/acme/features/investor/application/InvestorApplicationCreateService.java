
package acme.features.investor.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		InvestmentRound ir;
		Integer irId;

		irId = request.getModel().getInteger("irId");
		ir = this.repository.findOneIrByIrId(irId);

		return ir.getPublished();
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "date", "investmentRound", "investor", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("irId", request.getModel().getInteger("irId"));
		request.unbind(entity, model, "ticker", "statement", "offer");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;
		InvestmentRound ir;
		Integer irId;
		Investor investor;
		Principal principal;

		irId = request.getModel().getInteger("irId");
		ir = this.repository.findOneIrByIrId(irId);
		principal = request.getPrincipal();
		investor = this.repository.findOneInvestorById(principal.getActiveRoleId());

		result = new Application();
		result.setInvestmentRound(ir);
		result.setInvestor(investor);
		result.setStatus("PENDING");
		Date date = new Date(System.currentTimeMillis() - 100);
		result.setDate(date);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("offer")) {
			errors.state(request, entity.getOffer().getCurrency().equals("EUR") || entity.getOffer().getCurrency().equals("€"), "offer", "investor.application.error.euro");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
