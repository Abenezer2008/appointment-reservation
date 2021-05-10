package cs.ea.appointmentreservation.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter
public class Appointment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    @NonNull
    private Person customer;
    @ManyToOne
    @JoinColumn(name = "session_id")
    @NotNull
    @NonNull
    private Session session;
    private AppointmentStatus status = AppointmentStatus.PENDING;
}
