import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import SongList from './pages/SongList';
import CreateSong from './pages/CreateSong';

import './App.css';

export function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path={['/songs', '']} component={SongList} />
          <Route exact path={'/create'} component={CreateSong} />
        </Switch>
      </Router>
    </div>
  );
}
export default App;
