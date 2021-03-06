
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "date");
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "moneyStart", "moneyEnd", "email");
	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		Inquiry result;
		result = new Inquiry();
		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline().after(new Date(System.currentTimeMillis())), "deadline", "administrator.inquiry.error.deadline");
		}
		if (!errors.hasErrors("moneyStart") && !errors.hasErrors("moneyEnd")) {
			errors.state(request, entity.getMoneyStart().getAmount() < entity.getMoneyEnd().getAmount(), "moneyEnd", "administrator.inquiry.error.money");
		}
		if (!errors.hasErrors("moneyStart")) {
			errors.state(request, entity.getMoneyStart().getCurrency().equals("EUR") || entity.getMoneyStart().getCurrency().equals("€"), "moneyStart", "administrator.inquiry.error.euro");
		}
		if (!errors.hasErrors("moneyEnd")) {
			errors.state(request, entity.getMoneyEnd().getCurrency().equals("EUR") || entity.getMoneyEnd().getCurrency().equals("€"), "moneyEnd", "administrator.inquiry.error.euro");
		}
	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;
		Date date;
		date = new Date(System.currentTimeMillis() - 1);
		entity.setDate(date);
		this.repository.save(entity);
	}
}
