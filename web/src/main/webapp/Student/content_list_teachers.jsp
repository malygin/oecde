<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <c:set var="student" value="${sessionScope[\"education\"]}" />
        <c:set target="${student}" property="semestr" value="0" />
        <c:set var="teachersList" value="${student.teachersList}"/>

 <div id="scrollPane3" >
     <table cellspacing="0" class="fooBar">
			<tbody>
                <c:forEach items="${teachersList}" var="tea" varStatus="i">
                <tr  colspan="3" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                    <td class="chek_for_mess"><input id="chbox - teacher - ${tea.id}"   dojoType="dijit.form.CheckBox"  onChange="add_new_getter('teacher - ${tea.id}',arguments[0])" /> </td>
				    <td class="text_for_mess"><img style="float: left; margin-right: 10px;" src="../loadPhoto?id=${tea.id}&type=teacher&size=mini"/>
                                    </td>
                                      <td class="text_for_mess_2">
                                        <div style=" color: #74c6f6; font-weight: bold;" id="getter - teacher - ${tea.id}">${tea.surname} ${tea.name} ${tea.second_name}</div></td>
				</tr>

                 </c:forEach>



			</tbody>
			</table>
</div>
