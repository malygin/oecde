<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<c:set var="test" value="${sessionScope[\"education\"]}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ЦОО Начало прохождения теста</title>
<meta http-equiv="Content-Type" content="text/htm; charset=utf-8">
<link href="../style/studentStyles.css" rel="stylesheet" type="text/css">
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico">
</head>
	<style type="text/css">
		.dojoDndAvatarHeader {
                    background: #blue;
		}
                .dojoDndAvatarItem {
                    background: #white;
                }
                .dojoDndAvatarCanDrop{
                    background: #red;
                }
		.tundra .dijitDialogCloseIcon {
			background:0;
			width:0px;
		}
                .dojoDndItemOver {background: #98FB98;border: 1px dotted gray; }
                .dojoDndItemBefore {border-left: 3px dotted gray; }
                .dojoDndItemAfter {border-right: 3px dotted gray; }

		.tundra .dojoDndSource{
		display:inline; float: left; padding-left: 30px; padding-right: 30px; padding-top: 3px;padding-bottom: 3px; border: double 3px #98D9F7; margin-left: 10px; margin-right: 30px;	width: 65px;
		}
		.tundra .dojoDndContainer{
		display:inline; float: left; padding-left: 30px; padding-right: 30px; padding-top: 3px;padding-bottom: 3px; border: double 3px #98D9F7; margin-left: 10px; margin-right: 30px;	width: 65px;
		}
		.tundra .dojoDndTarget{
		display:inline; float: left; padding-left: 30px; padding-right: 30px; padding-top: 3px;padding-bottom: 3px; border: double 3px #98D9F7; margin-left: 10px; margin-right: 30px;	width: 430px;
		}

 		#testBody_title {
			color:white;
		}
  		#loading_title {
			color:white;
		}


  </style>
    <script type="text/javascript" src="../dojoroot/dojo/dojo.js"
        djConfig="parseOnLoad: true"></script>
    <script type="text/javascript">
		dojo.require("dojo.parser");
		dojo.require("dijit.TitlePane");
		dojo.require("dijit.ProgressBar");
		dojo.require("dijit.form.Button");
		dojo.require("dojox.data.dom");
		dojo.require("dijit.form.CheckBox");
		dojo.require("dijit.form.Form");
		dojo.require("dojo.dnd.Source");
		dojo.require("dijit.Dialog");
		dojo.require("dijit.form.ValidationTextBox");
		dojo.require("dojo.data.ItemFileReadStore");
	</script>

 <c:set var="id" value="${param[\"id\"]}"/>
 <c:set target="${test}"  property="id"  value="${id}"/>
 <c:set var="testI" value="${test.test}"/>
 <body class="tundra">
	 <script src="../scripts/test.js">
		</script>

<div align="center">
<div class="main_page">
<div class="top_shadow">
<div class="bot_shadow">
<div class="l_t_angle">
<div class="r_t_angle">
<div class="r_b_angle">
<div class="l_b_angle">
<div class="inf_pole">
<%@include file="../WEB-INF/Student/jspf/l_menu.jspf"%>
<div class="right">
<%@include file="../WEB-INF/Student/jspf/t_menu.jspf"%>
<div class="right_inside">
<div>
<div class="inf">
<%@include file="../WEB-INF/Student/jspf/fio.jspf"%>

<div class="main_inf" id=test >

<div class=title>
    <p id=titleTextM>Начало прохождения теста </p>
</div>
    <c:if test="${testI eq null}">
       <div id=head>
        <p class=head id="headTextM">Ошибка</p>
        </div>
        <div id=questM class="before_test_info">
        <p id=questTextM>Тест не найден или недоступен</p>
        </div>
    </c:if>

    <c:if test="${testI ne null}">
<div id=head>
 <c:set var="testI" value="${testI.testI}"/>
 <c:set var="test" value="${testI}" scope="session"/>
<p class=head id="headTextM"><c:out value="${testI.title}"/>, Модуль <c:out value="${testI.module}"/></p>
</div>

<div id=questM class="before_test_info">
<p>Количество вопросов: <c:out value="${testI.quantity}"/></p>
<p>Отведённое время: <c:out value="${testI.time}"/></p>
<p>О тесте: <c:out value="${testI.comment}"/></p>
<p>Прохождений: <c:out value="${testI.attempts}"/> из <c:out value="${testI.count}"/></p>
</div>

<div  id="buttonLayoutM" align="center">
<button dojoType="dijit.form.Button" id="okM" >Начать</button>
<button dojoType="dijit.form.Button" id="cancelM" onclick="window.location = 'main.jsp#course/id=<c:out value="${testI.umk}"/>'">Выйти</button>
</div>

<div align="center"  id="testBody" dojoType="dijit.Dialog" open="false" title="Тестирование" style="width: 90%;" href="" refreshOnShow>

    <div id=head_test style="-moz-user-select: none;" onselectstart="return false">
				<!--    float: left; margin-right:5px; display: none;-->
            <div style='float:right; margin-left: 5px; display: none;' id=timeout ><b id=done></b><p align=right id=time>0:00</p></div>
			<div id=quest style="display:inline" dojoType="dijit.layout.ContentPane" parseOnLoad preventCache refreshOnShow loadingMessage="">

			</div>
			<div style="clear: both;"></div>
    </div>



    <div style="-moz-user-select: none;" onselectstart="return false" id=answ align="left" style="display:inline" height="300" >
        <form  name="answersForm" id="answersForm" dojoType="dijit.form.Form" >$$ \int {2\over x}\,dx = \ln(x)+C $$</form>
        <div class="otdelitel"></div>
    </div>


    <div id="buttonLayout" align="left" >
            <button dojoType="dijit.form.Button" onclick="data.doAnswer();" id="ok">Ответить</button>
            <button dojoType="dijit.form.Button" id="cancel"  onclick="data.getQuestion()">Пропустить</button>
    </div>
    <div align="centr"><div class="otdelitel"></div></div>
    <div id="bars" style="display:none">
            <div class=answbar >
            <div id="progressBar" align="center" >
               <div dojoType="dijit.ProgressBar" style="width:500px; height:15px" jsId="jsProgress" id="testProgress" report="progressReport"></div>
            </div></div>

            <div class=timebar>
            <div  id="timeBar" align="center" >
               <div dojoType="dijit.ProgressBar" style="width:500px; height:15px" jsId="jsTime" id="timeProgress" report="timeReport"></div>
            </div></div>
    </div>

    <div id=grad>
        <div id=hx></div>
    </div>
    <div align=right>
        <div class=in >
             <button dojoType="dijit.form.Button" id="escapeTest" onclick="data.finish()">Завершить тест</button>
        </div>
    </div>
    <div id=help style="font-size:8pt; color:grey; text-align:left" ></div>

</div>
</c:if>
<div id=polka></div>

<div id=grad>
<div id=hx></div>
</div>


</div>

</div>
<%@include file="../WEB-INF/Student/jspf/r_menu.jspf"%>
<div align="centr"><div class="otdelitel"></div></div>
</div>
</div>
</div>
<div align="centr"><div class="otdelitel"></div></div>
</div>
<%@include file="../WEB-INF/Student/jspf/b_menu.jspf"%>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>