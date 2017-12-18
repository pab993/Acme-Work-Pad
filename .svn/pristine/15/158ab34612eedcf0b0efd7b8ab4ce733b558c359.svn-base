
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Student extends Actor {

	//Relationships
	// =======================================================

	private Collection<Subject>	subjects;
	private Collection<Group>	groups;


	@Valid
	@ManyToMany(mappedBy = "students")
	public Collection<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(final Collection<Subject> subjects) {
		this.subjects = subjects;
	}

	@Valid
	@ManyToMany(mappedBy = "students")
	public Collection<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(final Collection<Group> groups) {
		this.groups = groups;
	}

}
