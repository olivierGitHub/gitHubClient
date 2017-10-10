import React, { Component } from 'react';


class Home extends Component {

  constructor(props) {
      super(props);

      this.state = {
         homeText: 'sera initialisé par le componentWillMount'
      }
   };


  render() {
    return (
      <div>
        <br />
        Hello homepage
        </div>
    );
  }
}

export default Home;
