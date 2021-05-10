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
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private IPersonService personService;
    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private ISessionService sessionService;

    @GetMapping
    public Person getCustomer(){
        return personService.getPerson();}

//    @PostMapping
//    public Person createCustomer(@RequestBody Person person) {
//        person.addRole(Role.CUSTOMER);
//        return personService.createPerson(person);}

    @GetMapping("/appointments")
    public List<Appointment> getAppointments(){
        return appointmentService.getCustomerAppointments();}


    @PostMapping("/sessions/{session_id}/appointments")
    public Appointment createAppointment(@PathVariable long session_id){
        return appointmentService.createAppointment(session_id);}
}
