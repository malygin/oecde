dojo.provide("samples.widget.SmallChatWidget");
dojo.require("dojox.cometd");
dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

function send_mess (message){
        data = dojo.fromJson(message.data);
        var chatarea = dojo.byId("chat_inner");
        authorStyle = "";
         authorhref="";
        switch (data.authorType) {
            case "admin":
                authorStyle = "chat_name_admin";
                authorhref="#admin/id="+data.id;
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
        chatarea.innerHTML = chatarea.innerHTML + "<div class='chat_msg_s'><a href='"+authorhref+"' style='cursor: pointer' class='" + authorStyle + "'>" + data.author + "</a><b>" + data.date + "</b><p>" + data.text + "</p></div>";
        chatarea.scrollTop = chatarea.scrollHeight;
    };



dojo.declare("samples.widget.SmallChatWidget", [dijit._Widget, dijit._Templated], {
    templatePath: dojo.moduleUrl("samples", "widget/templates/SmallChatWidget.jsp"),

    title: "Chat",

    postCreate: function() {
        dojox.cometd.init("../cometd");
        dojox.cometd.subscribe("/chat/messages", this, "receiveMessage");
        dojo.xhrPost({
            url: "../chat",
          //  contentType: "application/x-www-form-urlencoded; Accept-Charset=utf-8",
            handleAs: 'json',
            load: function (response, io) {
                //console.log("11",response);
                 for (i = 0; i < response.length; i++) {
                    message = {data: response[i]};

                       data = dojo.fromJson(message.data);
                       // console.log("11",data.text);
                       // this.receiveMessage(message);
                      send_mess(message);
                }
                return response;
            },
            content: {action: "newconnect"}
        });

    },

    receiveMessage: send_mess,

    reply: function(name) {
        console.log("1"+name);
        this.chatText.value = name + ': ';
    },
	destroy: function() {
        dojox.cometd.disconnect();
		dojo.byId('SmallChatWidget').style.display = "none";

    },

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