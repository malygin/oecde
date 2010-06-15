<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div class="chat_small">
        <a class="chat_links" style="float: left;"></a>
        <a class="chat_links" style="float: right;" href="#cht">общий чат</a>
	<img width="215" src="../images/chat_sep.jpg">
        <div dojoAttachPoint="chatInner" id="chat_inner" class="chat_inner">

	</div>
	<img width="215" src="../images/chat_sep.jpg">
	<input type="text" dojoAttachPoint="chatText" dojoAttachEvent="onkeypress: enterCheck"></input>
        <img dojoAttachEvent="onclick: sendMessage" style="border: 0; margin: 0 0 -6px 0;" src="../images/chat_enter_s_squar.png">
        </br>
 </div>