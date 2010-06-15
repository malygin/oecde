dojo.addOnLoad(function topper(){
    first_size = dojo.byId("infopart").style.fontSize;
    var a = window.location.hash;
    a = a.substring(1);
    id = a.replace("^\\d")
    dojo.xhrGet({
        content:{id: id},
        url: "moduleWithTasks.jsp",
        handleAs: 'json',
        load: getTasks,
        error:error
    });
});

    function getTasks(type,data,evt){
        try{
            data = dojo.fromJson(data.xhr.responseText);
        }catch(e){
            console.debug(e)
            return error();
        }
        if(data.status!="success")
            return error()
        tasks = data.tasks;
        var length = tasks.length
        var pages = dojo.byId("pages");
        for(var k=0,i=1;k<tasks.length;k++,i++){
            if(tasks[k].id == window.location.hash.substring(1)){
                getTask(k)
            }
       //     if(k<3)
                pages.innerHTML += '<div class="pages_block"><p><a class="page_link" href="javascript:getTask('+k+')">'+i+'</a></p></div>'
      /*      else*/ if(i==length){
           //     pages.innerHTML += '<div class="pages_block"><p> ... </p></div>'+
           //     '<div class="pages_block"><p><a class="page_link" href="#'+tasks[k].id+'">'+i+'</a></p></div>'
            }
        }
        dojo.connect(dijit.byId("infopart"), "_onLoadHandler", function f21(){
             var path = "";
			 var infoprartHref = dijit.byId("infopart").href;
			 var href = infoprartHref.split("/");
			 var newHref = infoprartHref.replace(href[href.length-1],"")
			 var replace = newHref.replace("../".concat(href[1]).concat("/").concat(href[2]).concat("/"),"");
             dojo.query("img", dojo.byId("infopart")).forEach(function f457(item){
				 path = document.location.href.toString().replace("exbook.jsp","").replace(document.location.hash,"");
                 path = newHref+((newHref.lastIndexOf("/")+1!=newHref.length)?"/":"") + item.src.replace(path,"").replace(replace,"");
                 item.src = path;
             })
         })
        document.title = "ЦОО СГУ. "+data.name
        dojo.byId("moduleName").innerHTML = data.text;
        dojo.byId("back").href = 'main.jsp#course/id='+data.umk;
        dojo.byId("last").href = 'main.jsp#course/id='+data.umk;
    }

    function getTask(id){
        var k=id
        var next = dojo.byId("next").innerHTML;
        var previous = dojo.byId("previous").innerHTML;
        var length = tasks.length;
        var add = tasks[k].address.toString();
        var text = tasks[id].text;
        if(add.indexOf(".pdf")>-1||add.indexOf(".doc")>-1||add.indexOf(".xls")>-1||add.indexOf(".djvu")>-1||add.indexOf(".ppt")>-1||add.indexOf(".chm")>-1){
			dijit.byId("infopart").setContent("<p>Файл будет доступен в новом окне</p>"+
                            "<br>Если новое окно не открылось, то нажмите "+"\n\
                            <a  class='page_link' href='../textbooks/"+add+"'>сюда</a> ")
            window.open("../textbooks/"+add, text)
        }else{
            dijit.byId("infopart").setHref("../textbooks/"+add)
        }
        dojo.byId("title").innerHTML = text
        dojo.byId("currentPage").innerHTML = "Страница "+parseInt(id+1)+" из "+length
        if(k==0)
            previous = '<<<'
        else
            previous = '<a class="page_link" href="'+tasks[k-1].id+'"><<</a>'
        if(k==length-1)
            next = '>>>'
        else
            next.innerHTML = '<a class="page_link" href="'+tasks[k+1].id+'">>></a>'
    }

    function error(){
    }


	function larger_img(idd){
		//console.debug(dojo.byId(idd).style.left);
		dojo.byId(idd).childNodes[0].style.width = '27px';
		dojo.byId(idd).style.left = '18px';
		dojo.byId(idd).style.top = '-2px';
		//console.debug(dojo.byId(idd).style.left);

	}

	function smaller_img(idd){
		//console.debug(dojo.byId(idd).style.left);
		dojo.byId(idd).style.top = '0px';
		dojo.byId(idd).childNodes[0].style.width = '23px';
		dojo.byId(idd).style.left = '22px';

	}



	function sh_hi_bar(n, id1, id2){
		var ua = navigator.userAgent.toLowerCase();
		var isIE = (ua.indexOf("msie") != -1 && ua.indexOf("opera") == -1);
		if(!isIE){
			dojo.byId('move_block').style.top = dojo.byId('fixed_block').offsetHeight;
		}
		var isCHROME = (ua.indexOf("chrome") != -1);
		if(isCHROME){
			dojo.byId('red').style.left = -24;
		}
		isCHROME = (ua.indexOf("chrome") != -1);
		if(n == 1){
			dojo.byId("tools").style.display = "block";
			if(isCHROME){
				var tout = setTimeout(function ack_anim(){dojo.byId(id1).style.display = "block";}, 500);
				var slideRight = dojo.fx.slideTo({node: id2,duration: 350, left: 26});
				var slideLeft = dojo.fx.slideTo({node: id1,duration: 350, left: -24});
				var slideLeft_t = dojo.fx.slideTo({node: "tools",duration: 350, left: -31, top: 30});
				var fadeOut = dojo.fadeOut({node: id2,duration: 5});
				var fadeIn = dojo.fadeIn({node: id1,duration: 5});
				var fadeIn_t = dojo.fadeIn({node: "tools",duration: 5});
			}else{

				tout = setTimeout(function ack_anim(){dojo.byId(id1).style.display = "block";}, 500);
				slideRight = dojo.fx.slideTo({node: id2,duration: 300, left: 50});
				slideLeft = dojo.fx.slideTo({node: id1,duration: 300, left: 22});
				slideLeft_t = dojo.fx.slideTo({node: "tools",duration: 300, left: 15, top: 30});
				fadeOut = dojo.fadeOut({node: id2,duration: 5});
				fadeIn = dojo.fadeIn({node: id1,duration: 5});
				fadeIn_t = dojo.fadeIn({node: "tools",duration: 5});
			}
			dojo.fx.chain([fadeIn_t, slideLeft_t, slideRight, fadeOut, fadeIn, slideLeft]).play();
		}else{
			if(isCHROME){
				slideRight = dojo.fx.slideTo({node: id2,duration: 350, left: 26});
				slideLeft = dojo.fx.slideTo({node: id1,duration: 350, left: -24});
				fadeOut = dojo.fadeOut({node: id2,duration: 5});
				fadeIn = dojo.fadeIn({node: id1,duration: 5});
				var slideRight_t = dojo.fx.slideTo({node: "tools",duration: 350, left: 37, top: 30});
				var fadeOut_t = dojo.fadeOut({node: "tools",duration: 5});
			}else{
				slideRight = dojo.fx.slideTo({node: id2,duration: 300, left: 50});
				slideLeft = dojo.fx.slideTo({node: id1,duration: 300, left: 22});
				fadeOut = dojo.fadeOut({node: id2,duration: 5});
				fadeIn = dojo.fadeIn({node: id1,duration: 5});
				slideRight_t = dojo.fx.slideTo({node: "tools",duration: 300, left: 37, top: 30});
				fadeOut_t = dojo.fadeOut({node: "tools",duration: 5});
			}
			dojo.fx.chain([slideRight_t, fadeOut_t, slideRight, fadeOut, fadeIn, slideLeft]).play();
		}
	}

    function text_size(id){
		var text = dojo.byId(id).style.fontSize;
		text = text.replace("pt",'');
		text = parseInt(text) ;
		if(text <= 30){
			text = text + 1 + "pt";
			dojo.byId(id).style.fontSize = text;
		}
	}

	function text_size_min(id){
		var text = dojo.byId(id).style.fontSize;
		text = text.replace("pt",'');
		text = parseInt(text) ;
		if(text >= 8){
			text = text - 1 + "pt";
			dojo.byId(id).style.fontSize = text;
		}
	}

    function defolt_text(id){
        dojo.byId(id).style.fontSize = first_size;
	}

    function for_img_and_formuls(){

          dojo.query("img", dojo.byId("infopart")).forEach(function ffe(item){


                    if (item.width < 150)
                    {
                         item.parentNode.style.display = 'inline';
                         item.style.display = 'inline';
                    } else {
                         item.parentNode.style.display = 'block';
                         item.style.display = 'block';
                    }
          });

          arr = document.getElementById("infopart").getElementsByTagName("tbody");
          for (i=0; i<arr.length; i++)
          {

               arr1 = arr[i].childNodes;
               console.debug(arr1,"+++");
               t = "TR";
               k = 0;

               for (j=0; j<arr1.length; j++)
               {
                    if (arr1[j].nodeName == t) {console.debug("111",arr1[j].nodeName,"  ",t);k = k+1;}
               }
               console.debug(k,"+++++++");

               if (k == 1) {arr[i].parentNode.border = "0";}
          }
          var g = document.getElementsByTagName("br");
          for(i=0;i<g.length;i++){
               g[i].parentNode.removeChild(g[i]);
               i--;
          }
     }