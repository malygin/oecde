<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="student" value="${sessionScope[\"student\"]}" />
<c:set var="action" value="${pageContext.request.parameterMap[\"a\"][0]}"/>
  <c:set var="list" value="${student.studentsList}"/>
<script>


</script>

<!--<div dojoType="dijit.form.Button" onclick="makeScrollPane()">
  Make Widget
</div>-->
<!--<div align="center" id="loader" style="z-index: 1000; position: relative; margin-top:10px; display:none"><img src="../images/loader1.gif"></div>-->
 <div id="scrollPane1" >
     <table cellspacing="0" class="fooBar">
			<tbody>
                 <c:forEach items="${list}" var="stud" varStatus="i">
                <tr  colspan="3" style="line-height: 5px;">
                    <td>&nbsp;</td>
				</tr>
				<tr  class="spisok_ludei">
                    <td class="chek_for_mess"><input id="chbox - student - ${stud.id}"   dojoType="dijit.form.CheckBox"  onChange="add_new_getter('student - ${stud.id}',arguments[0])" /> </td>
	            <td class="text_for_mess"><img style="float: left; margin-right: 10px;" src="../loadPhoto?id=${stud.id}&type=student&size=mini"/></td>
                    <td class="text_for_mess_2">
                        <div id="getter - student - ${stud.id}">${stud.surname} ${stud.name} ${stud.second_name}</div></td>
				</tr>

                 </c:forEach>

                
               
			</tbody>
			</table>
</div>
