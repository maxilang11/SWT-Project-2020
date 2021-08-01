package de.hsrm.mi.swtpro.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import de.hsrm.mi.swtpro.server.models.Bookmark;
import de.hsrm.mi.swtpro.server.models.ShoppingCart;
import de.hsrm.mi.swtpro.server.models.User;
import de.hsrm.mi.swtpro.server.security.JWTSecurity;
import de.hsrm.mi.swtpro.server.services.UserService;
import java.util.List;
import javax.validation.Valid;
/**
 * Der Usercontroller ist fuer das verwalten von Benutzern unserer Anwendung zustaendig
 */
@RestController
/**
 * Der Usercontroller ist fuer das verwalten von Benutzern unserer Anwendung zustaendig
 */
public class UserController {
    @Autowired
    private JWTSecurity jwtsecurity;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;  

    /** 
     * @param actUser aktuell eingeloggter User
     * @return ResponseEntity<String> je nachdem, ob Username bzw. Passwort richtig eingegeben wurde wird eine response vom Server geschickt
     * @throws Exception
     */
    @PostMapping(value = "/api/userlogin")
    public ResponseEntity<String> login(@Valid @RequestBody User actUser) throws Exception {
        if (userService.isRegistered(actUser.getUsername())) {
            if (userService.checkPassword(actUser)) {
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(actUser.getUsername(), actUser.getPassword()));
                } catch (Exception e) {
                    throw new Exception("Username oder Passwort falsch");
                }
                return ResponseEntity.ok().body(jwtsecurity.generateToken(actUser.getUsername()));

            } else {
                return ResponseEntity.badRequest().body("Falsches Passwort für User " + actUser.getUsername());
            }
        } else {
            return ResponseEntity.badRequest().body("Error, User gibt es nicht!");
        }
    }

    /** 
     * @param username Username des aktuellen Users wird über Token herrausgefunden
     * @return User
     */
    @GetMapping("/api/profileUser/{username}")
    private User getActUser(@PathVariable String username){
        return userService.findByUsername(username);
    }

    /** 
     * @param editUserData zu editierender User
     * @return ResponseEntity<String> Antwort des Servers auf (falsche) Eingaben mittels http-response
     * @throws Exception
     */
    @PostMapping(value = "/api/userEdit")
    public ResponseEntity<String> editUser(@Valid @RequestBody User editUserData) throws Exception {
        userService.editProfile(editUserData);
        return ResponseEntity.ok().body("User Profil wurde erfolgreich bearbeitet");
    }

    /** 
     * @param newUser neu erstellter User
     * @return ResponseEntity<String> Antwort des Servers auf (falsche) Eingaben mittels http-response
     * @throws Exception
     */
    @PostMapping(value = "/api/userregister")
    public ResponseEntity<String> register(@Valid @RequestBody User newUser) throws Exception {
        ShoppingCart newShoppingCart = new ShoppingCart();
        Bookmark newBookmark = new Bookmark();

        if(userService.findByUsername(newUser.getUsername()) != null){
            return ResponseEntity.badRequest().body("Username gibt es bereits!");
        } else {
            userService.save(newUser, newShoppingCart, newBookmark);
            return ResponseEntity.ok().body("User wurde erfolgreich registriert");
        }
    }  

    //Alle Nutzer aus der DB holen
    @GetMapping(value = "/api/allUsers")
    public List<User> allUsers(){
        return (List<User>) userService.allUsers();
    }
}