
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Seminary;

@Repository
public interface SeminaryRepository extends JpaRepository<Seminary, Integer> {

	@Query("select s from Seminary s where s.teacher.id=?1")
	Collection<Seminary> findByTeacher(int idTeacher);

}
