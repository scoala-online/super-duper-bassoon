package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.model.Song;
import org.scoalaonline.api.repo.ArtistRepo;
import org.scoalaonline.api.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SongService {
  @Autowired
  SongRepo songRepo;
  @Autowired
  ArtistRepo artistRepo;

  public List<Song> getAllSongs () {
    /*List<Song> songList =  songRepo.findAll();
    songList.forEach((song) -> {
      song.setArtists(new HashSet<Artist>(artistRepo.getAllBySongs(song)));
    });*/
    return songRepo.findAll();
  }

  public Optional<Song> getSongById (long id){
    return songRepo.findById(id);
  }

  public Song addSong (Song song) {
    Song songToSave = new Song();

    songToSave.setName(song.getName());
    songToSave.setGenre(song.getGenre());
    songToSave.setReleaseDate(song.getReleaseDate());
    songToSave.setArtists(song.getArtists());

    return songRepo.save(songToSave);
  }

  public Song updateSong (long id, Song song) {
    Song songToUpdate = songRepo.findById(id).get();

    if (song.getName() != null) {
      songToUpdate.setName(song.getName());
    }

    if (song.getGenre() != null) {
      songToUpdate.setGenre(song.getGenre());
    }

    if (song.getReleaseDate() != null) {
      songToUpdate.setReleaseDate(song.getReleaseDate());
    }

    if (song.getArtists() != null) {
      songToUpdate.setArtists(song.getArtists());
    }

    return songRepo.save(songToUpdate);
  }

  public void deleteSong (long id) {
    songRepo.deleteById(id);
  }

  public boolean songExists (long id) {
    return songRepo.existsById(id);
  }
}
