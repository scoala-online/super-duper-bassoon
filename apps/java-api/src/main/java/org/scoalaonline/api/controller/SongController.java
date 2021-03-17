package org.scoalaonline.api.controller;


import org.scoalaonline.api.model.Song;
import org.scoalaonline.api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
  @Autowired
  SongService songService;

  @GetMapping(value = {"", "/"})
  public ResponseEntity<List<Song>> getAllSongs () {
    List<Song> songList = songService.getAllSongs();
    return new ResponseEntity<>(songList, HttpStatus.OK);
  }

  @PostMapping(value = {"", "/"})
  public ResponseEntity<Song> addSong (@RequestBody Song song) {
    Song savedSong = songService.addSong(song);
    return new ResponseEntity<>(savedSong, HttpStatus.CREATED);
  }
}
