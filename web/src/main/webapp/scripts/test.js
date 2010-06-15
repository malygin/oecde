dojo.addOnLoad(function fd14(){
    if(dijit.byId("SmallChatWidget"))
        dijit.byId("SmallChatWidget").destroy();
    okConnect = dojo.connect(dijit.byId("okM"), "onClick", function qw09(){
        data = new getData();
        data.showDialog();
    })
})

var getData = function f4we(){
    var store = new dojo.data.ItemFileReadStore({
        url: "serviceQuestions.jsp?"+Math.random()
    });
    var dndSource;
    var form;
    var count=0;
    var sec=0;
    var min=0;
    var values;
    var number = -1;
    var quantity;
    var isAnswered = new Array();
    var aClass = function empt(){};
    var answersItems = new aClass();
    var tmr;
    var help;
    var ok;
    var parsedImgIds = -1;
    var sp;
    var re = /\$\$.*?\$\$/ig;
    var re2 = /\$.*?\$/ig;
    this.showDialog = function a23(){
        form = dojo.byId("answersForm");
        help = dojo.byId("help");
        ok = dojo.byId('ok');
        onbeforeunload = function fr(e){
            return "Вы уверены, что хотите покинуть прохождение теста ?"
        }
        dojo.connect(form,'onsubmit',function fc(e){
            window.data.doAnswer();
            return dojo.stopEvent(e);
        });
        dojo.connect(dojo.byId("testBody"),"onkeypress", function fe(e){
            if(e.keyCode == dojo.keys.ESCAPE){
                dojo.stopEvent(e);
            }
        });
        store.fetchItemByIdentity({
            identity: 'test',
            onItem: function f1351(item){
                quantity = item.quantity;
                dojo.byId('timeBar').value = item.time*60;
                dijit.byId("testProgress").update({
                    maximum:  quantity,
                    progress:0
                });
                dijit.byId("timeProgress").update({
                    maximum: item.time*60,
                    progress:0
                });
                tmr =  setInterval(function f5691(){
                    changeTimer(item.time*60);
                },1000);
                dijit.byId("testBody").show();
                answersItems.test = item.testId;
            }
        })
        dojo.byId('titleTextM').innerHTML = "Прохождение теста";
        dojo.byId('questM').innerHTML = "<br>Прохождение теста<br><br>";
        dojo.byId('timeout').style.display = "block";
        dojo.byId('cancel').style.display = "block";
        dojo.byId('answ').style.display = "block";
        dojo.byId('bars').style.display = "block";
        this.getQuestion();
    }

    function destroyElements() {
        if(form.type){
            if(form.type.value == "comparison"){
                dojo.byId("containerResult").removeChild(dojo.byId("containerDndIds"))
                dojo.byId("containerResult").removeChild(dojo.byId("containerComparisons"))
                form.removeChild(dojo.byId("containerResult"))
                dndSource.selectAll();
                dndSource.deleteSelectedNodes();
                dndSource.destroy();
                form.removeChild(dojo.byId("containerFilter"))
            }else if (form.type.value == "order"){
            /*TODO
 *сделать для ордера
 **/
            }else{
                store.fetchItemByIdentity({
                    identity: number,
                    onItem: function f1351(item){
                        if (item.type == "radio"||item.type == "check"){
                            for (var j = 0; j < item.answers.length; j++){
                                if(dijit.byId("content"+j))
                                    dijit.byId("content"+j).destroy();
                                form.removeChild(dojo.byId("li"+j))
                                form.removeChild(dojo.byId("bri"+j))
                                dijit.byId("i"+j)
                                dijit.byId("i"+j).destroy()
                            }
                        }else if(item.type == "text"){
                            dijit.byId("txt").destroy();
                        }
                    }
                })
            }
            form.removeChild(form.type);
        }
    }

    this.finish = function(){
        clearTimeout(tmr);
        dijit.byId("testBody").hide();
        dojo.disconnect(okConnect);
        store.fetch({
            query:{
                id: "\\d*"
            },
            onItem: function f1351(item){
                answersItems[item.qid] = isAnswered[item.id];
            }
        })
        answersItems.time = Math.floor(count/60);
        dojo.xhrPost({
            content:answersItems,
            load:showResults,
            url: "../checkAndPutTestResults.c"
        });
    }

    function showResults(type,data,evt){
        dojo.byId('titleTextM').innerHTML = "Результаты теста";
        document.title = "ЦОО СГУ Тест завершён"
        store.fetchItemByIdentity({
            identity: 'test',
            onItem: function f1351(item){
                dojo.byId('headTextM').innerHTML = "Количество вопросов - " + item.quantity + "<br> Отведённое время - "+ item.time + " минуты";
            }
        })
        data = data.xhr.responseText;
        ok = dijit.byId("okM")
        var error = false;
        try{
            data = dojo.fromJson(data);
        }catch(e){
            console.debug(e)
            error = true;
        }
        error = !error?(data.status.toString()=='fail'):error;
        if(!error){
            var tempArray = dojo.filter(isAnswered,function f121s(item){
                return item!=undefined
                })
            if(data.status.toString()=='ok'){
                dojo.byId('questM').innerHTML = "Тест завершён <img src='../images/chk.gif'><br><br>Вы набрали "
                + data.points+ " из "+data.maxPoints+" баллов<p>Количество правильных ответов – "
                + data.rightAnswers +" из "+tempArray.length+"-ти. <br><b>Затраченное время: "+ Math.floor(count/60) +" минут</b></p>";
            }else if(data.status.toString()=='error'){
                dojo.byId('questM').innerHTML = "Во время прохождения теста скорее всего произошла ошибка и он не был записан";
            }
            dijit.byId("okM").destroy();
            delete tempArray;
            delete isAnswered;
            delete window.data;
            delete answersItems;
            delete store;
            delete getData;
            window.onbeforeunload = null
            delete window.onbeforeunload;
            var hide = dojo.connect(dijit.byId("testBody"),'hide',function fdq(){
                dijit.byId("testBody").destroy();
                dojo.disconnect(hide);
            });
        }else{
            dojo.byId('questM').innerHTML = "Тест завершён, но во время записи произошла ошибка.<br><br>"
            +"Нажмите на кнопку, чтобы отправить результат ещё раз";
            dojo.byId('okM').innerHTML = "Отправить"
            okConnect = dojo.connect(ok,'onClick',function fdq(){
                window.data.finish()
                });
        }
        delete timeReport;
        delete progressReport;
    }

	function match(txt){
		return txt.match(re)||txt.match(re2);
	}

    this.getQuestion = function (){
        destroyElements()
        var tempArray = dojo.filter(isAnswered,function f121s(item){
            return item!=undefined
            })
        if(tempArray.length == quantity){
            this.finish();
        }else{
            do{
                number = number++==quantity-1?0:number++;
            }while (isAnswered[number]);
            delete tempArray;
            document.title = "Выполнение теста. Вопрос " + parseInt(number+1);
            dojo.byId('done').innerHTML = number+1 +"й";
            form.innerHTML = "";
            sp = 1;
            store.fetchItemByIdentity({
                identity: number,
                onItem: function f1508(item){
                    var link = null;
                    var s = match(item.text.toString());
                    var text=item.text;
                    if(item.text.toString().indexOf("link:")!=-1){
                        link = item.text.toString().replace("link:","");
                        dojo.attr(dijit.byId("quest"),"href","../textbooks/"+link);
                    }else{
                        if(s!=null){
                            text = makeFormula(text);
                        }
                        dojo.byId('quest').innerHTML = "<p>"+text+"</p>";
                    }
                    form.innerHTML += "<input type='hidden' name='type' value='"+item.type+"'/>";
                    var j = 0;
					parsedImgIds  = -1
                    do{
                        fillAnswersField(item.type,item.answers[j],j);
                        j++;
                    }while(j < item.answers.length)
                    if(parsedImgIds>-1||link!=null){
                        var path = "";
                        var infoprartHref;
                        var contentPane;
                        if(parsedImgIds>-1){
                            infoprartHref = dijit.byId("content"+parsedImgIds).href;
                            contentPane = "content"+parsedImgIds;
                        }else{
                            infoprartHref = item.text.toString().replace("link:","");
                            contentPane = "quest";
                        }
                        var href = infoprartHref.split("/");
                        var newHref = infoprartHref.replace(href[href.length-1],"")
                        var parseConnect = dojo.connect(dijit.byId(contentPane), "_onLoadHandler", function f21(){
                            dojo.query("img", dojo.byId("testBody")).forEach(function f457(img){
								var src = img.src.toString();
                                if(src.indexOf("textbooks")<0&&src.indexOf("forkosh.dreamhost")<0&&src.indexOf("latex.php")<0){
                                    path = document.location.href.toString().replace("test.jsp","").replace(document.location.search,"");
                                    path = "../textbooks/"+newHref+((newHref.lastIndexOf("/")+1!=newHref.length)?"/":"") + img.src.replace(path,"")
                                    img.src = path;
                                }
                            })
                        dojo.disconnect(parseConnect)
                        })
                    }
                }
            });
        }
    }

    function checkInput(){
        /*
          *TODO
          *dojo.query('input:checked', 'answersForm').length
заюзать
          **/
        values = dijit.byId("answersForm").getValues()
        var i = 0;
        for (var k in values){
            if( values[k][0]!= undefined ){
                i++
            }
        }
        if(i >0)
            ok.disabled = 0;
        else
            ok.disabled = 1;
    }

    function changeTimer(time){
        dijit.byId("timeProgress").update({
            progress: dijit.byId("timeProgress").progress+1
            });
        ++count;
        ++sec;
        if(sec == 60){
            ++min;
            sec = 0;
        }
        if(sec<10){
            sec = "0" + sec;
        }
        dojo.byId("time").innerHTML = min + ":" + sec;
        if (count == time){
            this.data.finish();
        }
    }

    this.doAnswer = function(){
        values = dijit.byId("answersForm").getValues()
        var tempArray = new Array();
        if(form.type){
            isAnswered[number] = "";
            if(form.type.value == "comparison"){
                var comparisons = dojo.byId("containerComparisons").innerHTML.split("<br>");
                dojo.forEach(dndSource.getAllNodes(),function (item,index,array){
                    tempArray.push(item.id + "@-"+comparisons[index]);
                })
            }else{
                for (var k in values){
                    if(values[k]!="")
                        tempArray.push(values[k]);
                }
            }
            tempArray.sort()
            isAnswered[number] = tempArray.join("@:");
            delete tempArray;
            dijit.byId("testProgress").update({
                progress: dijit.byId("testProgress").progress+1
                });
            this.getQuestion();
        }
    }

    function makeFormula(text){
        var ss = match(text.toString());
		var tt = text.toString();
        dojo.forEach(ss,function f31(item){
            //tt = tt.replace(item,"<img src='http://www.forkosh.dreamhost.com/mathtex.cgi?"+item.replace(/\$/g,"")+"'>")
            tt = tt.replace(item,"<img src='http://oec.sgu.ru/latex/latex.php?code="+item.replace(/\$\$/g,"").replace(/\$/g,"").replace(/\+/g,'%2B')+"'>")
			//tt = tt.replace(item,"<img src='http://baldr.sgu.ru/cgi-bin/mimetex.cgi?"+item.replace(/\$\$/g,"").replace(/\$/g,"").replace(/\+/g,'%2B')+"'>")
		})
        return tt;
    }

    function fillAnswersField(type,item,p){
        var input;
        if( type == "radio" || type  == "check"){
            var div = dojo.place(dojo.create("div"),form);
            if (type == "radio"){
                input = new dijit.form.RadioButton({
                    name:"answer",
                    id:"i"+p,
                    value:item.id
                    },div);
            }else if (type == "check"){
                input = new dijit.form.CheckBox({
                    name:"answer"+p,
                    id:"i"+p,
                    value:item.id
                    },div);
            }
            var label = dojo.place(dojo.create("label"),form);
            dojo.attr(label,"style","width:500");
            dojo.attr(label,"id","l"+input.id);
            dojo.attr(label,"htmlFor",input.id);
            var s = match(item.text.toString());
            text=item.text;
            if(item.text.toString().indexOf("link:")!=-1){
                div = dojo.place(dojo.create("div"),label);
                var content = new dijit.layout.ContentPane({
                    id:"content"+p,
                    style:"display:inline",
                    parseOnLoad:true,
                    preventCache:true,
                    refreshOnShow:true,
                    loadingMessage:""
                },div)
                dojo.attr(content,"href","../textbooks/"+item.text.toString().replace("link:",""));
                parsedImgIds = p
            }else {
                if(s!=null){
                    text = makeFormula(text);
                }
                label.innerHTML = text;
            }
            var br = dojo.place(dojo.create("br"),form);
            dojo.attr(br,"id","br"+input.id);
            dojo.connect(input,'onClick',checkInput);
            ok.disabled = 1;
            help.innerHTML = "<br>Напишите ответ в текстовом поле без использования специальных символов";

        }else if(type == "text"){
            div = dojo.place(dojo.create("div"),form);
            input = new dijit.form.ValidationTextBox({
                name:"txt",
                id:"txt",
                trim:true,
                required:false,
                invalidMessage:"Ответ не должен содержать пробелы и недопустимые символы",
                lowercase:true,
                intermediateChanges:false,
                style: "width:35em"
            },div);
            input.focus();
            ok.disabled = 0;
            help.innerHTML = "<br>Напишите ответ в текстовом поле без использования специальных символов";
        }else if(type == "inlineText"){
            switch (p){
                case 0:
                    var before = dojo.place(dojo.create("div"),form);
                    dojo.attr(before,"style","display:inline");
                    dojo.attr(before,"style","height:10");
                    before.innerHTML = item.text;
                    break;
                case 1:
                    div = dojo.place(dojo.create("div"),form);
                    input = new dijit.form.ValidationTextBox({
                        name:'answer',
                        id:"txt",
                        trim:false,
                        required:false,
                        invalidMessage:"Ответ не должен содержать пробелы и недопустимые символы",
                        lowercase:true,
                        intermediateChanges:true,
                        size:5,
                        maxlength:50
                    },div);
                    input.focus();
                    break;
                case 2:
                    var after = dojo.place(dojo.create("div"),form);
                    dojo.attr(after,"style","display:inline");
                    after.innerHTML = item.text;
                    dojo.attr(after,"style","height:10");
            }
            ok.disabled = 0;
            help.innerHTML = "<br>Напишите ответ одним словом без использования пробелов и специальных символов";

        }else if(type == "comparison"||type == "order"){
            if(type == "comparison"){
                help.innerHTML = "<br>Необходимо в верхнем блоке выстроить предлагаемые варианты в порядке, который соответствует  вариантам нижнего блока, для чего зафиксируйте левой кнопкой мыши текстовую строку, подсвеченную зелёным цветом и, удерживая кнопку, переместить внутри блока на ту позицию, которую вы считаете правильной (Не перетаскивать из верхнего блока в нижний). Зелёная стрелочка означает, что туда, куда вы навели мышкой, перетащить можно, красный крестик означает, что нельзя."
            }
            if(type == "order"){
                help.innerHTML = "<br>Составьте верный порядок элементов, \n для чего нажмите \n\ левой кнопкой мыши на подсвеченное зелёным цветом слово и, удерживая кнопку, перетащите на ту позицию, которую вы считаете правильной"
            }
            if(p == 0){
                ok.disabled = 0;
                function nodeCreator( data, hint ) {
                    var node = dojo.create( 'div' );
                    dojo.attr(node,"id",data.id);
                    var div = dojo.place(dojo.create("div"),node);
                    dojo.attr(div,"id","n"+data.aid);
                    dojo.attr(div,"class",'dojoDndHandle');
                    text = data.text;
                    var s = match(data.text.toString());
                    if(s!=null){
                        text = makeFormula(data.text);
                    }
                    if(type == "order"){
                        div.innerHTML = text;
                    }else{
                        div.innerHTML = (parseInt(data.aid)) + ") " + text;
                    }
                    return {
                        node: node,
                        data: data,
                        type: [ 'qItem' ]
                        };
                }
                var container = dojo.place(dojo.create("div"),form);
                dojo.attr(container,"id","containerFilter");
                //dojo.attr(container,"container","display:inline");
                dojo.attr(container,"style","width:500; float: none; display: block;");
                dndSource = new dojo.dnd.Source(
                    'containerFilter', {
                        creator: nodeCreator,
                        accept: [ 'qItem' ],
                        withHandles: true,
                        copyOnly: false,
                        skipForm: true
                    }
                    );
                if(type == "comparison"){
                    var txt = dojo.doc.createElement("div");
                    form.appendChild(txt);
                    var forDndIds = dojo.doc.createElement("div");
                    txt.appendChild(forDndIds);
                    forDndIds.id = "containerDndIds";
                    forDndIds.style.display = "inline";
                    forDndIds.setAttribute("style","float: left");
                    var forComparisons = dojo.doc.createElement("div");
                    txt.appendChild(forComparisons);
                    forComparisons.id = "containerComparisons";
                    dojo.attr(forComparisons,"style","display: inline; font-size: 10pt;");
                    txt.style.width = '500';
                    txt.id = "containerResult";
                    txt.setAttribute("class","containerResult");
                    txt.setAttribute("className","containerResult");
                }
            }
            if(item.id[0].search("comparison")==-1){
                var spot = sp++;
                dndSource.insertNodes( null, [{
                    id:item.id,
                    aid:spot,
                    text:item.text
                    }]);
                if(type == "comparison"){
                    dojo.byId("containerDndIds").innerHTML += (spot) + " &#8658; <br/>";
                }
            }else{
                s = match(item.text.toString());
                var text=item.text;
                if(s!=null){
                    text = makeFormula(item.text);
                }
                if(type == "comparison"){
                    dojo.byId("containerComparisons").innerHTML += text+"<br/>";
                }
            }
            if(p == 0&&type == "comparison"){
                dojo.subscribe("/dnd/drop", function(source,nodes,iscopy){
                    var t = dojo.dnd.manager().source;
                    var x = t.getAllNodes();
                    dojo.byId("containerDndIds").innerHTML = "";
                    for(var i = 0;i<x.length;i++){
                        dojo.byId("containerDndIds").innerHTML += (t.getItem(x[i].id).data.aid)+ " &#8658; <br/>";
                    }
                });
            }
        }
        if(typeof(jsMath)!='undefined'&&typeof(jsMath)!='null'){
            jsMath.ConvertTeX('testBody');
            jsMath.ProcessBeforeShowing('testBody');
        }

    }
}

var timeReport = function(percent){
    var minute = percent*this.maximum/60;
    return "Прошло: " + (parseInt(minute)) + " из " + this.maximum/60 + " минут";
}

var progressReport = function(percent){
    return "Отвечено: " + Math.floor((percent*this.maximum)) + " из " + this.maximum + " вопросов";
}
