package cs.ea.appointmentreservation.dao;

import cs.ea.appointmentreservation.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("select a from Appointment  a where  a.customer.id = :customer_id")
    List<Appointment> findAppointmentByCustomer_Id(@Param("customer_id") long customer_id);

    @Query("select a from Appointment  a where  a.session.id = :session_id")
    List<Appointment> findAppointmentBySession_Id(@Param("session_id") long session_id);

    @Query("select a from Appointment  a where  a.session.counselor.id = :counselor_id")
    List<Appointment> findAppointmentByCounselor_Id(@Param("counselor_id") long counselor_id);
}
