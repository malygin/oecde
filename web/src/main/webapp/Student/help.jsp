<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<link rel="stylesheet" href="../dojoroot/dojox/image/resources/image.css" type="text/css">


<script type="text/javascript">
  dojo.require("dojox.image.Lightbox");
  dojo.require("dojo.parser");
</script>

<script type="text/javascript">
	dojo.addOnLoad(function(){
		if (dojo.byId('all_help_text').style.height = "0") {console.debug(176565)} else {console.debug(7777777)}
	});
</script>

<script type="text/javascript">

function show(id1){
  /*  for (i=1;i<3;i++){
          myWidget = dijit.byId("TP"+i);
          console.log(myWidget);
          if (myWidget.open){
           console.debug(myWidget.open)
           // myWidget.toggle();
            }

      }*/
   /* dojo.query("*[id^='nav_for_help_']").forEach(function(pp){pp.style.display = "none";});*/

    dojo.query("*[id^='TP']").forEach(function(ppp){
        myWidget1 = dijit.byId(ppp.id);
        //console.log(myWidget1);
        if (myWidget1.open){
           //console.debug(myWidget1.open)
           //myWidget1.toggle();


       }
    });
    //dojo.byId('hr_line').style.display = "block";
    dojo.query("*[id^='help_text_']").forEach(function(pp){pp.style.display = 'none'});


    if (dojo.byId(id1).style.display != "block") {
    dojo.byId(id1).style.display = "block";
    //dojo.byId('nav_for_help').style.display = "block";
    }

    //dojo.byId(cl).style.display = "block !important";
   // console.debug(id1);
};


function show_smoll(id1,id2){    //id1 == help_menu_text_2_1_1
    var index=id1.substring(9);
   /* console.debug(id1,id2)
    dojo.query("*[id^='nav_for_help_']").forEach(function(pp){
      //console.debug(pp)
      if (id1 != id2){
            pp.style.display = "inline";
       } else {
            pp.style.display = "none";
    }
    });
*/

    dojo.byId(id2).style.display = "none";
    dojo.query("*[id^='help_menu_text"+index+"_']").forEach(function(pp){
        pp.style.display = 'none';
       // console.debug(pp.id);
    });


    if (dojo.byId(id1).style.display != "block") {
    dojo.byId(id1).style.display = "block";
    //dojo.byId('nav_for_help').style.display = "block";
    }
};
</script>

<style type="text/css">
       		.menu2 {
                margin: 0px;
                padding: 0;
                list-style: none;
            }
            .menu2 p {
                padding: 10px;
                margin: 0 2px;
                float: left;
                position: relative;
                text-align: center;
            }
            .menu2 a {
                padding: 14px 10px;
                display: block;
                color: #000000;
                width: 144px;
                text-decoration: none;
                font-weight: bold;
            }
            .menu2 div {
                width: 90px;
                height: 90px;
                position: absolute;
                top: -85px;
                left: 5px;
                z-index: 3333;
            }

               .menu2 img {
                z-index: 3333;

            }



		   .dijitTitlePaneContentOuter{
			margin-top: 5px;
			margin-right: 5px;
		   }

        </style>


<div class="help_pane">

    <div dojoType="dijit.TitlePane" id="TP1" class="main_title_pane" title="Часто задаваемые вопросы" open="true">
           <div onClick="show('help_text_1_1')"><img src='../images/help_images/hh2.png'><p>Что такое куки? </p></div>
           <div onClick="show('help_text_1_2')"><img src='../images/help_images/hh2.png'><p>Как очистить кэш?</p></div>
           <div onClick="show('help_text_1_3')"><img src='../images/help_images/hh2.png'><p>Как связаться с деканатом? </p></div>
           <div onClick="show('help_text_1_4')"><img src='../images/help_images/hh2.png'><p>Что такое видеоконференция?</p></div>
           <div onClick="show('help_text_1_5')"><img src='../images/help_images/hh2.png'><p>Как загрузить аватарку?</p></div>
           <!--<div onClick="show('help_text_1_5')"><img src='../images/help_images/hh2.png'><p>Что делать, если у меня повис компьютер/портал?</p></div>-->
       </div>

     <div dojoType="dijit.TitlePane" id="TP2" class="main_title_pane" title="Обзор справки" open="true">
           <div onClick="show('help_text_2_1')"><img src='../images/help_images/hh2.png'><p>Контроль процесса обучения ("Моё обучение")</p></div>
           <div onClick="show('help_text_2_2')"><img src='../images/help_images/hh2.png'><p>Работа с учебно-методическими материалами</p></div>
           <div onClick="show('help_text_2_3')"><img src='../images/help_images/hh2.png'><p>Тесты</p></div>
           <div onClick="show('help_text_2_4')"><img src='../images/help_images/hh2.png'><p>Общение</p></div>
           <!--<div onClick="show('help_text_2_5')"><img src='../images/help_images/hh2.png'><p>Контрольные работы</p></div>
           <div onClick="show('help_text_2_6')"><img src='../images/help_images/hh2.png'><p>Мои оценки</p></div>-->
           <div onClick="show('help_text_2_7')"><img src='../images/help_images/hh2.png'><p>Библиотека курсов</p></div>
           <div onClick="show('help_text_2_8')"><img src='../images/help_images/hh2.png'><p>Лента событий</p></div>
       </div>

