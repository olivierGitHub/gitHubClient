import React from 'react';

import $ from 'jquery';
import Reflux from 'reflux';
import RepositoriesActions from '../actions/RepositoriesActions.js';
import RepositoriesStore from '../stores/RepositoriesStore.js';


class Repository extends Reflux.Component {

  constructor(props) {
      super(props);

      this.store = RepositoriesStore;

      this.state = {
         repo: null,
         error: false,
         owner: ''
      }
      this.handleRepos = this.handleRepos.bind(this);
      this.requestServer = this.requestServer.bind(this);
      this._onChangeOwnerValue = this._onChangeOwnerValue.bind(this);
      this.handleSearch = this.handleSearch.bind(this);
      };

   componentDidMount(){
      this.requestServer(this.props.match.params.user);
      //RepositoriesActions.getAllRepos(this.props.match.params.user);
   }


   requestServer(owner){
    if (owner){
      var ctx = this;
      $.ajax({
          type: "GET",
          url: "http://localhost:8080/repositories/"+owner,
          contentType: "application/json; charset=utf-8",
          crossDomain: true,
          dataType: "json",
          success: function(data){
            if(data[0]){
              ctx.setState({repo:data});
              ctx.setState({error:false});
            }else{
              ctx.setState({error:true});
            }
          },
          failure: function(errMsg) {
              alert(errMsg);
          }
        });
      }else{
        this.setState({error:true});
      }
   }

   handleRepos(){
      return(<div>
        <p> here is all the repositories of the owner <b>{this.state.repo[0].owner}</b></p>
        <br />
        {
          this.state.repo.map(function(repo){
            return (
              <div key={repo.id}>
                <a href={repo.commitsUrl}> {repo.name} </a>
              </div>)
          })
        }
        </div>)
   }

   _onChangeOwnerValue(e){
      this.setState({owner:e.target.value})
   }

   handleSearch(){
    this.props.history.push("/repositories/"+this.state.owner);
  }


  render() {
    return (
      <div>
      <div className="row homepage">
            <div className="commonOverlay"></div>
            <div className="homepageOverlay">
              <h1 className="homepageTitle"> GitHub Api Client </h1>
              <h4 className="homepageSubTitle">please enter a github owner in the field below</h4>
              <form onSubmit={this.handleSearch} className="homepageInput col-sm-offset-4 col-sm-4">

                <input
                  type="text"
                  value={this.state.owner}
                  onChange={this._onChangeOwnerValue}
                  placeholder="type a github owner here" />
                </form>
              </div>
        </div>
        <br />
        {
          this.state.repo
          ?
          this.handleRepos()
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

export default Repository;
