function hide_form(id){
    dojo.fx.wipeOut({node: id,duration: 200}).play();
}
function show_form(id){
    dojo.query("*[id^='answ_form_']").forEach(
        function(item) {
            if((item.style.display != 'none') && (item.id!=id)){
                var b = item.style;
                hide_form(item.id);
            }
        }
    )
	dojo.fx.wipeIn({node: id,duration: 200}).play();
}

function requestResult(type,response,evt){
  
    response = response.xhr.responseText;
    var data = dojo.fromJson(response);   
    if (data.status == 'succes'){         
            dijit.byId("contentPane").setHref("messages_list.jsp?list="+listT+"&page="+crtPage);
    }
}
//восстановление сообщения
function recovery_message(id){ 
      var block = dojo.byId('message_'+id);    
      dojo.removeAttr(block, "style");
      var new_tr = dojo.byId('del_'+id);
      var fadeArgs = {node: new_tr,duration: 500, onEnd: function(){
                dojo._destroyElement(new_tr);
      }};
      dojo.fadeOut(fadeArgs).play();
  
    type='del';
     msgs=id+'=true';

    dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../RecoveMsg.c",
  
       handleAs: 'json'
   });
}

//действия над одним сообщением когда читаем его
function action_one_message(type, id){
  //type="del";
   msgs= id+ "=true";
  dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../ArchDelMsg",
      load: function(data, ioargs){
          if(type=="del"){
                    dojo.style(dojo.byId("delete_message_success"), {'display':'block'});
                    block=dojo.byId("message_content");                  
                    var fadeArgs = {
                     node: block,
                     duration: 500
                     };
                     dojo.fadeOut(fadeArgs).play();
          }else{
                window.location.href = "main.jsp#msg";
          }


         },
       handleAs: 'json'
   });
}

//восстановление одного сообщения
function recovery_one_message(id){  
    type='del';
    msgs=id+'=true';
    dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../RecoveMsg.c",
       load: function(data, ioargs){
                    dojo.style(dojo.byId("delete_message_success"), {'display':'none'});
                    block=dojo.byId("message_content");
                    var fadeArgs = {
                     node: block,
                     duration: 500
                     };
                     dojo.fadeIn(fadeArgs).play();

         },
       handleAs: 'json'
   });
}

    
//архивирование и удаление если id==null удалеям выделенные объекты
function action_del_arch(type, id){
   
    var msgs = "";
    //цвет блока при удалениее-архивировании
    var color="#fbc7c7";
    //
    if (type=='arch'){color="#b6d8fe";    
    }else{
       // dijit.byId('DeleteDialog').show();
    }
//удаление-архивирование одного письма
    if (id!=null){
        var block = dojo.byId('message_'+id);
        if (type!='arch'&&type!='re_arch'){
        //добавляем сверху что  удалено
        var them=dojo.byId('theme_of_letter_'+id);
        
        var theme_of_letter = them.childNodes[1].innerHTML;
        var temp_ar = new Array();
        var temp_ar1 = new Array();
        temp_ar = theme_of_letter.split('>');
        temp_ar1 = temp_ar[1].split('<');
        theme_of_letter = temp_ar1[0];

        var new_tr=dojo.create("td", {colspan:3, style: { opacity: 0 },  innerHTML: "Сообщение <b>'"+theme_of_letter+"'</b> удалено. <a class='recovery_link' onclick='recovery_message("+id+")' href='javascript:void(0)'>Восстановить!?</a> " }, dojo.create("tr", { id: "del_"+id }, dojo.byId('mess_list_content'), "first") , "first");
        //dojo.attr(new_tr, "style", {border: "1px solid silver", width: "457px", padding: "10px", background: "#F2FAFB"});
        dojo.attr(new_tr, "class", "del_result_mes");
   }
  
      var anim = dojo.fx.chain([
            dojo.animateProperty({
                node: block,
                duration: 500,
                properties: {
                backgroundColor: color
            }}),
            dojo.fadeOut({ node: block})
        ]);
        dojo.connect(anim, "onEnd", function(){
        dojo.style(block, {'display':'none'});
        });
        anim.play();
         if (type!='arch'&&type!='re_arch'){
            var fadeArgs = {node: new_tr,duration: 2000 };
            dojo.fadeIn(fadeArgs).play();
         }
        msgs=id + "=true";
   

    }else{
        //удаляем все выделенные письма
    dojo.query(".message_checked").forEach(
      function(item){
         console.log(item);
         id=item.id.toString().substr(8);
         console.log(item.id.toString().substr(8));
        var block = dojo.byId('message_'+id);
   //если элемент еще и видим
       if (dojo.style(block, "opacity")=="1"){
                msgs+= id+ "=true" + "&";            
                var block = dojo.byId('message_'+id);
                if (type!='arch'){
                    //добавляем сверху что  удалено
                    var them=dojo.byId('theme_of_letter_'+id);

                    var theme_of_letter = them.childNodes[1].innerHTML;
                    var temp_ar = new Array();
                    var temp_ar1 = new Array();
                    temp_ar = theme_of_letter.split('>');
                    temp_ar1 = temp_ar[1].split('<');
                    theme_of_letter = temp_ar1[0];

                    var new_tr=dojo.create("td", {colspan:3, style: { opacity: 0 },  innerHTML: "Сообщение <b>'"+theme_of_letter+"'</b> удалено. <a class='recovery_link' onclick='recovery_message("+id+")' href='javascript:void(0)'>Восстановить!?</a> " }, dojo.create("tr", { id: "del_"+id }, dojo.byId('mess_list_content'), "first") , "first");
                    //dojo.attr(new_tr, "style", {border: "1px solid silver", width: "527px", padding: "10px"});
                    dojo.attr(new_tr, "class", "del_result_mes");
                   }
                var anim = dojo.fx.chain([
                    dojo.animateProperty({
                        node: block,
                        duration: 500,
                        properties: {
                            backgroundColor: color
                    }}),
                    dojo.fadeOut({ node: block})
                ]);
                dojo.connect(anim, "onEnd", function(){
                dojo.style(block, {'display':'none'});
                });

                anim.play();
              if (type!='arch'){
                    var fadeArgs = {node: new_tr,duration: 2000 };
                    dojo.fadeIn(fadeArgs).play();
                 }

             }
         }
     );
 }
  if (type!='re_arch'){
  //отправили запрос на сервер
   dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../ArchDelMsg",
       //load: requestResult,
       handleAs: 'json'
   });}
