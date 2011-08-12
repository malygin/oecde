

$(window).bind('beforeunload', function(){
    return 'Ваша попытка не будет засчитана, вы уверены?';
});

function checkTime() {
    document.getElementById('mainForm:j_idt101:completeTest').click();
   // console.log("dsdsd");

}
function removeCounter() {
    $("#counter").remove();
}

$(function(){
    $(".qTitle").each(function(i){
        if($(this).text().indexOf("link:")!=-1){
            str=$(this).text();
            $(this).html("");
            $('<iframe height="100px"   frameborder="0"  width="700px" name="myFrame"/>').attr('src', '../TestServlet?task='+str.substring(5)).appendTo($(this));
        }
    });
});
     
$(function(){
    $("label").each(function(i){
        if($(this).text().indexOf("<img")!=-1){
            $(this).html($(this).text());
        }

        if($(this).text().indexOf("link:")!=-1){
            str=$(this).text();
            //console.log(str);
            //    console.log(str.indexOf(":"))
            $(this).html("");
            $('<iframe height="70px"   frameborder="0"  width="300px" name="myFrame"/>').attr('src', '../TestServlet?task='+str.substring(str.indexOf(":")+1)).appendTo($(this));
        }

    });
});

$(function(){
    var iFrames = document.getElementsByTagName('iframe');

    function iResize(){
        for (var i = 0; i < iFrames.length; i++){
            h = iFrames[i].contentWindow.document.body.offsetHeight;
            iFrames[i].style.height = h +3+'px';
        }
    }
    if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
        $('iframe').load(function(){
            var tagP = this.contentWindow.document.body;
            tagP.setAttribute('style','margin: 0; padding: 0;');
            tagP.innerHTML = '<div>'+ tagP.innerHTML +'</div>';
            tagPHeight =  tagP.firstChild.offsetHeight;
            $(this).attr('style','height:'+tagPHeight+'px');

        //            $('.testQuestion .Ipsilon_Web_GreenText').attr('style','position:relative; bottom:' + (($(this).parent('.qTitle').children().height() - 3)/2) + 'px');
        //     var textQuestion = ($(this).parent('.qTitle').children().height() - 3)/2;
        //         console.debug(($(this).parent('.qTitle').children().first().height() - 3)/2);
        });

    }
    if ($.browser.safari){
        $('iframe').load(function(){
            var tagP = this.contentWindow.document.body;
            this.contentWindow.document.body.setAttribute('style','margin: 0; padding: 0;');
            tagP.setAttribute('style','margin: 0; padding: 0;');
            tagPHeight = tagP.offsetHeight;
            tagPChilds = tagP.childNodes;
            if(tagPChilds != null){
                for (i = 1; i < tagPChilds.length; i++){
                    k = tagPChilds[i].offsetHeight;
                    if(k >= tagPChilds[i-1]) tagPHeight = k;
                }
            }
            $(this).attr('style','height:'+tagPHeight+'px');
            this.contentWindow.document.firstChild.style.height = tagPHeight + 'px';

            style = this.contentWindow.document.createElement('style');
            style.innerHTML = '*{margin: 0px; }';
            this.contentWindow.document.head.appendChild(style);
            d = $(this).parent().parent();
            h = d.height();
            c = d.children();
            h = (h/2)-6;
            c[0].setAttribute('style','margin-top:'+h+'px');
        });
    }else{
        if($.browser.opera){
            $('iframe').load(function(){
                style = this.contentWindow.document.createElement("style");
                style.innerHTML = 'body,body>*{margin: 0px; display:inline;line-height: 100% !important;} *{margin: 0px; padding:0px;} ';
                style.type = 'text/css';
                this.contentWindow.document.head.appendChild(style);
                iResize();
                d = $(this).parent().parent();
                h = d.height();
                c = d.children();
                h = (h/2)-6;
                c[0].setAttribute('style','margin-top:'+h+'px');

            });
        }else{
            $('iframe').load(function(){
                style = this.contentWindow.document.createElement('style');
                style.innerHTML = '*{margin: 0px; }';
                this.contentWindow.document.head.appendChild(style);
                this.style.height = this.contentWindow.document.body.offsetHeight + 'px';
                d = $(this).parent().parent();
                h = d.height();
                c = d.children();
                h = (h/2)-6;
                n = $('.testQuestion').height() - 19;
                c[0].setAttribute('style','margin-top:'+h+'px');
                $('.testQuestionNumber').attr('style','position: relative; bottom:'+ n+'px;')
            });
            
        }
    }
});

$(function(){
    $('iframe').load(function(){
//        iframEWidth = ($(this).width())/2;
//        picture = this.contentWindow.document.getElementsByClassName('imG')[0];
//        pictureWidth = ($(picture).width())/2;
//        margiN = iframEWidth - pictureWidth -42;
//        pictureParent = $(picture).parent();
//        pictureParent.attr('style', 'margin-left:'+ margiN+ 'px;');
        n = $('.testQuestion').height() - 19;
        c[0].setAttribute('style','margin-top:'+h+'px');
        $('.testQuestionNumber').attr('style','position: relative; bottom:'+ n+'px;')
    });
});

$(function(){
    vd = document.getElementsByClassName('testComparVariants');
   // console.log($(vd).children().children().attr('style','width: 170px;'));
   
});

