
package acme.features.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

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
		result = entrepreneur.getId() == principal.getActiveRoleId();

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

		request.unbind(entity, model, "ticker", "type", "title", "description", "moneyAmount", "moreInformation");
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
		if (!errors.hasErrors("moneyAmount")) {
			errors.state(request, entity.getMoneyAmount().getCurrency().equals("EUR") || entity.getMoneyAmount().getCurrency().equals("€"), "moneyAmount", "entrepreneur.investmentRound.error.euro");
		}
		if (!errors.hasErrors("description")) {

			String spamWords = this.repository.getSpamWords();

			List<String> palabrasSpam = Arrays.asList(spamWords.split(", "));

			Double spamThresholdPercent = this.repository.getSpamThreshold();

			String description = entity.getDescription();

			String[] descWords = description.replace(",", " ").replace(".", " ").replace("  ", " ").split(" ");

			List<String> listDescWords = Arrays.asList(descWords);

			List<String> finishedDescWords = listDescWords.stream().map(x -> x.toLowerCase()).collect(Collectors.toList());

			Long spamWordCount = finishedDescWords.stream().filter(x -> this.wordContainedInWordList(x, palabrasSpam)).collect(Collectors.counting());

			Double spamThreshold = finishedDescWords.size() * spamThresholdPercent * 0.01;

			Boolean isSpam = spamWordCount > spamThreshold;

			errors.state(request, !isSpam, "description", "entrepreneur.investmentRound.error.description");
		}
	}
	private Boolean wordContainedInWordList(final String word, final List<String> list) {
		return list.stream().anyMatch(x -> word.contains(x));
	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
}
