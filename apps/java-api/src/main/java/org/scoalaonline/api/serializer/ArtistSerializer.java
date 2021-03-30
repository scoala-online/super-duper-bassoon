package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Artist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArtistSerializer extends StdSerializer<List<Artist>> {
  public ArtistSerializer(){
    this(null);
  }
  public ArtistSerializer(Class<List<Artist>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Artist> artists, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<UUID> ids = new ArrayList<>();
    for (Artist artist : artists){
      ids.add(artist.getIdArtist());
    }
    jsonGenerator.writeObject(ids);
  }
}
