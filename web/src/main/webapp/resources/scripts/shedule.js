var lessons = new Object();
var day = new Date();
var year=day.getFullYear(); 
var month=day.getMonth()+1;

$(function(){
    $('#datepicker').datepicker({
            inline: true,
            onChangeMonthYear: function(year, month, inst) {
                    repaint(year, month);
            }
    });
    repaint(year, month);
});

function repaint(year, month){
     $.post("../SheduleServlet", 
            {month: month, year: year}, 
            function(lessonData){
                    lessons = eval(lessonData);                             
                    $('a.ui-state-default[href="#"]').each(function(){
                                                        var title="";
                                                        while($(this).html() == lessons.Super[0].day){
                                                                $(this).parent().attr('class',"lectures");
                                                                title += "<span style='color:#5BBF5D; display: inline;'>"+lessons.Super[0].time+"</span>"+" "+"<span style='color:#89C9F5; display: inline;'>"+lessons.Super[0].discipline+"</span>"+" "+"<span style='color:#5BBF5D; display: inline;'>"+lessons.Super[0].teacherFIO+"</span>"+" "+lessons.Super[0].room+" комната"+"<br />";
                                                                lessons.Super=lessons.Super.slice(1);
                                                                if(lessons.Super.length==0) {
                                                                    $(this).simpletip({ content: title});
                                                                    return false;
                                                                }
                                                         }
                                                         $(this).simpletip({ content: title});
                                                });

            $('#datepicker td').each(function(){
                    $(this).removeAttr("onclick");
            });
     });
}