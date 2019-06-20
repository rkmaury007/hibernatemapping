package org.rkm.hibernatemapping.repository;

import org.rkm.hibernatemapping.model.Collage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollageRepository extends JpaRepository<Collage,Long> {

}
