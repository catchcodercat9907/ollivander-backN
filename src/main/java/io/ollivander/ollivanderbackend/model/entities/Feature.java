package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "feature")
public class Feature implements java.io.Serializable {

    public static final String COMMODITY_KEY = "COMMODITY_KEY";
    public static final String CUSTOMER_CARE_KEY = "CUSTOMER_CARE_KEY";
    public static final String SELL_KEY = "SELL_KEY";
    public static final String PAYMENT_KEY = "PAYMENT_KEY";

    public static final Integer SELL = 1;
    public static final Integer PAYMENT = 2;
    public static final Integer COMMODITY = 3;
    public static final Integer CUSTOMER_CARE = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "feature_key", nullable = false, length = 50)
    private String featureKey;

    @Column(name = "feature_label", nullable = false, length = 50)
    private String featureLabel;

    @Column(name = "group", length = 50)
    private String group;

    @Column(name = "version", length = 50)
    private String version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public void setFeatureKey(String featureKey) {
        this.featureKey = featureKey;
    }

    public String getFeatureLabel() {
        return featureLabel;
    }

    public void setFeatureLabel(String featureLabel) {
        this.featureLabel = featureLabel;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
