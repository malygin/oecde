
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "msgs.model.*" %>
<%@ page import = "admin.model.*" %>
<%@ page import = "java.util.*" %>
 <%@ page import = "base.model.*" %>
   <%@ page import = "login.model.UserType" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
      <link href="../dojoroot/dojox/form/resources/FileUploader.css" rel="stylesheet" />

<%
    Messg msg = new Messg();
     HashMap types = new HashMap();
     String kod;
     String value;

   String id_send=(String)request.getParameter("id_send");
   String type_send=(String)request.getParameter("type_send");
   String type_let=(String)request.getParameter("type_let");
   String  user_send=null;
  UserItem userI=null;
   System.out.println("id_send "+(String)request.getParameter("id_send"));
   System.out.println("type_send "+(String)request.getParameter("type_send"));

   if(id_send != null && type_send!=null){
       System.out.println("id_send "+(String)request.getParameter("id_send"));
        User user=new User();
        userI=user.get_user_by_type(type_send, id_send);
        System.out.println("fio= "+userI.getFIO());

        if (type_send.equals("1")){
         user_send="admin - "+userI.getId();
        }else if(type_send.equals("3")){
          user_send="teacher - "+userI.getId();
        }else if(type_send.equals("4")){
          user_send="student - "+userI.getId();
        }

    }



    //System.out.println("id_send "+(String)request.getParameter("id_send"));
   // System.out.println("type_send "+(String)request.getParameter("type_send"));
   // System.out.println("type_let "+(String)request.getParameter("type_let"));
    /*    if(!msg.isRight_Type(type_send, type_let)){

    }*/

     UserType userType= (UserType) session.getAttribute("type");
     types=msg.getTypes(Integer.toString(userType.getId()));
     Set pSet = types.entrySet();
%>

    <script type="text/javascript">
        var djConfig = {
            parseOnLoad: true,
            isDebug: true,
          debugAtAllCosts:false
        };
    </script>


<script type="text/javascript">
       dojo.require("dijit.form.TextBox");
       dojo.require("dojox.form.DropDownSelect");
       dojo.require("dijit.Editor");
       dojo.require("dijit.ProgressBar");
       dojo.require("dijit.form.Button");
       dojo.require("dijit.Dialog");
       dojo.require("dojox.form.FileUploader");
       dojo.require("dojo.parser");
       dojo.require("dijit.layout.ContentPane");
       dojo.require("dijit.layout.TabContainer");
       dojo.require("dijit.form.CheckBox");
        //using this early for the forceNoFlash test:
       dojo.require("dojox.embed.Flash");
       dojo.require("dojox.layout.ScrollPane");

   </script>

   <script type="text/javascript">
       var flag_file=true;
       var list_getters="";
       		addThumb = function(d, id){
                    console.log("THUMB:", d);
                    var fileRoot = dojo.moduleUrl("dojox.form", "tests").toString();
                    var img = '<img src='+fileRoot+"/"+escape(d.file)+
                    (d.width>d.height ?
                    ' width="50"/>' :
                    ' height="50"/>');
                    console.log("IMG:", img)
                    var str = '<div id="file_'+d.name+'" class="thumb"><div class="thumbPic">'+img+'</div>';
                    str += '<div class="thumbText">';
                    if(d.fGroup || d.hGroup){
                        str += 'Group: '+(d.fGroup || d.hGroup)+'<br/>';
                    }
                    str += 'Title: '+d.name+'<br/>';
                    if(d.author){
                        str += 'Author: '+ d.author+'<br/>';
                    }
                    if(d.date){
                        str += d.date+' ';
                    }
                    str += '</div></div>';
                    dojo.byId(id).innerHTML += str;
                }

    dojo.addOnLoad(function(){
			var props = {
				isDebug:true,
				hoverClass:"uploadHover",
				activeClass:"uploadPress",
				disabledClass:"uploadDisabled",
				uploadUrl:"../Upload_File.c"
            }
        if(dojo.byId("btnH")){
				dojo.byId("hFiles").value = "";
				var h = new dojox.form.FileUploader(dojo.mixin({
					force:"html",
					showProgress:true,
					progressWidgetId:"progressBarHtml",
					selectMultipleFiles:true,
					fileListId:"hFiles",
					tabIndex:11
				}, props), "btnH");
                console.log(h);

				dojo.connect(dijit.byId("hSubmit"), "onClick", function(){
                    //alert(3);
					h.submit(dojo.byId("formH"));
				});
				dojo.connect(h, "onComplete", function(dataArray){
                    console.log(h);
                    flag_file=false;
                   hide_form_message();
				});
                dojo.connect(h, "onError", function(dataArray){
                    console.log("error");
                    hide_form_message();
				});
			}




});




