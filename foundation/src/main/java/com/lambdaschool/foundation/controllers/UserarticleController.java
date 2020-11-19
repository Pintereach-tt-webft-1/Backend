package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.Userarticle;
import com.lambdaschool.foundation.services.HelperFunctions;
import com.lambdaschool.foundation.services.UserarticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The entry point for client to access user, article combinations
 */
@RestController
@RequestMapping("/userarticles")
public class UserarticleController
{
    /**
     * Using the Userarticle service to process user, article combinations data
     */
    @Autowired
    UserarticleService userarticleService;

    @Autowired
    HelperFunctions helperFunctions;

    /**
     * List of all users articles
     * <br>Example: <a href="http://localhost:2019/userarticles/userarticles">http://localhost:2019/userarticles/userarticles</a>
     *
     * @return JSON list of all users articles
     */
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/userarticles",
        produces = "application/json")
    public ResponseEntity<?> listAllUserarticles()
    {
        List<Userarticle> allUserArticles = userarticleService.findAll();
        return new ResponseEntity<>(allUserArticles,
            HttpStatus.OK);
    }

    /**
     * Return the user article combination referenced by the given primary key
     * <br>Example: <a href="http://localhost:2019/userarticles/userarticle/8">http://localhost:2019/userarticles/userarticle/8</a>
     *
     * @param userarticleId the primary key of the user article combination you seek
     * @return JSON object of the user article combination you seek with a status of OK
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/userarticle/{userarticleId}",
        produces = "application/json")
    public ResponseEntity<?> getUserArticleById(
        @PathVariable
            Long userarticleId)
    {
        Userarticle ue = userarticleService.findUserarticleById(userarticleId);
        return new ResponseEntity<>(ue,
            HttpStatus.OK);
    }

    /**
     * Removes the given user email combination
     * <br>Example: <a href="http://localhost:2019/userarticles/userarticle/8">http://localhost:2019/userarticles/userarticle/8</a>
     *
     * @param userarticleid the primary key of the user article combination you wish to remove
     * @return Status of OK
     */
    @DeleteMapping(value = "/userarticle/{userarticleid}")
    public ResponseEntity<?> deleteUserArticleById(
        @PathVariable
            long userarticleid)
    {
        userarticleService.delete(userarticleid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Change the article associated with the given user article combination
     * <br>Example: <a href="http://localhost:2019/userarticles/userarticle/9/article/______">http://localhost:2019/userarticles/userarticle/9/article/______</a>
     *
     * @param userarticleid  The primary key of the user email combination you wish to change
     * @param updateArticle The updated article (Userarticle)
     * @return Status of OK
     */
    @PutMapping(value = "/userarticle/{userarticleid}", consumes = "application/json")
    public ResponseEntity<?> updateUserArticle(
        @PathVariable
            long userarticleid,
        @RequestBody
            Userarticle updateArticle)
    {
        updateArticle.setUser(helperFunctions.getCurrentUser());
        userarticleService.update(userarticleid,
            updateArticle);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Adds a new user article combination
     *
     * @param newArticle the new article of the new user article combination
     * @return A location header with the URI to the newly created user article combination and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see UserarticleService#save(Userarticle)
     */
    @PostMapping(value = "/user/article", consumes = "application/json")
    public ResponseEntity<?> addNewUserArticle(
        @Valid
        @RequestBody
            Userarticle newArticle) throws
                                 URISyntaxException
    {
        newArticle.setUser(helperFunctions.getCurrentUser());

        Userarticle newUserArticle = userarticleService.save(newArticle);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserArticleURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/{userarticleid}")
            .buildAndExpand(newUserArticle.getUserarticleid())
            .toUri();
        responseHeaders.setLocation(newUserArticleURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }
}
