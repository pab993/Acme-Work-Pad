
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

	@Query("select a from Assignment a join a.subject s where s.id = ?1")
	Collection<Assignment> findAllBySubject(int subjectId);

}
