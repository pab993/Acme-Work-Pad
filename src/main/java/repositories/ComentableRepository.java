
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.ComentableEntity;

public interface ComentableRepository extends JpaRepository<ComentableEntity, Integer> {

	@Query("select c from ComentableEntity c where c.id = ?1")
	ComentableEntity findOneAux(int comentableId);

}
