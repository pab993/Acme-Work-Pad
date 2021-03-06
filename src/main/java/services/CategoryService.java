
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Actor;
import domain.Administrator;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;

	@Autowired
	private ActorService		actorService;


	public CategoryService() {
		super();
	}

	// CRUD methods --------------------------------------------------------------------------------
	public Category create(final Category category) {

		Assert.notNull(category);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getClass().isInstance(Administrator.class));

		Category result;

		result = new Category();
		result.setCategoryFather(category);

		return result;
	}

	public Category findOne(final int categoryId) {
		return this.categoryRepository.findOne(categoryId);
	}

	public Collection<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category save(final Category category) {

		Assert.notNull(category);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getClass().isInstance(Administrator.class));

		Category res;

		res = this.categoryRepository.save(category);

		return res;
	}

	public Category save2(final Category category) {

		Assert.notNull(category);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		Category res;

		res = this.categoryRepository.save(category);

		return res;
	}

	public void delete(final Category category) {

		Assert.notNull(category);
		Assert.isTrue(this.categoryRepository.exists(category.getId()));

		final Actor principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isTrue(principal.getClass().isInstance(Administrator.class));

		this.categoryRepository.delete(category);

		Assert.isTrue(!this.categoryRepository.exists(category.getId()));
	}

	// Other business methods --------------------------------------------------------------------------------

}
