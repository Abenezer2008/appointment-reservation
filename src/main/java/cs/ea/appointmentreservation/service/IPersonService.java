package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.domain.Appointment;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Role;

import java.util.List;

public interface IPersonService {
    Person createPerson(String firstName, String lastName, String email, String username, String password, Role role);
    Person createPerson(Person person);
    Person getPerson();
    List<Person> getAllByRole(Role role);
    List<Person> getAll();
    Person giveRole(long personId,Role role);
    Person removeRole(long personId, Role role);
    void deletePerson(long personId);
}
