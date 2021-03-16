package org.scoalaonline.api.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artist")

public class Artist
{
  //--------------- Fields ---------------
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "artist_id")
  private long id;

  @Column(name = "artist_name", nullable = false, length = 50)
  private String name;

  @ManyToMany(mappedBy = "artists")
  private Set<Song> songs;

  public Artist() {
  }

  //--------------- Getters ---------------

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Song> getSongs() {
    return new HashSet<Song>(songs);
  }

  //--------------- Setters ---------------

  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSongs(Set<Song> songs) {
    this.songs = new HashSet<Song>(songs);
  }

  //--------------- Equals & Hashcode ---------------

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Artist)) return false;
    Artist artist = (Artist) o;
    return id == artist.id &&
      Objects.equals(name, artist.name) &&
      Objects.equals(songs, artist.songs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, songs);
  }
}
