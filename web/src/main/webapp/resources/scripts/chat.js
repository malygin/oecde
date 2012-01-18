      $(document).ready(function() {
                             chatAction(1)});
    

           function chatAction(roomId) {
                 update(roomId);
                 $('#chatShortHistory'+roomId).scroll(function() {
              //    console.log('scroooll');
              });

                 $('#button'+roomId).click(function(){
                      console.log($('#message'+roomId).val().length);
                      if ($('#message'+roomId).val().length>200) alert("слишком большое сообщение! Не более 200 символов.");
                      else
                       $.post('../ChatList',
                              { message: $('#message'+roomId).val(), room: roomId},
                              function(data){
                                    update(roomId);
                                    $('#message'+roomId).val('');
                               }
                       );
                  });
                  $('#message'+roomId).keypress(function(e) {
                   
             
        
                        if(e.which == 13) {
                            
                        if ($('#message'+roomId).val().length>200) alert("слишком большое сообщение! Не более 200 символов.");
                        else 
                           $.post('../ChatList',
                           { message: $('#message'+roomId).val(), room: roomId},
                               function(data){
                                    update(roomId);
                                    $('#message'+roomId).val('');
                              }
                            );
                        }
                    });
           }

         function update(roomId){
            $.post("../ChatList", {room: roomId}, function(data){
                            var object = $.parseJSON(data);
                            $('#chatShortHistory'+roomId).empty();
                            $.each(object.Super, function() {                           
                                     $('#chatShortHistory'+roomId).prepend( '<div class="chatPost"><span><div class="'+this.type+'" style="float: left; width: 70%;" ><a href="'+this.link+'.xhtml?id='+this.id+'" title="'+this.fio+'">'+this.fio+'</a></div><span class="chatPostDate">'+this.date+'</span><div class="floatDestroyer"/></span><span class="chatPostText">'+this.message+'</span><div class="floatDestroyer"/></div>');
                            });
                            $('#chatShortHistory'+roomId).scrollTop(16000);
                                });
             setTimeout('update('+roomId+')', 32000);
         }

