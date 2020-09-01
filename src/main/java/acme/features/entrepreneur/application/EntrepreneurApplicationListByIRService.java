
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurApplicationListByIRService implements AbstractListService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
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

		return result && ir.getPublished();
	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;
		Collection<Application> result;
		Integer irId;
		irId = request.getModel().getInteger("irId");
		result = this.repository.findByIRId(irId);
		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "investmentRound.title", "status");
	}
}