</div>

<div class="all_help_text">

 <div id="help_text_1_1" class="border" style="display: none;">
    <h1><img src='../images/help_images/hh2.png'>Что такое куки?</h1>
    <div class="ht"><p>Куки (слово не склоняется; от англ. cookie — печенье) — небольшой фрагмент данных, созданный веб-сервером и хранимый на компьютере пользователя в виде файла, который веб-клиент (обычно веб-браузер) каждый раз пересылает веб-серверу в HTTP-запросе при попытке открыть страницу соответствующего сайта.</p><p>Для удобства пользователей на портале введена система хранения информации о заходах студента на портала внутри самого браузера, это позволяет заходить на портал без постоянного набора логина и пароля. Это удобно, если с вашего компьютера на портал заходите только вы.</p><p>Внимание! Если вы заходите на портал с чужого компьютера или из компютерных классов обязательно завершайте сеанс работы на портале нажатием ссылки "Выход".</p></div>
 </div>

<div id="help_text_1_2" class="border"   style="display: none;">
    <h1><img src='../images/help_images/hh2.png'>Как очистить кэш?</h1>
    <div class="ht"><p>Если у вас внезапно перестали отображаться какие-либо учебные материалы, которые раньше отображались или не открываются тесты попробуйте выполнить следующие действия:</p><p>1. Несколько раз обновить страницу — нажав клавишу F5 или клавишу обновить в браузере Mozilla Firefox.</p><p>2. Если не помог пункт 1, то необходимо очистить кэш браузера Mozilla Firefox:</p><p>1) На верхней панели браузера найти кнопку 'Инструменты' — нажать.</p><p>2) В появившемся меню выбрать 'Настройки' — нажать.</p><p>3) В открывшемся окне в верхней строке нажать кнопку 'Дополнительно'.</p><p>4) Из нескольких, появившихся чуть ниже закладок, выбрать 'Сеть' и нажать кнопку 'Очистить сейчас'.</p><p>5) Нажать 'ОК' внизу окна.</p><p>6) Попробовать открыть изучаемые материалы или тесты снова.</p><p>Кэш браузера — часть памяти, выделенной для хранения страниц, чтобы не поддержавать постоянное соединение с сетью Интернет, а сохранять наиболее часто используемые страницы локально на вашем компьютере. Постепенно происходит заполнение всего объема выделенной кэш-памяти, которую и необходимо очищать, для корректного отображения Интернет страниц.</p><p>3. Иногда возникает ситуация, что вы попадаете на личную страницу и видите null null null. Необходимо нажать кнопку 'Выход' в верхней части страницы, перезапустить браузер Mozilla Firefox путем закрытия и последующего открытия.</p><p>Это связано со множественными входами и выходами под разными логинами. Такая ситуация может проявиться, если несколько студентов работают на одном компьютере. В этом случае рекомендуется каждый сеанс работы на портале завершать нажатием ссылки "Выход" вверху справа.</p></div>
 </div>

<div id="help_text_1_3" class="border"   style="display: none;">
    <h1><img src='../images/help_images/hh2.png'>Как связаться с деканатом?</h1>
    <div class="ht"><p>Оперативная обратная связь – преимущество нашей системы перед другими. Вы всегда сможете посмотреть их в меню «Информация»/«контакты». Кроме того, задать интересующий вас вопрос можно на форуме.</p></div>
 </div>

