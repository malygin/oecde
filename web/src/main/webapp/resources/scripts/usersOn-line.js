//$(document).ready(function(){
//    hiddenBlocks(".clickToHid");
//});
//
//
//function hiddenBlocks(param){
//    $(param).click(
//        function() {
//            if ($(this).parent().find(".hiddenUsersOnlineTableContainer").css("display") == 'none') {
//
////                $(this).parent().css('list-style', 'none');
//                      $(this).parent().css('list-style-image', 'url("../resources/images/greyMinus.png")');
//            } else {
//                            $(this).parent().css('list-style-image', 'url("../resources/images/greyPlus.png")');
////                $(this).parent().css('list-style', 'none');
//            }
//            $(this).parent().find(".hiddenUsersOnlineTableContainer").toggle();
//        }
//        );
//}
 function toHidElements (hiddenElement){
           $("*[class*='clickToHid']").click(
           function(){
               var hiddenElem = $(this).parent().find(hiddenElement);
               $(hiddenElem).toggle();
               if(hiddenElement == '.hiddenUsersOnlineTableContainer'){
                    if ($(hiddenElem).css("display") == 'none') {

//                $(this).parent().css('list-style', 'none');
                      $(hiddenElem).parent().css('list-style-image', 'url("../resources/images/greyPlus.png")');
                    } else {
                      $(hiddenElem).parent().css('list-style-image', 'url("../resources/images/greyMinus.png")');
//                $(this).parent().css('list-style', 'none');
            }
               }
           }
        )};


