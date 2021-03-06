package cs.ea.appointmentreservation.dao;

import cs.ea.appointmentreservation.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ISessionRepository extends JpaRepository<Session,Long> {
    @Query("select s from Session  s where  s.counselor.id = :counselor_id")
    List<Session> findSessionByCounselor_Id(@Param("counselor_id") long counselor_id);

//    @Query("select s from Session  s where  s.approvedAppointment.customer.id = :customer_id")
//    List<Session> findSessionByCustomer_Id(@Param("customer_id") long customer_id);
}
