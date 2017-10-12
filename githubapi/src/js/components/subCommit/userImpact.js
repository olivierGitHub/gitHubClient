import CommitHelper from '../../helpers/commitHelper.js';
import React, { Component } from 'react';
import Highcharts  from 'highcharts';


class UserImpact extends Component {

  componentDidMount() {

    var commitsByName = CommitHelper.groupBy(this.props.commits, 'committerName');

    var xCategories = [];
    for(var k in commitsByName) xCategories.push(k);
    var yCategories = [];
    for(var key in commitsByName) yCategories.push(commitsByName[key].length);

    var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'highchartsContainer',
            type: 'areaspline'
        },
        title: {
            text: 'Users Impact'
        },
        xAxis: {
            title: {
                text: 'number of commits'
            },
            categories: xCategories
        },
        yAxis: {
            title: {
                text: 'users'
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        series: [{
            data: yCategories
        }]
    });
  }

  render() {
    return (
      <div id="highchartsContainer" className="highchartsDiv">
      </div>
      )
  }

}

export default UserImpact;
