// считывает данные об определенном модуле по его id
// если change = true, то просиходит запись измененных данных в json, если false, то создаются соответствующие слайдеры
function readData(id,change){
        var marks;
          store.fetchItemByIdentity({
             identity: id,
              onItem: function(item){
                    if(change==false){
                         marks = parseInt(store.getValue(item,"weight"))/100*globalMarks;
                         dojo.byId("pmain").innerHTML = "Всего за модуль: " + store.getValue(item,"weight") + "% (<b>"+ marks + " баллов</b>)";
                    }
                    var attributes = store.getAttributes(item);
                    for (var j = 0; j < attributes.length; j++){
                        var values = store.getValues(item, attributes[j]);
                         for(var k = 0; k < values.length; k++){
                             var value = values[k];
                             if(store.isItem(value)){
                                 if(store.isItemLoaded(value)){
                                    if(change==true){                                       
                                        store.setValue(value, "weight", Math.floor(dijit.byId("hslider"+store.getValue(value,"id")).getValue()));
                                        store.save();
                                        dojo.byId("p"+ store.getValue(value, "id")).innerHTML = Math.floor(dijit.byId("hslider" + store.getValue(value, "id")).getValue()) + "%";
                                    }else{
                                        createSlider(store.getValue(value, "id"), store.getValue(value,"weight"),store.getValue(value, "name"), marks);
                                    }
                                }else{                                   
                                    store.loadItem({item: value, onItem: lazyLoadComplete});
                                }
                             }                             
                         }
                    }
             }
      })
 }

//считывает/записывает модули умк
function readModules(change){  
   dojo.byId("pmain").innerHTML = "Всего за семестр:  100%" + " (<b>"+ globalMarks + " баллов</b>)"; 
   store.fetch({
        onComplete: function gotList(items, request){
            dojo.forEach(items, function(i){               
                if(change==true){
                        store.setValue(i, "weight", Math.floor(dijit.byId("hslider" + store.getValue(i, "id")).getValue()));
                        store.save();
                        dojo.byId("p"+store.getValue(i, "id")).innerHTML = Math.floor(dijit.byId("hslider" + store.getValue(i, "id")).getValue()) + "%";

                }else{
                        createSlider(store.getValue(i, "id"), store.getValue(i,"weight"), store.getValue(i,"name"), globalMarks);
               }
             });
        }
   });

    if(change==true){
       activityWeight =  Math.floor(dijit.byId("hslideractiv").getValue());
       activityCount =  Math.floor(dojo.byId("inputcount").value);     
       itogWeight =  Math.floor(dijit.byId("hslideritog").getValue());
    }
    else{      
        createSlider_activ("activ", activityWeight, "Активность", globalMarks);
    }
        

}

