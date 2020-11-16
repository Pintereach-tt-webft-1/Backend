package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * The entity allowing interaction with the userarticles table
 * <p>
 * requires each combination of user and userarticle to be unique. The same email cannot be assigned to the same user more than once.
 */
@Entity
@Table(name = "userarticles")
public class Userarticle
    extends Auditable
{
    /**
     * The primary key (long) of the userarticles table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userarticleid;

    /**
     * Article (String) for this user. Cannot be nullable.
     */
    @NotNull
//    @Email
    private String userarticle;

    /**
     * Category (String) for this article. Cannot be nullable.
     */
    

    /**
     * The userid of the user assigned to this article is what is stored in the database.
     * This is the entire user object!
     * <p>
     * Forms a Many to One relationship between userarticles and users.
     * A user can have many articles.
     */
    @ManyToOne
    @NotNull
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "userarticles",
        allowSetters = true)
    private User user;

    /**
     * The default controller is required by JPA
     */
    public Userarticle()
    {
    }

    /**
     * Given the parameters, create a new useremail object
     *
     * @param user      the user (User) assigned to the email
     * @param userarticle userarticle (String) for the given user
     */
    public Userarticle(
        User user,
        String userarticle)
    {
        this.userarticle = userarticle;
        this.user = user;
    }

    /**
     * Getter for userarticleid
     *
     * @return the primary key (long) of this userarticle object
     */
    public long getUserarticleid()
    {
        return userarticleid;
    }

    /**
     * Setter for userarticleid. Used for seeding data
     *
     * @param userarticleid the new primary key (long) of this userarticle object
     */
    public void setUserarticleid(long userarticleid)
    {
        this.userarticleid = userarticleid;
    }

    /**
     * Getter for userarticle
     *
     * @return the article (String) associated with this userarticle object in lowercase
     */
    public String getUserarticle()
    {
        return userarticle;
    }

    /**
     * Setter for userarticle
     *
     * @param userarticle the article (String) to replace the one currently assigned to this userarticle object, in lowercase
     */
    public void setUserarticle(String userarticle)
    {
        this.userarticle = userarticle;
    }


    /**
     * Getter for user
     *
     * @return the user object associated with this userarticle.
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Setter for user
     *
     * @param user the user object to replace the one currently assigned to this userarticle object
     */
    public void setUser(User user)
    {
        this.user = user;
    }
}
