import CommitHelper from '../../helpers/commitHelper.js';
import React, { Component } from 'react';


class AllCommitters extends Component {


  render() {
    var groupByCommitter = CommitHelper.groupBy(this.props.commits, 'committerName');
    var actualCommitters = [];
    for(var k in groupByCommitter) actualCommitters.push(k);

    if(actualCommitters.length === 0){
     return (<div className="loader"></div>)
    }

    return (<div>
      <p> here is all the committers on the repository <b>{this.props.repo}</b></p>
      <br />
      {
        actualCommitters.map(function(committer){
          return (
            <div key={committer}>
              <span>{committer}</span>
            </div>)
        })
      }
      </div>)
  }

}

export default AllCommitters;
