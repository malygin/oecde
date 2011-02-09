
       $(document).ready(function(){
                 update();
                 $('#button').click(function(){
                       $.post('../ChatList',
                              { message: $('#message').val()},
                              function(data){
                                    update();
                                    $('#message').val('');
                               }
                       );
                       });
                  $('#message').keypress(function(e) {
                        if(e.which == 13) {
                           $.post('../ChatList',
                              { message: $('#message').val()},
                              function(data){
                                    update();
                                    $('#message').val('');
                              }
                            );
                        }
                    });
        });

         function update(){
            $.post("../ChatList", {}, function(data){                       
                            var object = $.parseJSON(data);
                            $('#chatShortHistory').empty();
                            $.each(object.Super, function() {
                                console.log("3213");
                                     $('#chatShortHistory').prepend( '<div class="chatPost"><span><span class="'+this.type+'"><a href="'+this.link+'.xhtml?id='+this.id+'">'+this.fio+'</a></span><span class="chatPostDate">'+this.date+'</span><div class="floatDestroyer"/></span><span class="chatPostText">'+this.message+'</span><div class="floatDestroyer"/></div>');
                            });
                            $('#chatShortHistory').scrollTop(2000);
             });
             setTimeout('update()', 11000);
         }

