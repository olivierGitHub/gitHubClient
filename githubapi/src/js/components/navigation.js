import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import logo from '../../static/github.png';

class Navigation extends Component {
  render() {
    return (
        <ul className="menu expanded">
          <li><Link to="/"><img src={logo} className="logo_gl" alt="logo" /></Link></li>
          <li><Link to="/repositories/">Repository</Link></li>
          <li><Link to="/commits">Commits</Link></li>
          <li><Link to="/help">Help</Link></li>
        </ul>
    );
  }
}

export default Navigation;
