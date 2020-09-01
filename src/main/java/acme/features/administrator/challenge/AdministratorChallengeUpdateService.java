
package acme.features.administrator.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorChallengeUpdateService implements AbstractUpdateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "rookieGoal", "rookieReward", "averageGoal", "averageReward", "expertGoal", "expertReward");
	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		Challenge result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("rookieReward")) {
			errors.state(request, entity.getRookieReward().getCurrency().equals("EUR") || entity.getRookieReward().getCurrency().equals("€"), "rookieReward", "administrator.challenge.error.euro");
		}
		if (!errors.hasErrors("averageReward")) {
			errors.state(request, entity.getAverageReward().getCurrency().equals("EUR") || entity.getAverageReward().getCurrency().equals("€"), "averageReward", "administrator.challenge.error.euro");
		}
		if (!errors.hasErrors("expertReward")) {
			errors.state(request, entity.getExpertReward().getCurrency().equals("EUR") || entity.getExpertReward().getCurrency().equals("€"), "expertReward", "administrator.challenge.error.euro");
		}
		if (!errors.hasErrors("rookieReward") && !errors.hasErrors("averageReward")) {
			errors.state(request, entity.getRookieReward().getAmount() < entity.getAverageReward().getAmount(), "rookieReward", "administrator.challenge.error.bigAmount");
		}
		if (!errors.hasErrors("expertReward") && !errors.hasErrors("averageReward")) {
			errors.state(request, entity.getExpertReward().getAmount() > entity.getAverageReward().getAmount(), "expertReward", "administrator.challenge.error.littleAmount");
		}
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline().after(new Date(System.currentTimeMillis())), "deadline", "administrator.challenge.error.deadline");
		}
	}

	@Override
	public void update(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
