<%-- 
    Document   : test_info
    Created on : 21.04.2009, 16:16:45    
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<jsp:useBean id="test" class="test.model.Test"/>

 <c:set target="${test.testI}"  property="id"  value="${param[\"id\"]}"/>
 <c:set var="testI" value="${test.testById}"/>

<c:if test="${testI.title == null || param[\"id\"] == null}">
     <div class=head_main_math>
     <p style="float: left;">Тест не найден.</p>
        <div style="float: right;">
		</div>
		<div class="otdelitel"></div>
	</div>

    <div class=tree>
        <br/>
        <p style="color: #777777;  font-size: 14pt;  font-weight: bold; margin: 0px; font-family: Arial; "> &nbsp&nbsp  Извините, но по Вашему запросу ничего не найдено</p>
        <p><a href="umkpassport.jsp?umk=<c:out value="${param[\"umk\"]}"/>"><<Вернуться к паспорту курса</a></p>
    </div>
 </c:if>

<c:if test="${testI.title != null && param[\"id\"] != null }">
<style>
    .GrayTitle .dijitTitlePaneTitle .dijitArrowNodeInner{
        display: none;
    }
    .GrayTitle .dijitTitlePaneTitle .dijitTitlePaneFocused .dijitArrowNodeInner{
        display: none;
    }
    .GrayTitle .dijitTitlePaneTitle .dijitArrowNode{
        margin-top: 3px;
    }
</style>




<script>
 
  dojo.byId("begindate").setAttribute("value", toDate("<c:out value="${testI.begin}" />"));
  dojo.byId("begintime").setAttribute("value","T"+ toTime("<c:out value="${testI.begin}" />"));
  dojo.byId("enddate").setAttribute("value", toDate("<c:out value="${testI.end}" />"));
  dojo.byId("endtime").setAttribute("value","T"+ toTime("<c:out value="${testI.end}" />"));
  dojo.require("dijit.form.Form");
  dojo.require("dojox.form.DropDownSelect");
  
</script>

 <div class=head_main_math>
        <p style="float: left;"><c:out value="${testI.umk}" /></p>
    <div style="float: right; vertical-align: middle;">
        <div class="back_to_cource">
            <a href="umkpassport.jsp?umk=<c:out value="${param[\"umk\"]}"/>"><<<Вернуться к курсу</a>
        </div>
    </div>
		<div style="clear: both;"></div>
</div>
    <form id="info" method="POST" name="info"  dojotype="dijit.form.Form">
        <table>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Название:*
                </td>
                <td>
                    <input type="hidden" name ="id" value="${testI.id}">
                    <input type="text" name="testname" size="50" value="<c:out value="${testI.title}"/>">
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Тип теста:*
                </td>
                <td>
                    <c:choose>
                        <c:when test="${testI.type eq 'regular'}">
                            <input dojoType="dijit.form.RadioButton" id="type1" name="type"
                               checked="checked" value="0" type="radio" />
                               <label for="type2"> Промежуточный </label>
                            <input dojotype="dijit.form.RadioButton" id="type2"  name="type"
                               value="1" type="radio" />
                               <label for="type2"> Пробный </label>
                        </c:when>
                         <c:when test="${testI.type eq 'trial'}">
                            <input dojoType="dijit.form.RadioButton" id="type1" name="type"
                                value="0" type="radio" />
                               <label for="type2"> Промежуточный </label>
                            <input dojotype="dijit.form.RadioButton" id="type2"  name="type"
                                checked="checked" value="1" type="radio" />
                               <label for="type2"> Пробный </label>
                        </c:when>
                         <c:otherwise>
                             <input dojoType="dijit.form.RadioButton" id="type1" name="type"
                               checked="checked" value="0" type="radio" />
                               <label for="type2"> Промежуточный </label>
                            <input dojotype="dijit.form.RadioButton" id="type2"  name="type"
                               value="1" type="radio" />
                               <label for="type2"> Пробный </label>
                        </c:otherwise>
                    </c:choose>
                    
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Вопросов*
                </td>
                <td>
                     <input dojoType="dijit.form.NumberSpinner"                        
                        style="width: 60px"
                        value="${testI.quantity}"
                        class="medium"
                        intermediatechanges="true" 
                        constraints="{min:0,max:90,places:0}"                   
                        name="quest"
                        id="quest"
                        >
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Всего прохождений*
                </td>
                <td>
                     <input dojoType="dijit.form.NumberSpinner"
                        style="width: 60px"
                        value="${testI.count}"
                        class="medium"
                        intermediatechanges="true"
                        constraints="{min:0,max:90,places:0}"
                        name="count"
                        id="count">
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Пробных прохождений
                </td>
                <td>
                    <input dojoType="dijit.form.NumberSpinner"
                        style="width: 60px"
                        value="${testI.trial}"
                        class="medium"
                        intermediatechanges="true"
                        constraints="{min:0,max:90,places:0}"
                        name="trial"
                        id="trial">
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Начало периода прохождения*
                </td>
                <td>
                    <div class="left_input"><input dojoType="dijit.form.DateTextBox" type="text" id="begindate" name="begindate" ></div>
                    <div class="right_input"><input id="begintime" type="text" name="begintime"
                        class="medium" 
                        dojoType="dijit.form.TimeTextBox"
                        constraints="{formatLength:'short'}"
                        required="true"                        
                        invalidMessage="That is an invalid value" /></div>
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Конец периода прохождения*
                </td>
                <td>
                   <div class="left_input"><input dojoType="dijit.form.DateTextBox" type="text" id="enddate" name="enddate" ></div>
                    <div class="right_input"><input id="endtime" type="text" name="endtime"
                        class="medium" 
                        dojoType="dijit.form.TimeTextBox"
                        constraints="{formatLength:'short'}"
                        required="true"
                        invalidMessage="That is an invalid value" /></div>
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Время прохождения*
                </td>
                <td>
                    <input dojoType="dijit.form.NumberSpinner"
                        style="width: 60px"
                        value="${testI.time}"
                        class="medium"
                        intermediatechanges="true" 
                        constraints="{min:0,max:90,places:0}"
                        name="timedo"
                        width="50"
                        id="timedo">
                </td>
            </tr>
            <tr><td><br></td></tr>
                        <tr>
                <td>
                    Способ оценивания*
                </td>
                <td>
                      <select name="estimation" dojoType="dojox.form.DropDownSelect">
                          <c:choose>
                              <c:when test="${testI.estimation eq 3}">
                                  <option value="2">по лучшему результату</option>
                                  <option selected value="3">по среднему результату</option>
                              </c:when>
                              <c:otherwise>
                                  <option selected value="2">по лучшему результату</option>
                                  <option value="3">по среднему результату</option>
                              </c:otherwise>
                          </c:choose>
                      </select>
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr>
                <td>
                    Комментарии
                </td>
                <td>
                    <textarea rows="3" cols="45" name="comment"><c:out value="${testI.comment}"/></textarea>
                </td>
            </tr>
            <tr><td><br></td></tr>
            <tr><td colspan="2"> &nbsp&nbsp&nbsp  * - поля, обязательные для заполнения </td></tr>
            <tr><td></td>
            <td>
                <button id="saveInfo" type="submit" dojoType="dijit.form.Button"  >Сохранить</button><button dojoType=dijit.form.Button type="reset">Отменить</button></td></tr>
        </table>
    </form>
    <div id="rsloaddiv"><p id="resultload"></p></div>
    <div class="GrayTitle">
    <div dojoType="dijit.TitlePane" title="Показать вопросы теста" open="false">
        <div id="questionList" class="questionList">
            <form id="questForm" name="questForm" method="post" dojotype="dijit.form.Form">
            <table cellpadding="0" cellspacing="0" class="questionList_t_border">
                <c:forEach items="${testI.questions}" var="question" begin="0" varStatus="count">
                     <c:choose>
                          <c:when test="${count.count % 2 == 0}">
                                <c:set var="rowStyle" scope="page" value="darck"/>
                          </c:when>
                          <c:otherwise>
                                <c:set var="rowStyle" scope="page" value="light"/>
                          </c:otherwise>
                     </c:choose>
                    <tr class="<c:out value="${rowStyle}"/>">
                        <td valign="middle" class="questionList_tr_td_r_b">
                            <p class="quest_num"><c:out value="${count.index}"/>.</p>
                        </td>
                        <td class="questionList_tr_td_r_b">
                            <c:out value="${question.text}"/>
                        </td>
                        <td class="questionList_tr_td_r_b">
                            <input dojoType="dijit.form.NumberSpinner"
                                style="width: 60px"
                                value="${question.weight}"
                                class="medium"
                                intermediatechanges="true"
                                constraints="{min:0,max:10,places:0}"
                                name="weight<c:out value="${question.id}"/>"
                                id="weight<c:out value="${question.id}"/>">
                    </td>
                    <td class="questioList_tr_td_b">
                         <c:if test="${question.conclusion}">
                            <input type="checkbox" name="itog<c:out value="${question.id}"/>" id ="itog<c:out value="${question.id}"/>" checked>
                         </c:if>
                         <c:if test="${!question.conclusion}">
                            <input type="checkbox" name="itog<c:out value="${question.id}"/>" id ="itog<c:out value="${question.id}"/>" >
                         </c:if>
                    </td>
                    </tr>
                </c:forEach>                    
                    <tr><td colspan="4" class="questionList_buttons"><button dojoType="dijit.form.Button" type="submit">Сохранить</button><button dojoType=dijit.form.Button type="reset">Отменить</button></td></tr>
            </table>
            </form>
        </div>
    </div>
    </div>
</c:if>