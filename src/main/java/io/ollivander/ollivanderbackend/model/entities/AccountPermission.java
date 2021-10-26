package io.ollivander.ollivanderbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "account_permission")
public class AccountPermission implements java.io.Serializable {

    public static final Integer VIEW = 8;

    public static final Integer CREATE = 4;

    public static final Integer UPDATE = 2;

    public static final Integer DELETE = 1;

    public static final Integer ASSIGNABLE = 0;

    public static final Integer FULL_PERMISSION = VIEW + CREATE + UPDATE + DELETE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @Column(name = "permission")
    private Integer permission;

    public AccountPermission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public AccountPermission(Integer id, Integer accountId, Integer featureId, String featureKey, Integer permission) {
        this.id = id;
        this.account = new Account(accountId);
        this.feature = new Feature();
        this.feature.setId(featureId);
        this.feature.setFeatureKey(featureKey);
        this.permission = permission;
    }

    public AccountPermission(Account account, Feature feature, Integer permission) {
        this.account = account;
        this.feature = feature;
        this.permission = permission;
    }

}