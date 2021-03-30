package org.scoalaonline.api.service;

import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.repo.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
  @Autowired
  ArtistRepo artistRepo;

  public List<Artist> getAllArtists () {
    return artistRepo.findAll();
  }

  public Optional<Artist> getArtistById (UUID id){
    return artistRepo.findById(id);
  }

  public Artist addArtist (Artist artist) {
    Artist artistToSave = new Artist();

    artistToSave.setName(artist.getName());
    artistToSave.setSongs(artist.getSongs());

    return artistRepo.save(artistToSave);
  }

  public Artist updateArtist (UUID id, Artist artist) {
    Artist artistToUpdate = artistRepo.findById(id).get();

    if (artist.getName() != null) {
      artistToUpdate.setName(artist.getName());
    }

    if (artist.getSongs() != null) {
      artistToUpdate.setSongs(artist.getSongs());
    }

    return artistRepo.save(artistToUpdate);
  }

  public void deleteArtist (UUID id) {
    artistRepo.deleteById(id);
  }

  public boolean artistExists (UUID id) {
    return artistRepo.existsById(id);
  }
}
