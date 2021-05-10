package cs.ea.appointmentreservation.controller;

import cs.ea.appointmentreservation.domain.Appointment;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Role;
import cs.ea.appointmentreservation.domain.Session;
import cs.ea.appointmentreservation.service.IAppointmentService;
import cs.ea.appointmentreservation.service.IPersonService;
import cs.ea.appointmentreservation.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counselors")
public class CounselorController {
    @Autowired
    private IPersonService personService;
    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private ISessionService sessionService;

    @GetMapping
    public Person getCounselor(){
        return personService.getPerson();}

    @PostMapping
    public Person createCounselor(@RequestBody Person person) {
        person.addRole(Role.COUNSELOR);
        return personService.createPerson(person); }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments(){
        return appointmentService.getCounselorAppointments();}

    @GetMapping("/sessions")
    public List<Session> getSessions(){
        return sessionService.getCounselorSessions();
    }

    @PostMapping("/sessions")
    public Session createSession(@RequestBody Session session) {
        return sessionService.createSession(session);}

    @GetMapping("/sessions/{session_id}/appointments")
    public List<Appointment> getSessionAppointments(@PathVariable long session_id){
        return appointmentService.getSessionAppointments(session_id);}

    @GetMapping("/sessions/appointments/{appointment_id}")
    public Appointment approveAppointment(@PathVariable long appointment_id){
        return appointmentService.approveAppointment(appointment_id);}
}

