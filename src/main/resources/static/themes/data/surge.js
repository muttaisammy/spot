$.get('/rest/v1/api/surge/summaries').done(function(data) {
var options =  {
          chart: {
           renderTo: 'surge_output',
              zoomType: 'xy'
          },
          title: {
              text: 'AMPATH Weekly Surge Report (Visits)',
              align: 'left'
          },
          subtitle: {
              text: 'Source: AMRS & KenyaEMR',
              align: 'left'
          },
          xAxis: [{
              categories: {} ,
              crosshair: true
          }],
          yAxis: [{ // Primary yAxis
              labels: {
                  //format: '{value}',
                  style: {
                      color: Highcharts.getOptions().colors[2]
                  }
              },
              title: {
                  text: 'On Schedule',
                  style: {
                      color: Highcharts.getOptions().colors[2]
                  }
              },
              opposite: true

          }, { // Secondary yAxis
              gridLineWidth: 0,
              title: {
                  text: 'Total Visits',
                  style: {
                      color: Highcharts.getOptions().colors[0]
                  }
              },
              labels: {
                 // format: '{value} mm',
                  style: {
                      color: Highcharts.getOptions().colors[0]
                  }
              }

          }, { // Tertiary yAxis
              gridLineWidth: 0,
              title: {
                  text: 'Visit this week',
                  style: {
                      color: Highcharts.getOptions().colors[1]
                  }
              },
              labels: {
                 // format: '{value}',
                  style: {
                      color: Highcharts.getOptions().colors[1]
                  }
              },
              opposite: true
          }],
          tooltip: {
              shared: true
          },
          legend: {
              layout: 'vertical',
              align: 'left',
              x: 80,
              verticalAlign: 'top',
              y: 55,
              floating: true,
              backgroundColor:
                  Highcharts.defaultOptions.legend.backgroundColor || // theme
                  'rgba(255,255,255,0.25)'
          },
          series: [{
              name: 'Total Visits',
              type: 'column',
              yAxis: 1,
              data: [{}],
              tooltip: {
                  valueSuffix: ' clients'
              }

          }, {
              name: 'Visit this week',
              type: 'spline',
              yAxis: 2,
              data: [{}],
              marker: {
                  enabled: false
              },
              dashStyle: 'shortdot',
              tooltip: {
                  valueSuffix: ' clients'
              }

          }, {
              name: 'On Schedule',
              type: 'spline',
              data: [{}],
              tooltip: {
                  valueSuffix: ' clients'
              }
          }],
          responsive: {
              rules: [{
                  condition: {
                      maxWidth: 500
                  },
                  chartOptions: {
                      legend: {
                          floating: false,
                          layout: 'horizontal',
                          align: 'center',
                          verticalAlign: 'bottom',
                          x: 0,
                          y: 0
                      },
                      yAxis: [{
                          labels: {
                              align: 'right',
                              x: 0,
                              y: -6
                          },
                          showLastLabel: false
                      }, {
                          labels: {
                              align: 'left',
                              x: 0,
                              y: -6
                          },
                          showLastLabel: false
                      }, {
                          visible: false
                      }]
                  }
              }]
          }
      };

        let total_visits = [];
        let categories = [];
        let visit_this_week =[];
        let on_schedule =[];

         for(var i = 0; i < data.length; i++) {
                var item = data[i];
                categories.push(item['year_week']);
                total_visits.push(parseFloat(item['scheduled_this_week']));
                visit_this_week.push(parseFloat(item['visit_this_week']));
                on_schedule.push(parseFloat(item['on_schedule']));

         }
         alert(visit_this_week);
                options.xAxis.categories = categories;
                options.series[0].data = total_visits;
                options.series[1].data = total_visits;
                options.series[2].data = on_schedule;
                var chart = new Highcharts.Chart(options);

});