    function floodPreventing(linkId,id){
        var listHolder = linkId.parentNode.parentNode.parentNode;
        var list = linkId.parentNode.parentNode;
        var ol = document.createElement("ol");
        var li = document.createElement("li");
        dojo.attr(ol,"class","events_menu");

        li.innerHTML = "Создано";
        ol.appendChild(li);
        var li1 = document.createElement("li");
        dojo.attr(li1, "class", "x");
        li1.innerHTML = "|";
        ol.appendChild(li1);
        var li2 = document.createElement("li");
        dojo.attr(li2, "class", "");
        li2.innerHTML = "Отредактировано";
        ol.appendChild(li2);
        var li3 = document.createElement("li");
        dojo.attr(li3, "class", "x");
        li3.innerHTML = "|";
        ol.appendChild(li3);
        var li4 = document.createElement("li");
        dojo.attr(li4, "class", "");
        li4.innerHTML = "Удалено"
        ol.appendChild(li4);
        var otd = document.createElement("div");
        dojo.attr(otd, "class", "otdelitel");
        ol.appendChild(otd);

        dojo.byId("f" + id).style.height = dojo.byId("inf_math_reit_" + id).offsetHeight + "px";
        dojo.byId("f" + id).style.opacity = 0;

        var actElem = document.createElement("div");
        var k = "actElem" + id;
        dojo.attr(actElem, "id", k);
        actElem.innerHTML = "Создано";
        dojo.attr(actElem, "style", "border: 1px solid green; color: green; font-size: 10pt; padding: 5px; background: #CCFFCC;");
        var h = dojo.byId("inf_math_reit_" + id).offsetHeight/2 - 15 + "px 15px 15px 15px";
        actElem.style.margin = h;
        dojo.byId("f" + id).appendChild(actElem);

        dojo.fadeIn({
            node: dojo.byId("f" + id),
            onBegin: function _756(){
                listHolder.innerHTML ="";
                listHolder.appendChild(ol);
                listHolder.appendChild(otd);
                dojo.xhrPost({
                    content: {how: linkId.id,umkId: id},
                    url: 'RecordUmkEditEvent.c'
                });
            },
            onEnd: function _756(){
                var t = setTimeout(function _234(){
                    dojo.fadeOut({
                        node: dojo.byId("f" + id),
                        onEnd: function _786(){
                            dojo.byId("f" + id).innerHTML = "";
                            listHolder.innerHTML = "";
                            listHolder.appendChild(list);
                        },
                        duration: 300
                    }).play();
                }, 500);
            },
            duration: 300
        }).play();
    }


    function sendEmptyCw(id,number,semestr,disc){
        dojo.xhrPost({
            content:{disc:disc,numberCw:number,id:id,semestr:semestr},
            url: "../putEmptyControlWork.c",
            load: function f12(e){dijit.byId("cw").refresh()},
            error:function f31(e){console.debug(e)}
        });
    }


    function deleteEmptyCw(id,cwId){
        dojo.xhrPost({
            content:{id:id,cwId:cwId},
            url: "DeleteEmptyCw.c",
            load: function f12(e){dijit.byId("cw").refresh()},
            error:function f31(e){console.debug(e)}
        });
    }

   function deletePhoto(id,type){
      function error(e){
           console.debug(e)
           dojo.byId("error").style.visibility = "visible";
      }
      dojo.xhrPost({
          content: {type: type,id: id},
          url: 'deletePhoto.c',
          load: function sh123(){   
            try{
               var data = dojo.fromJson(response)
            }catch(e){
                error(e);
            }
            if(data.status == 'success'){                
                dojo.byId("photo").src = dojo.byId("photo").src+"&" + Math.random()+ "";
            }else{
                error("fail")
            }
          },
          error: error
      });
      dojo.xhrPost({
          content: {them: "Внимание!",rich_text: "Ваша фотография была удалена, так как её содержимое некорректно",getters:"4-"+id+";"},
          url: '../SendLetter.c',
          error: error
      });
   }

   dojo.addOnLoad(function getPagew3(){
      var type = "student"
      var con =  dojo.connect(dojo.byId("searchField"),"onkeypress", function fe35(e){connect(e)});
        var ua = navigator.userAgent.toLowerCase();
        var isChrome = (ua.indexOf("chrome") != -1);
        var targetId;
        if(dojo.isIE){
            dojo.connect(document, "onclick", function dddddd(){
                targetId = document.activeElement.id
                foFinderPane(targetId);
            })
        }else{
            dojo.connect(window,"onmousedown",function eventClick(event){
                if(dojo.isMozilla){
                    targetId = event.explicitOriginalTarget.id;
                }
                if(dojo.isOpera){
                    targetId = event.srcElement.id;
                }
                if(isChrome){
                    targetId = event.srcElement.id;
                    targetId = targetId.replace("_label",'')
                }
                foFinderPane(targetId); 
            })
        }

        function foFinderPane(id){
            switch(id){
                case("drop"):
                        if(dojo.byId("types").style.display == "none"){
                            dojo.byId("types").style.display = "block";
                        }else{
                            dojo.byId("types").style.display = "none";
                        }
                        dojo.byId("search").innerHTML="Поиск";
                        var tout = setTimeout(function fe(){dojo.byId('searchField').focus()},100);
                    break;
                case("search"):
                        if(dojo.byId("types").style.display != "none")
                            dojo.byId("types").style.display = "none";
                        doS(type)
                    break;
                case("by_student"):
                        searchStore.url= "getSearchResults.view?type=student"
                        hide_dropDownList()
                        type="student"
                        dojo.disconnect(con)
                        con =  dojo.connect(dojo.byId("searchField"),"onkeypress", function fe35(e){connect(e)});
                        dojo.byId("search").innerHTML="По студенту";
                        var tout = setTimeout(function fe(){dojo.byId('searchField').focus()},100);
                    break;
                case("by_teacher"):
                        searchStore.url= "getSearchResults.view?type=teacher"
                        hide_dropDownList()
                        type="teacher"
                        dojo.disconnect(con)
                        con =  dojo.connect(dojo.byId("searchField"),"onkeypress", function fe35(e){connect(e)});
                        dojo.byId("search").innerHTML="По преподавателю";
                        var tout = setTimeout(function fe(){dojo.byId('searchField').focus()},100);
                    break;
                case("by_umk"):
                        searchStore.url= "getSearchResults.view?type=umk"
                        hide_dropDownList()
                        dojo.disconnect(con)
                        type="umk"
                        con =  dojo.connect(dojo.byId("searchField"),"onkeypress", function fe35(e){connect(e)});
                        dojo.byId("search").innerHTML="По умк";
                        var tout = setTimeout(function fe(){dojo.byId('searchField').focus()},100);
                    break;
                case("small_inp"):
                case("searchField"):
                    break;
                default:
                        hide_big();
                        dojo.byId("searchField").blur();
                    break;
            }
        }
        function connect(e){
            if(e.keyCode == dojo.keys.ENTER){
               hide_dropDownList()
               doS(type)
            }
        }
    })

    function logEvent(type, id){
        dojo.xhrPost({
            content: {type: type,id: id},
            url: 'RecordUmkEditEvent.c',
            load: function sh123(){dojo.byId("").style.display = "block"},
            error: showError
        });
    }

    function doS(type){
        dijit.byId("searchField")._hideResultList()
        document.location.href = "#search/w="+dojo.byId("searchField").value+"&searchtype="+type;
    }
    
    function hide_small(_1, _2){
        dojo.byId(_1).style.display = 'none';
        dojo.byId(_1).parentNode.className =  'search_inp_big_content';
        dojo.byId(_2).style.display = 'block';
        if(dojo.byId("types").style.display == "none"){
            dojo.byId("types").style.display = "block";
        }

        dojo.byId('searchField').focus();

    }

    function hide_big(){
        dojo.byId("large_inp").style.display = 'none';
        dojo.byId("large_inp").parentNode.className =  'search_input';
        dojo.byId("small_inp").style.display = 'block';
    }
    function chose_type(_1, _2){
        dojo.byId(_1).focus();
        dojo.byId(_2).style.display = "none";
    }
    function hide_dropDownList(){
        dojo.byId("types").style.display = "none";
        if(dojo.isOpera){
            dojo.byId('searchField').focus();
        }else{
            dijit.byId('searchField').focus();
        }
    }