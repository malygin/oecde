 function sendGrades(){
       dojo.xhrGet({
                 form:document.grades,
                 url: "putGrades.c",
                 contentType:	"text/plain;charset=utf-8",
                 load:getResponse,
                 handleAs: 'text'
            });
   }
   function getResponse(type,response,evt){
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
                dijit.byId("tab_grades").refresh()
            }else{
                dojo.byId('request_result').innerHTML='<div  class="request_resultF" style="margin-left: 20px;"><p>Возникла ошибка</p></div>';
                var tm = setTimeout(function fwer(){dojo.byId('request_result').innerHTML="";clearTimeout(tm);},1000)
            }

   }
        function validate(element){
            element.value=element.value.replace(/([^0-9])/g,'');
               if(!element.value.isNaN){
                  if(element.value>100){
                     element.value = 100;
                  }
               }else{
                   element.value = 0;
               }

        }
        function makeSum(row){
			var sum = 0;
            var pNode = "";
            dojo.query("#table input[type='text']:not(input[name='result'])").filter(function f18(item){
                if(item.parentNode.parentNode.rowIndex == row){
                    return true;
                }
            }).forEach(function f5(item){
                var current = parseInt(item.value);
                sum += current;
                pNode = item.parentNode.parentNode;
            })
            dojo.query("#table  td:not(#visits)").filter(function f19s(item){
                if(item.parentNode.rowIndex == row&&item.id!=undefined&&item.id!=''){
                    return true;
                }
            }).forEach(function f5(item, index, array){
                if(index<6){
                    var current = parseInt(item.innerHTML);
                    sum += current;

                }
            })
            if(!sum.isNaN){
                var maximum = parseInt(dojo.byId("max").innerHTML);
                dojo.query("*[id='sum'] ",pNode).forEach(function f237r(item){
                    item.innerHTML = sum;
                    if(sum<=(maximum*0.3)){
                        dojo.attr(item, "style", "color:#FF6769;text-align: center !important;")
                    }else if(sum>maximum*0.3&&sum<maximum*0.6){
                        dojo.attr(item, "style", "color:#d89108;text-align: center !important;")
                    }else if(sum>=maximum*0.6){
                        dojo.attr(item, "style", "color:#43DC46;text-align: center !important;")
                    }

                });
            }
        }
    dojo.query("#table input[type='text']").onkeyup(function f412(evt){
        validate(evt.target);
        if(evt.target.value != ''){
             makeSum(evt.target.parentNode.parentNode.rowIndex);
        }
    })
    dojo.query("#table input[type='text']").onmousedown(function f477(evt){
        validate(evt.target);
    })
    dojo.query("#table input[type='text']:not(input[name='result'])").onblur(function f424(evt){
        if(evt.target.value == ''){
            evt.target.value = '0';
            makeSum(evt.target.parentNode.parentNode.rowIndex);
        }
    })
    dojo.query("#table input[type='text']:not(input[name='result'])").onfocus(function f443(evt){
        if(evt.target.value == '0')
            evt.target.value = ''
    })
    dojo.query("#table input[type='text']").onkeypress(function f444(evt){
        validate(evt.target);
    })
    dojo.query("#table input[type='text']:not(input[name='result'])").connect('onchange',function f454(e){
       validate(e.target);
       if(this.value == '')
           this.value = 0;
       else
           makeSum(e.target.parentNode.parentNode.rowIndex);
    })