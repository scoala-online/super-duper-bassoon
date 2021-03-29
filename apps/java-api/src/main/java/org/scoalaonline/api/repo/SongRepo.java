package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepo extends JpaRepository<Song, UUID>{

}
