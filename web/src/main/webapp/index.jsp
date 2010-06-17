<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="org.springframework.security.BadCredentialsException" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
<title>ЦОО Вход в систему</title>
<meta http-equiv="Content-Type" content="text/htm; charset=utf-8">
			<link rel="stylesheet" href="style/universalStyles.css" type="text/css"/>
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico">
    <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-5800747-2");
pageTracker._trackPageview();
} catch(err) {}</script>
	</head>
    <script type="text/javascript" src="dojoroot/dojo/dojo.js"
        djConfig="parseOnLoad: true"></script>

	<body class="login_page">

		<div align="center" style="background: url('images/grad_top4.jpg') repeat-y top;">
<div style="height: 70px; width: 10px;"></div>
			<div>
				<div style="background: url('images/blue_fon.png') no-repeat; width: 801px; height: 261px;">
					<img style="float: left; margin-left: 60px; margin-top: 50px; margin-right: 50px;" src="images/ipsilon.png">
					<div style="padding-top: 50px; margin: 0px; text-align: left;">
						<table>
							<tr>
								<td colspan="2"><img src="images/Ipsilon-web.png"></td>
							</tr>
							<tr>
								<td colspan="2"><p style="margin: 0px; margin-top: 4px; margin-bottom: 10px; color: white; font-size: 15pt;">Система дистанционного образования</p></td>
							</tr>
						</table>
						<form name="UserForm" action="j_spring_security_check" method="POST">
							<table>
								<tr>
									<td class="login_text"><div style="float: left; width: 50px; margin-top: 2px;">Логин: </div><input class="login_input" name="j_username"><div class="otdelitel"></div></td>
								</tr>
								<tr>
									<td class="login_text"><div style="float: left; width: 50px; margin-top: 2px;">Пароль: </div><input class="login_input" name="j_password" type="password"><div class="otdelitel"></div></td>
								</tr>
								<c:if test="${param[\"error\"] eq \"1\"}">
								<tr>
									<td class="red_word"  colspan="2">Неправильный логин или пароль</td>
								</tr>
								</c:if>
								<tr>
									<td class="login_text"  colspan="2"><input id="ch" name="j_spring_security_remember_me" type="checkbox" class="grey_checkbox"><label for="ch">Запомнить меня</label></td>
								</tr>
								<tr>
									<td class="login_text"><button type="submit" onclick="dojo.fadeOut({node: document.body, duration:500,
                                                                            onEnd: function ff(){ document.body.style.display = 'none';}}).play();"  class="blue_button">Войти</button><button type="reset" class="blue_button">Отмена</button></form><form style="float: right;" action="signOn.do" method="POST"><input value="guest" name="pass" type="hidden"><input type="hidden" value="guest" name="login"><button type="submit" class="blue_button">Гостевой вход</button></form></td>
								</tr>
                                                                <%--<tr>
                                                                    <td>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="guest_akk_enter" href="signOn.do?login=guest&pass=guest">Гостевой вход</a>
                                                                    </td>
                                                                </tr>--%>
							</table>

					</div>
					<div class="otdelitel"></div>
				</div>
			</div>
			<div class="white_part">
<br>
<p class="grey_text_login2" align="center">ВНИМАНИЕ!</p>1
<%/*
for (String o:session.getValueNames()){
    out.println(o);
    out.println(session.getAttribute(o));
    out.println("<br>");
    }*/
out.println(((org.springframework.security.context.SecurityContextImpl)(session.getAttribute("SPRING_SECURITY_CONTEXT"))).getAuthentication().getAuthorities()[0]);
%>1
<p class="grey_text_login">
В 2008 году Саратовский Государственный университет  начал реализовывать систему дистанционного обучения, которая делает возможным получение образования независимо от места проживания и удаленности от  вуза. </p>
<p class="grey_text_login">
Работа над проектом велась в течение двух лет в рамках Инновационной образовательной программы Саратовского Госуниверситета и не прекращается и сейчас. Мы  постоянно развиваемся, чтобы сделать ваше обучение более комфортным и качественным.</p>
<p class="grey_text_login">
Теперь вы можете обучаться так, как удобно вам, без отрыва от места жительства и работы, сводя к минимуму посещение головного вуза.
Узнать больше можно на сайте  <a href="http://oec.sgu.ru" class="link_blue_login">http://oec.sgu.ru</a>. Вы можете написать нам по адресу
oecdo@mail.ru</p>


<p class="grey_text_login">Для работы с этой системой рекомендуется использовать бесплатно распространяемый браузер  <img src="images/firefox-3.0.png"> Mozilla Firefox.
Вы можите скачать его по этой <a href="http://www.mozilla-russia.org/" class="link_blue_login">ссылке</a></p>

			</div>
			<div class="bottom_coner">
				<div class="left_coner">
					<img src="images/circul.png">
				</div>
				<div class="bottom_center">
				</div>
				<div class="right_coner">
					<img src="images/circul.png">
				</div>
			</div>
		</div>



	</body>

</html>
