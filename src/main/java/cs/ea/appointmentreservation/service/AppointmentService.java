package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.dao.IAppointmentRepository;
import cs.ea.appointmentreservation.dao.IPersonRepository;
import cs.ea.appointmentreservation.dao.ISessionRepository;
import cs.ea.appointmentreservation.domain.Appointment;
import cs.ea.appointmentreservation.domain.AppointmentStatus;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService implements IAppointmentService{
    @Autowired
    private IAppointmentRepository appointmentRepository;
    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private PersonDetailService personDetailService;

    @Override
    public Appointment createAppointment(long sessionId) {
        Person customer = personDetailService.getCurrentPerson();
        Session session = sessionRepository.findById(sessionId).get();
        Appointment appointment = new Appointment(customer,session);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }


    @Override
    public List<Appointment> getCustomerAppointments() {
        Person customer = personDetailService.getCurrentPerson();
        return appointmentRepository.findAppointmentByCustomer_Id(customer.getId());
    }

    @Override
    public List<Appointment> getSessionAppointments(long sessionId) {
        return appointmentRepository.findAppointmentBySession_Id(sessionId);
    }

    @Override
    public List<Appointment> getCounselorAppointments() {
        Person person = personDetailService.getCurrentPerson();
        return appointmentRepository.findAppointmentByCounselor_Id(person.getId());
    }

    @Override
    public Appointment approveAppointment(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.setStatus(AppointmentStatus.APPROVED);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
