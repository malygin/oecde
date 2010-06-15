<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <div class="head_main_math">
      <p>Новости</p>
 </div>

 <%
    String id = (String)request.getParameter("id");
     if(id != null){
     if(id.equals("1")){
     %>
     <p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/">Вернуться к списку новостей</a></p>
    <div class=list style="margin-top:10px; //margin-left: 10px;">
             <ol>
                   <!-- <img src="<=newsI.getImg_big() %>">-->
              <li style=" background:  #F9FFFF; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-bottom: 10px; padding-left:20px; padding-right: 10px;">

                     <div align="centr"><div class="otdelitel"></div></div>
					<div class='ostup_sleva1' style="color:#71C5F4;float:left;font-size:11pt;font-weight:bolder;margin-bottom:0;margin-left:-30px;margin-right:0;margin-top:10px;">Новая система тестирования</div>
                    <div class='ostup_sleva1' style="display: block;color:#71C5F4;float:right;margin-bottom:0;margin-left:0;margin-right:0px;margin-top:10px;" class="news_name_date">28-октября-2009</div>
                    <div style="clear: both;"></div>
                    <p style="margin-top: 10px;">
                         Внимание! Начинает работу новая система тестирования! Для каждой специальности  тесты будут открываться последовательно - в определённый день (со 2 по 5 ноября).

       29 октября будут доступны тесты для специальности ГМУ. О возможности прохождения тестов Вы узнаете из "новостей" и  "ленты событий" на Вашей личной странице . Обращайте внимание на обновление.

       Для прохождения каждого  промежуточного теста (к модулю) вам доступно 3 попытки. Первое прохождение не идёт в зачёт и нигде не отображается. Из последующих двух выбирается лучшая и идёт в зачет. Для итоговых тестов доступно только 2 попытки: первая пробная, вторая  идёт в зачёт. На прохождение теста  даётся определённое количество времени.
     Результаты о попытках, идущих в зачёт, Вы, как и Ваш преподаватель, можете увидеть двумя способами:
1. Слева в меню обучение ссылка "Мои Результаты Тестов"/"Моя переэкзаменовка"
2. В списке изучаемых материалов результаты тестирования (ссылка "подробнее"). На этой страничке представлен весь список тестов, которые необходимо пройти, с результатами, если они есть.
    Кроме того,  данные по тестам можно посмотреть в разделе "моё обучение" на 4м и 5м шаге. Там представлены списки непройденных, либо пройденных плохо тестов. По некоторым дисциплинам возможно увелечение количества тестов (связано с преподавателями и возможной доработкой тестовых заданий под изучаемые материалы). Обратите на это ОСОБОЕ внимание.

      Тесты - это контроль вашей промежуточной успеваемости. Следует учитывать, что у каждого теста есть количество попыток прохождения. Успешное прохождение тестов - важный шаг к получению хорошей итоговой оценки.

     Итоговые тесты - это контроль вашей общей успеваемости за семестр и основной показатель, который используют преподаватели при выставлении итоговой оценки. Они будут доступны в конце семестра и начинать их прохождению следует хорошо подготовившись.

     Обратите особое внимание на то, что попытка будет использована, если вы ответили на все вопросы, закончилось время либо вы нажали на кнопку завершить тест. Будьте внимательны.
                    </p>
                    

                     <div align="centr"><div class="otdelitel"></div></div>
             </li>
              </ol>
       </div>
	<p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/>">Вернуться к списку новостей</a></p>

     <%
         }
         if(id.equals("2")){
     %>
     <p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/">Вернуться к списку новостей</a></p>
    <div class=list style="margin-top:10px; //margin-left: 10px;">
             <ol>
                   <!-- <img src="<=newsI.getImg_big() %>">-->
              <li style=" background:  #F9FFFF; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-bottom: 10px; padding-left:20px; padding-right: 10px;">

                     <div align="centr"><div class="otdelitel"></div></div>
					<div class='ostup_sleva1' style="color:#71C5F4;float:left;font-size:11pt;font-weight:bolder;margin-bottom:0;margin-left:-30px;margin-right:0;margin-top:10px;">
Доступна возможность загрузки контрольных работ.</div>
                    <div class='ostup_sleva1' style="display: block;color:#71C5F4;float:right;margin-bottom:0;margin-left:0;margin-right:0px;margin-top:10px;" class="news_name_date">20-октября-2009</div>
                    <div style="clear: both;"></div>
                    <p style="margin-top: 10px;">
                        В интерфейсе "Мои контрольные", что в левом меню, в получаете доступ к интерфейсу отправки контрольных работ. Здесь по всем необходимым дисциплинам вы можете отправить преподавателю свои контрольные работы, дождаться комменатариев через систему сообщений, и, если это необходимо, загрузить новые варианты контрольных. Кроме того, вы всегда можете скачать старые попытки, если вам это необходимо.

Работать с этим интерфейсом очень просто. Вы нажимаете на иконку синего письма с зеленой стрелкой и в появившемся окошке выбираете нужный файл.

Внимательно читайте "Подсказку" внизу интерфейса!

По всем вопросам и предложениям пишите в службу круглосуточной поддержки в левом меню или на форуме.
                    </p>


                     <div align="centr"><div class="otdelitel"></div></div>
             </li>
              </ol>
       </div>
	<p class='ostup_sleva1'><a style="float: right;" class="teh_a" style="TEXT-DECORATION: underline;" href="#news/>">Вернуться к списку новостей</a></p>

     <%
        }
     }else{
 %>
 <div class=list_news>
    <ol>
      <li style="background:  white; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-left:0px;">
            <div>
                <div style="margin: 0px; color: #71C5F4; margin-right: 10px; margin-top: 10px; float: right;">
                    28-октября-2009
                </div>
                <div class="otdelitel"></div>
                <div style="margin: 0px; margin-left: 10px; margin-top: 10px; float: left; font-size: 11pt; color: #71C5F4; font-weight: bolder;">
                    Новая система тестирования
                </div>
                <div class="otdelitel"></div>
            </div>
            <div style="margin: 0px; margin-bottom: 10px; margin-left: 20px; margin-right: 20px;">
            <p>
                Внимание! Начинает работу новая система тестирования! Для каждой специальности  тесты будут открываться последовательно - в определённый день (со 2 по 5 ноября).
            </p>
            <a style="margin-left: 10px;" class="more_news" title="Читать подробнее новость" href="#news/id=1">подробнее</a></div>
      </li>
      <li style="background:  #F9FFFF; border-bottom: solid 1px silver; border-top: solid 1px silver; border-left: solid 1px silver;border-right: solid 1px silver;margin-left: 10px; margin-right: 10px; padding-left:0px;">
            <div>
                <div style="margin: 0px; color: #71C5F4; margin-right: 10px; margin-top: 10px; float: right;">
                    20-октября-2009
                </div>
                <div class="otdelitel"></div>
                <div style="margin: 0px; margin-left: 10px; margin-top: 10px; float: left; font-size: 11pt; color: #71C5F4; font-weight: bolder;">
                    Доступна возможность загрузки контрольных работ.
                </div>

                <div class="otdelitel"></div>
            </div>
            <div style="margin: 0px; margin-bottom: 10px; margin-left: 20px; margin-right: 20px;">
            <p>
                Теперь вы можете непосредственно через образовательный портал отправить свою контрольную работу преподавателю, контролировать ход ее проверки и получать через систему сообщений комментарии к доработке контрольной работы!
            </p>
            <a style="margin-left: 10px;" class="more_news" title="Читать подробнее новость" href="#news/id=2">подробнее</a></div>

      </li>

    </ol>
 </div><%}%>