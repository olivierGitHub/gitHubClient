import CommitHelper from '../../helpers/commitHelper.js';
import React, { Component } from 'react';
import Highcharts  from './configuration/configurationHighChart.js';

class CommitsProjection extends Component {

  componentDidMount() {

    var commitsByDate = CommitHelper.groupBy(this.props.commits, 'date');

    var data = [];
    for(var key in commitsByDate) {
      var subData = [];
      subData[0] = Date.UTC(Number(key.substring(0,4)), Number(key.substring(5,7)) -1, Number(key.substring(8,10)));
      subData[1] = commitsByDate[key].length;
      data.push(subData);
    }


    Highcharts.chart('highchartsContainerProjection', {
    chart: {
        type: 'spline'
    },
    title: {
        text: 'Commits projection'
    },
    xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the dummy year
            month: '%e. %b',
            year: '%b'
        },
        title: {
            text: 'Date'
        }
    },
    yAxis: {
        title: {
            text: 'number of commits'
        },
        min: 0
    },
    tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
    },

    plotOptions: {
        spline: {
            marker: {
                enabled: true
            }
        }
    },

    series: [{
        name: 'commits',
        // Define the data points. All series have a dummy year
        // of 1970/71 in order to be compared on the same x axis. Note
        // that in JavaScript, months start at 0 for January, 1 for February etc.
        data: data
    }]
});

    }


  render() {
    return (
      <div id="highchartsContainerProjection" className="highchartsDiv">
      </div>
      )
  }

}

export default CommitsProjection;
