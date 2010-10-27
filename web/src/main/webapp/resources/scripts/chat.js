
       $(document).ready(function(){
                 update();
                 $('#button').click(function(){
                       $.post('/web/ChatSend',
                              { message: $('#message').val()},
                              function(data){
                                    update();
                                    $('#message').val('');
                               }
                       );});
        });

         function update(){
            $.post("/web/ChatList", {}, function(data){
                           var object = $.parseJSON(data);
                            $('#chatShortHistory').empty();
                            $.each(object.Super, function() {
                                     $('#chatShortHistory').prepend( '<div class="chatPost"><span><span class="'+this.type+'"><a href="#">'+this.fio+'</a></span><span class="chatPostDate">'+this.date+'</span><div class="floatDestroyer"/></span><span class="chatPostText">'+this.message+'</span><div class="floatDestroyer"/></div>');
                            });
                            $('#chatShortHistory').scrollTop(1000);
             });
             setTimeout('update()', 11000);
         }