<div id="help_text_1_4" class="border"   style="display: none;">
    <h1><img src='../images/help_images/hh2.png'>Что такое видеоконференция?</h1>
	<div class="ht"><p>Видеоконференции используются в системе дистанционного образования специально для общения между студентами и преподавателями. Что удобно - нет необходимости ни студентам приезжать в Саратов, ни преподавателям ездить по городам.</p><p>В правой части окна в меню "Общение" есть раздел "Видеоконференции". Выбрав этот раздел студент далее может выбрать на какую конференцию ему надо попасть и присоединиться к одной из уже запущенных конференций.</p> <div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic0_1.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic0_1_s.jpg"></a></div></div>
</div>
<div id="help_text_1_5" class="border"   style="display: none;">
    <h1><img src='../images/help_images/hh2.png'>Как загрузить аватарку?</h1>
	<div class="ht"><p>Аватара - небольшое изображение, которое вы используете на портале для визуальной идентификации вас студентами
                и преподавателями. Загрузить ее очень просто под картинкой на главной странице есть кнопка "Загрузить фото". Нажав на нее
                вы увидете форму для подгрузки изображения. Изображением не должно быть слишком большим (не более 5 мб) и рекомендуется, чтобы
                на нем было ваше изображение.

            <div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1">
                <a href="../images/help_images/help_pic15.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()">
                    <img src="../images/help_images/help_pic15_s.jpg"></a></div></div>
</div>

<div id="help_text_1_5" class="border"   style="display: none;">
    <div class="ht">35</div>
 </div>

