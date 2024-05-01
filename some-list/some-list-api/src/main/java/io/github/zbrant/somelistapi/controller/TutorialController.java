package io.github.zbrant.somelistapi.controller;

import io.github.zbrant.somelistapi.model.Tutorial;
import io.github.zbrant.somelistapi.repository.TutorialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {

  @Autowired
  TutorialRepository repository;

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
    List<Tutorial> listResponseEntity = repository.findAll();
    if (listResponseEntity.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(listResponseEntity, HttpStatus.OK);
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Optional<Tutorial> tutorial = repository.findById(id);
    return tutorial.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/tutorials")
  @Transactional
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    Tutorial savedTutorial = repository.save(tutorial);
    return new ResponseEntity<>(savedTutorial, HttpStatus.CREATED);
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Optional<Tutorial> tutorialUpdated = repository.findById(id).map(tutorial_map -> {
      tutorial_map.setDescription(tutorial.getDescription());
      tutorial_map.setTitle(tutorial.getTitle());
      tutorial_map.setPublished(tutorial.isPublished());
      repository.save(tutorial_map);
      return tutorial_map;
    });

    return tutorialUpdated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/tutorials/{id}")
  @Transactional
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    try {
      repository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials")
  @Transactional
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      repository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    List<Tutorial> listResponse = repository.findByPublished(true);
    return new ResponseEntity<>(listResponse, HttpStatus.OK);
  }
}
