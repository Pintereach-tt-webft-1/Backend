package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.Userarticle;
import com.lambdaschool.foundation.repository.UserarticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the UseremailService Interface
 */
@Transactional
@Service(value = "useremailService")
public class UserarticleServiceImpl
    implements UserarticleService
{
    /**
     * Connects this service to the Useremail model
     */
    @Autowired
    private UserarticleRepository useremailrepos;

    /**
     * Connects this servive to the User Service
     */
    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;

    @Override
    public List<Userarticle> findAll()
    {
        List<Userarticle> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        useremailrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Userarticle findUserarticleById(long id)
    {
        return useremailrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Userarticle with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (useremailrepos.findById(id)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(useremailrepos.findById(id)
                .get()
                .getUser()
                .getUsername()))
            {
                useremailrepos.deleteById(id);
            }
        } else
        {
            throw new ResourceNotFoundException("Userarticle with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Userarticle update(
        long userarticleid,
        String articletitle)
    {
        if (useremailrepos.findById(userarticleid)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(useremailrepos.findById(userarticleid)
                .get()
                .getUser()
                .getUsername()))
            {
                Userarticle userarticle = findUserarticleById(userarticleid);
                userarticle.setUserarticle(articletitle.toLowerCase());
                return useremailrepos.save(userarticle);
            } else
            {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Useremail with id " + userarticleid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Userarticle save(
        long userid,
        String articletitle)
    {
        User currentUser = userService.findUserById(userid);

        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            Userarticle newUserArticle = new Userarticle(currentUser,
                articletitle);
            return useremailrepos.save(newUserArticle);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