function createSlider(id, weight, name, marks){
    var text_content = document.createElement("div");   
    dojo.byId("moduleContent").appendChild(text_content);
    dojo.byId("moduleContent").setAttribute("class","module_content_class_for_umcredact");
    text_content.setAttribute("class","umk_text_input_content");

    var text = document.createElement("font");
    text_content.appendChild(text);
    text.setAttribute("id", "ptext"+id);
    text.innerHTML = name;
    text.setAttribute("class","name_umk_redacting");
    
   /* var input = document.createElement("input");
    input.setAttribute("size", "7");
    input.id= "sinput"+id;
    input.value = weight+"%" + "("+parseInt(weight)/100*marks +")";
    input.setAttribute("readonly", true);
    input.setAttribute("class","input_style");
    text_content.appendChild(input);*/

    var  div = document.createElement("div");
    text_content.appendChild(div);

    var ol = document.createElement("ol");
    div.appendChild(ol);

    var ruleLabel = new dijit.form.HorizontalRuleLabels(
                             {container: "topDecoration",
                              style: "height:1.2em;font-size:75%;color:gray;",
                              numericMargin: 1,
                              count: 6
                              }, ol);

    var divRule = document.createElement("div");
    div.appendChild(divRule);

    var rule = new dijit.form.HorizontalRule({
                             container: "topDecoration",
                             count: 11,
                             style: "height:5px;"
                             },divRule);

    var divLabel = document.createElement("div");
    div.appendChild(divLabel);



    var slider = new dijit.form.HorizontalSlider(
                            {id:'hslider'+id,
                             name: 'hslider'+id,
                             onChange: function(){
                                  var summa = 0;
                                  var mess = "";
                                  var result;
                                  var balls = arguments[0]/100*marks;
                                 // dojo.byId("sinput"+id).value = dojo.number.format(Math.floor(arguments[0])/100,{places:0,pattern:'#%'}) + "("+ Math.floor(balls)+")";
                                  dojo.byId("sinput"+id+"div").innerHTML = dojo.number.format(Math.floor(arguments[0])/100,{places:0,pattern:'#%'}) + "(<b>"+ Math.floor(balls)+"</b>)";
                                  dojo.query("*[id^='hslider']").forEach(
                                  function(item) {                                     
                                      summa += parseInt(dijit.byId(item.id).value);
                                  })
                                  if(summa < 100){
                                      result = 100-summa;
                                      mess = "Осталось: <b>" + result + "%</b>";
                                      dojo.byId("message").setAttribute("class", "not_so_much");
                                  }
                                  else if(summa > 100){
                                      result = summa - 100;
                                      mess = "Превышено максимально допустимое значение на <b>" + result + "%</b>";
                                      dojo.byId("message").setAttribute("class", "much_more");
                                  }
                                  dojo.byId("message").innerHTML = mess;
                             },
                             value: weight,
                             maximum: 100,
                             minimum: 0,
                             showButtons: false,
                             intermediateChanges: true,
                             discreteValues: 100,
                             sizeShare: 12,
                             slideDuration: 0,
                             style: "width:85%; height: 20px; float: left;"
                             },div);


    slider.startup();
    ruleLabel.startup();
    rule.startup();
    
    var inp_bl = document.createElement("div");
    inp_bl.id = "sinput"+id+"div";
    var znach = weight+"%" + "(<b>"+parseInt(weight)/100*marks +"</b>)";
    inp_bl.setAttribute("style","float: right; margin-left: 10px; margin-top: 8px;");
    inp_bl.innerHTML = znach;
    text_content.appendChild(inp_bl);

    var otd = document.createElement("div");
    otd.setAttribute("class","otdelitel");
    text_content.appendChild(otd);
}

