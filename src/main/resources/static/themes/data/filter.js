// alert("Hapa");
$(document).ready(function() {

        let  partner ="" ;

        $('#partner').on('change', function(e) {
          var selected = $(e.target).val();
          e. preventDefault();
           // console.dir(selected);
            partner =selected.toString();
            $.get('/api/v1/facilities/countines/'+ partner +'').done(function(data) {
             $('#county').empty();
             var optiondata = "";
                for (var index = 0; index < data.length; index++) {
                    var item = data[index];
                   // alert(item["county"]);
                         optiondata += "<option value = '" + item["county"] + "'>" + item["county"] + " </option>";

                    //   $("#county").append(new Option(item["county"], item["county"]));
                    //   $('#county').append('<option value="five" selected="selected">Five</option>');
                    //   $('#subcounty').append('<option value="five" selected="selected">Five</option>');

                }

                //  $("#county").append(optiondata).prop('selected', true);
                 // $('#county').multiselect('loadOptions', options);
                 $('#county').empty();
                  $('#county')
                      .find('option')
                      .remove()
                      .end()
                      .append(optiondata)
                      //.val('whatever')
                      .multiselect();


                  $('#subcounty').multiselect( 'reload');


            });
          //  console.log(selected.toString());

        });

       /* $('#county').on('change', function(e) {

          console.log(partner.length);

          if (partner.length == 0){
           $("#partner").html("**This is Required");
            $("#partner").prop('required',true);

           alert("This is Required");
          }else{
          // console.log(partner);
           var selected = $(e.target).val();
            console.log(selected + " partner "+ partner );
            }
        }); */

 $('#subcounty').multiselect({
              columns: 1,
              placeholder: 'Select options',
              search   : true,
                  selectAll: true,
                  texts    : {
                      placeholder: 'Select States',
                      search     : 'Search States'
                  }

            });

            $('input[name="datetimes"]').daterangepicker({
                    timePicker: true,
                    startDate: moment().startOf('hour'),
                    endDate: moment().startOf('hour').add(32, 'hour'),
                    locale: {
                      format: 'M/DD hh:mm A'
                    }
                });



    });