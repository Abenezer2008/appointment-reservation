package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.domain.Appointment;

import java.util.List;

public interface IAppointmentService {
    Appointment createAppointment(long sessionId);
    Appointment getAppointment(long appointmentId);
    List<Appointment> getAppointments();
    List<Appointment> getCustomerAppointments();
    List<Appointment> getSessionAppointments(long sessionId);
    List<Appointment> getCounselorAppointments();
    Appointment approveAppointment(long appointmentId);
    Appointment cancelAppointment(long appointmentId);
    void deleteAppointment(long appointmentId);
}
