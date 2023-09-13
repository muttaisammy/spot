    $(function() {
    $('form').submit(function() {
       document.getElementById('facility').style.display = 'none';
       document.getElementById('year').style.display = 'none';
       document.getElementById('month').style.display = 'none';
     //  document.getElementById("sidenav").setAttribute("hidden", "hidden");
      // document.getElementById("navpanel").style.display = 'none';
       $("#loading-div-background").show();
                    var facility = document.getElementById("facility").value;
                    var year = document.getElementById("year").value;
                    var month = document.getElementById("month").value;
                    var filename = new FormData($("#upload")[3]);
                    var filen = new FormData($("#upload")[3]);
                    var getUrl = window.location;
                    var baseurls =  getUrl.origin + '/' +getUrl.pathname.split('/')[1]+'/uploadFile';
                     // alert(baseurls);
                     //alert(filename);
                      console.log($('#filename')[0].files[0])
                      var formData = new FormData();
                      var files = $('#filename')[0].files[0];
                      formData.append("file", files);
                      formData.append("facility", facility);
                      formData.append("year", year);
                      formData.append("month",month);
                        $.ajax({
                                url: baseurls,
                                type: 'POST',
                                data: formData,
                                enctype: 'multipart/form-data',
                                contentType : false,
                                cache : false,
                                processData : false,
                                success: function (result) {
                                var x = result;
                               // alert(x);
                                   document.getElementById('facility').style.display = 'block';
                                   document.getElementById('year').style.display = 'block';
                                   document.getElementById('month').style.display = 'block';
                                $(".txtnav").hide();
                               //  document.getElementById("sidenav").removeAttribute("hidden");
                               //  document.getElementById("navpanel").style.display = 'block';

                              //  $("#loading-div-background").hide();
                                 //console.log(result);
                                   $.toast({ heading: 'Success',
                                          text: 'Record Operation Successfully',
                                          showHideTransition: 'slide',
                                          icon: 'success',
                                          position : 'top-right',
                                          hideAfter : 7000,
                                          stack : 5,
                                          })
                                        window.setTimeout(function(){location.reload()},7000)

                                  }
                        });

        return false;
    });
});