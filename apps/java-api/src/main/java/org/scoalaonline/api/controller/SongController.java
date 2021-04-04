package org.scoalaonline.api.controller;

import org.scoalaonline.api.exception.ResourceNotFoundException;
import org.scoalaonline.api.model.Song;
import org.scoalaonline.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/songs")
public class SongController {
  @Autowired
  SongService songService;

  @GetMapping(value = {"/test"})
  public ResponseEntity<String> getTest () {
    return new ResponseEntity<>("Wow, you made it !", HttpStatus.OK);
  }

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Song>> getAllSongs () {
    List<Song> songList = songService.getAllSongs();
    return new ResponseEntity<>(songList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Song> getSongById (@PathVariable("id") UUID id){
    System.out.println(id);
    Song song = songService.getSongById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Song found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(song, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Song> addSong (@RequestBody Song song) {
    Song savedSong = songService.addSong(song);
    return new ResponseEntity<>(savedSong, HttpStatus.CREATED);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Song> updateSong (@PathVariable("id") UUID id,
                                          @RequestBody Song song) {
    if (songService.songExists(id)) {
      Song updatedSong = songService.updateSong(id, song);
      return new ResponseEntity<>(updatedSong, HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot update non-existing Song", new ResourceNotFoundException()
      );
    }
  }

  @DeleteMapping(value = ("/{id}"))
  public ResponseEntity<HttpStatus> deleteSong (@PathVariable("id") UUID id) {
    if (songService.songExists(id)) {
      songService.deleteSong(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "Cannot delete non-existing Song", new ResourceNotFoundException()
      );
    }
  }
}
