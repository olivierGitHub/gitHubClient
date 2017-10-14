import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import logo from '../../static/github.png';

class Navigation extends Component {
  render() {
    return (
      <nav className="navbar-default">
        <div className="container-fluid">
          <div className="navbar-header">
            <Link className="navbar-brand" to="/repositories"><img src={logo} className="logo_github" alt="logo" /></Link>
          </div>
          <ul className="nav navbar-nav pull-right">
            <li><Link to="/help">Help</Link></li>
          </ul>
        </div>
      </nav>
    );
  }
}

export default Navigation;