else{
    //для разархиврования
     dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../RecoveMsg.c",
       //load: requestResult,
       handleAs: 'json'
   });}



}

function actionById(type, id){
    var msgs = "";
    if(id != null || id != ""){
       msgs = id + "=" + "true&";
    }
console.debug("sdf"+type);
   dojo.xhrGet({
       content: {type: type, msgs: msgs},
       url: "../ArchDelMsg",
       load: requestResult,
       handleAs: 'json'
   });

   window.location = '#msg';

}

function Select_All() {  
    dojo.query("*[id^='message']").forEach(
        function(item){
         dojo.toggleClass(item, "message_checked");
        })
}

function select_div_mess(id) {
   // console.log(id);
    div=dojo.byId("message_"+id);
    dojo.toggleClass(div, "message_checked");
  
}

function toPage(page){   
    dijit.byId("contentPane").setHref("messages_list.jsp?list="+listT+"&page="+page);

}

function resultAnswer(id, type,response){
    //console.log("id ",type, response.status);
               var dj= dijit.byId("text_"+id).setValue("");
               //  console.log(dj);
                hide_form("answ_form_"+id);
               // var tmout = setTimeout(function tout(){
                  //  dojo.byId("requestResult_"+id).style.display = "none";
                  //  },5000);

                 var fadeOut = dojo.fadeOut({node: "requestResult_"+id,duration: 700});
                 var fadeIn  = dojo.fadeIn ({node: "requestResult_"+id,duration: 700});
                                  
                 if (response.status == 'succes'){
                     console.log("success");
                     dojo.attr("requestResult_"+id, "class", "success_res_of_answer");
                     dojo.byId("requestResult_"+id).style.display = "block";
                     dojo.fx.chain([ fadeIn, fadeOut]).play();
                     
                     var tmout = setTimeout(function tout(){
                        dojo.byId("requestResult_"+id).style.display = "none";
                     },1400);
                     
                     dojo.byId("requestResult_"+id).innerHTML = "Ваше сообщение отправлено";
                 }
                 else if(response.status == 'error'){
                     dojo.attr("requestResult_"+id, "class", "error_res_of_answer");
                     dojo.byId("requestResult_"+id).style.display = "block";
                     dojo.fx.chain([ fadeIn, fadeOut]).play();

                     var tmout = setTimeout(function tout(){
                        dojo.byId("requestResult_"+id).style.display = "none";
                     },1400);

                     dojo.byId("requestResult_"+id).innerHTML = "�?звините, возникла ошибка. Попробуйте отправить сообщение позже";
                 }
}


function quickAnswer(id, type){
    console.log(type);


  if(dijit.byId("text_"+id).value != null && dijit.byId("text_"+id).value != ""){

     dojo.xhrPost({
       content: {msg: id, text: dijit.byId("text_"+id).value },
       url: "../SendAnswer.c",
       load: function(data, ioargs){
           if (type=='alone'){
                 dojo.style(dojo.byId("post_message_success"), {'display':'block'});
                    block=dojo.byId("message_content");
                    var fadeArgs = {
                     node: block,
                     duration: 500
                     };
                     dojo.fadeOut(fadeArgs).play();
           }else{
                resultAnswer(id, type ,data);
           }
          
      },
       handleAs: 'json'
   });

  }
}

