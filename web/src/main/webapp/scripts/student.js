
function save_info(id, content) {
    dojo.xhrPost({
        content: {type: id,content: content},
        url: 'SendStudentInfo.c',
        error: console.debug(e)
    });
}
function save_setting(form) {
    dojo.xhrPost({
        form: 'Settings',
        url: 'SaveStudentSettings.view',
        load: function (data) {
        dojo.byId("save_settings_success").style.display = "block";
      //  console.log('succecc: ', data);
        },
        error:  function (data) {
        dojo.byId("save_settings_error").style.display = "block";
        //console.error('succecc: ', dojo.byId("save_settings_success").style.display);
         }
    });
}

function check_old_password(pass){
        dojo.xhrPost({

                        content: {password: pass},
                        url: 'CheckOldStudentPassword.view',
                        load: function (data) {
                        if (data=='bad!')dojo.byId("old_password_error").style.display = "block";
                        if (data=='good!'){
                            dojo.byId("old_password_error").style.display = "none";
                            dojo.byId("check_old_password_success").style.display = "block";
                            dojo.byId("new_password").disabled = false;
                            dojo.byId("confirm_password").disabled = false;
                           // dojo.byId("new_password").setAttribute("disabled", false);

                    }
                            
                        // console.log('succecc: ', data);

                        },
                        error:  function (data) {
                        //dojo.byId("save_settings_error").style.display = "block";
                       // console.error('error: ', '');
                         }
                    });


    //  dojo.byId("check_old_password_success").style.display = "block";
     // console.error('check: ', "");
}
 function check_password(){
      var new_pass =  dojo.byId("new_password").value;
      var confirm_pass = dojo.byId("confirm_password").value;
      if (new_pass!=confirm_pass){
           dojo.byId("confirm_password_error").style.display = "block";
           dijit.byId('button_change_password').setAttribute('disabled', true);
      }else{
           dojo.byId("confirm_password_error").style.display = "none";
           dojo.byId("check_confirm_password_success").style.display = "block";
         //  dijit.byId('callButton').setAttribute('disabled', false);
         //  dijit.byId("button_change_password").disabled = false;
         dijit.byId('button_change_password').setAttribute('disabled', false);
           
      }
 }
 
 function save_change_password(form) {
    dojo.xhrPost({

            form: 'change_password',
            url: 'SaveChangePassword.view',
            load: function (data) {
            dojo.byId("save_password_success").style.display = "block";
             console.log('succecc: ', data);
            },
            error:  function (data) {
             }
        });
 }

function LoadControlWork(){
      dojo.io.iframe.send({
        form: "ControlWorkForm",
        load: function fq3(){dijit.byId("contentPane").refresh()},
        url: 'loadControlWork.c',
        error: function feq3(e){dijit.byId("contentPane").refresh()}
     });
    
}

function ShowLoadFormCW(discId, number,semestr){
    console.log(discId);
   dojo.xhrPost({
       // method: "GET",
        content: {discId:discId, numberCW: number,semestr:semestr},
        form: "ControlWorkForm",
        load: function(){dijit.byId('cw_sender').show();},
        url: 'loadControlWorkParameters.c',
        error:  function feq3(e){console.debug(e)}
     });
    
}
