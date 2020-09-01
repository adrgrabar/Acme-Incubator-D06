
package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurActivityListByIRService implements AbstractListService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		boolean result;
		InvestmentRound ir;
		Integer irId;

		irId = request.getModel().getInteger("irId");
		ir = this.repository.findOneIRById(irId);

		Principal principal;
		Entrepreneur entrepreneur;

		principal = request.getPrincipal();
		entrepreneur = ir.getEntrepreneur();
		result = principal.getAccountId() == entrepreneur.getUserAccount().getId();

		return result;
	}

	@Override
	public Collection<Activity> findMany(final Request<Activity> request) {
		assert request != null;
		Collection<Activity> result;
		Integer investmentRoundId = request.getModel().getInteger("irId");
		result = this.repository.findByInvestmentRound(investmentRoundId);
		return result;
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startDate");
	}
}
