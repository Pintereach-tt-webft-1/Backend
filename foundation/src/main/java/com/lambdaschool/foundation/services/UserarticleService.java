package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Userarticle;

import java.util.List;


/**
 * The Service that works with the Userarticle Model
 * <p>
 * Note: Articles are added through the add user process
 */
public interface UserarticleService
{
    /**
     * Returns a list of all users and their articles
     *
     * @return List of users and their articles
     */
    List<Userarticle> findAll();

    /**
     * Returns the user article combination associated with the given id
     *
     * @param id The primary key (long) of the user article combination you seek
     * @return The user article combination (Userarticle) you seek
     */
    Userarticle findUserarticleById(long id);

    /**
     * Remove the user article combination referenced by the given id
     *
     * @param id The primary key (long) of the user article combination you seek
     */
    void delete(long id);

    /**
     * Replaces the article of the user article combination you seek
     *
     * @param userarticleid  The primary key (long) of the user article combination you seek
     * @param articletitle The new article title (String) for this user article combination
     * @return The Userarticle object that you updated including the new article address
     */
    Userarticle update(
        long userarticleid,
        String articletitle);

    /**
     * Add a new User Article combination
     *
     * @param userid       the userid of the new user article combination
     * @param articletitle the article title of the new user article combination
     * @return the new user article combination
     */
    Userarticle save(
        long userid,
        String articletitle);
}
