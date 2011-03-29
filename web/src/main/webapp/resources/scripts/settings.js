jQuery(function($) {
//маска на номер телефона
$.mask.definitions['~']='[+-]';
$('input[id*="cellPhone"]').mask('+7(999)999-99-99');
});

$(document).ready(function() {
 $("input[id*='eMail']").keyup(function(){
   var email = $("input[id*='eMail']").val();
   if(email != 0){
    if(isValidEmailAddress(email)){
    $("#validEmail").html("");
    } else {
    $("#validEmail").html("неправильный    e-mail");
    }
    } else {
    $("#validEmail").html("введите e-mail");
    }
 });
 });
 function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    return pattern.test(emailAddress);
}