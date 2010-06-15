<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import = "admin.model.*" %>
<%@ page import = "java.util.*" %>
<%
    Admin t = new Admin();
    List<AdminItem> ListSpec = t.getAdmins();
%>




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
                        <%

     for(AdminItem l:ListSpec){
            %>
				<tr  class="spisok_ludei">
                     <td class="chek_for_mess"><input id="chbox - admin - <%=l.getId() %>"  dojotype="dijit.form.CheckBox" onChange="add_new_getter('admin - <%=l.getId() %>',arguments[0])"  /> </td>
                    <td class="text_for_mess"><img style="float: left; margin-right: 10px;" src="../loadPhoto?id=<%=l.getId() %>&type=admin&size=mini"/></td>
                    <td class="text_for_mess_2">
                        <div style=" color: #74c6f6; font-weight: bold;" id="getter - admin - <%=l.getId() %>"><%=l.getSurname() %> <%=l.getName() %> <%=l.getSecondName() %></div>
                        <p><%=l.getInfo() %></td>
				</tr>
                <tr  colspan="4" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
                   <%
            }
        %>

		

			</tbody>
			</table>
</div>
