package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "feature_account_setting")
public class FeatureAccountSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public FeatureAccountSetting() {
    }

    public FeatureAccountSetting(Feature feature, Account account) {
        this.feature = feature;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
