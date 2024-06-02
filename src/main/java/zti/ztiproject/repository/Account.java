package zti.ztiproject.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Represents an account entity.
 */
@Document
public class Account implements UserDetails {

    @Id
    private String id;
    @Field
    private String password;

    /**
     * Constructs an Account object with the provided ID and password.
     * @param id the account ID
     * @param password the account password
     */
    public Account(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /**
     * Retrieves the username of the account.
     * @return the username
     */
    public String getUsername() {
        return id;
    }

    /**
     * Sets the ID of the account.
     * @param id the account ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the account.
     * @return the account ID
     */
    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * Retrieves the password of the account.
     * @return the account password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account.
     * @param password the account password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
