package io.ollivander.ollivanderbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    public static final Role ROLE_ADMIN = new Role(1, "ROLE_ADMIN");

    public static final Role ROLE_STAFF = new Role(2, "ROLE_STAFF");

    public static final Role ROLE_MEMBER = new Role(3, "ROLE_MEMBER");

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    private String name;

    public Role() {}

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role))
            return false;
        if (obj == this)
            return true;

        Role rhs = (Role) obj;

        if (this.name.equals(rhs.name))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
