
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "moneyStart", "moneyEnd", "email");
	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		Overture result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline().after(new Date(System.currentTimeMillis())), "deadline", "administrator.inquiry.error.deadline");
		}
		if (!errors.hasErrors("moneyStart") || !errors.hasErrors("moneyEnd")) {
			errors.state(request, entity.getMoneyStart().getAmount() < entity.getMoneyEnd().getAmount(), "moneyEnd", "administrator.inquiry.error.money");
		}
		if (!errors.hasErrors("moneyStart")) {
			errors.state(request, entity.getMoneyStart().getCurrency().equals("EUR") || entity.getMoneyStart().getCurrency().equals("€"), "moneyStart", "administrator.overture.error.euro");
		}
		if (!errors.hasErrors("moneyEnd")) {
			errors.state(request, entity.getMoneyEnd().getCurrency().equals("EUR") || entity.getMoneyEnd().getCurrency().equals("€"), "moneyEnd", "administrator.overture.error.euro");
		}
	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
