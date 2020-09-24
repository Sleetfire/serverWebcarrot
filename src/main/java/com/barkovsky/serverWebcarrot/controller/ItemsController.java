//package com.barkovsky.serverWebcarrot.controller;
//
//
//import com.barkovsky.serverWebcarrot.model.Collections;
//import com.barkovsky.serverWebcarrot.model.Items;
//import com.barkovsky.serverWebcarrot.model.User;
//import com.barkovsky.serverWebcarrot.repository.CollectionsRepository;
//import com.barkovsky.serverWebcarrot.repository.ItemsRepository;
//import com.barkovsky.serverWebcarrot.security.services.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
////@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/collections")
//public class ItemsController {
//
//    @Autowired
//    ItemsRepository itemsRepository;
//
//    @Autowired
//    CollectionsRepository collectionsRepository;
//
//
//    @GetMapping("/{collectionsId}")
//    public ResponseEntity<List<Items>> getAllItems(@PathVariable("collectionsId") long collectionsId, @RequestParam(required = false) String title) {
//        try {
//            List<Items> items = new ArrayList<Items>();
//
//            if (title == null)
//                items.addAll(itemsRepository.findAll());
//            else
//                items.addAll(itemsRepository.findByTitleContaining(title));
//
//            if (items.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(items, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//@GetMapping("/{collectionsId}/items/{id}")
//public ResponseEntity<Items> getItemsById(@PathVariable("collectionsId") long collectionsId, @PathVariable("id" ) long id) {
//        Optional<Items> itemsData = itemsRepository.findById(id);
//
//        if (itemsData.isPresent()) {
//            return new ResponseEntity<>(itemsData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @GetMapping("/items/{id}")
////    public ResponseEntity<Items> getItemsById(@PathVariable("id") long id) {
////        Optional<Items> itemsData = itemsRepository.findById(id);
////
////        if (itemsData.isPresent()) {
////            return new ResponseEntity<>(itemsData.get(), HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////
////    @PostMapping("/items")
////    public ResponseEntity<Items> createItems(@RequestBody Items items) {
////        try {
////            Items _items =itemsRepository
////                    .save(new Items(items.getTitle(), items.getDescription(), false));
////            return new ResponseEntity<>(_items, HttpStatus.CREATED);
////        } catch (Exception e) {
////            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @PutMapping("/items/{id}")
////    public ResponseEntity<Items> updateItems(@PathVariable("id") long id, @RequestBody Items items) {
////        Optional<Items> itemsData = itemsRepository.findById(id);
////
////        if (itemsData.isPresent()) {
////            Items _items = itemsData.get();
////            _items.setTitle(items.getTitle());
////            _items.setDescription(items.getDescription());
////            _items.setPublished(items.isPublished());
////            return new ResponseEntity<>(itemsRepository.save(_items), HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
////    }
////
////    @DeleteMapping("/items/{id}")
////    public ResponseEntity<HttpStatus> deleteItems(@PathVariable("id") long id) {
////        try {
////            itemsRepository.deleteById(id);
////            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////        } catch (Exception e) {
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @DeleteMapping("/items")
////    public ResponseEntity<HttpStatus> deleteAllItems() {
////        try {
////            itemsRepository.deleteAll();
////            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////        } catch (Exception e) {
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
////
////    @GetMapping("/items/published")
////    public ResponseEntity<List<Items>> findByPublished() {
////        try {
////            List<Items> items = itemsRepository.findByPublished(true);
////
////            if (items.isEmpty()) {
////                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////            }
////            return new ResponseEntity<>(items, HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
//
//
//}
