       $(window).bind('beforeunload', function(){
		return 'Ваша попытка не будет засчитана, вы уверены?';
	   });

         function checkTime() {
           document.getElementById('mainForm:completeTest').click();
           console.log("dsdsd");

        }
         function removeCounter() {
          $("#counter").remove();

        }