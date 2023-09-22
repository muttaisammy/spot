// Create the chart
$.get('/api/v1/facilities/tx_curr').done(function(data) {
         var datavaluee = [];
         var catvalue = [];
         for(var i = 0; i < data.length; i++) {
                          const myObj = JSON.parse(data[i]);
                           var item = data[i];
                          catvalue.push(myObj.name);
                           datavaluee.push(myObj);
                         }
                         //alert(catvalue);
                        // alert(data);
                         var jsonString= JSON.stringify(data);
alert(jsonString);
                        // alert(data);

Highcharts.chart('divChart', {
    chart: {
        type: 'column'
    },
    title: {
        align: 'left',
        text: 'August, 2023 ART Distribution'
    },
    subtitle: {
        align: 'left',
        text: 'Click the columns to view Sub county Distribution'
    },
    accessibility: {
        announceNewData: {
            enabled: true
        }
    },
    xAxis: {
        type: 'category'
    },
    yAxis: {
        title: {
            text: 'Tx_Curr'
        }

    },
    legend: {
        enabled: false
    },
    plotOptions: {
        series: {
            borderWidth: 0,
            dataLabels: {
                enabled: true //,
               // format: '{point.y:.1f}%'
            }
        }
    },

    tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
    },

    series: [
        {
            name: 'Counties',
            colorByPoint: true,
            data: jsonString
        }
    ]

 });
