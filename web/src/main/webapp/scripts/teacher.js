
function readTask(disc,student,attemptId){
	var tmr = setTimeout(function grw(){
    dojo.xhrPost({
        content:{disciplineId: disc,studentId:student,attemptId:attemptId},
        url: "TaskHasBeenReadServlet.c",
        error:function fe3(e){console.debug(e)}
    })},200);
}

function incrementCw(spec,doWhat){
    dojo.xhrPost({
        content:{disc: disc,spec:spec,semestr:semestr,dowhat:doWhat},
        url: "incrementCw.c",
        load:function f4qw(){dijit.byId("contentPane").refresh()},
        error:function ff423(e){console.debug(e)}
    });
}

function gggg(id1){
    if(dojo.byId(id1).style.display == "none"){
        dojo.fx.wipeIn({node: id1, duration: 1}).play();
    }else{
        dojo.fx.wipeOut({node: id1, duration: 1}).play();
    }
}

function text_g(id1){
    switch(id1.nextSibling.value){
        case "1":
            id1.innerHTML = "Не зачтено";
            id1.nextSibling.value=2
            id1.style.color = "#FF4500";
            break;
        case "2":
            id1.innerHTML = "Зачтено"
            id1.nextSibling.value=3
            id1.style.color = "#2CBA2D";
            break;
        case "3":
            id1.innerHTML = "\u041dе пров\u0435рено"
            id1.nextSibling.value=1
            id1.style.color = "#000000";
            break;

    }
}

function sendResults(){
    dojo.xhrPost({
        form:dojo.doc.cwValues,
        url: "updateProgress.c",
        load: function f23(type,response,evt){
            var error = false;
            var data;
            response = response.xhr.responseText;
            try{
                data = dojo.fromJson(response);
            }catch(e){
                console.debug(e)
                error = true;
            }
            error = !error?(data.status.toString()!='succes'):error;
            if(!error){
                dijit.byId("tab_cw").refresh()
            }
        },
        error:function f34(e){console.debug(e)}
    });
}

function sendEmptyCw(id,number){
    dojo.xhrPost({
        content:{disc:disc,numberCw:number,id:id,semestr:semestr},
        url: "../putEmptyControlWork.c",
        load: function f12(e){dijit.byId("contentPane").refresh()},
        error:function f31(e){console.debug(e)}
    });
}

var groupReport = function(percent){
	return "Оценено: " + Math.floor((percent*this.maximum)) + " из " + this.maximum + " групп";
}

var cwReport = function(percent){
	return "Просмотрено: " + Math.floor((percent*this.maximum)) + " из " + this.maximum + " работ";
}

function requestResult(type,response,evt){
    response = response.xhr.responseText;
    var error = false;
    var data;
    try{
        data = dojo.fromJson(response);
    }catch(e){
        console.debug(e)
        error = true;
    }
    error = !error?(data.status.toString()!='succes'):error;
    if (!error){
        dojo.byId('succesResult').innerHTML="<p class=\"success\">Сохранение данных произошло успешно.</p>";
    }else{
        dojo.byId('succesResult').innerHTML="<p class=\"failed\">Сохранение данных сломалось.</p>";
    }
    var tmout = setTimeout(function fr3(){dojo.byId('succesResult').innerHTML="";},2000);
}
function attend(id,el){
    el.disabled = 1;
  dojo.xhrGet({
             form: dojo.byId("Attend"+id),
             url: "setAttendance.c",
             load: requestResult,
             handleAs: 'json'
        });
}
