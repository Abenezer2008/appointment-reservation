package cs.ea.appointmentreservation.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter @ToString
public class Person {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @NonNull
    private String firstName;
    @NotNull
    @NonNull
    private String lastName;
    @NotNull
    @NonNull
    private String email;
    @NotNull
    @NonNull
    private String username;
    @NotNull
    @NonNull
    private String passwordHash;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();


    public void addRole(Role role){
        this.roles.add(role);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
    }
}
