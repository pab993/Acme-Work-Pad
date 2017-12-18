
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	//Relationships
	// =======================================================

	private Collection<Subject>	subjects;


	@Valid
	@OneToMany(mappedBy = "administrator")
	public Collection<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(final Collection<Subject> subjects) {
		this.subjects = subjects;
	}

}
