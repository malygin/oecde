<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<script>
	document.title="Новое событие";
	dojo.require("dijit.Editor");
    dojo.require("dijit.form.Button");
    dojo.require("dijit._editor.plugins.AlwaysShowToolbar");
    dojo.require("dijit._editor.plugins.EnterKeyHandling");
    dojo.require("dijit._editor.plugins.TextColor");
    dojo.require("dijit._editor.plugins.LinkDialog");

	function _redactError(){
		dojo.fadeOut({
			node: "errorMes",
			duration: 200,
			onEnd: function _121(){
				dojo.byId("errorMes").parentNode.removeChild(dojo.byId("errorMes"));
				dojo.byId("editor").style.display = "block";
				dojo.fadeIn({
					node: "editor",
					duration: 200
				}).play();
			}
		}).play();
	}
	function _preSubmit(){
		dojo.fadeOut({
			node: "editor",
			onEnd: function _123(){
				dojo.byId("editor").style.display = "none";
				var content = dijit.byId("editorElem").getValue();
				if(content != "<br _moz_editor_bogus_node=\"TRUE\" />" && content.length > 20){
					dojo.byId("preSubmit").style.display = "block";
					dojo.byId("eventText").innerHTML = content;
					dojo.fadeIn({
						node: "preSubmit",
						duration: 200
					}).play();
				}else{
					var div = document.createElement("div");
					dojo.byId("eventArea").appendChild(div);
					div.innerHTML = "Ошибка! Введены неверные данные. Отредактируйте текст события."
					dojo.attr(div,"style","margin: 10px; text-align: center; border: 1px solid red; background: #FFE4E1; padding: 10px;");
					div.id = "errorMes";
					var div2 = document.createElement("div");
					div.appendChild(div2);
					dojo.attr(div2,"style","text-align: right; margin-top: 10px;");
					var backBut = document.createElement("input");
					div2.appendChild(backBut);
                    dojo.attr(backBut,"type","image");
					dojo.attr(backBut,"src","../images/chk.gif");
					dojo.attr(backBut,"onClick","_redactError()");
					dojo.attr(backBut,"value","");
					dojo.attr(backBut,"title","Нажмите, чтобы вернуться к редактированию тескта события.");
				}
			},
			duration: 200
		}).play();
	}
	function _redactEvent(){
		dojo.fadeOut({
			node: "preSubmit",
			onEnd: function _123(){
				dojo.byId("preSubmit").style.display = "none";
				dojo.byId("editor").style.display = "block";
				//dojo.byId("eventText").innerHTML = dijit.byId("editor").getValue();
				dojo.fadeIn({
					node: "editor",
					duration: 200
				}).play();
			},
			duration: 200
		}).play();
	}
	function _successCreating(){
        dojo.xhrPost({
            content:{message:dijit.byId("editorElem").getValue()},
            url:'OwnEventCreationMessageServlet.c',
            load:success,
            error:err
        })
    }

    function err(e){
        console.debug(e);
        dojo.byId("resulOfEventCreating").style.display = "block";
        dojo.byId("resulOfEventCreating").innerHTML = "Во время добавления возникла ошибка";
    }

    function success(){
		dojo.fadeOut({
			node: "yesNoButtons",
			onEnd: function _124(){
				dojo.byId("yesNoButtons").style.display = "none";
				dojo.byId("resulOfEventCreating").style.display = "block";
				dojo.byId("resulOfEventCreating").innerHTML = "Событие было успешно добавлено.";
				dojo.fadeIn({
					node: "resulOfEventCreating",
					onEnd: function _125(){
						dojo.fadeOut({
							node: "preSubmit",
							onEnd: function _126(){
                                dijit.byId("editorElem").setValue("")
								dojo.byId("editor").style.display = "block";
								dojo.fadeIn({
									node: "editor",
									onEnd: function _127(){
										dojo.byId("preSubmit").style.display = "none";
										dojo.byId("resulOfEventCreating").style.display = "none";
										dojo.byId("eventText").style.display = "block";
										dojo.byId("yesNoButtons").style.display = "block";
										dojo.byId("yesNoButtons").style.opacity = "1";
									},
									duration: 1000
								}).play();
							},
							duration: 300
						}).play();
					},
					duration: 300
				}).play();
			},
			duration: 200
		}).play();
	}
</script>
<div class="head_main_math">
    <p>Новое событие</p>
</div>
<div id="eventArea">
	<div class="event_editor_area" id="editor">
		<form method="post" name="eventCreator">
			<div dojoType="dijit.Editor" id="editorElem" plugins="['undo', 'redo','|','bold', 'italic', 'underline','|','indent', 'outdent', '|', 'justifyLeft', 'justifyRight', 'justifyCenter', 'justifyFull','|','createLink','|','foreColor','|','hiliteColor','|']"></div>
			<div style="text-align: center; margin-top: 10px;">
                            <div class="stand_button_new">
                                <div dojoType="dijit.form.Button" onclick="_preSubmit()">
                                    Создать событие
                                </div>
                            </div>
			</div>
		</form>
	</div>
	<div id="preSubmit" class="befor_submiting_show">
		<div id="eventText" style="text-align: justify;" class="eventText">

		</div>
		<div id="yesNoButtons" style="text-align: right; margin-top: 5px;">
			<input type="image" value="" src="../images/chk.gif" onclick="_successCreating()" title="Нажмите, чтобы сохранить событие"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="image" value="" src="../images/no.png" onclick="_redactEvent()" title="Нажмите, чтобы вернуться к редактированию события"/>
		</div>
		<div id="resulOfEventCreating" style="display: none; text-align: center; margin: 10px; border: 1px solid silver; padding: 10px;">
			
		</div>
	</div>
</div>