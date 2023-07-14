package com.gdsc.CGLH.repository;


import com.gdsc.CGLH.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationRepository {
    private final EntityManager em;

    /**
     * save, find
     */
    public void save(Location location) {
        if(location.getId() == null) {
            em.persist(location);
        }
    }

    public Location findOne(Long id) {return em.find(Location.class, id);}

    public List<Location> findAll() {
        return em.createQuery("select l from Location l", Location.class)
                .getResultList();
    }

}
