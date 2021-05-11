package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.dao.IPersonRepository;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class PersonDetailService implements UserDetailsService {
    @Autowired
    private IPersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username).get();
        if(person == null){
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        return new User(person.getUsername(),person.getPasswordHash(),getGrantedAuthority(person));
    }

    private Collection<GrantedAuthority> getGrantedAuthority(Person person){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(person.getRoles().contains(Role.ADMIN)){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if(person.getRoles().contains(Role.CUSTOMER)){
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        }
        if(person.getRoles().contains(Role.COUNSELOR)){
            authorities.add(new SimpleGrantedAuthority("ROLE_COUNSELOR"));
        }
        return authorities;
    }

    public Person getCurrentPerson(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        String username = ((UserDetails)principal).getUsername();
        return personRepository.findByUsername(username).get();
    }
}
