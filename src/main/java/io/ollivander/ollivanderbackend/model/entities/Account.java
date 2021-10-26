package io.ollivander.ollivanderbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(name = "password", nullable = true, length = 45)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "first_name", length = 100)
    private String firstName = "";

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "last_login")
    private Date lastLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_action_time", length = 19)
    private Date lastActionTime;

    @Column(name = "last_ip")
    private String lastIp;

    @Column(name = "display_name")
    private String displayName;

    private boolean live;

    @Column(name = "email_backup")
    private String emailBackup;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = {
            @JoinColumn(name = "account_id", nullable = false, updatable = true) }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = true) })
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", orphanRemoval = true)
    @Cascade({ CascadeType.ALL })
    private Set<AccountPermission> accountPermission = new HashSet<>();

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", orphanRemoval = true)
    @Cascade({ CascadeType.ALL })
    private Set<FeatureAccountSetting> featureAccountSettings = new HashSet<>();

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(Integer accountId) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastActionTime() {
        return lastActionTime;
    }

    public void setLastActionTime(Date lastActionTime) {
        this.lastActionTime = lastActionTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public String getEmailBackup() {
        return emailBackup;
    }

    public void setEmailBackup(String emailBackup) {
        this.emailBackup = emailBackup;
    }

    public Set<AccountPermission> getAccountPermission() {
        return accountPermission;
    }

    @Transient
    public Set<AccountPermission> getAccountPermission(Integer accountId) {

        Set<AccountPermission> permissions = new HashSet<>();
        if (!CollectionUtils.isEmpty(accountPermission)) {
            for (AccountPermission ap : accountPermission) {
                if (ap.getAccount().getId().equals(accountId)) {
                    permissions.add(ap);
                }
            }
        }
        return permissions;
    }

    @Transient
    public void updatePermission(Feature feature, Integer permission, Account account) {

        // get all permissions by all sponsor
        // ( side effect apply directly to field this.accountPermission
        Set<AccountPermission> allPermisions = this.accountPermission;

        Iterator<AccountPermission> iterator = allPermisions.iterator();
        AccountPermission newPermission = null;

        // will update or add new if not existed
        boolean found = false;
        while (iterator.hasNext()) {
            AccountPermission existedPermission = iterator.next();

            // if update for specific sponsor
            if (account != null && existedPermission.getAccount() != null
                    && !account.equals(existedPermission.getAccount())) {
                continue;
            }

            // if feature found then create new from existing with new value
            if (existedPermission.getFeature().getId().equals(feature.getId())) {
                // update permission value
                existedPermission.setPermission(permission);
                found = true;
                break;
            }
        }

        // add new account permission if not found
        if (found == false) {
            newPermission = new AccountPermission(this, feature, permission);
            allPermisions.add(newPermission);
        }
    }

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty("roles")
    public String[] getRoleNames() {
        ArrayList<String> result = new ArrayList<String>();
        for (Role role : getRoles()) {
            result.add(role.getName());
        }
        return result.toArray(new String[result.size()]);
    }

    @Transient
    boolean isAdmin() {
        return getRoles().contains(Role.ROLE_ADMIN);
    }

    @Transient
    boolean isStaff() {
        return getRoles().contains(Role.ROLE_STAFF);
    }

    @Transient
    public String getRole() {
        String role = "unknown";
        if (isAdmin()) {
            role = Role.ROLE_ADMIN.getAuthority();
        } else if (isStaff()) {
            role = Role.ROLE_STAFF.getAuthority();
        } else if (isStaff()) {
            role = Role.ROLE_MEMBER.getAuthority();
        }

        return role;
    }

    @Transient
    @JsonIgnore
    public int getRoleId() {
        int role = 0;
        if (isAdmin()) {
            role = Role.ROLE_ADMIN.getId();
        } else if (isStaff()) {
            role = Role.ROLE_STAFF.getId();
        } else if (isStaff()) {
            role = Role.ROLE_MEMBER.getId();
        }

        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account))
            return false;

        if (obj == this)
            return true;

        Account acc = (Account) obj;

        return this.getId() != null && acc.getId() != null && this.getId().equals(acc.getId());
    }

    @Override
    public int hashCode() {
        return this.getId() == null ? super.hashCode() : this.getId();
    }
}
