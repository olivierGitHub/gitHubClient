import React from 'react';
import Reflux from 'reflux';
import CommitsActions from '../actions/CommitsActions.js';
import CommitsStore from '../stores/CommitsStore.js';


class Commit extends Reflux.Component {

  constructor(props) {
      super(props);

      this.store = CommitsStore;


      this.handleCommits = this.handleCommits.bind(this);
      };

   componentDidMount(){
      CommitsActions.getAll(this.props.match.params.user, this.props.match.params.repo);
   }

   handleCommits(){
      return(<div>
        <p> here is all the commits on the repository <b>{this.props.match.params.repo}</b></p>
        <br />
        {
          this.state.commits.map(function(commit){
            return (
              <div key={commit.sha}>
                <span>{commit.name}</span>
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
              this.handleCommits()
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
