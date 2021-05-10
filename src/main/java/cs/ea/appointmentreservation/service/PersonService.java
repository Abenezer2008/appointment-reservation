package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.dao.IAppointmentRepository;
import cs.ea.appointmentreservation.dao.IPersonRepository;
import cs.ea.appointmentreservation.dao.ISessionRepository;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService implements IPersonService{
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public Person createPerson(String firstName, String lastName, String email, String username, String password, Role role) {
        Person person = new Person(firstName,lastName,email,username,new BCryptPasswordEncoder().encode(password));
        person.addRole(role);
        System.out.println("PERSON TO BE CREATED : " + person);
        return personRepository.save(person);
    }

    public Person createPerson(Person person) {
        person.setPasswordHash(new BCryptPasswordEncoder().encode(person.getPasswordHash()));
        return personRepository.save(person);
    }

    @Override
    public Person getPerson() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return personRepository.findByUsername(username).get();
//        return personRepository.findById(personId).get();
    }

    @Override
    public List<Person> getAllByRole(Role role) {
        return personRepository.findPeopleByRole(role);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person giveRole(long personId, Role role) {
        Person person = personRepository.findById(personId).get();
        person.addRole(role);
        return personRepository.save(person);
    }

    @Override
    public Person removeRole(long personId, Role role) {
        Person person = personRepository.findById(personId).get();
        person.removeRole(role);
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(long personId) {
         personRepository.deleteById(personId);
    }
}
