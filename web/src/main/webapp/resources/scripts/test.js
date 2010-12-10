       $(window).bind('beforeunload', function(){
		return 'Ваша попытка не будет засчитана, вы уверены?';
	   });

         function checkTime() {
           document.getElementById('testForm:completeTest').click();

        }
         function removeCounter() {
          $("#counter").remove();

        }