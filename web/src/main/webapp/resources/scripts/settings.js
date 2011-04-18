jQuery(function($) {
//маска на номер телефона
$.mask.definitions['~']='[+-]';
$('input[id*="cellPhone"]').mask('+7(999)999-99-99');
$('input[id*="officePhoneNumber"]').mask('99-99-99');
});

//проверка правильности ввода адреса эл.почты
$(document).ready(function() {
 $("input[id*='eMail']").keyup(function(){
   var email = $("input[id*='eMail']").val();
   if(email != 0){
    if(isValidEmailAddress(email)){
    $("#validEmail").html("");
     $("#validEmail").attr('style','background: none;')
    } else {
    $("#validEmail").html("неправильный e-mail");
    $("#validEmail").attr('style','color: red; height: 20px; line-height: 18px;font-weight: normal;')
    }
    } else {
    $("#validEmail").html("введите e-mail");
     $("#validEmail").attr('style','color: red; height: 20px; line-height: 18px;font-weight: normal;')
    }
 });
 });
 function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
    return pattern.test(emailAddress);
}

//проверка уровня сложности пароля
  $(document).ready(function(){
          $( 'input.password-box' ).live( 'keyup', function() {
           var howStrong = passwordStrength( $( this ).val() );
                   switch( howStrong ) {
               case 'Strong' :
                            howStrong = 'Сильный';
                   $('#passLevel').attr('style','color: darkgreen; background-color:#EDF9FC;height: 20px;line-height: 18px;  text-align: center;font-weight: normal;');
                   break;
               case 'Weak' :
			   howStrong = 'Средний';
                   $( '#passLevel').attr('style', 'color: #F79C09; background-color:#EDF9FC;height: 20px;line-height: 18px; text-align: center;font-weight: normal;');
                   break;
               case 'Short' :
			   howStrong = 'Слабый';
                           $('#passLevel').attr('style','color: red; background-color: #EDF9FC;height: 20px;line-height: 18px; text-align: center;font-weight: normal;');
                           break;
               default :
			   howStrong = 'Введите пароль'
                   $('#passLevel').attr('style','color: #555; background-color: #EDF9FC;height: 20px;line-height: 18px; text-align: center;font-weight: normal;');
                   }
           $( '.strength-text').text( howStrong );
                   }).focusout( function() {
                       $( this ).trigger( 'keyup' );
                   });
           });

//проверка правильности ввода номера icq
$(document).ready(function(){
     
    //Вызывается когда вводятся символы в поле с id quantity
    $("input[id*='icq']").keypress(function (e)
    {
      //Если символ - не цифра, ввыодится сообщение об ошибке, другие символы не пишутся
      if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
      {
        //Вывод сообщения об ошибке
        $("#errmsg").html("только цифры").attr('style','color: red; height: 18px;line-height: 18px; font-weight: normal;');
        return false;
      }
      else{
        $("#errmsg").html("").attr('style','background: none;');
       
      }
    });
  });
