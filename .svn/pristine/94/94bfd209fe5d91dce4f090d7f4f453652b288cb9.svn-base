
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

	@Query("select g from Group g join g.students s where s.id = ?1 and g.subject = ?2")
	Group findOneByStudentAndSubject(int idStudent, int idSubject);

}
