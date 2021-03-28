package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepo extends JpaRepository<Artist, Long>{
}
