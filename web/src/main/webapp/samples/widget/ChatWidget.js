dojo.provide("samples.widget.ChatWidget");
dojo.require("dojox.cometd");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

function send_mess_big (message){
        data = dojo.fromJson(message.data);
        var chatarea = dojo.byId("chat_inner_big");
          authorStyle = "";
          authorhref="";
        switch (data.authorType) {
            case "admin":
                authorStyle = "chat_name_admin";
                break;
            case "adminLite":
                authorStyle = "chat_name_admin";
                break;
            case "student":
                authorStyle = "chat_name_student";
                 authorhref="#student/id="+data.id;
                break;
            case "teacher":
                authorStyle = "chat_name_teacher";
                authorhref="#teacher/id="+data.id;
                break;
            default:
                authorStyle = "chat_name_student";
                break;
        }
        chatarea.innerHTML = chatarea.innerHTML + "<div class='chat_msg_s' style='height:67px;'><div style='float: left; width: 13%; height:67px;'><img src='http://oecdo.sgu.ru/loadPhoto?id="+data.id+"&type="+data.authorType+"&size=mini'> </div> <div style='width: 87%;'><a href='"+authorhref+"' style='cursor: pointer' class='" + authorStyle + "'>" + data.author + "</a><b>" + data.date + "</b><br>" + data.text + "</div></div>";
        chatarea.scrollTop = chatarea.scrollHeight;
    };



dojo.declare("samples.widget.ChatWidget", [dijit._Widget, dijit._Templated], {
    templatePath: dojo.moduleUrl("samples", "widget/templates/ChatWidget.html"),

    title: "Chat",

    postCreate: function() {
        dojox.cometd.init("../cometd");
        dojox.cometd.subscribe("/chat/messages", this, "receiveMessage");

            dojo.xhrPost({
            url: "../chat",
            handleAs: 'json',
            load: function (response, io) {
                //console.log("11",response);
                 for (i = 0; i < response.length; i++) {
                    message = {data: response[i]};
                    data = dojo.fromJson(message.data);
                    send_mess_big(message);
                }
                return response;
            },
            content: {action: "newconnect"}
        });

    },

    receiveMessage: send_mess_big ,

    sendMessage: function() {
        dojo.xhrPost({url: "../chat", handleAs: 'json', content: {message: this.chatText.value}});
        this.chatText.value = "";
    },

    enterCheck: function(evt) {
        if(evt.keyCode == dojo.keys.ENTER) {
            this.sendMessage();
            dojo.stopEvent(evt);
        }
    },

    _appendText: function(node, text){
        while(node.firstChild){
	    node.removeChild(node.firstChild);
	}
        node.appendChild(document.createTextNode(text));
    }
});
