
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);

	@Query("select min(a.activityRecords.size), max(a.activityRecords.size), avg(a.activityRecords.size) from Actor a")
	Object[] minMaxAvgActivityRecordsByActor();

	@Query("select a from Actor a where a.activityRecords.size <= (select avg(a1.activityRecords.size)*1.1 from Actor a1) and a.activityRecords.size >= (select avg(a2.activityRecords.size)*0.9 from Actor a2)")
	Collection<Actor> findActorsPlusMinus10AvgActivityRecords();

	@Query("select min(a.socialIdentities.size), max(a.socialIdentities.size), avg(a.socialIdentities.size) from Actor a")
	Object[] minMaxAvgSocialIdentitiesByActor();

	@Query("select count(a)*1.0 / (select count(a1)*1.0 from Actor a1) from Actor a where a.socialIdentities.size >= 1")
	Object[] ratioActorsWithSocialIdentity();

}
