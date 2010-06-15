/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function requestResult(type,response,evt){
    response = response.xhr.responseText;
    var data = dojo.fromJson(response);
    var tmout = setTimeout(function tout(){
        dojo.byId("rsloaddiv").style.display = "none";
        },2000);
    if (data.status == 'succes'){
       dojo.byId("rsloaddiv").style.display = "block";
       dojo.byId("resultload").innerHTML = "Данные успешно загружены";

    }
    else if(data.status == 'error'){
        dojo.byId("rsloaddiv").style.display = "block";
        dojo.byId("resultload").innerHTML = "Произошла ошибка. Попробуйте еще раз";
    }
}

function sendinfo(){     
    dojo.xhrGet({
       content: dijit.byId("info").getValues(),     
       url: "../TestEdit",
       load: requestResult,
       handleAs: 'json'
     }); 
}

function toDate(datetime){  
    //var date = datetime.substring(0,10);
    var date = datetime.substring(6,10) + "-" + datetime.substring(3,5) + "-" + datetime.substring(0,2);  
    return date;
}

function toTime(datetime){
 var time = datetime.substring(11);
 return time;
}

function showQuestList(){
    if (dojo.byId("questionList").style.display != "none")
        dojo.byId("questionList").style.display = "none";
    else
        dojo.byId("questionList").style.display = "block";   
}

function showControlQuestions(){
    if (dojo.byId("controlList").style.display != "none")
        dojo.byId("controlList").style.display = "none";
    else
        dojo.byId("controlList").style.display = "block";
}

function sendQuestions(){  
    dojo.xhrGet({
       form: dojo.doc.questForm,
       url: "../UpdateQuestion",
       load: requestResult,
       handleAs: 'json'
  });
}

function sendControlQuestions(){
    console.debug(dojo.doc.questControlForm);
    dojo.xhrGet({
       form: dojo.doc.questControlForm,
       url: "../UpdateQuestion",
       load: requestResult,
       handleAs: 'json'
  });
}

