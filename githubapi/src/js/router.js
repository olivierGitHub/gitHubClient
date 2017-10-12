import './app.css';
import React, { Component } from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Commit from './components/commit.js';
import Repository from './components/repository.js';
import NotFound from './components/notFound.js';
import Navigation from './components/navigation.js';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="main">
          <Navigation />
          <div className="content">
            <Switch>
              <Route path="/" exact component={Repository} />
              <Route path="/repositories" exact component={Repository} />
              <Route path="/repositories/:user" exact component={Repository} />
              <Route path="/repositories/:user/:repo" exact component={Repository} />
              <Route path="/commits" exact component={Commit} />
              <Route path="/commits/:user/:repo" exact component={Commit} />
              <Route path="/commits/:user/:repo/:sha" exact component={Commit} />
              <Route component={NotFound} />
            </Switch>
          </div>
        </div>
        </Router>
    );
  }
}

export default App;