function createSlider_activ(id, weight, name, marks){
    var text_content = document.createElement("div");
    dojo.byId("moduleContent").appendChild(text_content);
    text_content.setAttribute("class","umk_text_input_content_activ");

    var text = document.createElement("font");
    text_content.appendChild(text);
    text.setAttribute("id", "ptext"+id);
    text.innerHTML = name;
    text.setAttribute("class","name_activ");
    
   /* var input = document.createElement("input");
    input.setAttribute("size", "7");
    input.id= "sinput"+id;
    input.value = weight+"%" + "("+parseInt(weight)/100*marks +")";
    input.setAttribute("readonly", true);
    input.setAttribute("class","input_style");
    text_content.appendChild(input);*/

    var otd = document.createElement("div");
    otd.setAttribute("class","otdelitel");
    text_content.appendChild(otd);

    var  div = document.createElement("div");
    text_content.appendChild(div);

    var ol = document.createElement("ol");
    div.appendChild(ol);

    var ruleLabel = new dijit.form.HorizontalRuleLabels(
                             {container: "topDecoration",
                              style: "height:1.2em;font-size:75%;color:gray;",
                              numericMargin: 1,
                              count: 6
                              }, ol);

    var divRule = document.createElement("div");
    div.appendChild(divRule);

    var rule = new dijit.form.HorizontalRule({
                             container: "topDecoration",
                             count: 11,
                             style: "height:5px;"
                             },divRule);

    var divLabel = document.createElement("div");
    div.appendChild(divLabel);



    var slider = new dijit.form.HorizontalSlider(
        {id:'hslider'+id,
         name: 'hslider'+id,
                             onChange: function(){
                                  var summa = 0;
                                  var mess = "";
                                  var result;
                                  var balls = arguments[0]/100*marks;
                                 // dojo.byId("sinput"+id).value = dojo.number.format(Math.floor(arguments[0])/100,{places:0,pattern:'#%'}) + "("+ Math.floor(balls)+")";
                                  dojo.byId("sinput"+id+"div").innerHTML = dojo.number.format(Math.floor(arguments[0])/100,{places:0,pattern:'#%'}) + "(<b>"+ Math.floor(balls)+"</b>)";
                                  dojo.query("*[id^='hslider']").forEach(
                                  function(item) {
                                      summa += parseInt(dijit.byId(item.id).value);
                                  })
                                  if(summa < 100){
                                      result = 100-summa;
                                      mess = "Осталось: <b>" + result + "%</b>";
                                      dojo.byId("message").setAttribute("class", "not_so_much");
                                  }
                                  else if(summa > 100){
                                      result = summa - 100;
                                      mess = "Превышено максимально допустимое значение на <b>" + result + "%</b>";
                                      dojo.byId("message").setAttribute("class", "much_more");
                                  }
                                  dojo.byId("message").innerHTML = mess;
                             },
         value: weight,
         maximum: 100,
         minimum: 0,
         showButtons: false,
         intermediateChanges: true,
         discreteValues: 100,
         sizeShare: 12,
         slideDuration: 0,
         style: "width: 85%; height: 20px; float: left;"
         },div);


    slider.startup();
    ruleLabel.startup();
    rule.startup();
    
    var inp_bl = document.createElement("div");
    inp_bl.id = "sinput"+id+"div";
    var znach = weight+"%" + "(<b>"+parseInt(weight)/100*marks +"</b>)";
    inp_bl.setAttribute("style","float: right; margin-left: 10px; margin-top: 8px;");
    inp_bl.innerHTML = znach;
    text_content.appendChild(inp_bl);

    var otd1 = document.createElement("div");
    otd1.setAttribute("class","otdelitel");
    text_content.appendChild(otd1);

    var label = document.createElement("div");
    label.innerHTML = ("Введите необходимое число посещений ");
    text_content.appendChild(label);
    label.setAttribute("class", "number_of_visits");

   /* var count = document.createElement("div");
    text_content.appendChild(count);*/

    var input = document.createElement("input");
    input.setAttribute("size", "3");
    input.id= "inputcount";
    input.value = activityCount;
    label.appendChild(input);
}

//отправляет данные о модулях и их весах на сервлет
function modulesSend(id){ 
   var param = "";
   store.fetch({
       onComplete: function gotList(items, request){
           dojo.forEach(items, function(i){
                param += store.getValue(i, "id") + "=" + store.getValue(i,"weight") + "&";
            });
        }
   });

  dojo.xhrGet({
       content: {type:'umk',id: id, weight: activityWeight, count: activityCount,itog: itogWeight, modules: param},
       url: "../updateWeight",
       load: requestResult,
       handleAs: 'json'
  });

}

//отправляет данные об элементах модуля и х весах на сервлет
function elementsSend(id){
   var tests = "";

 store.fetchItemByIdentity({
 identity: id,
  onItem: function(item){

        var attributes = store.getAttributes(item);
        for (var j = 0; j < attributes.length; j++){
            var values = store.getValues(item, attributes[j]);
             for(var k = 0; k < values.length; k++){
                 var value = values[k];
                 if(store.isItem(value)){
                     if(store.isItemLoaded(value)){
                         if(store.getValue(value, "id").indexOf('test') != -1){
                              tests += store.getValue(value, "id") + "=" + store.getValue(value,"weight") + "&";
                         }
                    }
                 }
             }
        }
    }
})
  dojo.xhrGet({
       content: {type:'module', tests: tests},
       url: "../updateWeight"      
  });
}

//создает диалоговое окно модуля
function showDialog(id){
    globalId = id;
    readData(id,false);
    dijit.byId('moduleDialog').show();
    dojo.connect(dijit.byId("moduleDialog"), "hide", destroyElement);
}

//создает диалоговое окно для всего умк
function showModuleDialog(){
    globalId = null;
    readModules(false);
    dijit.byId('moduleDialog').show();
    dojo.connect(dijit.byId("moduleDialog"), "hide", destroyElement);
}

