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
import java.util.Locale;

@RestController
@RequestMapping("/public")
public class ProfileController {
    @Autowired
    private IPersonService personService;

    @PostMapping("/{role}")
    public Person signUp(@RequestBody Person person,@PathVariable String role) {
        person.addRole(Role.valueOf(role.toUpperCase()));
        return personService.createPerson(person);}

//    @PostMapping
//    public Person logIn(@RequestBody Person person) {
//        person.addRole(Role.CUSTOMER);
//        return personService.createPerson(person);}

}