
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.BulletinRepository;
import security.Authority;
import domain.Actor;
import domain.Bulletin;
import domain.ComentableEntity;
import forms.CommentForm;

@Service
@Transactional
public class BulletinService {

	// Repositories
	// ====================================================================

	@Autowired
	private BulletinRepository	commentRepository;

	// Supporting services
	// ====================================================================

	@Autowired
	private ComentableService	comentableService;

	@Autowired
	private ActorService		actorService;


	// Simple CRUD methods
	// ====================================================================

	public Bulletin findOne(final int commentId) {
		Assert.isTrue(commentId != 0);
		Bulletin result;

		result = this.commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Bulletin> findAll() {
		Collection<Bulletin> result;

		result = this.commentRepository.findAll();

		return result;
	}

	public Bulletin create(final ComentableEntity comentable) {
		Bulletin result;
		Actor principal;
		Date createMoment;

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		createMoment = new Date(System.currentTimeMillis() - 10);
		result = new Bulletin();
		result.setMoment(createMoment);
		result.setActor(principal);
		comentable.getBulletins().add(result);
		result.setComentable(comentable);
		result.setBan(false);

		return result;
	}

	public Bulletin save(final Bulletin comment) {
		Assert.notNull(comment);
		Bulletin result;

		result = this.commentRepository.save(comment);

		return result;
	}

	// Other business methods
	// ===================================================================

	public Double avgPerActorOfferRequest() {
		Double result;

		result = this.commentRepository.avgPerActorOfferRequest();
		Assert.notNull(result);

		return result;
	}

	public Double avgPerAdminsAndCustomers() {
		Double result;

		result = this.commentRepository.avgPerAdminsAndCustomers();
		Assert.notNull(result);

		return result;
	}

	public Collection<Actor> actorsBetweenAvgTenPerCent() {
		Collection<Actor> result;

		result = this.commentRepository.actorsBetweenAvgTenPerCent();
		Assert.notNull(result);

		return result;
	}

	public Collection<Bulletin> findAllByComentable(final int subjectId) {
		//		Assert.notNull(comentable);

		Collection<Bulletin> res;

		res = this.commentRepository.findAllByComentable(subjectId);

		return res;
	}

	public Bulletin reconstruct(final CommentForm commentForm, final BindingResult binding) {
		//Comentable comentable2 = comentableService.findOne(commentForm.getIdComentable());
		final ComentableEntity comentable = this.comentableService.findOneAux(commentForm.getIdComentable());
		final Bulletin res = this.create(comentable);
		res.setStars(commentForm.getStars());
		res.setText(commentForm.getText());
		res.setTitle(commentForm.getTitle());

		return res;

	}

	public Collection<Bulletin> filterComments(final ComentableEntity comentable) {
		final Actor principal = this.actorService.findByPrincipal();
		Collection<Bulletin> res = new HashSet<Bulletin>();
		final Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if (principal.getUserAccount().getAuthorities().contains(i))
			res = comentable.getBulletins();
		else
			res = this.commentRepository.findNotBaned(comentable.getId(), principal.getId());

		return res;
	}

	public void banMethod(final int commentId) {
		final Bulletin comment = this.findOne(commentId);
		comment.setBan(!comment.getBan());
		this.save(comment);

	}
}
