
  var dataArr = [];
 $.get('/api/v1/facilities/getall').done(function(data) {
  //alert(data);
   var datavalue = [];
        for(var i = 0; i < data.length; i++) {
        const myObj = JSON.parse(data[i]);
           var item = data[i];
            var xxjj = "{ value:'" + myObj.value + "', name:'"+ myObj.name + "'}";
            datavalue.push(myObj);
       }
       var chartDom = document.getElementById('divTree');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
          title: {
            text: 'EMR Coverage',
            subtext: 'Count of EMR & Paper Facilities',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: 'Total Facilities',
              type: 'pie',
              radius: '50%',
              data: datavalue,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };

        if (option && typeof option === 'object') {
            myChart.setOption(option);
          }
          window.addEventListener('resize', myChart.resize);
         // alert(data);
 });

//=========================================================================
function getART(){
$.get('/api/v1/facilities/getall').done(function(data) {
         var datavaluee = [];
         var catvalue = [];
         for(var i = 0; i < data.length; i++) {
                          const myObj = JSON.parse(data[i]);
                           var item = data[i];
                          catvalue.push(myObj.name);
                           datavaluee.push(myObj);
                         }
alert(catvalue);
        Highcharts.chart('divChart', {

          chart: {
            type: 'column'
          },
          title: {
            text: 'Current OnART Distribution ',
            align: 'left'
          },
          xAxis: {
            categories: catvalue ['Arsenal', 'Chelsea', 'Liverpool', 'Manchester United']
          },
          yAxis: {
            min: 0,
            title: {
              text: 'Number of Persons'
            },
            stackLabels: {
              enabled: true
            }
          },
          legend: {
            align: 'left',
            x: 70,
            verticalAlign: 'top',
            y: 70,
            floating: true,
            backgroundColor:
              Highcharts.defaultOptions.legend.backgroundColor || 'white',
            borderColor: '#CCC',
            borderWidth: 1,
            shadow: false
          },
          tooltip: {
            headerFormat: '<b>{point.x}</b><br/>',
            pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
          },
          plotOptions: {
            column: {
              stacking: 'normal',
              dataLabels: {
                enabled: true
              }
            }
          },
          series: [{
            name: 'Tx_Curr',
            data: [3, 5, 1, 13]
          }, {
            name: 'Tx_New',
            data: [14, 8, 8, 12]
          }, {
            name: 'IIT',
            data: [0, 2, 6, 3]
          }]
        });
 });
 }
//============================================================================