<div id="help_text_2_1" class="border" style="display: none;">

    <p class="name_theme">Выбрана тема:</p><p class="select_item">Контроль процесса обучения ("Моё обучение")</p>

	<div id="nav_for_help_1" class="help_narrows" style="display: none;">
        <img src='../images/help_images/b.png'>
        <img src='../images/help_images/f.png'>
    </div>

    <div id="help_menu_2_1" class="little_menu">
        <p onclick="show_smoll('help_menu_text_2_1_1','help_menu_2_1')">Учебный план на семестр</p>
        <p onclick="show_smoll('help_menu_text_2_1_2','help_menu_2_1')">Моё расписание на неделю</p>
        <p onclick="show_smoll('help_menu_text_2_1_3','help_menu_2_1')">Мои изучаемые материалы</p>
        <p onclick="show_smoll('help_menu_text_2_1_4','help_menu_2_1')">Мои результаты тестов</p>
        <p onclick="show_smoll('help_menu_text_2_1_5','help_menu_2_1')">Моя переэкзаменовка</p>
        <p onclick="show_smoll('help_menu_text_2_1_6','help_menu_2_1')">Мои контрольные</p>
		<p onclick="show_smoll('help_menu_text_2_1_7','help_menu_2_1')">Мои оценки</p>
        <p onclick="show_smoll('help_menu_text_2_1_8','help_menu_2_1')">Курсовые работы</p>
        <p onclick="show_smoll('help_menu_text_2_1_9','help_menu_2_1')">События</p>
        <p onclick="show_smoll('help_menu_text_2_1_10','help_menu_2_1')">Результаты контрольных</p>
    </div>


    <div id="help_menu_text_2_1_1"   style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>Учебный план на семестр</h1>
		<p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><p>В "Учебный план на семестр" можно просмотреть дисциплины обучения за летний или зимний семестр(переход от семестра к семестру легко осуществляется посредством нажатия на ссылку в верхней части раздела).</p><p> По информации, размещённой в этом разделе, можно определить, какие вам предстоит изучить предметы в данном учебном году.</p><p>В первой части таблицы записаны дисциплины, которые надо сдать в зимнем(летнем) семестре, а в оставшейся части таблицы - которе надо сдать в летнем(зимнем) семестре.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic1.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic1_s.jpg"></a></div><p>Также в первой части таблицы записано, сколько контрольных работ нужно сдать преподавателю в течение данного учебного семестра. И необходимо ли сдавать курсовую работу.</p><p>В последней колонке таблицы записано, каким образом будет осуществляться контроль - в виде зачёта или в виде экзамена.</p></div>
    </div>

    <div id="help_menu_text_2_1_2"   style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>Моё расписание на неделю</h1>
		<p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><p>В данном разделе можно скачать расписание знаятий каждой группы в удобном для чтения формате Excel путём нажатия на название группы.</p><p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic2.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic2_s.jpg"></a></div></div>
	</div>

    <div id="help_menu_text_2_1_3"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Мои изучаемые материалы</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p>В этом разделе показаны все изучаемые студентом дисциплины с указанием имени преподавателя. К материалам электронного учебника можно легко перейти, нажав на название УМК.</p><p> С этой страницы можно также перейти к тестам по каждой из изучаемых дисциплин и посмотреть на успешность их прохождения.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic3.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic3_s.jpg"></a></div><p>Синей полосой с указанием процентов показано, насколько успешно студент справляется с заданиями, приложенными к УМК и насколько активно он ведёт себя на консультациях.</p></div>
    </div>

    <div id="help_menu_text_2_1_4"   style="display:none;">
       <h1><img src='../images/help_images/hh2.png'>Мои результаты тестов</h1>
	   <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	   <div class="ht"><p>В таблице есть список всем УМК, изцчаемых студентом, и показано, как он проходит тесты по ним.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic4.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic4_s.jpg"></a></div><p>В таблице есть графы количества попыток, количество правильных ответов, резутьтат по каждому тесту в баллах и способ оценивания.</p></div>
	</div>

    <div id="help_menu_text_2_1_5"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Моя переэкзаменовка</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p>На этой сранице показано, какие тесты из незачтённых ранее студент перепроходил и на сколько удачно.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic5.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic5_s.jpg"></a></div></div>
	</div>

    <div id="help_menu_text_2_1_6"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Мои контрольные</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p><b>Внимание! Начиная с этого семестра все операции с контрольными будут производится с помощью образовательного портала, отправка, проверка и высталение оценок!</b></p><p>В этой вкладке показана информация по результатам контрольных работ - предмет, имя преподавателя, название контрольной, количество сданных версий каждой контрольной работы, оценка за конткольную.</p><p>В последнем столбце есть изображение конверта - при нажатии на него открывается окно отправки контрольной работы. И что удобно - в строке адреса автоматическти выбирается имя того предодавателя, который преподаёт данную дисциплину!</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic6.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic6_s.jpg"></a></div><p>Зайдя в меню информация, на главной странице, выберете вкладку «по контрольным работам». Здесь содержаться общие правила по оформлению и отсылке контрольных работ. Помимо выложенных здесь правил, преподаватель может сообщить вам дополнительные требования на консультации, или включив их в установочный модуль.Результаты контрольных работ смотрите в пункте «Мои оценки».</p></div>
	 </div>

	 <div id="help_menu_text_2_1_7"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Мои оценки</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p>В этой вкладке показана информация по общей успеваемости студента - баллы и оценки по тестам, контрольным,самостоятельным, активность на консультациях  и итоговые оценки по каждой из дисциплин.</p><p> Также показано, каким образом осуществляется контроль знаний - в виде зачёта или же в виде экзамена.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic7.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic7_s.jpg"></a></div></div>
	</div>

	 <div id="help_menu_text_2_1_8"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Курсовые работы</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p>Здесь можно скачать темы курсовых работ с приложенными к ним списками литературы.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic8.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic8_s.jpg"></a></div></div>
	 </div>

	 <div id="help_menu_text_2_1_9"   style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>События</h1>
		<p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><p>Под окном личной информации о студенте расположена лента новостей. Она служит для оповещения студента о последних событиях на портале, таких как: обновления УМК, добавления тестов, обновления учебного плана, выставление оценок, усовершенствования портала и т.д.</p><p>Благодаря прочтению сообщений из данной системы уведомления вы всегда будете в курсе изменений, произошедших на портале.</p><p>Список выводимых событий можно регулировать - достаточно выбрать нужные элементы из списка фильтра!</p><p>Лента вмещает в себя ограниченное количество сообщений. Устаревшее сообщение вытесняется более свежим. История сообщений хранится в папке события.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic9.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic9_s.jpg"></a></div></div>
	 </div>

	 <div id="help_menu_text_2_1_10"   style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Результаты контрольных</h1>
	  <p onclick="show_smoll('help_menu_2_1','help_menu_2_1')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
	  <div class="ht"><p>В этой вкладке показана информация по результатам контрольных работ всей группы.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic10.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic10_s.jpg"></a></div></div>
	 </div>

 </div>

