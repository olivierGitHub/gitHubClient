import Reflux from 'reflux';
import $ from 'jquery';
import CommitsActions from '../actions/CommitsActions.js';

class CommitsStore extends Reflux.Store
{
    constructor()
    {
        super();
        this.state = {
            commits:null,
            error:false
        };
        this.listenables = CommitsActions;
    }

    onGetAll(owner, repo){
        if (owner && repo){
          var ctx = this;
          $.ajax({
              type: "GET",
              url: "http://localhost:8080/commits/"+owner+"/"+repo,
              contentType: "application/json; charset=utf-8",
              crossDomain: true,
              dataType: "json",
              success: function(data){
                if(data[0]){
                  ctx.setState({commits:data});
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

    onGetSpecificCommit(user, repo)
    {
        var ctx = this;
        $.ajax({
                type: "GET",
                url: "http://localhost:8080//repositories/"+user+"/"+repo,
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function(data){
                    ctx.setState({commits:data});
                },
                failure: function(errMsg) {
                    alert(errMsg);
                }
          });
    }
}

export default CommitsStore;

