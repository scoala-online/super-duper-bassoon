package org.scoalaonline.api.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.scoalaonline.api.serializer.SongSerializer;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "artist")
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "idArtist")
public class Artist
{
  //--------------- Fields ---------------
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "artist_id")
  private long idArtist;

  @Column(name = "artist_name", nullable = false, length = 50)
  private String name;

  @ManyToMany
  @JoinTable(
    name = "song_artist",
    joinColumns = @JoinColumn(name = "artist_id"),
    inverseJoinColumns = @JoinColumn(name = "song_id")
  )
  @JsonSerialize(using = SongSerializer.class)
  private List<Song> songs;

  public Artist() {
  }

  //--------------- Getters ---------------

  public long getIdArtist() {
    return idArtist;
  }

  public String getName() {
    return name;
  }

  public List<Song> getSongs() {
    return new ArrayList<Song>(songs);
  }

  //--------------- Setters ---------------

  public void setIdArtist(long idArtist) {
    this.idArtist = idArtist;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSongs(List<Song> songs) {
    this.songs = new ArrayList<Song>(songs);
  }

  //--------------- Equals & Hashcode ---------------


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Artist)) return false;
    Artist artist = (Artist) o;
    return idArtist == artist.idArtist &&
      Objects.equals(name, artist.name) &&
      Objects.equals(songs, artist.songs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idArtist, name, songs);
  }
}
