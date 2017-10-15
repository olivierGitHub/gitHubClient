import React from 'react';
import Reflux from 'reflux';
import CommitsActions from '../actions/CommitsActions.js';
import CommitsStore from '../stores/CommitsStore.js';

import CommitsProjection from './subCommit/commitsProjection.js';
import UserImpact from './subCommit/userImpact.js';
import AllCommitters from './subCommit/allCommitters.js';


class Commit extends Reflux.Component {

  constructor(props) {
      super(props);

      this.store = CommitsStore;
      };

   componentDidMount(){
     CommitsActions.getAll(this.props.match.params.user, this.props.match.params.repo);
   }


  render() {
    return (
      <div>
        <div className="row homepage">
            <div className="commitsOverlay"></div>
            <div className="homepageOverlay">
              <h2 className="homepageTitle"> Dashboard </h2>
            </div>
        </div>

        {
          this.state.commits
          ?
          <div>
            <ul className="nav nav-tabs">
              <li className="active"><a data-toggle="tab" href="#allCommitters">All Committers</a></li>
              <li><a data-toggle="tab" href="#usersImpacts">Users impacts</a></li>
              <li><a data-toggle="tab" href="#commitsProjection">Commits projection</a></li>
            </ul>
            <div className="tab-content">
              <div id="allCommitters" className="tab-pane fade in active">
                <AllCommitters commits={this.state.commits} repo={this.props.match.params.repo}/>
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
