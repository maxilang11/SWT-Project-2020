package de.hsrm.mi.swtpro.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
/**
 * Hier werden alle Sicherheitsfeatures verwaltet. JWT im Login, CORS, CSRF und PasswortEncoder
 */
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
  @Qualifier("userDetailsServiceImpl")
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JWTAuthenticationFilter jwtfilter;

  /**
   * @return BCryptPasswordEncoder
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * @throws Exception
   */

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  /**
   * @param web
   * @throws Exception
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/actuator", "/actuator/**", "/instances");
  }

  /**
   * @param http Angabe von Zugriffen auf Rest-Endpunkte
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
      .antMatchers("/h2-console/**", "/userlogin", "/userregister", "/shoppingCartQuantity", "/bookmark", "/cart/{id}", "/totalPrice", "/category/**", "/search/{search}", "/products/**").permitAll()
      .antMatchers("/cartdel", "/bookmarkdel", "/amount", "/profileUser/{username}", "/checkout/{username}", "/userOrders/{username}", "/userEdit").hasAuthority("USER")
      .antMatchers("/productEdit", "/delProduct", "/admin/products/export/{file}").hasAuthority("ADMIN")
      .antMatchers("/lagerist/bestelluebersicht", "/orders", "/orders/{orderId}",
          "/orders/user/{orderId}", "/orderstatus/{orderId}", "/changeAmount/{id}/{amount}").hasAuthority("LAGERIST")
      .and()
        .exceptionHandling()
      .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
    http.headers().frameOptions().disable();
  }
  
  /**
   * @return AuthenticationManager
   * @throws Exception
   */
  @Bean(name =  BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }
}
