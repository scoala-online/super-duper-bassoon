package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongSerializer extends StdSerializer<List<Song>> {
  public SongSerializer(){
    this(null);
  }
  public SongSerializer(Class<List<Song>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Song> songs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<Long> ids = new ArrayList<>();
    for (Song song : songs){
      ids.add(song.getIdSong());
    }
    jsonGenerator.writeObject(ids);
  }
}
