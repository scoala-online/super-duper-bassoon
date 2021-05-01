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
  class ArtistId {
    private UUID idArtist;

    public ArtistId(UUID idArtist) {
      this.idArtist = idArtist;
    }

    public UUID getIdArtist() {
      return idArtist;
    }

    public void setIdArtist(UUID idArtist) {
      this.idArtist = idArtist;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ArtistId)) return false;
      ArtistId artistId = (ArtistId) o;
      return Objects.equals(idArtist, artistId.idArtist);
    }

    @Override
    public int hashCode() {
      return Objects.hash(idArtist);
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
    List<ArtistId> ids = new ArrayList<>();
    for (Artist artist : artists){
      ArtistId id = new ArtistId(artist.getIdArtist());
      ids.add(id);
    }
    jsonGenerator.writeObject(ids);
  }
}

