package org.scoalaonline.api.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.scoalaonline.api.serializer.ArtistSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "song")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "idSong")
public class Song
{

  //--------------- Fields ---------------
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "song_id")
  private long idSong;

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
  @JsonSerialize(using = ArtistSerializer.class)
  private List<Artist> artists;

  public Song() {

  }

  //--------------- Getters ---------------


  public long getIdSong() {
    return idSong;
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

  public List<Artist> getArtists() {
    return new ArrayList<Artist>(artists);
  }

  //--------------- Setters ---------------


  public void setIdSong(long idSong) {
    this.idSong = idSong;
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


  public void setArtists(List<Artist> artists) {
    this.artists = new ArrayList<Artist>(artists);
  }

  //--------------- Equals & Hashcode ---------------


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Song)) return false;
    Song song = (Song) o;
    return Objects.equals(idSong, song.idSong) &&
      Objects.equals(name, song.name) &&
      Objects.equals(releaseDate, song.releaseDate) &&
      Objects.equals(genre, song.genre) &&
      Objects.equals(artists, song.artists);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idSong, name, releaseDate, genre, artists);
  }
}
