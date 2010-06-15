<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<c:set var="se" value="${sessionScope[\"education\"]}"/>
<c:set target="${se}" property="semestr" value="${se.currentSemestr}" />
<c:set target="${se}" property="id" value="${param[\"umk\"]}" />
<c:set var="umk" value="${se.umk}"/>

 <c:set var="itog" value="0"/>
 <c:if test="${umk.name == null || umk.id == null || param[\"umk\"] == null }">
     <div class=head_main_math>
     <p style="float: left;">Курс не найден.</p>
        <div style="float: right;">
		</div>
		<div class="otdelitel"></div>
	</div>

    <div class=tree>
        <br/>
        <p style="color: #777777;  font-size: 14pt;  font-weight: bold; margin: 0px; font-family: Arial; "> &nbsp&nbsp  Извините, но по Вашему запросу ничего не найдено</p>
        <p><a href="main.jsp#mat"><<Вернуться к списку курсов</a></p>
    </div>    
 </c:if>

<c:if test="${umk.name != null && umk.id != null && param[\"umk\"] != null }">
<style type="text/css">
    .dijitDialogTitle{
        color: white;
    }
</style>
<script>
   
    var data;
    data ={label:'name', identifier:'id', items:[
    <c:forEach items="${umk.module}" var="module" begin="0" varStatus="count">
          <c:if test="${!count.first}"> , </c:if>
         {id:<c:out value="${module.id}"/>,name:'<c:out value="${module.name}"/>', weight:<c:out value="${module.weight}"/>,
               elements:[
                     <c:forEach items="${module.tests}" var="test" begin="0" varStatus="countTest">
                         <c:if test="${!countTest.first}"> , </c:if>
                             {id:"test"+<c:out value="${test.id}"/>, name:'<c:out value="${test.title}"/>',weight:<c:out value="${test.weight}"/>}
                     </c:forEach>
                ]}
    </c:forEach>
        ,{id: 'itog', name: "Контрольный модуль", weight: <c:out value="${umk.itoqWeight}"/>,
            elements:[
                <c:forEach items="${umk.tests}" var="test">
                    <c:if test="${test.module eq null}">
                         <c:if test="${!countTest.first}"> , </c:if>
                        {id:"test"+<c:out value="${test.id}"/>, name:'<c:out value="${test.title}"/>',weight:<c:out value="${test.weight}"/>}
                    </c:if>
                </c:forEach>
            ]
        }
]}
var activityWeight = <c:out value="${umk.activityWeight}"/>;
var activityCount = <c:out value="${umk.activityCount}"/>;
var itogWeight = <c:out value="${umk.itoqWeight}"/>;
var globalMarks = 500;
var store = new dojo.data.ItemFileWriteStore({data: data})
var umkId = <c:out value="${umk.id}"/>;


    function activate(id){      
        id.childNodes.setAttribute("style", "margin: 0px; margin-top: 2px; color: black; font-size: 8pt;");
        id.setAttribute("style","text-align: center; height: 21px; width: 28px; border: #868686 1px solid; background: #FFFFBB url('../1images/module_redacting.png') no-repeat; cursor: pointer;");
    }
    function disactivate(id){
        id.childNodes.setAttribute("style", "margin: 0px; margin-top: 2px; color: #5D5D5D; font-size: 8pt;");
        id.setAttribute("style","text-align: center; height: 21px; width: 28px; border: silver 1px solid; background: InfoBackground; cursor: pointer;");
    }
