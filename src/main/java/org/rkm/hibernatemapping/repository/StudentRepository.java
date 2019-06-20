package org.rkm.hibernatemapping.repository;

import org.rkm.hibernatemapping.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Page<Student> findByCollageId(Long collageId, Pageable pageable);
    Optional<Student> findByIdAndCollageId(Long id, Long collageId);
}
