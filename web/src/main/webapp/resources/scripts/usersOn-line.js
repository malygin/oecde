$(document).ready(function(){
    $("ul.dropdown li").click(
      function() {
          if ($(this).find(".usersOnlineTableContainer").css("display") == 'none') {
              $(this).css('list-style-image', 'url("../resources/images/greyMinus.png")');
          } else {
              $(this).css('list-style-image', 'url("../resources/images/greyPlus.png")');
          }
          $(this).find(".usersOnlineTableContainer").toggle();
      }
  );
});


