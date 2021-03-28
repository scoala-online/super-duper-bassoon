package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song, Long>{

}
