import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';
import SongList from './pages/SongList';

export function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path={['/songs', '']} component={SongList} />
        </Switch>
      </Router>
    </div>
  );
}
export default App;
