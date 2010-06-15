<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="shed"  value="${sessionScope[\"education\"]}"/>
    <script type="text/javascript">
        document.title = "ЦОО СГУ. Учебный план";
		dojo.require("dojo.parser");
		dojo.require("dojo.fx");
		
		
		function scroller(){
			var heig=document.getElementById('chengable_block').offsetHeight;
			console.debug(heig);
			window.scroll(200,heig);
		}
    </script>
    <div class="title">
        <p>Учебный план на 
           <c:set var="semestr" value="${param[\"s\"]}"/>
    <c:choose>
      <c:when test="${semestr == '1'}">
         <c:set var="semestr" value="1"/>
          предыдущий семестр
          <a class='stmat_link' href='#shedule/s=0'>(текущий семестр)</a>
      </c:when>
      <c:otherwise>
         <c:set var="semestr" value="0"/>
             текущий семестр
             <a class='stmat_link' href='#shedule/s=1'>(предыдущий семестр)</a>
           </c:otherwise>
    </c:choose>

		
		<a class="help_part_href0" onClick="scroller()">Подсказка</a>
		
        </p>
    </div>
    <div class="name_shed">					
                    <p><c:out  value="${sessionScope[\"studentItem\"].spec.name}" />,  <c:out  value="${sessionScope[\"studentItem\"].year}" />  курс</p>
    </div>
    <div class="chengable_block">
        <table id="chengable_block" cellspacing="0" cellpadding="0" bgcolor="white" align="left" width="522">
            <tbody>
                <tr>
                    <td>
                        <table class="sch" cellspacing="0" cellpadding="0" border="1" width="523">
                            <tbody class="shedule_table">
                                <tr class="hat_shedule_table">
                                    <td align="center" width="80" valign="center"><img src="../images/cikl.png"></td>
                                    <td align="center" width="500" valign="center"><img src="../images/disciplina.png"></td>
                                    <td class="vertical" width="24" style="display: none;"><img src="../images/lections.png"></td>
                                    <td class="vertical" width="24" style="display: none;"><img src="../images/labs.png"></td>
                                    <td class="vertical" width="24" style="display: none;"><img src="../images/practics.png"></td>
                                    <td class="vertical" width="24"><img src="../images/tests.png"></td>
                                    <td class="vertical" width="24"><img src="../images/courses.png"></td>
                                    <td class="vertical" width="24"><img src="../images/control.png"></td>
                                </tr>
  <c:set target="${shed}" property="semestr" value="${semestr}"/>
  <c:set var="cSum" value="0"/>
  <c:forEach items="${shed.shedule}" var="sched">
        <c:set var="cSum" value="${cSum+sched.kr}"/>
            <c:if test='${sched.zach == "З"}'> <c:set var="simbStyle" value="contr_simb1"/> </c:if>
            <c:if test='${sched.zach == "Э"}'> <c:set var="simbStyle" value="contr_simb2"/> </c:if>
             <tr id="ctroka_0">
                 <td align="left" class="data" classname="data" style="padding-left: 5px; padding-right: 5px;"><c:out value="${sched.cycle}" /></td>
                 <td align="left" class="data" classname="data" style="padding-left: 5px; padding-right: 5px;"><c:out value="${sched.name}" /></td>
                 <td align="center" class="data" classname="data" style="padding-left: 5px; padding-right: 5px;">
                    <c:if test='${sched.kontrol != "0"}'> <c:out value="${sched.kontrol}" /></c:if></td>
                 <td align="center" class="data" classname="data" style="padding-left: 5px; padding-right: 5px;">
                    <c:if test='${sched.kr != "0"}'> <c:out value="${sched.kr}" /></c:if></td>
                 <td align="center" class="${simbStyle}">
                     <c:if test='${sched.zach != "0"}'> <c:out value="${sched.zach}" /></c:if>
                 </td>
            </tr>
</c:forEach>

                                <tr id="summa">
                                    <td align="left" class="vsego" classname="vsego" colspan="2" style="padding-left: 5px; padding-right: 5px;">   Всего:</td>
                                    <td align="center" class="vsego" classname="vsego"></td>
                                    <td align="center" class="vsego" classname="vsego"><c:out value="${cSum}"/></td>
                                    <td align="center" class="vsego" classname="vsego"></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div align="centr"><div class="otdelitel"></div></div>
    
	<div class="prosto_tekst">
        <p class="help_part">Подсказки</p>
		<p class="comment">
		
            Перед вами рабочий план на 1 семестр (летнюю сессию).<br>
            1. В начале списка дисциплин указаны те, которые сдаются в зимнюю сессию. Так же отмечены в столбце «контроль»: «з» - сдается зачет, «э» - сдается экзамен.<br>
            2. Далее перечислены дисциплины, которые сдаются во 2 семестре – в летнюю сессию;<br>
            3. В столбце контрольные работы отмечаются дисциплины, по которым в 1 семестре сдаются контрольные работы;<br>
            4. В столбце курсовые работы отмечаются дисциплины, по которым в 1 семестре происходит защита курсовых работ.<br>
            Напоминаем, контрольные работы за 1 семестр сдаются в СГУ до 15 декабря.
            Темы по курсовым работам необходимо получить у преподавателя, ведущего соответствующую дисциплину.
        </p>
		<a class="help_part_href" onClick="javascript: scroll(0,0);">Наверх</a>
    </div>	

