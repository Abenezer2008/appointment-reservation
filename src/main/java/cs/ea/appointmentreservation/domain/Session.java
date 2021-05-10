package cs.ea.appointmentreservation.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter @ToString
public class Session {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Future
    @NonNull
    private LocalDate date;
    @NotNull
    @NonNull
    private LocalTime startTime;
    @NotNull
    @NonNull
    private int duration;
    @ManyToOne
    @JoinColumn(name = "counselor_id")
    @NotNull
    @NonNull
    private Person counselor;
}
