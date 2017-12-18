
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String	name;


	public Category() {
		super();
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	//Relationships
	// =======================================================

	private Collection<Subject>		subjects;
	private Collection<Category>	categoriesChild;
	private Category				categoryFather;


	@Valid
	@OneToMany()
	public Collection<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(final Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	@Valid
	@OneToMany(mappedBy = "categoryFather")
	public Collection<Category> getCategoriesChild() {
		return this.categoriesChild;
	}

	public void setCategoriesChild(final Collection<Category> categoriesChild) {
		this.categoriesChild = categoriesChild;
	}

	@Valid
	@ManyToOne(optional = true)
	public Category getCategoryFather() {
		return this.categoryFather;
	}

	public void setCategoryFather(final Category categoryFather) {
		this.categoryFather = categoryFather;
	}

}
