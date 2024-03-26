package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Buyers")
public class Buyers extends BaseEntity{

    @NotBlank(message = "BuyerCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String code;
    @Indexed(unique = true)
    @NotBlank(message = "BuyerName is required")

    @Size(max = 50)
    private String name;

    private String firstName;
    private String lastName;

    private String farmer;

    private String areaCode;


    private String ice;

    private String region;

    private String taxIdentification;

    private String city;

    private String latitude;

    private String longitude;

    private String country;

    private String activityArea;

    private String simo;

    private String contact;

    private String sector;

    private String phone;

    private String domain;

    private String company;

    private String  warehouse;

    //facturisation et livraison

    private String addressFactur;
    private String addressLivre;

    private String cityFactur;
    private String cityLivre;

    private String RegionFactur;
    private String RegionLivre;


    private String areaCodeFactur;
    private String areaCodeLivre;

    private String CountryFactur;
    private String CountryLivre;


    //coor bank

    private String numberAccount;
    private String nameBank;
    private String  addressBank;


    //advanced

    private BigDecimal maxCredit;

    private String discountName;

    private Currency currency;

    private float discountPercent;

    private  String category;

    private int dueInDays;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTaxIdentification() {
        return taxIdentification;
    }

    public void setTaxIdentification(String taxIdentification) {
        this.taxIdentification = taxIdentification;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public String getSimo() {
        return simo;
    }

    public void setSimo(String simo) {
        this.simo = simo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getAddressFactur() {
        return addressFactur;
    }

    public void setAddressFactur(String addressFactur) {
        this.addressFactur = addressFactur;
    }

    public String getAddressLivre() {
        return addressLivre;
    }

    public void setAddressLivre(String addressLivre) {
        this.addressLivre = addressLivre;
    }

    public String getCityFactur() {
        return cityFactur;
    }

    public void setCityFactur(String cityFactur) {
        this.cityFactur = cityFactur;
    }

    public String getCityLivre() {
        return cityLivre;
    }

    public void setCityLivre(String cityLivre) {
        this.cityLivre = cityLivre;
    }

    public String getRegionFactur() {
        return RegionFactur;
    }

    public void setRegionFactur(String regionFactur) {
        RegionFactur = regionFactur;
    }

    public String getRegionLivre() {
        return RegionLivre;
    }

    public void setRegionLivre(String regionLivre) {
        RegionLivre = regionLivre;
    }

    public String getAreaCodeFactur() {
        return areaCodeFactur;
    }

    public void setAreaCodeFactur(String areaCodeFactur) {
        this.areaCodeFactur = areaCodeFactur;
    }

    public String getAreaCodeLivre() {
        return areaCodeLivre;
    }

    public void setAreaCodeLivre(String areaCodeLivre) {
        this.areaCodeLivre = areaCodeLivre;
    }

    public String getCountryFactur() {
        return CountryFactur;
    }

    public void setCountryFactur(String countryFactur) {
        CountryFactur = countryFactur;
    }

    public String getCountryLivre() {
        return CountryLivre;
    }

    public void setCountryLivre(String countryLivre) {
        CountryLivre = countryLivre;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getAddressBank() {
        return addressBank;
    }

    public void setAddressBank(String addressBank) {
        this.addressBank = addressBank;
    }

    public BigDecimal getMaxCredit() {
        return maxCredit;
    }

    public void setMaxCredit(BigDecimal maxCredit) {
        this.maxCredit = maxCredit;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getDueInDays() {
        return dueInDays;
    }

    public void setDueInDays(int dueInDays) {
        this.dueInDays = dueInDays;
    }

    private String email;

    private boolean active =true ;
    private HashSet<String> tags;

    @Size(max = 100)
    private String notes;

    private String address="";

    private Boolean isDeleted=false;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    public void addTags(HashSet<String> tags) {
        for(String t :tags){
            this.tags.add(t);
        }
    }

}
