
package acme.features.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateStatusService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		InvestmentRound ir;
		Integer applicationId;
		Application application;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		ir = application.getInvestmentRound();

		Principal principal;
		Entrepreneur entrepreneur;

		principal = request.getPrincipal();
		entrepreneur = ir.getEntrepreneur();
		result = principal.getAccountId() == entrepreneur.getUserAccount().getId();

		return result && application.getStatus().equals("PENDING");
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		Application result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (request.getModel().getString("newStatus").equals("REJECTED") && !errors.hasErrors("justification")) {
			errors.state(request, entity.getJustification().length() > 10, "justification", "entrepreneur.application.error.justificationMissing");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus(request.getModel().getString("newStatus"));
		this.repository.save(entity);
	}
}
