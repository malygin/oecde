<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>






<!--<div dojoType="dijit.form.Button" onclick="makeScrollPane()">
  Make Widget
</div>-->
<!--<div align="center" id="loader" style="z-index: 1000; position: relative; margin-top:10px; display:none"><img src="../images/loader1.gif"></div>-->
 <div id="scrollPane4" >

<table cellspacing="0">
			<tbody>
                <tr  colspan="4" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                     <td class="chek_for_mess"><input id="chbox - admin - 42"  dojotype="dijit.form.CheckBox" onChange="add_new_getter('admin - 42',arguments[0])"  /> </td>
                    <td class="text_for_mess"><img style="float: left; margin-right: 10px;" src="../loadPhoto?id=42&type=admin&size=mini"/></td>
                    <td class="text_for_mess_2">
                        <div style=" color: #74c6f6; font-weight: bold;" id="getter - admin - 42">Иванова Наталья Александровна</div>
                        <p>вопросы связанные с организацией дистанционного образования</td>
				</tr>
                <tr  colspan="4" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                     <td class="chek_for_mess"><input id="chbox - admin - 1"  dojotype="dijit.form.CheckBox" onChange="add_new_getter('admin - 1',arguments[0])"  /> </td>
                    <td class="text_for_mess"><img style="float: left; margin-right: 10px;" src="../loadPhoto?id=1&type=admin&size=mini"/></td>
                    <td class="text_for_mess_2">
                        <div  style=" color: #74c6f6; font-weight: bold;"  id="getter - admin - 1">Шихов Михаил Юрьевич</div>
                        <p>вопросы связанные с работой портала, сообщайте об ошибках и предлагайте улучшения</td>
				</tr>
                <tr  colspan="4" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>

			</tbody>
			</table>
</div>
