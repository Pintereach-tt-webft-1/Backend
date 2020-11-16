package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.Userarticle;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD Repository connecting Userarticle to the rest of the application
 */
public interface UserarticleRepository
    extends CrudRepository<Userarticle, Long>
{
}
