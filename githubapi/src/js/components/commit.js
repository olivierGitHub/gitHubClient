import React from 'react';
import Reflux from 'reflux';
import CommitsActions from '../actions/CommitsActions.js';
import CommitsStore from '../stores/CommitsStore.js';
import CommitHelper from '../helpers/commitHelper.js';

import CommitsProjection from './subCommit/commitsProjection.js';
import UserImpact from './subCommit/userImpact.js';


class Commit extends Reflux.Component {

  constructor(props) {
      super(props);

      this.store = CommitsStore;

      this.handleCommitters = this.handleCommitters.bind(this);
      };

   componentDidMount(){
     CommitsActions.getAll(this.props.match.params.user, this.props.match.params.repo);
   }


   handleCommitters(){

    var groupByCommitter = CommitHelper.groupBy(this.state.commits, 'committerName');
    var committers = [];
    for(var k in groupByCommitter) committers.push(k);

    return(<div>
      <p> here is all the committers on the repository <b>{this.props.match.params.repo}</b></p>
      <br />
      {
        committers.map(function(committer){
          return (
            <div key={committer}>
              <span>{committer}</span>
            </div>)
        })
      }
      </div>)
    }


      render() {
        return (
          <div>

            <br />
            {
              this.state.commits
              ?
              <div>
                <ul className="nav nav-pills">
                  <li className="active"><a data-toggle="pill" href="#allCommityers">All Committers</a></li>
                  <li><a data-toggle="pill" href="#usersImpacts">Users impacts</a></li>
                  <li><a data-toggle="pill" href="#commitsProjection">Commits projection</a></li>
                </ul>
                <div className="tab-content">
                  <div id="allCommitters" className="tab-pane fade in active">
                    {this.handleCommitters()}
                  </div>
                  <div id="usersImpacts" className="tab-pane fade">
                    <UserImpact commits={this.state.commits} />
                  </div>
                  <div id="commitsProjection" className="tab-pane fade">
                    <CommitsProjection commits={this.state.commits} />
                  </div>
                </div>

              </div>
              :
                this.state.error
                ?
                <p> No results found</p>
                :
                <div className="loader"></div>
            }
            </div>
        );
      }
    }

export default Commit;
