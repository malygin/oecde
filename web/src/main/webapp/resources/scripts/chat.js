      $(document).ready(function() {
                             chatAction(1)});
      $(document).ready(function() {
                             chatAction(2)});

           function chatAction(roomId) {
                 update(roomId);
                 $('#chatShortHistory'+roomId).scroll(function() {
              //    console.log('scroooll');
              });

                 $('#button'+roomId).click(function(){
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
                                     $('#chatShortHistory'+roomId).prepend( '<div class="chatPost"><span><span class="'+this.type+'" ><a href="'+this.link+'.xhtml?id='+this.id+'" title="'+this.fio+'">'+this.fio+'</a></span><span class="chatPostDate">'+this.date+'</span><div class="floatDestroyer"/></span><span class="chatPostText">'+this.message+'</span><div class="floatDestroyer"/></div>');
                            });
                            $('#chatShortHistory'+roomId).scrollTop(8000);
                                });
             setTimeout('update('+roomId+')', 8000);
         }

