/*
 * AnonymousUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select ir from InvestmentRound ir where ir.entrepreneur.id=?1")
	Collection<InvestmentRound> findByEntrepreneurId(Integer id);

	@Query("select ir from InvestmentRound ir where ir.id=?1")
	InvestmentRound findOneById(Integer id);

	@Query("select e from Entrepreneur e where e.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(Integer id);

	@Query("select e from Entrepreneur e where e.id=?1")
	Entrepreneur findOneEntrepreneurById(Integer entrepreneurId);

	@Query("select sum(a.budget.amount) from Activity a where a.investmentRound.id=?1")
	Double sumActivitiesOffer(Integer investmentRoundId);

	@Modifying
	@Transactional
	@Query("delete from Activity a where a.investmentRound.id=?1")
	void deleteActivitiesOfIR(int id);

	@Query("select cs.spamWords from CustomisationParameters cs")
	String getSpamWords();

	@Query("select cs.spamThreshold from CustomisationParameters cs")
	Double getSpamThreshold();

}
