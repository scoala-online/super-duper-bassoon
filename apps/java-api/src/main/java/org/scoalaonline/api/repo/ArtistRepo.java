package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, Long>{
  public List<Artist> getAllBySongs (Song song);
}
