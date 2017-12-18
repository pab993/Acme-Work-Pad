
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Teacher extends Actor {

	//Relationships
	// =======================================================

	private Collection<Subject>		subjects;
	private Collection<Seminary>	seminaries;


	@Valid
	@OneToMany(mappedBy = "teacher")
	public Collection<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(final Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	@Valid
	@OneToMany(mappedBy = "teacher")
	public Collection<Seminary> getSeminaries() {
		return this.seminaries;
	}

	public void setSeminaries(final Collection<Seminary> seminaries) {
		this.seminaries = seminaries;
	}

}
