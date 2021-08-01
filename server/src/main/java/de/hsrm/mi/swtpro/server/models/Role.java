package de.hsrm.mi.swtpro.server.models;

import javax.persistence.*;
import java.util.List;
import lombok.*;
/**
 * Java-Klasse, welche die Rolle unserer User darstellt.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    /**
     * @param id ID der Rolle (1=ADMIN, 2=LAGERIST, 3=USER)
     * @param name Name der Rolle
     * @param user Liste von Users mit Rolle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "role")
    private List<User> user;
}