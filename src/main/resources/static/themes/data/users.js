    $(function() {
    $('form').submit(function() {
       document.getElementById('username').style.display = 'none';
       document.getElementById('full_name').style.display = 'none';
       document.getElementById('email').style.display = 'none';
       document.getElementById('phone').style.display = 'none';
       document.getElementById('program').style.display = 'none';
       document.getElementById('county').style.display = 'none';
     //  document.getElementById("sidenav").setAttribute("hidden", "hidden");
      // document.getElementById("navpanel").style.display = 'none';
       $("#loading-div-background").show();
                    var username = document.getElementById("username").value;
                    var full_name = document.getElementById("full_name").value;
                    var email = document.getElementById("email").value;
                    var phone = document.getElementById("phone").value;
                    var program = document.getElementById("program").value;
                    var county = document.getElementById("county").value;
//                    var filename = new FormData($("#upload")[3]);
//                    var filen = new FormData($("#upload")[3]);
                    var getUrl = window.location;
                    var baseurls =  getUrl.origin + '/' +getUrl.pathname.split('/')[1]+'/createaccount';
                       console.log($('#filename')[0].files[0])
                      var formData = new FormData();
                      var files = $('#filename')[0].files[0];
                      formData.append("file", files);
                      formData.append("username", username);
                      formData.append("full_name", full_name);
                      formData.append("email",email);
                      formData.append("phone",phone);
                      formData.append("program",program);
                      formData.append("county",county);

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
                                   document.getElementById('username').style.display = 'block';
                                   document.getElementById('full_name').style.display = 'block';
                                   document.getElementById('email').style.display = 'block';
                                   document.getElementById('phone').style.display = 'block';
                                   document.getElementById('partner').style.display = 'block';
                                   document.getElementById('county').style.display = 'block';
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