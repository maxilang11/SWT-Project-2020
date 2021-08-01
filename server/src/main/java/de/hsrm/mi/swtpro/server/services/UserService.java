package de.hsrm.mi.swtpro.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swtpro.server.models.Bookmark;
import de.hsrm.mi.swtpro.server.models.ShoppingCart;
import de.hsrm.mi.swtpro.server.models.User;
import de.hsrm.mi.swtpro.server.repository.BookmarkRepository;
import de.hsrm.mi.swtpro.server.repository.RoleRepository;
import de.hsrm.mi.swtpro.server.repository.ShoppingCartRepository;
import de.hsrm.mi.swtpro.server.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import de.hsrm.mi.swtpro.server.models.EMailCfg;

import java.util.List;

import javax.transaction.Transactional;

@Service
/**
 * Serviceklasse fuer alle Benutzer unsere Anwendung. Hier wird sich auch um das senden der Mail nach der register gekuemmert
 */
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EMailCfg emailCfg;


    /**
     * @param user
     */
    public void save(User user, ShoppingCart shoppingCart, Bookmark bookmark) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.getOne((long) 3));
        userRepository.save(user);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());

        // Eine E-Mail Instanz wird erstellt
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("service@misupermarkt.de");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Vielen Dank für Ihre Registrierung");
        mailMessage.setText("Ihre Registrierung beim Super MiMarkt war erfolgreich, " + user.getGender() + " "
                + user.getSurname() + "." + " Viel Spaß beim Shoppen!");

        // Die E-Mail wird versendet
        mailSender.send(mailMessage);
        shoppingCart.setId(user.getId());
        shoppingCartRepository.save(shoppingCart);
        bookmark.setId(user.getId());
        bookmarkRepository.save(bookmark);
    }

    /**
     * @param username
     * @return Boolean
     */
    public Boolean isRegistered(String username) {
        return userRepository.findByUsername(username) != null ? true : false;
    }

    /**
     * @param User
     * @return Boolean
     */
    public Boolean checkPassword(User user) {
        User validUser;
        validUser = findByUsername(user.getUsername());
        return bCryptPasswordEncoder.matches(user.getPassword(), validUser.getPassword());
    }

    /**
     * @param username
     * @return User
     */
    public User findByUsername(String username) {
        User u = new User();
        u = userRepository.findByUsername(username);
        return u;
    }

    /**
     * Zum Bearbeiten eines Nutzers
     * @param editUserData
     */
    @Transactional
    public void editProfile(User editUserData) {
        userRepository.findByUsername(editUserData.getUsername()).setGender(editUserData.getGender());
        userRepository.findByUsername(editUserData.getUsername()).setSurname(editUserData.getSurname());
        userRepository.findByUsername(editUserData.getUsername()).setName(editUserData.getName());
        userRepository.findByUsername(editUserData.getUsername()).setStreet(editUserData.getStreet());
        userRepository.findByUsername(editUserData.getUsername()).setAddressnr(editUserData.getAddressnr());
        userRepository.findByUsername(editUserData.getUsername()).setZipcode(editUserData.getZipcode());
        userRepository.findByUsername(editUserData.getUsername()).setCity(editUserData.getCity());
        userRepository.findByUsername(editUserData.getUsername()).setEmail(editUserData.getEmail());
    }

    // Alle Benutzer
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}