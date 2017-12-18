
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "group_id")
@Entity
@Access(AccessType.PROPERTY)
public class Group extends DomainEntity {

	private String	name;
	private String	description;
	private Date	startDate;
	private Date	endDate;


	public Group() {
		super();
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}


	//Relationships
	private Collection<Student>		students;
	private Subject					subject;
	private Collection<Deliverable>	deliverables;


	@Valid
	@ManyToMany()
	public Collection<Student> getStudents() {
		return this.students;
	}

	public void setStudents(final Collection<Student> students) {
		this.students = students;
	}

	@Valid
	@ManyToOne(optional = false)
	public Subject getSubject() {

		return this.subject;
	}

	public void setSubject(final Subject subject) {
		this.subject = subject;
	}

	@Valid
	@OneToMany(mappedBy = "group")
	public Collection<Deliverable> getDeliverables() {
		return this.deliverables;
	}

	public void setDeliverables(final Collection<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}

}