</script>

<script type="text/javascript">
    function makeScrollPane(id) {
                    // programatic example:
                    var widget = new dojox.layout.ScrollPane({
                      orientation:"vertical",
                      style:"width:600px; height:370px;border: none;"
                    },id);

                    widget.startup();
                  }


</script>

 <script type="text/javascript" src="../scripts/msgs.js" djConfig="parseOnLoad: true"></script>

<style type="text/css">
  table.fooBar td {
    /*width:50px;
    border-right:2px solid #000;
    padding:20px;*/
  }
  .foo { float:left; }
  .dojoxScrollWindow {
	position:relative;
}
.dojoxScrollHelper .helperInner {
	visibility: hidden;
}
.dojoxScrollHelper {
	border:1px solid #b7b7b7;
	width:4px;
	background:#ededed;
	height:4px;
	position:absolute;
	top:4px;
	left:4px;
	border-radius: 3px;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
}
.dojoxScrollWrapper {
 	width:100%;
}

</style>

<div class="head_main_math">
<p>Новое сообщение</p>
</div>
<div id="send_message_success" class="delete_message_success_from_reading" style="display: none;">
    Сообщение успешно отправлено!<br>
    Вы можете <a class="recovery_link" href="javascript:void(0)" onClick="window.location.reload()">написать новое сообщение</a> или <a class="link_back_to_mes_list" href="main.jsp#msg">перейти к списку входящих писем!</a>
</div>

<div dojoType="dijit.Dialog" id="dialog_for_mess" title="Выбор адресата" execute="alert('submitted w/args:\n' + dojo.toJson(arguments[0], true));" onLoad="makeScrollPane()">

 <div id="mainTabContainer" dojoType="dijit.layout.TabContainer" style="width:535px;height:400px">

 <div id='layout4'  dojoType="dijit.layout.ContentPane"  href="content_list_service.jsp" title="Администраторы" selected="true" refreshOnShow="true" onLoad="makeScrollPane('scrollPane4')">
    </div>
     <div id='layout5'  dojoType="dijit.layout.ContentPane"  href="content_list_service2.jsp" title="Представители" selected="true" refreshOnShow="true" onLoad="makeScrollPane('scrollPane5')">
    </div>
</div>

<div class="bottom_part"><!--onclick="check_img(this)"-->
 <div class="stand_button_new" style="text-align: right;">

         <div tabIndex="10"  class="uploadBtn btn" style="width: 120px !important;float: left;margin: 7px;" onmouseover="change_bg(this)" onmouseout="change_back(this)" onclick="confirm_list_getters()">Подтвердить</div>
         <div tabIndex="10"  class="uploadBtn btn" style="width: 120px !important;float: right;margin: 7px;" onmouseover="change_bg(this)" onmouseout="change_back(this)" onclick="cancel_list_getters()">Отмена</div>
          <!--
        <div dojoType="dijit.form.Button" onclick="confirm_list_getters()">Подтвердить</div>
        <div dojoType="dijit.form.Button" onclick="cancel_list_getters()">Отмена</div>-->
    </div>
</div>
</div>