<div id="help_text_2_2" style="display: none;" class="border" >
       <p class="name_theme">Выбрана тема:<p class="select_item">Работа с учебно-методическими материалами</p></p>

   <!--<p class="name_theme">Выбрана тема: <p onclick="show_smoll('help_menu_2_2','help_menu_2_2')" class="select_item">Работа с учебно-методическими материалами</p></p>-->
	   <div id="nav_for_help_2" class="help_narrows" style="display: none;">
	        <img src='../images/help_images/b.jpg'>
	        <img src='../images/help_images/f.jpg'>
	   </div>

   <div id="help_menu_2_2" class="little_menu">
        <p onclick="show_smoll('help_menu_text_2_2_1','help_menu_2_2')"><img src='../images/help_images/help_mark.png'> О структуре УМК</p>
        <p onclick="show_smoll('help_menu_text_2_2_2','help_menu_2_2')"><img src='../images/help_images/help_mark.png'> Баллы за УМК</p>
        <p onclick="show_smoll('help_menu_text_2_2_3','help_menu_2_2')"><img src='../images/help_images/help_mark.png'> Тесты к УМК</p>
    </div>

    <div id="help_menu_text_2_2_1" style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>О структуре УМК</h1>
		<p onclick="show_smoll('help_menu_2_2','help_menu_2_2')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><p>В колонке «Обучение» зайдите в пункт в «Изучаемые материалы». Здесь представлены курсы на весь семестр. Курс состоит из учебника и тестов.</p><p>Особым компонентом системы является Учебник. Учебник состоит из нескольких частей – модулей. Обычно из шести модулей: установочного, четырех учебных.</p><p>Установочный модуль выполняет задачу ознакомления студента с планом на семестр, а так же содержит в себе дополнительную информацию об УМК, дополнительные материалы (контрольные и лабораторные работы). В него могут входить:<br>1.Описание дисциплины<br>2.Требования<br>3.Система оценивания<br>4. Задачи<br>5. Умения<br>6. Сведения об авторах<br>7. Обращение авторов<br>8. Методические рекомендации<br>9. Календарный план<br>10. Самостоятельная работа<br>11. Вопросы к экзамену (зачету)<br>12. Комментарии авторов к курсу<br>13. Перечень обязательной и дополнительной литературы<br>14. Глоссарий<br>15. Контрольные и лабораторные работы на семестр</p><p>Учебный модуль состоит из занятий (обычно двух), теста и дополнительных материалов, относящихся непосредственно к данному модулю.  Нажав на нужный теоретический раздел модуля, Вы переходите на соответствующую страницу Учебника. Под каждым курсом вы увидите полосу – это показатель прогресса в изучении курса, за основу здесь берутся результаты ваших тестов.</p><p>Каждый курс включает в себя учебные материалы для изучения, а также подразумевает прохождение промежуточных и контрольных тестов.</p></div>
    </div>

    <div id="help_menu_text_2_2_2" style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Баллы за УМК</h1>
	  <p onclick="show_smoll('help_menu_2_2','help_menu_2_2')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
      <div class="ht"><p><b>Критерии выставления оценок.</b></p><p>Для того, чтобы получить аттестационную оценку за семестр вам необходимо: <br>1.сдать все промежуточные тесты, итоговый тест. Стандартный процент правильности прохождения должен быть не меньше 60 %. Однако у некоторых преподавателей требования по зачету тестов больше, информацию об этом вы получите непосредственно от них и лучше уточнить это на первой же консультации.<br>2.получить зачет по обязательным контрольным и самостоятельным работам.</p><p><b>Внимание, работы могут не входить в план и назначаться преподавателем для отдельного студента, или для группы в целом. При этом контрольная остается контрольной, со всеми вытекающими.</b></p><p>За тесты и контрольные вы получаете оценочные балы.</p><p>Итоговая оценка выводится в соответствии с этими балами. Чем больше балов, тем выше оценка. Кроме выполнения обязательных работ, вы можете заработать балы выполнив дополнительную самостоятельную работу, назначенную вам преподавателем.Для получения хорошего бала важна активность в работе с учебником. Специальная программа отслеживает интенсивность вашей работы с материалами, сообщая данные преподавателю.Оценивается присутствие и активность  на видеоконференциях.  Преподаватель будет учитывать вашу активность, что выразится в дополнительных балах, которые могут ощутимо улучшить ваш общий результат.</p><p>Результаты прохождения тестов, контрольных и самостоятельных работ, а также ваша активность на консультациях и во время работы на портале учитываются и влияют на выведение итоговой оценки. Все свои результаты вы можете узнать, зайдя в раздел «оценки», в колонке «обучение». Рекомендуем вам проводить периодический мониторинг собственной успеваемости в разделе "Мое обучение", чтобы не забыть о возможной несданной работе, что может повлечь серьезные осложнения.</p></div>
    </div>

    <div id="help_menu_text_2_2_3" style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Тесты к УМК</h1>
	  <p onclick="show_smoll('help_menu_2_2','help_menu_2_2')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
      <div class="ht"><p>После того, как вы достаточно изучили курс за отведенный отрезок времени, вам предстоит прохождение тестов.</p><p>Тесты находятся внутри курсов, являясь вторым компонентом. Прохождение тестов – обязательный и очень важный этап обучения. Готовьтесь тщательно  – количество попыток на прохождение теста ограничено, обратите на это внимание.</p></div>
    </div>

 </div>


 <div id="help_text_2_3" style="display: none;" class="border" >
     <h1><img src='../images/help_images/hh2.png'>Тесты</h1>
	 <div class="ht"><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic11.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic11_s.jpg"></a><a href="../images/help_images/help_pic12.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic12_s.jpg"></a></div><p><b>Количество попыток прохождения теста ограничено!</b></p><p> Чтобы запустить тест, зайдите в пункт «изучаемые материалы» и в нужном вам курсе нажмите «перейти к тестам», выберите здесь нужный тест и нажмите кнопку начать.</p><p>Когда тест запущен одновременно начинается отсчет времени (оно ограничено). По ходу прохождения теста вы даете ответы на вопросы, выбирая один из предоставленных вариантов и кликая по нему. Вопрос можно пропустить, отложив ответ на него на конец тестирования. Когда вы дали ответы на все вопросы, включая те, которые пропустили, нажмите на кнопку закончить тест.</p><p><b>Как оценивают</b></p><p>Тест оценивается по стобальной шкале, каждый правильный ответ дает свой процент от ста балов, в зависимости от количества вопросов: 10 вопросов – десят балов за один ответ. Двадцать – 5. И т. д.  Из всех попыток выбирается лучший результат, он и идет в зачет.</p><p>Результаты тестов вы можете просмотреть, нажав на кнопку «Мои результаты тестов» в колонке обучение.</p></div>
 </div>

 <div id="help_text_2_4" style="display: none;" class="border" >
    <p class="name_theme">Выбрана тема: <p class="select_item">Общение</p>

	<div id="nav_for_help_4" class="help_narrows" style="display: none;">
        <img src='../images/help_images/b.jpg'>
        <img src='../images/help_images/f.jpg'>
    </div>

	<div id="help_menu_2_4" class="little_menu">
        <p onclick="show_smoll('help_menu_text_2_4_1','help_menu_2_4')"><img src='../images/help_images/help_mark.png'>Сообщения</p>
        <p onclick="show_smoll('help_menu_text_2_4_2','help_menu_2_4')"><img src='../images/help_images/help_mark.png'>Форум</p>
        <!--<p onclick="show_smoll('help_menu_text_2_4_3','help_menu_2_4')"><img src='../images/help_images/help_mark.png'>Чат</p>-->
    </div>

    <div id="help_menu_text_2_4_1" style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>Сообщения</h1>
		<p onclick="show_smoll('help_menu_2_4','help_menu_2_4')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic13.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic13_s.jpg"></a><!--a href="../images/help_images/help_pic14.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic14_s.jpg"></a>--></div>	<p>На портале дистанционного образования работает новая система обмена сообщений между студентами, преподавателями, администраторами.</p><p>Вы можете отправить не только письмо, но и прикрепить к нему любое число адресатов из список ваших одногруппников, ваших преподавателей и администраторов.</p><p>Если вам пришло сообщение, то ответить на него также можно используя быстрый ответ.</p><p>Также вы имеете возможность архивировать сообщения (они отстанутся доступными в разделе "Архив"), также можно удалять сообщения безвозвратно.</p></div>
	</div>

    <div id="help_menu_text_2_4_2" style="display:none;">
		<h1><img src='../images/help_images/hh2.png'>Форум</h1>
		<p onclick="show_smoll('help_menu_2_4','help_menu_2_4')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
		<div class="ht"><p>Теперь вы можете работать с форумом в двух режимах.</p><p><b>Просто "Форум"</b></p>Выйдя сюда, вы сможете задать вопросы на интересующие вас темы, поучаствовать в обсуждении, либо просто посмотреть, какие вопросы задавали другие студенты.</p><p>1.     Правила форума<br> У форума есть свои правила. Ознакомиться с ними вы можете, на нашем информационном сайте oec.sgu.ru, перейдя на него по гиперссылке.</p><div style="text-align: center;margin: 10px;" id="for_pic_bkgr_1"><a href="../images/help_images/help_pic14.jpg" dojoType="dojox.image.Lightbox" title="" onfocus="this.blur()"><img src="../images/help_images/help_pic14_s.jpg"></a></div><p>2.     Как им пользоваться<br>Когда вы заходите на форум вы можете заметить, что он достаточно четко структурирован по темам обсуждения и по курсам. Будьте добры, для оптимизации общения, придерживаться заданной администрацией структуры. </p><p><b>"Форум моей группы"</b></p><p>Он предназначен для общения внутри группы, здесь вы можете публично общаться между собой.</p></div>
    </div>

	<div id="help_menu_text_2_4_3" style="display:none;">
      <h1><img src='../images/help_images/hh2.png'>Чат</h1>
	  <p onclick="show_smoll('help_menu_2_4','help_menu_2_4')" class="select_item_1"><!--<img style="position: relative; top: 1px;" src="../images/return_ar.png">-->Вернуться к выбору подтемы</p>
      <div class="ht"> 1.     Правила чата<br>2.     Как пользоваться + интерфейс</div>
    </div>


 </div>

	 <div id="help_text_2_5" style="display: none;" class="border" >
	    <h1><img src='../images/help_images/hh2.png'>Контрольные работы</h1>
	    <div class="ht"><p>Зайдя в меню информация, на главной странице, выберете вкладку «по контрольным работам». Здесь содержаться общие правила по оформлению и отсылке контрольных работ. Помимо выложенных здесь правил, преподаватель может сообщить вам дополнительные требования на консультации, или включив их в установочный модуль.</p><p>Результаты контрольных работ смотрите в пункте «Мои оценки».</p></div>
	</div>

	<div id="help_text_2_6" style="display: none;" class="border" >
	    <h1><img src='../images/help_images/hh2.png'>Мои оценки</h1>
	    <div class="ht">Оценки – что это + интерфейс (или ссылка на подсказки - интерфейсы)- уже есть в другом пункте</div>
	 </div>

	 <div id="help_text_2_7" style="display: none;" class="border" >
	    <h1><img src='../images/help_images/hh2.png'>Библиотека курсов</h1>
	    <div class="ht"><p>Здесь вы сможете работать с курсами, которые были доступны вам в предыдущих годах обучения. Интерфейс работы схож с интерфейсом работы "Изучаемых материалов".</p></div>
	 </div>

	 <div id="help_text_2_8" style="display: none;" class="border" >
	    <h1><img src='../images/help_images/hh2.png'>Лента событий</h1>
	    <div class="ht"><p>Под окном личной информации о студенте расположена лента новостей. Она служит для оповещения студента о последних событиях на портале, таких как: обновления УМК, добавления тестов, обновления учебного плана, выставление оценок, усовершенствования портала и т.д….</p><p>Благодаря прочтению сообщений из данной системы уведомления вы всегда будете в курсе изменений, произошедших на портале.</p><p>Лента вмещает в себя ограниченное количество сообщений. Устаревшее сообщение вытесняется более свежим. История сообщений хранится в папке события.</p></div>
	 </div>

</div>