function read_msg(id){    
    if(id != null && id != "")
         dijit.byId("contentPane").setHref("message_write.jsp?id="+id);
}
//отправка сообщения
function post_letter(){
           dojo.byId('rich_text').value = dijit.byId('main_text').getValue();
           var h=dijit.byId("btnH");
            console.log(h);
           dojo.xhrPost({
               form: dojo.byId("form_post_letter"),
               url: "../SendLetter.c",
               load: function(data, args){                           
                     //check - we have files?
                      if (dojo.byId('hFiles').childNodes.length==0){
                          console.error("nothing!!");
                             hide_form_message();
                      }else{
                        console.log("something!!!");
                          h.submit(dojo.byId("formH"));

           }
               },
               handleAs: "text"

              });
  }


 function hide_form_message(){
       dojo.style(dojo.byId("send_message_success"), {'display':'block'});
        block=dojo.byId("main_message");
        console.warn("block!!!!");
        var fadeArgs = {
         node: block,
         duration: 500
         };
         dojo.fadeOut(fadeArgs).play();

}

  function aesd (id,ch){
          dijit.byId('layout'+id).refresh();
               dijit.byId('layout'+id).onLoad = function (){
               if (dojo.byId(ch).checked) {
               dojo.query("*[id^='foto']").forEach(function(pp){pp.style.display = 'inline'});
               dojo.query(".img_for_mess").forEach(function (cl){cl.style.width = 60});
               dojo.query(".img_for_mess").forEach(function (cl){cl.style.height = "60px"});
               dojo.query(".text_for_mess_2").forEach(function (cll){cll.style.width = "190px"});
               makeScrollPane('scrollPane'+id);

                } else {
                dojo.query("*[id^='foto']").forEach(function(p){p.style.display = 'none'});
                dojo.query(".img_for_mess").forEach(function (cl){cl.style.width = 10});
                dojo.query(".img_for_mess").forEach(function (cl){cl.style.height = "0px"});
                dojo.query(".text_for_mess_2").forEach(function (cll){cll.style.width = "238px"});
                makeScrollPane('scrollPane'+id);
               }}
         }

        function check_img(ch){
         aesd(1,ch);aesd(4,ch);

        /* if (dojo.byId('layout1').selected){aesd(1,ch)}
         if (dojo.byId('layout2').selected){aesd(2,ch)}
         if (dojo.byId('layout3').selected){aesd(3,ch)}
         if (dojo.byId('layout4').selected){aesd(4,ch)}*/
        }

        function removeSubstring(myStr, mySubstr) {

              myStr = myStr.substring(1, myStr.length-1);
              return myStr;
        }

        function del_getter(getter){
            console.log(dojo.byId('getters').value);
            dojo.byId('getters').value=dojo.byId('getters').value.replace(getter+";",'');
            node=dojo.byId(getter);
            node.parentNode.removeChild(node);
            console.log(dojo.byId('getters').value);
        }


        function show_select_getters(){
            //console.log("!!", dijit.byId("select_getters").value)
            dijit.byId('dialog_for_mess').show();
        }

        //выбор адрессатов в окошке выбора
        function add_new_getter(id, checken){
            div_getter=dojo.byId(id);
            if (checken){
                if (div_getter==null){
                 list_getters +=id+";"; }
             }else{
                  list_getters=list_getters.replace(id+";","");
             }

        }

        //подтвердить выбор
        function  confirm_list_getters(){
            dojo.byId('getters').value+=list_getters;
            list_id =list_getters.split(';');
            for (i=0; i<list_id.length-1; i++){
               // console.log("getter_id ",list_id[i] );
                div_getter2=dojo.byId('getter - '+list_id[i])
                dojo.create("div", { id: list_id[i], innerHTML:""+div_getter2.innerHTML+'<a href="javascript:void(0)" onclick="del_getter(\''+list_id[i]+'\')"><img style="position: relative; left:4px; top: 2px;" src="../images/no.png"/></a>'}, dojo.byId('vvod_info_mes'), "first");
              //  console.log(dijit.byId('chbox - '+list_id[i]).checked);
              //  console.log(dijit.byId('chbox - '+list_id[i]));
                dijit.byId('chbox - '+list_id[i]).checked=false;
            }
            console.log(dojo.query());
           list_getters="";
           dijit.byId('dialog_for_mess').hide()

        }
        
        //отменить выбор
        function  cancel_list_getters(){
            list_getters="";
            dijit.byId('dialog_for_mess').hide()

        }
        function remove_time_text(id, text, type){
          if (type==0){
            input=dijit.byId(id);
            val= new String(text);         
            k= new String(input.attr("value"));   

            if (val.toString()==k.toString()){
             input.attr("value","");}}
          else if(type==1){

             input=dojo.byId(id);
              console.log(input.value);
              val= new String(text);
              k= new String(input.value);
              if (val.toString()==k.toString()){
              input.value="";}

            }
        }



