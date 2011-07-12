$(document).ready(function(){
   sdfsdf("ul.dropdown li");
});


function sdfsdf(sdfsd){
     $(sdfsd).click(
      function() {
          if ($(this).find(".usersOnlineTableContainer").css("display") == 'none') {
              $(this).css('list-style-image', 'url("../resources/images/greyMinus.png")');
          } else {
              $(this).css('list-style-image', 'url("../resources/images/greyPlus.png")');
          }
          $(this).find(".usersOnlineTableContainer").toggle();
      }
  );
}