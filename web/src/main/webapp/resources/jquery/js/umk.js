$(document).ready(function fn(){
    var contentHeight = $("div.content").height()
    console.debug(contentHeight);
    $('iframe').attr('style','height:'+contentHeight+'px');
});