import React, { useEffect } from "react";
import makeAnimated from "react-select/animated";
import AsyncSelect from "react-select/async";
import httpService from '../services/httpService';

export default function SongArtists (props) {
    const animatedComponents = makeAnimated();
    
    const customStyles = {
        menu: (provided) => ({
          ...provided,
          color: "black",
          borderBottom: "1px dotted black",
          padding: 10,
        }),
    };

    let defaultOptions = [];

    props.artists.forEach((element) => {
        let option = { value: element.idArtist, label: element.name };
        defaultOptions.push(option);
    });
    

    function handleChangeArtists(event) {
        let artists = [];
        if (event) {
          event.forEach((element) => {
            artists.push({ idArtist: element.value, name: element.label });
          });
        }
        props.handleChange({
          target: {
            name: "artists",
            value: artists,
          },
        });
      }
  

    const getArtists = (inputValue) =>
        httpService
            .get('/artists')
            .then((response) => {
                let artistList = response.data.filter((artist) => 
                    artist.name.toLowerCase().includes(inputValue.toLowerCase())
                )
                console.log(artistList);
                let options = [];
                artistList.forEach((element) => {
                    let option = { value: element.idArtist, label: element.name };
                    options.push(option);
                });
                return options;
            })
            .catch((e) => {
                console.log(e);
            });
    
    return (
        <>
            <label>Faculties: </label>
            <br />
            <AsyncSelect
                cacheOptions
                animatedComponents={animatedComponents}
                styles={customStyles}
                defaultValue={defaultOptions}
                defaultOptions
                isMulti
                loadOptions={getArtists}
                onChange={handleChangeArtists}
            />
        </>
    )
}