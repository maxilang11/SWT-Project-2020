package de.hsrm.mi.swtpro.server.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
/**
 * Java Klasse, die einen User unserer Anwendung darstellt. Der User hat 
 * die üblichen Felder wie Username, Passwort etc und zusätzlich ein Feld "Rolle", das
 * über die ID mit einem Feld der Role Entity gejoined wird.
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
  /**
   * @param id ID des Users
   * @param username Username des Users
   * @param password Passwort des Users
   * @param name Vorname des Users
   * @param surname Nachname des Users
   * @param email Email des Users
   * @param street Straße des Users
   * @param adressnr Hausnummer des Users
   * @param gender Geschlecht des Users
   * @param city Stadt des Users
   * @param zipcode PLZ des Users
   * @param role Rolle des Users
   */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String street;
    private int addressnr;
    private String gender;
    private String city;
    private int zipcode;
    @JsonIgnore
    @OneToOne
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(
          name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(
          name = "role_id", referencedColumnName = "id")})
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Order> orders = new ArrayList<>();


    @Override
    public String toString() {
        return "User [ID = " + id + "Username = " + username + ", Password = " + password + ", Name = " + name
                + ", Surname = " + surname + ", Email = " + email + ", Straße = " + street + ", Hausnummer = "
                + addressnr + ", Geschlecht = " + gender + ", Stadt = " + city + ", PLZ = " + zipcode + ", Rolle = " + role + "]";
    }

}