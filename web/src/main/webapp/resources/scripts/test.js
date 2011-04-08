

//       //       $(window).bind('beforeunload', function(){
//		return 'Ваша попытка не будет засчитана, вы уверены?';
//	   });

         function checkTime() {
           document.getElementById('mainForm:completeTest').click();
           console.log("dsdsd");

        }
         function removeCounter() {
          $("#counter").remove();

        }

          $(function(){
           $(".qTitle").each(function(i){
                      if($(this).text().indexOf("link:")!=-1){
                          str=$(this).text();
                          $(this).html("");
                          $('<iframe height="100px"   frameborder="0"  width="700px" name="myFrame"/>').attr('src', '../TestServlet?task='+str.substring(5)).appendTo($(this));
                        }
                });
                   });
     
          $(function(){
           $("label").each(function(i){

                if($(this).text().indexOf("<img")!=-1){
                    $(this).html($(this).text());
               }

               if($(this).text().indexOf("link:")!=-1){
                          str=$(this).text();
                          $(this).html("");
                          $('<iframe height="70px"   frameborder="0"  width="400px" name="myFrame"/>').attr('src', '../TestServlet?task='+str.substring(6)).appendTo($(this));
                        }

                });
                   });

$(function(){
    var iFrames = document.getElementsByTagName('iframe');

    function iResize(){
        for (var i = 0; i < iFrames.length; i++){
            iFrames[i].style.height = iFrames[i].contentWindow.document.body.offsetHeight +1+ 'px';
        }
    }

    if ($.browser.safari || $.browser.opera){
        $('iframe').load(function(){
            style = this.contentWindow.document.createElement("style");
            style.innerHTML = 'body,body>*{margin: 0px; display: inline;} ';
            style.type = 'text/css';
            this.contentWindow.document.head.appendChild(style);

        });
        $('iframe').load(function(){
            t = setTimeout(iResize, 0);
        });

        for (i = 0; i < iFrames.length; i++){
            iSource = iFrames[i].src;
            iFrames[i].src = '';
            iFrames[i].src = iSource;
            console.log(123)
        }
    }else{
        $('iframe').load(function(){
            style = this.contentWindow.document.createElement('style');
            style.innerHTML = '*{margin: 0px; }';
            this.contentWindow.document.head.appendChild(style);
            this.style.height = this.contentWindow.document.body.offsetHeight + 'px';
        });
    }
    
});