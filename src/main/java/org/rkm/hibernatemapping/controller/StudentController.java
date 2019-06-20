package org.rkm.hibernatemapping.controller;

import org.rkm.hibernatemapping.exception.ResourceNotFoundException;
import org.rkm.hibernatemapping.model.Collage;
import org.rkm.hibernatemapping.model.Student;
import org.rkm.hibernatemapping.repository.CollageRepository;
import org.rkm.hibernatemapping.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CollageRepository collageRepository;

    @GetMapping("/collage/{collageId}/comments")
    public Page<Student> getAllCommentsByPostId(@PathVariable(value = "collageId") Long collageId,
                                                Pageable pageable) {
        return studentRepository.findByCollageId(collageId, pageable);
    }

    @PostMapping("/collage/{collageId}/comments")
    public Student createComment(@PathVariable (value = "collageId") Long collageId,
                                 @Valid @RequestBody Student student) {
        return collageRepository.findById(collageId).map(collage -> {
            student.setCollage(collage);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("CollageID " + collageId + " not found"));
    }

    @PutMapping("/collage/{collageId}/student/{studentId}")
    public Student updateComment(@PathVariable (value = "collageId") Long collageId,
                                 @PathVariable (value = "student") Long studentId,
                                 @Valid @RequestBody Student commentRequest) {
        if(!collageRepository.existsById(collageId)) {
            throw new ResourceNotFoundException("PostId " + collageId + " not found");
        }

        return studentRepository.findById(studentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return studentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentId " + studentId + "not found"));
    }

    @DeleteMapping("/collage/{collageId}/student/{studentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "collageId") Long collageId,
                                           @PathVariable (value = "studentId") Long studentId) {
        return studentRepository.findByIdAndCollageId(collageId, studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + studentId + " and postId " + collageId));
    }
}