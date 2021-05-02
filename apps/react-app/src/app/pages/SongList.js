import httpService from '../services/httpService';
import { Row, td, Container, Button, Table } from 'react-bootstrap';
import React, { useEffect, useState } from 'react';

export default function SongList(props) {
  const [songs, setSongs] = useState([]);
  console.log('asd');

  function getSongList() {
    httpService
      .get('/songs')
      .then((response) => {
        console.log('getSongList Response :');
        console.log(response.data);

        setSongs(response.data);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  function removeObjectFromList(list, objectId) {
    let k = 0;
    while (k < list.length) {
      if (list[k].idSong == objectId) {
        break;
      }
      k++;
    }
    list.splice(k, 1);
  }

  function showDate(list) {
    return (
      list[2].toString() + '/' + list[1].toString() + '/' + list[0].toString()
    );
  }

  function deleteSong(id) {
    httpService
      .delete('/songs/' + id)
      .then((response) => {
        console.log('deleteSong Response :');
        console.log(response);
        if (response.status == 202) {
          let newSongList = JSON.parse(JSON.stringify(songs));
          removeObjectFromList(newSongList, id);
          setSongs(newSongList);
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }

  useEffect(() => {
    getSongList();
  }, []);

  return (
    <Container>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>genre</th>
            <th>Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {songs.map((song, index) => {
            return (
              <tr key={index}>
                <td>{index + 1}</td>
                <td>{song.name}</td>
                <td>{song.genre}</td>
                <td>{showDate(song.releaseDate)}</td>
                <td>
                  <Button
                    variant="light"
                    onClick={() => deleteSong(song.idSong)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </Container>
  );
}
