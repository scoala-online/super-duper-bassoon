package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {
  @Autowired
  ArtistService artistService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Artist>> getAllArtists() {
    List<Artist> artistList = artistService.getAllArtists();
    return new ResponseEntity<>(artistList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Artist> getArtistById(@PathVariable("id") long id) {
    Artist artist = artistService.getArtistById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Artist found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(artist, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Artist> addArtist (@RequestBody Artist artist) {
    Artist savedArtist = artistService.addArtist(artist);
    return new ResponseEntity<>(savedArtist, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Artist> updateArtist (@PathVariable("id") long id,
                                              @RequestBody Artist artist) {
    if (artistService.artistExists(id)) {
      Artist updatedArtist = artistService.updateArtist(id, artist);
      return new ResponseEntity<>(updatedArtist, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Artist", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteArtist (@PathVariable("id") long id) {
    if (artistService.artistExists(id)) {
      artistService.deleteArtist(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Artist", new ResourceNotFoundException()
      );
    }
  }
}
