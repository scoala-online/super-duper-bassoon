import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import httpService from '../services/httpService';
import { Button } from 'react-bootstrap';
import { Label } from 'reactstrap';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import SongArtists from '../components/SongArtists';
import InputField from '../components/InputField';
import { useHistory } from 'react-router';

export default function UpdateSong(props) {
  let history = useHistory();
  const params = useParams();
  const id = params.id;

  const [song, setSong] = useState({
    idSong: null,
    name: '',
    genre: '',
    releaseDate: null,
    artists: [],
  });
  const [releaseDate, setReleaseDate] = useState(null);

  function handleChange(event) {
    const { name, value } = event.target;
    setSong({
      ...song,
      [name]: value,
    });
  }

  function handleSubmit() {
    httpService.put('/songs/' + id, song).then((response) => {
      console.log('update song Response :');
      console.log(response.data);
      history.push('/songs');
    });
  }

  useEffect(() => {
    let date = releaseDate;
    if (date == null) {
      date = new Date();
    }

    let dateList = [
      date.getFullYear(),
      parseInt(
        date.getMonth() < 9 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
      ),
      parseInt(date.getDate() < 10 ? '0' + date.getDate() : date.getDate()),
    ];

    setSong({
      ...song,
      releaseDate: dateList,
    });
  }, [releaseDate]);

  function getSong() {
    httpService
      .get('/songs/' + id)
      .then((response) => {
        console.log('getSong Response :');
        let data = response.data;
        console.log(response.data);
        setSong(data);
        var date = new Date(
          parseInt(data.releaseDate[0]),
          parseInt(data.releaseDate[1]) - 1,
          parseInt(data.releaseDate[2])
        );
        setReleaseDate(date);
      })
      .catch((e) => {
        console.log(e);
      });
  }

  useEffect(() => {
    getSong();
  }, []);

  return (
    <div>
      <header>
        <h1>Update Song</h1>
      </header>
      <form>
        <InputField
          handleChange={handleChange}
          label="Name"
          name="name"
          value={song.name}
        />

        <InputField
          handleChange={handleChange}
          label="Genre"
          name="genre"
          value={song.genre}
        />

        <Label>Release Date</Label>
        <br></br>
        <DatePicker
          selected={releaseDate}
          showYearDropdown
          scrollableMonthYearDropdown
          onChange={(date) => setReleaseDate(date)}
        />
        <br></br>

        <SongArtists handleChange={handleChange} artists={song.artists} />
        <br></br>

        <Button onClick={handleSubmit}>Update Song</Button>
      </form>
    </div>
  );
}
