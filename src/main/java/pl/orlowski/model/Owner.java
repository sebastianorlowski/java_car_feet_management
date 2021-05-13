package pl.orlowski.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class Owner {

    public Owner(Long id, Long pesel, String firstName, String lastName,
                 String phoneNumber, String email, Car car) {
        this.id = id;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.car = car;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pesel;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @ManyToOne
    private Car car;

}