<div id="main_message">
    <form id="form_post_letter">

        <div class="otdel_blok_mes">
            <table class="tab_for_write_new_mes">
                <tr>
                    <td valign="top" style="color: #9ebfc9;font-size: 11pt;">Кому</td>
                    <td id="vvod_info_mes" class="vvod_info_mes">
                         <% if(id_send != null && type_send!=null){ %>
                        <div id="<%=user_send %>"><%=userI.getFIO()%>  (<a href="javascript:void(0)" onclick="del_getter('<%=user_send %>')">удалить</a>)</div>
                        <input type="hidden" id="getters" name="getters" value='<%=user_send %>;' />
                    <% } else{   %>
                    <input type="hidden" id="getters" name="getters" value='' />
                    <% }    %>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div tabIndex="10" class="uploadBtn btn" style="width: 120px !important;" onmouseover="change_bg(this)" onmouseout="change_back(this)" onclick="show_select_getters()">+Добавить адресата</div>
                        <!--<div class="stand_button_new"><div dojoType="dijit.form.Button" onclick="show_select_getters()">+Добавить адресата</div></div>-->
                        </td>
                </tr>
                <tr>
                    <td style="color: #9ebfc9;font-size: 11pt;">Тема</td>
                    <td class="vvod_info_mes">
                        <input type="text" id="them" name="them" value="Тема вашего сообщения"
                        dojoType="dijit.form.TextBox"
                        trim="true"
                        propercase="true" />
                    </td>
                </tr>
                <tr>
                    <td style="color: #9ebfc9;font-size: 11pt;">Тип</td>
                    <td class="typeOf_new_mes">
                        <select id="select_getters" dojoType="dojox.form.DropDownSelect"
                        name="type"
                        autocomplete="false"
                        value="CA">
                        <%
                            Iterator pI = pSet.iterator();
                              while(pI.hasNext()){
                                     Map.Entry p = (Map.Entry)pI.next();
                                     value = (String)p.getValue();
                                     kod = (String) p.getKey();
                                     out.print("<option value="+kod+">"+value+"</option>");
                              }
                        %>
                        </select>
                    </td>
            </table>
        </div>

        <div class="text_new_mes">
            <div name="main_text" dojoType="dijit.Editor" id="main_text"
              plugins="['undo', 'redo', '|', 'cut', 'copy', 'paste','|', 'bold','italic','underline','|', 'indent', 'outdent', '|', 'strikethrough', 'subscript','superscript', '|'  ]" >

            </div>

            <input type="hidden" name="rich_text" id="rich_text" />

        </div>
        <div  class="grey_button adding_files">

			<form id="formH" class="form">
                <table style="border-bottom: 1px solid silver;">
                    <tr>
                        <td style="padding: 10px; padding-left: 0px;" >
                            <div>Вы можете прикрепить к данному сообщению необходимые файлы (размер не более 4 мб): </div>
                        </td>
                        <td>
                            <div tabIndex="11"  id="btnH" class="uploadBtn btn" style="width: 120px !important;">Прикрепить файл</div>
                        </td>
                    </tr>
                </table>
                <div  class="field" id="hFiles"></div>
				<div indeterminate="false" id="progressBarHtml" class="statud_of_process_marg progBar" dojoType="dijit.ProgressBar"></div>

			</form>

			<div id="hThumbs" class="thumbList"></div>

            <div class="otdelitel"></div>
            <div class="line_top">
                <table class="tab_for_write_new_mes3 standart_dojo_buttons">
                    <tr>
                        <td>
                            <div tabIndex="10"  class="uploadBtn btn" style="width: 120px !important;" onmouseover="change_bg(this)" onmouseout="change_back(this)" onclick="post_letter()">Отправить</div>
                           <!-- <button id="pl" dojoType="dijit.form.Button" onclick="post_letter()"  style="background:transparent url(../images/disabledFileUplButn1.png)">
                              Отправить
                            </button>-->
                        </td>
                             <td style="padding-left: 232px;">
                                <div tabIndex="10"  class="uploadBtn btn" style="width: 120px !important;" onmouseover="change_bg(this)" onmouseout="change_back(this)" onclick="call_function()">Отмена</div>
                              </td>
                       <!--
                            <button dojoType="dijit.form.Button" onclick="call_function()"   style="background:transparent url(../images/disabledFileUplButn1.png); ">
                              Отмена
                            </button>
                        </td>-->
                    </tr>
                </table>
            </div>
        </div>


    </form>
</div>
