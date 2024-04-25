$.get('/rest/v1/api/surge/summaries').done(function(data) {

        var total_visits = [];
        var categories = [];
        var visit_this_week =[];
        var on_schedule =[];
        var kept_appointment =[];
        var missed_appointment=[];

         for(var i = 0; i < data.length; i++) {
                var item = data[i];
                categories.push(item['year_week']);
                total_visits.push(parseFloat(item['scheduled_this_week']));
                visit_this_week.push(parseFloat(item['visit_this_week']));
                on_schedule.push(parseFloat(item['on_schedule']));
                kept_appointment.push(parseFloat(item['kept_app']))
                missed_appointment.push(parseFloat(item['missed_appointment_this_week']))
         }

// alert(visit_this_week);
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: 'Weekly Appointments Report',
            align: 'left'
        },
        subtitle: {
            text:'Source: AMRS & KenyaEMR',
            align: 'left'
        },
        xAxis: {
            categories: categories,
            crosshair: true,
            accessibility: {
                description: 'A'
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Count of Patients'
            }
        },
        tooltip: {
            valueSuffix: ' (#)'
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [
            {
                name: 'Scheduled',
                data: total_visits
            },
            {
                name: 'Kept',
                data: visit_this_week
            },
            {
                name: 'Missed',
                data: missed_appointment
            },
            {
                name: 'At Rick of IIT',
                data: missed_appointment
            }
        ]
    });
});