//удаление элементов диалогового окна
function destroyElement(){     
  
     dojo.query("*[id^='hslider']").forEach(
     function(item) {       
          dijit.byId(item.id).destroy();
     })    

     dojo.query("*[id^='sinput']").forEach(
      function(item) {           
           dojo.byId(item.id).parentNode.removeChild(dojo.byId(item.id));          
      })
      dojo.query("*[id^='ptext']").forEach(
      function(item){          
          dojo.byId(item.id).parentNode.removeChild(dojo.byId(item.id));
      }
      )
      dojo.query("div",dojo.byId("moduleContent")).forEach(
          function(item){
              item.parentNode.removeChild(item);
          }
      )

     dojo.byId("errorSum").innerHTML = "";
     dojo.byId("pmain").innerHTML = "";
     dojo.byId("message").innerHTML = "";
  
}

//считает сумму весов модулей или элементов модуля
function sumModules(type){
     var sum = 0;    
     var result = 0;
     
     if(type == "umk"){
         dojo.query("*[id^='sinput']").forEach(
          function(item) {            
              sum += parseInt(dojo.byId(item.id).innerHTML);
          })
          result = sum;         
     }
     else if(type == "module"){
          dojo.query("*[id^='sinputtest']").forEach(
          function(item) {
              sum += parseInt(dojo.byId(item.id).innerHTML);
          })
          result = sum;
        
     }
    
      return result;

}

function saveData(id){   
    if(globalId == null){
        if(sumModules('umk') == 100){
             readModules(true);
             modulesSend(id);
             dijit.byId('moduleDialog').hide();
        }
        else{
            dojo.byId("errorSum").style.visibility = "visible";
            if(sumModules('umk')>100)
                dojo.byId("errorSum").innerHTML = "<p class='alert'>Общее количество баллов превышает 100%</p>";
            else
                dojo.byId("errorSum").innerHTML = "<p class='alert'>Общее количество баллов менее 100%</p>";
        }       
    }
    else{
       if(sumModules("module") == 100){
             readData(globalId, true);
             elementsSend(globalId);
             dojo.byId("module"+globalId).style.color = "#777777";
             dijit.byId('moduleDialog').hide();
       }
        else{
            dojo.byId("errorSum").style.visibility = "visible";
            if(sumModules('module')>100)
                 dojo.byId("errorSum").innerHTML = "<p class='alert'>Общее количество баллов превышает 100%</p>";
            else
                dojo.byId("errorSum").innerHTML = "<p class='alert'>Общее количество баллов менее 100%</p>";
        }          
    }     
}

//проверяет, есть ли у модуля элементы для редактирования, если есть - показать диалоговое окно
function checkModule(id){
    var count = 0;  
    store.fetchItemByIdentity({
    identity: id,
    onItem: function(item){
        var attributes = store.getAttributes(item);
        for (var j = 0; j < attributes.length; j++){           
            var values = store.getValues(item, attributes[j]);
             for(var k = 0; k < values.length; k++){
                 var value = values[k];
                 if(store.isItem(value)){
                     if(store.isItemLoaded(value)){
                         if(store.getValue(value, "id").indexOf('test') != -1){
                              //tests += store.getValue(value, "id") + "=" + store.getValue(value,"weight") + "&";
                         }
                         count++;
                    }
                 }
             }
        }
    }
})
if(count != 0)
    showDialog(id);
}


function requestResult(type,response,evt){

     dojo.query("*[id^='test']").forEach(
      function(item) {
          if(dijit.byId(item.id) != null){
               dijit.byId(item.id).destroy();
          }
      })
    response = response.xhr.responseText;   
    var data = dojo.fromJson(response);   
    if (data.status == 'succes'){       
        dijit.byId("contentPane").setHref("task.jsp?umk="+umkId);
    }   
}

function sendtest(){
    if(dojo.byId("moduleDialog")){
        dijit.byId("moduleDialog").destroy();
    }      
    
     
      dojo.xhrGet({
           content: dijit.byId("elements").getValues(),
           url: "../concatTest",
           load: requestResult,
           handleAs: 'json'
      });
}

