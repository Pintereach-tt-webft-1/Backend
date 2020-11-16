package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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
    private String articletitle;

    /**
     * Category (String) for this article. Cannot be nullable.
     */
    @NotNull
    private String category;

    /**
     * Priority (int) for this article. Cannot be nullable.
     */
    @NotNull
    private int priority = 1000;

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
     * @param articletitle articletitle (String) for the given user
     */
    public Userarticle(
        User user,
        String articletitle,
        String category,
        int priority)
    {
        this.articletitle = articletitle;
        this.user = user;
        this.category = category;
        this.priority = priority;
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
    public String getArticletitle()
    {
        return articletitle;
    }

    /**
     * Setter for userarticle
     *
     * @param userarticle the article (String) to replace the one currently assigned to this userarticle object, in lowercase
     */
    public void setArticletitle(String userarticle)
    {
        this.articletitle = userarticle;
    }

    /**
     * Getter for category
     *
     * @return the category (String) associated with this userarticle.
     */

    public String getCategory()
    {
        return category;
    }

    /**
     * Setter for category
     *
     * @param category the category (String) to replace the one currently assigned to this userarticle object
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Getter for priority
     *
     * @return the priority (int) associated with this userarticle.
     */

    public int getPriority()
    {
        return priority;
    }

    /**
     * Setter for priority
     *
     * @param priority the priority (int) to replace the one currently assigned to this userarticle object
     */
    public void setPriority(int priority)
    {
        this.priority = priority;
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
