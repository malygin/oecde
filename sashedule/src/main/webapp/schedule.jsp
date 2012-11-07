<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>    
<head> <title>Ipsilon Schedule 0.3 ALPHA</title></head>
<body>   
    <script type="text/javascript">
            function deleteValue(object){            
               object.value='';
            }
        </script>
 <style type="text/css">
   A {
    text-decoration: none;
    font-size:11px;
    color:#6495ed;

   }
   A:hover {
    text-decoration: none;
    font-size:11px;
    color:#6495ed;
   }
  </style>
<f:view>
    <rich:effect id="effct1" for="EditDayRich" event="render" type="Fade" />
   <h:outputLink value="../j_spring_security_logout" >logout</h:outputLink>
<rich:panel style="padding:0; vertical-align:top;" headerClass="outpanelHeader" >
    <f:facet name="header">
        <a4j:status id="status1">
                <f:facet name="start">
                    <h:outputText style="color:red" value="loading..."/>
                </f:facet>
                 <f:facet name="stop">
                    <h:outputText value="Ipsilon Schedule 0.9 ALPHA"/>
                </f:facet>
        </a4j:status>
    </f:facet>
   <h:panelGrid style="vertical-align:top;" width="100%" columns="2" >             
        <h:panelGroup id="EditLesson">
             <rich:panel bodyClass="inpanelBody" id="PanelEditLesson" header="Edit Lesson" rendered="#{EditLessonBean.render}">
                 <rich:panel>
                     <f:facet name="header">
                         <h:outputText value="User Info:" />
                     </f:facet>
                     <h:panelGrid>
                            <a4j:form id="lessonForm" ajaxSubmit="true" reRender="name">
                                <h:outputText value="time: #{EditLessonBean.currentTime}; room: #{EditLessonBean.currentRoom}"/><br/>
                                <h:outputText value="Select teacher please" />
                                <h:panelGrid columns="2" border="0" cellpadding="0" cellspacing="0">
                                        <h:inputText  onclick="#{rich:component('suggestion')}.callSuggestion(true)" value="#{FormEditLessonBean.currentTeacher.fio}" style="margin:0px;" id="statesinput" size="50" />
                                        <h:graphicImage value="/images/icons/arrow.png"
                                            onclick="#{rich:component('suggestion')}.callSuggestion(true)"
                                            alt="" />
                                          <h:graphicImage value="/images/icons/delete.gif"
                                                          onclick="deleteValue(#{rich:element('statesinput')});"
                                            alt="" />
                                          <h:panelGroup id="TeacherAttention">
                                              <h:outputText style="color:red;" value="This teacher has lesson in this time!!"  rendered="#{FormEditLessonBean.teacherAttentionRender}" />
                                          </h:panelGroup>
                                </h:panelGrid><br/>

                                <rich:suggestionbox  height="200" width="400"
                                                    usingSuggestObjects="true"
                                    suggestionAction="#{FormEditLessonBean.autocomplete}" var="teacher"
                                    for="statesinput" fetchValue="#{teacher.fio}" id="suggestion" tokens=",">
                                    <h:column>
                                        <h:outputText value="#{teacher.fio}" />
                                    </h:column>
                                    <a4j:support event="onselect"  ajaxSingle="true" reRender="dicsiplinesByTeacher, TeacherAttention" >
                                        <f:setPropertyActionListener  value="#{teacher}"   target="#{FormEditLessonBean.currentTeacher}" />
                                    </a4j:support>
                                </rich:suggestionbox>

                                <h:outputText value="Select discipline please" />
                                <h:panelGrid columns="2" border="0" cellpadding="0" cellspacing="0">
                                    <h:inputText value="#{FormEditLessonBean.currentDiscipline.name}" size="50" style="margin:0px;" id="statesinput2"  onclick="#{rich:component('suggestion2')}.callSuggestion(true)" />
                                    <h:graphicImage value="/images/icons/arrow.png" style="align:left;"
                                                    onclick="#{rich:component('suggestion2')}.callSuggestion(true)"
                                                    alt="" />
                                     <h:graphicImage value="/images/icons/delete.gif"
                                                          onclick="deleteValue(#{rich:element('statesinput2')});"
                                            alt="" />
                                </h:panelGrid>
                               
                                <rich:suggestionbox height="200" width="400"
                                                    usingSuggestObjects="true"     for="statesinput2"
                                                    suggestionAction="#{FormEditLessonBean.autocompleteDisciplines}" var="discipline"
                                                    fetchValue="#{discipline.name}" id="suggestion2" tokens=",">
                                    <h:column >
                                        <h:outputText  value="#{discipline.name}" />
                                    </h:column>
                                    
                                    <a4j:support event="onselect"  ajaxSingle="true" reRender="cssValue, cssResult" >
                                          <f:setPropertyActionListener  value="#{discipline}"   target="#{FormEditLessonBean.currentDiscipline}" />
                                    </a4j:support>
                                </rich:suggestionbox>
                                    
                         </a4j:form>

                    <h:panelGrid >
                        <rich:panel style="">
                    <h:form id="FormEditCss">
                        <h:panelGrid columns="2">
                          <h:dataTable  id="cssValue" var="css" value="#{FormEditLessonBean.css}">
                           <f:facet name="header"> <h:outputText value="css Value"/></f:facet>
                            <h:column>
                                <a4j:commandLink action="#{EditLessonBean.ShowEditLesson}" reRender="cssValue, cssResult, numberStudent"  >
                                     <font color=red><b> <h:outputText id="formEditCssValue"   escape="false" value="#{css.group.name} "/>
                                      <h:outputText escape="false" value="#{css.city.name}"/>
                                     </b></font><h:outputText escape="false" value="#{css.group.speciality.name}"/>_
                                                <h:outputText escape="false" value="#{css.group.speciality.levelTypeSpeciality}"/>
                                    <f:setPropertyActionListener  value="#{css}"   target="#{FormEditLessonBean.cssSelect}" />
                                </a4j:commandLink>
                            </h:column>

                        </h:dataTable>
                        <h:dataTable  id="cssResult" var="css" value="#{FormEditLessonBean.cssResult}">
                             <f:facet name="header"> <h:outputText value="css Target"/></f:facet>
                            <h:column>
                               <a4j:commandLink action="#{EditLessonBean.ShowEditLesson}" reRender="cssValue, cssResult, numberStudent"  >
                                       <font color=red><b> <h:outputText id="formEditCssValue"   escape="false" value="#{css.group.name} "/>
                                        <h:outputText escape="false" value="#{css.city.name}"/>
                                       </b></font><h:outputText escape="false" value="#{css.group.speciality.name}"/>
                                       <f:setPropertyActionListener  value="#{css}"   target="#{FormEditLessonBean.cssSelectResult}" />
                                </a4j:commandLink>
                            </h:column>
                        </h:dataTable>
                          
                       </h:panelGrid>
                     </h:form>
                    </rich:panel>
                    <h:form>
                         <h:selectOneMenu value="#{FormEditLessonBean.type}">
                                            <f:selectItems value="#{FormEditLessonBean.types}" />
                          </h:selectOneMenu> 
                       <a4j:commandButton value="Save Lesson" reRender="organizer, EditDay" action="#{FormEditLessonBean.saveLesson}" />
                    </h:form>
                   <h:panelGroup id="numberStudent">
                       Students: <h:outputText escape="false" value="#{FormEditLessonBean.countOfStudent}"/>
                   </h:panelGroup>
                    </h:panelGrid>
                 </h:panelGrid>
            </rich:panel>
         </rich:panel>
       </h:panelGroup>

        <rich:panel  bodyClass="inpanelBody"  header="Calendar">
            <h:form id="form">
                <rich:calendar mode="ajax"  value="#{CalendarBean.selectedDate}"
                    popup="false" showApplyButton="false"
                    cellWidth="50px" cellHeight="50px"
                    boundaryDatesMode="none" showWeeksBar="false"
                    dataModel="#{calendarDataModel}"  id="organizer"  showFooter="true">
                    <a4j:support event="onchanged" action="#{EditDayBean.ShowSelectDate}" ajaxSingle="true" reRender="EditDay, EditLesson" />
                    <a4j:outputPanel  layout="block" id="cell"   style="height: 100%;" styleClass="organizer-cell">
                                <h:outputText value="{day}" style="align:center"/><br>
                                <h:outputText style="color:red" value="{data.shortDescription.escapeHTML()}" />
                                <h:outputText value="{data.description.escapeHTML()}"/>
                    </a4j:outputPanel>
                </rich:calendar>
            </h:form>
        </rich:panel>
   </h:panelGrid>

   <h:panelGroup id="EditDay">
    <rich:panel  style=""  bodyClass="inpanelBody"  rendered="#{EditDayBean.render}" id="EditDayRich" >
        <f:facet name="header">
           <h:outputText value="Edit Day"/>
        </f:facet>
        <rich:dataGrid style="width:100%" value="#{EditDayBean.allLessons}" var="lesson" columns="#{EditDayBean.numberTimeInterval}" elements="#{EditDayBean.numberTimeInterval*EditDayBean.numberRoom}" width="600px">
            <rich:panel style="background-color:#{lesson.colorPick};" >
                <f:facet name="header">
                      <h:outputText value="#{lesson.shortDate} - #{lesson.shortDateEnd}"/> 
                </f:facet>
                    <h:form rendered="#{lesson.notUsed}">

                    <a4j:commandLink   action="#{EditLessonBean.ShowEditLesson}"  reRender="EditLesson" >
                         <h:graphicImage value="/images/icons/create_folder.gif"  style="border:0;"  alt="add" />
                        <f:setPropertyActionListener target="#{EditLessonBean.currentTime}" value="#{lesson.lessonDate}" />
                        <f:setPropertyActionListener target="#{EditLessonBean.lesson}" value="#{lesson}" />
                        <f:setPropertyActionListener target="#{FormEditLessonBean.lessonEdit}" value="#{null}" />
                        <f:setPropertyActionListener target="#{EditLessonBean.currentRoom}" value="#{lesson.room}" />
                        <f:setPropertyActionListener target="#{FormEditLessonBean.currentTeacher}" value="" />
                        <f:setPropertyActionListener target="#{FormEditLessonBean.currentDiscipline}" value="" />
                      
                        <f:setPropertyActionListener target="#{EditLessonBean.currentLessonId}" value="" />
                    </a4j:commandLink >
                    </h:form>
                     <a4j:form ajaxSubmit="true" reRender="EditLesson" rendered="#{not lesson.notUsed}" >
                         <a4j:commandLink ajaxSingle="true"   action="#{EditLessonBean.ShowEditLesson}" reRender="EditLesson"  >

                               <h:graphicImage value="/images/icons/edit.gif" style="border:0;"   alt="edit" />
                                <f:setPropertyActionListener target="#{FormEditLessonBean.lessonEdit}" value="#{lesson}" />
                               <f:setPropertyActionListener target="#{EditLessonBean.currentTime}" value="#{lesson.lessonDate}" />
                               <f:setPropertyActionListener target="#{EditLessonBean.currentRoom}" value="#{lesson.room}" />
                               <f:setPropertyActionListener target="#{EditLessonBean.lesson}" value="#{lesson}" />
            
                               <f:setPropertyActionListener target="#{FormEditLessonBean.currentTeacher}" value="#{lesson.teacher}" />
                               <f:setPropertyActionListener target="#{FormEditLessonBean.currentDiscipline}" value="#{lesson.discipline}" />
                               <f:setPropertyActionListener target="#{FormEditLessonBean.type}" value="#{lesson.lessonType}" />
                                           
                               <f:setPropertyActionListener target="#{EditLessonBean.currentLessonId}" value="#{lesson.id}" />
                           </a4j:commandLink><br/>
                            <a4j:commandLink   onclick="#{rich:component('confirmation')}.show();return false"  >
                                   <h:graphicImage value="/images/icons/delete.gif" style="border:0;"  alt="delete" />
                                <f:setPropertyActionListener target="#{EditLessonBean.currentLessonId}" value="#{lesson.id}" />
                            </a4j:commandLink>
                           <rich:modalPanel id="confirmation" width="250" height="150">
                               <f:facet name="header">Delete lesson!</f:facet>
                               <h:panelGrid>
                                  <h:panelGrid columns="2">
                                     <h:outputText value="Are you sure?"  />
                                  </h:panelGrid>
                                  <h:panelGroup>
                                     <rich:spacer height="20px" />
                                     <a4j:commandButton value="ok" reRender="organizer, EditDay" action="#{FormEditLessonBean.deleteLesson}">
                                           <f:setPropertyActionListener target="#{EditLessonBean.currentLessonId}" value="#{lesson.id}" />
                                     </a4j:commandButton>
                                     <a4j:commandButton value="Cancel"  onclick="#{rich:component('confirmation')}.hide(); return false"/>
                                  </h:panelGroup>
                               </h:panelGrid>
                            </rich:modalPanel>
                            <rich:toolTip>
                                  <h:outputText value="#{lesson.teacher.fio}"/><br/>
                                

                                  <h:outputText value="#{lesson.discipline.name}"/><br/>
                                  <h:outputText value="#{lesson.lessonType.toString()}"/>
                               <rich:dataList var="css" value="#{makeList.list[lesson.css]}" >
                               
                                  <h:outputText value="#{css.speciality.name} " />
                              </rich:dataList>
                            </rich:toolTip>
                     </a4j:form>

            </rich:panel>
        </rich:dataGrid>
    </rich:panel>
   </h:panelGroup>
</rich:panel>
</f:view>
</body>
</html>

