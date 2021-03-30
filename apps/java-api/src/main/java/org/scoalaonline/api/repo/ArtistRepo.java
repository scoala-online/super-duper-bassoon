package org.scoalaonline.api.repo;

import org.scoalaonline.api.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepo extends JpaRepository<Artist, UUID>{
}
