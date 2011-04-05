  $(document).ready(function(){
          $( 'input.password-box' ).live( 'keyup', function() {
           var howStrong = passwordStrength( $( this ).val() );
                   var sColor = '#555';
                   switch( howStrong ) {
               case 'Strong' :
						howStrong = 'Сильный';
                   $( '.password-strength', $( this ).next() ).css(
'color', 'darkgreen' );
                   break;
               case 'Weak' :
			   howStrong = 'Средний';
                   $( '.password-strength', $( this ).next() ).css(
'color', '#D69526' );
                   break;
               case 'Short' :
			   howStrong = 'Слабый';
                           $( '.password-strength', $( this ).next() ).css(
'color', 'red' );
                           break;
               default :
			   howStrong = 'Введите пароль'
                   $( '.password-strength', $( this ).next() ).css(
'color', '#555' );
                   }
           $( '.strength-text', $( this ).next() ).text( howStrong );
                   }).focusout( function() {
                       $( this ).trigger( 'keyup' );
                   });
           });


