
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Deliverable extends DomainEntity {

	private String	contents;
	private String	attachment;
	private Integer	grade;


	public Deliverable() {
		super();
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getContents() {
		return this.contents;
	}

	public void setContents(final String contents) {
		this.contents = contents;
	}

	@URL
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	@Range(min = 0, max = 100)
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(final Integer grade) {
		this.grade = grade;
	}


	//Relationships
	private Group		group;
	private Assignment	assignment;


	@Valid
	@ManyToOne(optional = false)
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(final Group group) {
		this.group = group;
	}

	@Valid
	@ManyToOne(optional = false)
	public Assignment getAssignment() {
		return this.assignment;
	}

	public void setAssignment(final Assignment assignment) {
		this.assignment = assignment;
	}

}
