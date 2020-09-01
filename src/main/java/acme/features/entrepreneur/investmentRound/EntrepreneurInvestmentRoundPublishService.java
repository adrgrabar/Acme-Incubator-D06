
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundPublishService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		InvestmentRound ir;
		Integer irId;
		Principal principal;
		Entrepreneur entrepreneur;
		irId = request.getModel().getInteger("id");
		ir = this.repository.findOneById(irId);

		principal = request.getPrincipal();
		entrepreneur = ir.getEntrepreneur();
		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return !ir.getPublished() && result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		InvestmentRound result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		errors.state(request, this.repository.sumActivitiesOffer(entity.getId()) != null, "moneyAmount", "entrepreneur.investmentRound.error.activitiesNull");
		if (this.repository.sumActivitiesOffer(entity.getId()) != null) {
			errors.state(request, this.repository.sumActivitiesOffer(entity.getId()).equals(entity.getMoneyAmount().getAmount()), "moneyAmount", "entrepreneur.investmentRound.error.activitiesSum");
		}
	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		entity.setPublished(true);
		this.repository.save(entity);
	}
}
