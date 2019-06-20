package org.rkm.hibernatemapping.controller;

import org.rkm.hibernatemapping.exception.ResourceNotFoundException;
import org.rkm.hibernatemapping.model.Collage;
import org.rkm.hibernatemapping.repository.CollageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CollageController {

    @Autowired
    private CollageRepository collageRepository;

    @GetMapping("/collage")
    public Page<Collage> getAllPosts(Pageable pageable) {
        return collageRepository.findAll(pageable);
    }

    @PostMapping("/collage")
    public Collage createPost(@Valid @RequestBody Collage collage) {
        return collageRepository.save(collage);
    }

    @PutMapping("/collage/{collageId}")
    public Collage updatePost(@PathVariable Long collageId, @Valid @RequestBody Collage collageRequest) {
        return collageRepository.findById(collageId).map(collage -> {
            collage.setTitle(collageRequest.getTitle());
            collage.setDescription(collageRequest.getDescription());
            collage.setContent(collageRequest.getContent());
            return collageRepository.save(collage);
        }).orElseThrow(() -> new ResourceNotFoundException("CollageId " + collageId + " not found"));
    }


    @DeleteMapping("/collage/{collageId}")
    public ResponseEntity<?> deletePost(@PathVariable Long collageId) {
        return collageRepository.findById(collageId).map(collage -> {
            collageRepository.delete(collage);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("CollageId " + collageId + " not found"));
    }

}