import Reflux from 'reflux';
import $ from 'jquery';
import RepositoriesActions from '../actions/RepositoriesActions.js';

class RepositoriesStore extends Reflux.Store
{
    constructor()
    {
        super();
        this.state = {
            repos:null
        };
        this.listenables = RepositoriesActions;
    }

    onGetAllRepos(user)
    {
        var ctx = this;
        $.ajax({
                type: "GET",
                url: "http://localhost:8080/repositories/"+user,
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function(data){
                    ctx.setState({repos:data});
                },
                failure: function(errMsg) {
                    alert(errMsg);
                }
          });
    }

    onGetSpecificRepository(user, repo)
    {
        var ctx = this;
        $.ajax({
                type: "GET",
                url: "http://localhost:8080//repositories/"+user+"/"+repo,
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function(data){
                    ctx.setState({repos:data});
                },
                failure: function(errMsg) {
                    alert(errMsg);
                }
          });
    }
}

export default RepositoriesStore;

