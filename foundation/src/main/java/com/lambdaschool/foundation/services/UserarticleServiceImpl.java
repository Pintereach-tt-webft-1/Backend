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
 * Implements the UserarticleService Interface
 */
@Transactional
@Service(value = "userarticleService")
public class UserarticleServiceImpl
    implements UserarticleService
{
    /**
     * Connects this service to the Userarticle model
     */
    @Autowired
    private UserarticleRepository userarticleRepository;

    /**
     * Connects this service to the User Service
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
        userarticleRepository.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Userarticle findUserarticleById(long id)
    {
        return userarticleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Userarticle with id " + id + " Not Found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        if (userarticleRepository.findById(id)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(userarticleRepository.findById(id)
                .get()
                .getUser()
                .getUsername()))
            {
                userarticleRepository.deleteById(id);
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
        Userarticle updateUserarticle)
    {
        if (userarticleRepository.findById(userarticleid)
            .isPresent())
        {
            if (helperFunctions.isAuthorizedToMakeChange(userarticleRepository.findById(userarticleid)
                .get()
                .getUser()
                .getUsername()))
            {
                Userarticle userarticle = findUserarticleById(userarticleid);
                userarticle.setArticletitle(updateUserarticle.getArticletitle());
                userarticle.setCategory(updateUserarticle.getCategory().toLowerCase());
                userarticle.setPriority(updateUserarticle.getPriority());
                return userarticleRepository.save(userarticle);
            } else
            {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        } else
        {
            throw new ResourceNotFoundException("Userarticle with id " + userarticleid + " Not Found!");
        }
    }

    @Transactional
    @Override
    public Userarticle save(Userarticle newArticle)
    {
        User currentUser = userService.findUserById(newArticle.getUser().getUserid());

        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
//            Userarticle newUserArticle = new Userarticle(currentUser,
//                articletitle, category, priority);
//            return userarticleRepository.save(newUserArticle);

            return userarticleRepository.save(newArticle);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }
}
