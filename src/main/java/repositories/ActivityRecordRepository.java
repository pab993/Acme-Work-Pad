
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ActivityRecord;

@Repository
public interface ActivityRecordRepository extends JpaRepository<ActivityRecord, Integer> {

	@Query("select ar from ActivityRecord ar where ar.actor.id = ?1")
	Collection<ActivityRecord> findAllByActor(int actorId);
}
