import React, { Component } from 'react';
import { Link } from 'react-router-dom';


class NotFound extends Component {


  render() {
    return (
      <div className="notFound">
        <br />
        <h1 className="homepageTitle"> 404 not found</h1>
        <div className="homepageTitle">click <Link to="/">here</Link> to go back to the main page</div>
        </div>
    );
  }
}

export default NotFound;
