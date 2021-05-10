package cs.ea.appointmentreservation.dao;

import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IPersonRepository extends JpaRepository<Person,Long> {
    @Query("select p from Person  p where  p.roles = :role ")
    List<Person> findPeopleByRole(@Param("role") Role role);

    @Query("from Person WHERE  username=:username ")
    Optional<Person> findByUsername(@Param("username") String username);
}
