
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class ComentableEntity extends DomainEntity {

	// Constructor
	// ====================================================================================

	public ComentableEntity() {
		super();
	}


	// Getters & setters
	// ====================================================================================

	// Relationships
	// ====================================================================================

	private Collection<Bulletin>	bulletins;


	@OneToMany(mappedBy = "comentable")
	public Collection<Bulletin> getBulletins() {
		return this.bulletins;
	}

	public void setBulletins(final Collection<Bulletin> bulletins) {
		this.bulletins = bulletins;
	}
}
