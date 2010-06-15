<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page import = "teacher.model.*" %>
<%@ page import = "java.util.*" %>
<%
    Teacher t = new Teacher();
    List<TeacherItem> ListSpec = t.getAllTeachers();
%>

 <div id="scrollPane3" >

 <table cellspacing="0" class="fooBar">
			<tbody>
                <%
        
            for(TeacherItem l:ListSpec){  
            %>
         <tr  colspan="3" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                    <td class="chek_for_mess"><input id="chbox - teacher - <%=l.getId() %>"  dojoType="dijit.form.CheckBox"  onChange="add_new_getter('teacher - <%=l.getId() %>',arguments[0])" /> </td>
				    <td class="text_for_mess"><div id="getter - teacher - <%=l.getId() %>"><%=l.getSurname() %> <%=l.getName() %> <%=l.getSecondName() %> </div></td>
				</tr>
        <%
            }
        %>  
			</tbody>
			</table>
</div>
