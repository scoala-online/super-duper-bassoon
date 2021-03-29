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

@RestController
@RequestMapping(value = "/songs")
public class SongController {
  @Autowired
  SongService songService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Song>> getAllSongs () {
    List<Song> songList = songService.getAllSongs();
    return new ResponseEntity<>(songList, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Song> getSongById (@PathVariable("id") long id){
    System.out.println(id);
    Song song = songService.getSongById(id)
      .orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, "No Song found with this ID", new ResourceNotFoundException()
      ));
    return new ResponseEntity<>(song, HttpStatus.OK);
  }

  @PutMapping(value = ("/{id}"))
  public ResponseEntity<Song> updateSong (@PathVariable("id") long id,
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
}
