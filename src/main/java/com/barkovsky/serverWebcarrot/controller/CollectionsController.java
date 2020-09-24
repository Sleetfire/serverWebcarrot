package com.barkovsky.serverWebcarrot.controller;


import com.barkovsky.serverWebcarrot.model.Collections;
import com.barkovsky.serverWebcarrot.model.User;
import com.barkovsky.serverWebcarrot.repository.CollectionsRepository;
import com.barkovsky.serverWebcarrot.repository.UserRepository;
import com.barkovsky.serverWebcarrot.security.services.UserDetailsImpl;
import com.barkovsky.serverWebcarrot.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CollectionsController {

    @Autowired
    CollectionsRepository collectionsRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/collections")
    public ResponseEntity<List<Collections>> getAllCollections(@RequestParam(required = false) String title) {
        try {
            List<Collections> collections = new ArrayList<Collections>();

            if (title == null)
                collections.addAll(collectionsRepository.findAll());
            else
                collections.addAll(collectionsRepository.findByTitleContaining(title));

            if (collections.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/collections/{id}")
    public ResponseEntity<Collections> getCollectionsById(@PathVariable("id") long id) {
        Optional<Collections> collectionsData = collectionsRepository.findById(id);

        if (collectionsData.isPresent()) {
            return new ResponseEntity<>(collectionsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/collections")
    public ResponseEntity<Collections> createCollections(@RequestBody Collections collections) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        User user = userRepository.findById(userDetailsImpl.getId()).get();

        try {
            Collections _collections = collectionsRepository
                    .save(new Collections(collections.getTitle(), collections.getDescription(), false, user));
            return new ResponseEntity<>(_collections, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/collections/{id}")
    public ResponseEntity<Collections> updateCollections(@PathVariable("id") long id, @RequestBody Collections collections) {
        Optional<Collections> collectionsData = collectionsRepository.findById(id);

        if (collectionsData.isPresent()) {
            Collections _collections = collectionsData.get();
            _collections.setTitle(collections.getTitle());
            _collections.setDescription(collections.getDescription());
            _collections.setPublished(collections.isPublished());
            return new ResponseEntity<>(collectionsRepository.save(_collections), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/collections/{id}")
    public ResponseEntity<HttpStatus> deleteCollections(@PathVariable("id") long id) {
        try {
            collectionsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/collections/user/{id}")
    public ResponseEntity<HttpStatus> deleteAllCollections(@PathVariable (name = "id") String userId) {

        try {

            User user = userRepository.findById(Long.parseLong(userId));
            List<Collections> collections = collectionsRepository.findByUser(user);

            collectionsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/collections/published")
    public ResponseEntity<List<Collections>> findByPublished() {
        try {
            List<Collections> collections = collectionsRepository.findByPublished(true);

            if (collections.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/collections/user/{id}")
    public ResponseEntity<List<Collections>> findByUser(@PathVariable (name = "id") String userId) {
        try {
            User user = userRepository.findById(Long.parseLong(userId));
            List<Collections> collections = collectionsRepository.findByUser(user);

            if (collections.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