</script>

 <div class=head_main_math>
     <p style="float: left; width: 400px;"><c:out value="${umk.name}" /></p>
        <div style="float: right;">
			<div class="drop_b">
				<button dojoType="dijit.form.Button" onclick="showModuleDialog()" title="Настройка модулей УМК"></button>
			</div>
		</div>
		<div class="otdelitel"></div>
	</div>
    <div class=tree>
	<div class=GrayTitle>
	<div dojoType="dijit.TitlePane" title="Учебник">	
	<div id="Book" style="visibilite:hidden;">
        <p><a href="main.jsp#mat"><<Вернуться к списку курсов</a></p>
		<ol>
            <c:forEach items="${umk.module}" var="module" >

			<li>
				<table>
					<tbody>
						<tr>
							<td class="v" classname="v">
                                <div onmouseover="activate(this);" onmouseout="disactivate(this);" class="vsplivayuwie_knopki"><p id="p<c:out value="${module.id}"/>" class="button_of_redacting_module" onclick="checkModule(<c:out value="${module.id}"/>)" title="Настройка элементов модуля"><c:out value="${module.weight}" />%</p></div>
                            </td>
							<td class="tit_tree" classname="tit_tree">
                                <c:if test="${!empty module.tests}">
                                     <c:choose>                                                
                                        <c:when test="${module.summa >100}">
                                                <p id="module<c:out value="${module.id}" />" style="color:red"  title="Общее количество баллов превышает максимально допустимое значение" >&nbsp <c:out value="${module.name}" /> &nbsp;</p>
                                        </c:when>
                                        <c:when test="${module.summa <100}">
                                                <p id="module<c:out value="${module.id}" />" style="color:green" title="Общее количество баллов меньше необходимого значения">&nbsp <c:out value="${module.name}" /> &nbsp;</p>
                                        </c:when>
                                        <c:otherwise>
                                                 <p id="module<c:out value="${module.id}" />">&nbsp <c:out value="${module.name}" /> &nbsp;</p>
                                        </c:otherwise>
                                    </c:choose>                                    
                               </c:if>
                               <c:if test="${empty module.tests}">
                                    <p id="module<c:out value="${module.id}" />">&nbsp <c:out value="${module.name}" /> &nbsp;</p>
                               </c:if>
							</td>

                            <td class="drop_b">							
                            </td>
                            
						</tr>
					</tbody>
				</table>
			</li>
			<div>
                <c:forEach items="${module.tasks}" var="task">
                    <li class="li_str" classname="li_str">
                       <a  class="tree_ots" href="exbook.jsp?id=<c:out value="${task.id}"/>"><img class="leaf_umk_image" src="../images/leaf.gif"><p class="test_name_for_redact"><c:out value="${task.name}"/></p><div class="otdelitel"></div></a>
                    </li>
                 </c:forEach>

                 <c:forEach items="${module.tests}" var="test">
                         <li class="li_str" classname="li_str">
                           <div class="tests_redact_div" onmouseout="hide_menu('<c:out value="${test.id}" />_menu')" onmouseover="show_menu('<c:out value="${test.id}" />_menu')">
                               <div id="<c:out value="${test.id}" />_menu" class="div_list_tasks"><p id="ptest<c:out value="${test.id}" />" style="margin:0px; margin-right: 3px;  color: #777777;"> <c:out value="${test.weight}" />% &nbsp;</p></div>
                               <a href ="test_redact.jsp?id=<c:out value="${test.id}" />&umk=<c:out value="${umk.id}"/>&type=time"><img src="../images/test5.gif" class="redact_test_image" title="Редактирование теста"></a><p class="test_name_for_redact"><a  class="tree_ots" href="test_redact.jsp?id=<c:out value="${test.id}" />&umk=<c:out value="${umk.id}"/>&type=time"><c:out value="${test.title}" /></a></p>
                               <div class="otdelitel"></div>
                           </div>
                         </li>                    
                 </c:forEach>
                
			</div>
         </c:forEach>

            <li>
            	<table>
					<tbody>
						<tr>
							<td class="v" classname="v">
                                <div onmouseover="activate(this);" onmouseout="disactivate(this);" class="vsplivayuwie_knopki"><p id="pitog" class="button_of_redacting_module" onclick="checkModule('itog')" title="Настройка элементов модуля"><c:out value="${umk.itoqWeight}"/>%</p></div>
                            </td>
							<td class="tit_tree" classname="tit_tree">
                                  <p id="moduleitog" />&nbsp Контрольный модуль &nbsp;</p>
                            </td>

                            <td class="drop_b">
                            </td>

						</tr>
					</tbody>
				</table>
            </li>
            <div>
                <c:forEach items="${umk.tests}" var="test">
                    <c:if test="${test.module eq null}">
                         <li class="li_str" classname="li_str">
                           <div class="tests_redact_div" onmouseout="hide_menu('<c:out value="${test.id}" />_menu')" onmouseover="show_menu('<c:out value="${test.id}" />_menu')">
                               <div id="<c:out value="${test.id}" />_menu" class="div_list_tasks"><p id="ptest<c:out value="${test.id}" />" style="margin:0px; margin-right: 3px;  color: #777777;"> <c:out value="${test.weight}" />% &nbsp;</p></div>
                               <a href ="test_redact.jsp?id=<c:out value="${test.id}" />&umk=<c:out value="${umk.id}"/>&type=itog"><img src="../images/test5.gif" class="redact_test_image" title="Редактирование теста"></a><p class="test_name_for_redact"><a  class="tree_ots" href="test_redact.jsp?id=<c:out value="${test.id}" />&umk=<c:out value="${umk.id}"/>&type=itog"><c:out value="${test.title}" /></a></p>
                               <div class="otdelitel"></div>
                           </div>
                         </li>
                      </c:if>
                 </c:forEach>

			</div>

         </ol>
	</div>
	

	</div>
	</div>

    	<div id="Titles">
        <div class="GrayTitle" classname="GrayTitle">
            	<div dojoType="dijit.TitlePane" title="Тесты">
                      <form id ="elements" name="elements" method="GET" dojotype="dijit.form.Form">
                       <div class="msp" classname="msp" id="test_list">
                          
                            <ol>
                            </ol>
                            <c:if test="${!empty umk.tests}">
                                <div align="center">
                                    <button  dojoType=dijit.form.Button onClick="sendtest()">Сохранить</button><button dojoType=dijit.form.Button type="reset">Отменить</button>
                                </div>
                            </c:if>
                            
					</div>
                    </form>
				</div>
			</div>

            </div>
    
	</div>    
</c:if>


<div dojoType="dijit.Dialog" id="moduleDialog" style="width: 500px;" title="Редактирование модуля" execute="checkPw(arguments);" class="main_dialog">

   <div>
       <p id="pmain" class="pmain"></p><div class="message_of_umk_points"><p id="message"></p></div>
   </div>
   <div id="moduleContent">
   </div>
   <div  id="errorSum" style="visibility:hidden"></div>
    <div class="umk_buttons_redact" align="center">
        <div style="width: 200px;">
            <div style="float: left;"><button dojoType=dijit.form.Button onClick="saveData(<c:out value="${umk.id}"/>)">Сохранить</button></div><div class="in" style="float: right;"><button dojoType=dijit.form.Button onclick="dijit.byId('moduleDialog').hide()">Отменить</button></div>
            <div class="otdelitel"></div>
        </div>
    </div>
    <div class="otdelitel"></div>
</div>

