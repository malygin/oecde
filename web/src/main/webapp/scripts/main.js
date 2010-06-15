
function makeTabsVisible(){
    dojo.forEach( dijit.byId("mainTabContainer").getChildren(),function f32q(item){dojo.attr(item,"style","visibility:visible")});
}
function changeContent(page,parameters){
      //  dojo.back.addToHistory(state);
    var path = "page.do";
    if(typeof(parameters) == 'undefined'||typeof(parameters)=='null'||parameters==""){
        parameters = ""
    }else{
        parameters = "&"+parameters
    }
    dijit.byId("contentPane").setHref(path+"?p="+page+parameters);
}

function sendProperties(){
    var values = dijit.byId("form").getValues();
    for(var k in values){
         if(k == "beginDate"||k=="endDate")
             dojo.cookie(dojo.byId("cookiePath").value + k, dojo.date.locale.format(values[k],{datePattern: "yyyy-MM-dd", selector: "date"}))
         else
            dojo.cookie(dojo.byId("cookiePath").value + k, values[k]);
    }
    dojo.cookie(dojo.byId("cookiePath").value + "pageNumber",dojo.byId("pN").value);
    dijit.byId("contentPane").refresh();
}
    
dojo.addOnLoad(function getPage(){
    var href = window.location.href;
    var a = window.location.hash;
    a = a.substring(1,a.length);
    a = a.split("/");
    dojo.connect(dijit.byId("contentPane"), "onDownloadError", function f20(e){
       if(e.status == "403")
           window.location="../index.jsp";
       if(e.status == "404" || e.status == "500" || e.status == "302"){
            dijit.byId("contentPane").cancel();
       }
   });
    var inter = setInterval(function f90(){
        if(href != window.location.href){
            href = window.location.href;
            var a = window.location.hash;
            a = a.substring(1,a.length);
            a = a.split("/");
            dijit.byId("contentPane").cancel();
            changeContent(a[0],a[1]);
        }
    },300);
   // state = {
   //     back: function() { alert("Back was clicked!"); },
  //      forward: function(){ alert("123"); },
   //     handle: function (){ alert(44)}
  //  };
 //   dojo.back.setInitialState(state);
    changeContent(a[0],a[1]);

});

function getNewPhoto(){
     document.pictureForm.style.display = "none";
     dojo.byId("fileLoader").style.display = "block";
     dojo.io.iframe.send({
        form: document.pictureForm,
        contentType: "text/html",
        load: reloadPhoto,
        url: '../upload.c',
        error: function feq3(e){console.debug(e)}
     });
}

function reloadPhoto(response,ioArgs){
   var data = dojo.fromJson(response);
   if(data.status == 'success'){
       dojo.byId("photo").src = dojo.byId("photo").src+"&" + Math.random()+ "";
       dijit.byId("tooltipDlg").onCancel();
       document.pictureForm.style.display = "block";
       dojo.byId("fileLoader").style.display = "none";
   }else{
       dojo.byId("error").style.display = "block";
       dojo.byId("fileLoader").style.display = "none";
       var tmout = setTimeout(function f452q(){
           document.pictureForm.style.display = "block";
           dijit.byId("tooltipDlg").onCancel()
           dojo.byId("error").style.display = "none";
       },2000);
   }
}

/*TitlePane id = CookieName*/
function TitlePanePosMemory(CookieName){
    var position;
    if (dijit.byId(CookieName).open == true){
        position = "open";
    }else{
        position = "closed";
    }
    dojo.cookie(CookieName, position, {expires: "Mon, 24-12-2009, 00:00:00", path: "/"});
}
dojo.addOnLoad(function GetTitMemoryPos(){
    dojo.query("[id^='TitPane']").forEach(function(t){
        if(dojo.cookie(t.id) == "closed"){
            dijit.byId(t.id).toggle();
        }
    });
});
