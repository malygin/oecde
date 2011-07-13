$(document).ready(function(){
    hiddenBlocks("ul.dropdown li");
});


function hiddenBlocks(param){
    $(param).click(
        function() {
            if ($(this).find(".usersOnlineTableContainer").css("display") == 'none') {
             
                $(this).css('list-style', 'none');
            //              $(this).css('list-style-image', 'url("../resources/images/greyMinus.png")');
            //              $(this).css('list-style-position', 'inside;');
            } else {
                //              $(this).css('list-style-image', 'url("../resources/images/greyPlus.png")');
                $(this).css('list-style', 'none');
            }
            $(this).find(".usersOnlineTableContainer").toggle();
        }
        );
}
