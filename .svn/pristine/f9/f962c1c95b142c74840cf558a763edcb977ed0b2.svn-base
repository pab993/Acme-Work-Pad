
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Seminary extends DomainEntity {

	private String	title;
	private String	abstractSeminary;
	private Date	moment;
	private Integer	duration;
	private String	hall;
	private Integer	seats;


	public Seminary() {
		super();
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAbstractSeminary() {
		return this.abstractSeminary;
	}

	public void setAbstractSeminary(final String abstractSeminary) {
		this.abstractSeminary = abstractSeminary;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotNull
	@Range(min = 1, max = 500)
	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(final Integer duration) {
		this.duration = duration;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getHall() {
		return this.hall;
	}

	public void setHall(final String hall) {
		this.hall = hall;
	}

	@NotNull
	@Range(min = 0, max = 500)
	public Integer getSeats() {
		return this.seats;
	}

	public void setSeats(final Integer seats) {
		this.seats = seats;
	}


	//Relationships
	private Teacher				teacher;
	private Collection<Student>	students;


	@Valid
	@ManyToOne(optional = false)
	public Actor getTeacher() {
		return this.teacher;
	}

	public void setTeacher(final Teacher teacher) {
		this.teacher = teacher;
	}

	@Valid
	@ManyToMany()
	public Collection<Student> getStudents() {
		return this.students;
	}

	public void setStudents(final Collection<Student> students) {
		this.students = students;
	}

}
