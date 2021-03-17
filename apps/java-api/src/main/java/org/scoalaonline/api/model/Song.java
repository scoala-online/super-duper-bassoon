package org.scoalaonline.api.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "song")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
public class Song
{
  //--------------- Fields ---------------
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "song_id")
  private long id;

  @Column(name = "song_name", length = 50)
  private String name;

  @Column(name = "release_date")
  private LocalDate releaseDate;

  @Column(name = "song_genre", length = 20)
  private String genre;

  @ManyToMany
  @JoinTable(
    name = "song_artist",
    joinColumns = @JoinColumn(name = "song_id"),
    inverseJoinColumns = @JoinColumn(name = "artist_id")
  )
  private Set<Artist> artists;

  public Song() {

  }

  //--------------- Getters ---------------


  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public String getGenre() {
    return genre;
  }

  public Set<Artist> getArtists() {
    return new HashSet<Artist>(artists);
  }

  //--------------- Setters ---------------


  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setArtists(Set<Artist> artists) {
    this.artists = new HashSet<Artist>(artists);
    ;
  }

  //--------------- Equals & Hashcode ---------------

  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof Song)) return false;
    if (!super.equals(object)) return false;
    Song song = (Song) object;
    return id == song.id &&
      java.util.Objects.equals(name, song.name) &&
      java.util.Objects.equals(releaseDate, song.releaseDate) &&
      java.util.Objects.equals(genre, song.genre) &&
      java.util.Objects.equals(artists, song.artists);
  }

  public int hashCode() {
    return Objects.hash(super.hashCode(), id, name, releaseDate, genre, artists);
  }
}
