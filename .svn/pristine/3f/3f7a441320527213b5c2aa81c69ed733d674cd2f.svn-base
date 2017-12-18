
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class ConfigurationSystem extends DomainEntity {

	//Construct
	//====================================================

	//Attributes
	//====================================================

	private String	schoolName;
	private String	banner;


	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getSchoolName() {
		return this.schoolName;
	}
	public void setSchoolName(final String schoolName) {
		this.schoolName = schoolName;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getBanner() {
		return this.banner;
	}
	public void setBanner(final String banner) {
		this.banner = banner;
	}

	//Relationships
	//====================================================
}
