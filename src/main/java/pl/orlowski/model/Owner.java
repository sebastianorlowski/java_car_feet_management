package pl.orlowski.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "owners")
@Setter
@Getter
@RequiredArgsConstructor
@Builder
public class Owner {

    public Owner(Long id, Long pesel, String firstName, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pesel;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @OneToOne
    @JoinColumn
    private Car car;
}
