package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ArtistSerializer extends StdSerializer<List<Artist>> {
  class ArtistIdName {
    private UUID idArtist;
    private String name;

    public ArtistIdName(UUID idArtist, String name) {
      this.idArtist = idArtist;
      this.name = name;
    }

    public UUID getIdArtist() {
      return idArtist;
    }

    public void setIdArtist(UUID idArtist) {
      this.idArtist = idArtist;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ArtistIdName)) return false;
      ArtistIdName that = (ArtistIdName) o;
      return Objects.equals(idArtist, that.idArtist) &&
        Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(idArtist, name);
    }
  }
  public ArtistSerializer(){
    this(null);
  }
  public ArtistSerializer(Class<List<Artist>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Artist> artists, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<ArtistIdName> ids = new ArrayList<>();
    for (Artist artist : artists){
      ArtistIdName id = new ArtistIdName(artist.getIdArtist(),artist.getName());
      ids.add(id);
    }
    jsonGenerator.writeObject(ids);
  }
}

