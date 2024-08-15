package com.agrotech.api.model;

import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @Indexed(unique = true)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private String farmer;
    private String resetToken;

    private String nom;
    private String prenom;
    private Date dateNaissance; // Utilisez java.util.Date pour dateNaissance
    private String sexe;
    private String pays;
    private String region;
    private String address1;
    private String address2;
    private String bio;
    private String numeroTelephone;

    private String avatar;
    private String signature;
    private String activationToken;

    private Boolean verified=false;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User() {
    }
    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }

    public User(String username, String email, String password,  String nom, String prenom, Date dateNaissance, String sexe, String pays, String region, String numeroTelephone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.pays = pays;
        this.region = region;
        this.numeroTelephone = numeroTelephone;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    private HashSet<String> tags = new HashSet<>();
    private HashSet<String> modules = new HashSet<>();


    private List<NewNotification> notifications = new ArrayList<>();

    private int notificationsNbr=0;

    public List<NewNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NewNotification> notifications) {
        this.notifications = notifications;
    }

    public int getNotificationsNbr() {
        return notificationsNbr;
    }

    public void setNotificationsNbr(int notificationsNbr) {
        this.notificationsNbr = notificationsNbr;
    }

    public User(String resetToken) {
        this.resetToken = resetToken;
    }


    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public HashSet<String> getModules() {
        return modules;
    }

    public void setModules(HashSet<String> modules) {
        this.modules = modules;
    }


    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public User(String username, String email, String password, String resetToken, String nom, String prenom, Date dateNaissance, String sexe, String pays, String region, String numeroTelephone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.resetToken = resetToken;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.pays = pays;
        this.region = region;
        this.numeroTelephone = numeroTelephone;
    }

    public User(String username, String email, String password, String resetToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.resetToken = resetToken;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }



    public User(String username, String email, String password, Set<Role> roles, String farmer, String resetToken, String nom, String prenom, Date dateNaissance, String sexe, String pays, String region, String numeroTelephone, String avatar, Boolean verified, HashSet<String> tags, HashSet<String> modules) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.farmer = farmer;
        this.resetToken = resetToken;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.pays = pays;
        this.region = region;
        this.numeroTelephone = numeroTelephone;
        this.avatar = avatar;
        this.verified = verified;
        this.tags = tags;
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", farmer='" + farmer + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", sexe='" + sexe + '\'' +
                ", pays='" + pays + '\'' +
                ", region='" + region + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", activationToken='" + activationToken + '\'' +
                ", verified=" + verified +
                ", tags=" + tags +
                ", modules=" + modules +
                ", notifications=" + notifications +
                ", notificationsNbr=" + notificationsNbr +
                '}';
    }

    public User(String id, String username, String email, String nom, String prenom, Date dateNaissance, String sexe, String pays, String region, String address1, String address2, String bio, String numeroTelephone, String avatar, String signature, HashSet<String> tags, HashSet<String> modules, List<NewNotification> notifications, int notificationsNbr) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.pays = pays;
        this.region = region;
        this.address1 = address1;
        this.address2 = address2;
        this.bio = bio;
        this.numeroTelephone = numeroTelephone;
        this.avatar = avatar;
        this.signature = signature;
        this.tags = tags;
        this.modules = modules;
        this.notifications = notifications;
        this.notificationsNbr = notificationsNbr;
    }
}


