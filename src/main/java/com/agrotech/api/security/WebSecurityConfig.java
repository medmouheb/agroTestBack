package com.agrotech.api.security;


import com.agrotech.api.security.jwt.AuthEntryPointJwt;
import com.agrotech.api.security.jwt.AuthTokenFilter;
import com.agrotech.api.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/campany/**").permitAll()
                        .requestMatchers("/airport/**").permitAll()
                        .requestMatchers("/binDetails/**").permitAll()
                        .requestMatchers("/breedCode/**").permitAll()
                        .requestMatchers("/breedType/**").permitAll()
                        .requestMatchers("/brokers/**").permitAll()
                        .requestMatchers("/buyers/**").permitAll()
                        .requestMatchers("/category/**").permitAll()
                        .requestMatchers("/charge/**").permitAll()
                        .requestMatchers("/commande/**").permitAll()
                        .requestMatchers("/commandeFournisseur/**").permitAll()
                        .requestMatchers("/costcenter/**").permitAll()
                        .requestMatchers("/delivery/**").permitAll()
                        .requestMatchers("/division/**").permitAll()
                        .requestMatchers("/drivers/**").permitAll()
                        .requestMatchers("/facilityDetails/**").permitAll()
                        .requestMatchers("/ferme/**").permitAll()
                        .requestMatchers("/files/**").permitAll()
                        .requestMatchers("/fournisseur/**").permitAll()
                        .requestMatchers("/growout/**").permitAll()
                        .requestMatchers("/housse/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/Incoterms/**").permitAll()
                        .requestMatchers("/InventaireInitial/**").permitAll()
                        .requestMatchers("/logisticunit/**").permitAll()
                        .requestMatchers("/manufacturer/**").permitAll()
                        .requestMatchers("/operationmanagement/**").permitAll()
                        .requestMatchers("/productCategory/**").permitAll()
                        .requestMatchers("/produit/**").permitAll()
                        .requestMatchers("/rapprochement-des-stocks/**").permitAll()
                        .requestMatchers("/reason/**").permitAll()
                        .requestMatchers("/sales/**").permitAll()
                        .requestMatchers("/salesSku/**").permitAll()
                        .requestMatchers("/seaport/**").permitAll()
                        .requestMatchers("/shipmethods/**").permitAll()
                        .requestMatchers("/UtilisationDuProduit/**").permitAll()
                        .requestMatchers("/vehicles/**").permitAll()
                        .requestMatchers("/vehicleType/**").permitAll()
                        .requestMatchers("/vehicule/**").permitAll()
                        .requestMatchers("/vendorsContactInformation/**").permitAll()
                        .requestMatchers("/vendors/**").permitAll()
                        .requestMatchers("/vendorsinternaldetails/**").permitAll()
                        .requestMatchers("/vendorSku/**").permitAll()
                        .requestMatchers("/vendorsPayments/**").permitAll()
                        .requestMatchers("/vendorsRemit/**").permitAll()
                        .requestMatchers("/vendorsShipping/**").permitAll()
                        .requestMatchers("/vendorType/**").permitAll()
                        .requestMatchers("/vendorTypeDetail/**").permitAll()
                        .requestMatchers("/vendortypefeed/**").permitAll()
                        .requestMatchers("/vendorTypeFreight/**").permitAll()
                        .requestMatchers("/vendortypehistoryiinvoices/**").permitAll()
                        .requestMatchers("/vendorTypeHistoryIReceiving/**").permitAll()
                        .requestMatchers("/vendorTypePODetails/**").permitAll()
                        .requestMatchers("/vendorTypePricing/**").permitAll()
                        .requestMatchers("/vendorTypeProduct/**").permitAll()
                        .requestMatchers("/crop/**").permitAll()
                        .requestMatchers("/vendorTypePurchase/**").permitAll()
                        .requestMatchers("/vendorTypeReceiving/**").permitAll()
                        .requestMatchers("/warehouse/**").permitAll()
                        .requestMatchers("/willaya/**").permitAll()
                        .requestMatchers("/buy/**").permitAll()
                        .requestMatchers("/api/test/**").permitAll().anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
