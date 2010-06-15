<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import = "admin.model.*" %>
<%@ page import = "java.util.*" %>
<%
    Admin t = new Admin();
    List<AdminItem> ListSpec = t.getLightAdmins();
%>

 <div id="scrollPane5" >

 <table cellspacing="0" class="fooBar">
			<tbody>
                <%

            for(AdminItem l:ListSpec){
            %>
         <tr  colspan="3" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                    <td class="chek_for_mess"><input id="chbox - admin - <%=l.getId() %>"  dojoType="dijit.form.CheckBox"  onChange="add_new_getter('admin - <%=l.getId() %>',arguments[0])" /> </td>
				 
				 <td class="text_for_mess_2">
                        <div id="getter - admin - <%=l.getId() %>"><%=l.getSurname() %> <%=l.getName() %> <%=l.getSecondName() %></div>
                        </td>
                                </tr>
        <%
            }
        %>
			</tbody>
			</table>
</div>
