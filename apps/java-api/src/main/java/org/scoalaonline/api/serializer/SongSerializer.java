package org.scoalaonline.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.scoalaonline.api.model.Artist;
import org.scoalaonline.api.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SongSerializer extends StdSerializer<List<Song>> {
  class SongId {
    private UUID idSong;

    public SongId(UUID idSong) {
      this.idSong = idSong;
    }

    public UUID getIdSong() {
      return idSong;
    }

    public void setIdSong(UUID idSong) {
      this.idSong = idSong;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof SongId)) return false;
      SongId songId = (SongId) o;
      return Objects.equals(idSong, songId.idSong);
    }

    @Override
    public int hashCode() {
      return Objects.hash(idSong);
    }
  }

  public SongSerializer(){
    this(null);
  }
  public SongSerializer(Class<List<Song>> t) {
    super(t);
  }

  @Override
  public void serialize(List<Song> songs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    List<SongId> ids = new ArrayList<>();
    for (Song song : songs){
      SongId id  = new SongId(song.getIdSong());
      ids.add(id);
    }
    jsonGenerator.writeObject(ids);
  }
}
