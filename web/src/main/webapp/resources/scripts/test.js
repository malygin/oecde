

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

         
