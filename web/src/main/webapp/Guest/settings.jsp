<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<script>
    dojo.require("dijit.form.Button");
	dojo.require("dijit.form.Form");
    dojo.require("dijit.form.TextBox");
    dojo.require("dijit.form.ValidationTextBox");
   // dojo.require("dijit.form.PasswordWidget");
    document.title = "ЦОО СГУ. Настройка Аккаунта";
</script>

<div class="head_main_math">
<p>Настройка аккаунта</p>
</div>

<div class="grey_button">
<div id="gr_line" class="GrayTitle">
        <div dojoType="dijit.TitlePane" title="Изменить/Убрать личную информацию">
             <form  method='post' id='Settings' name="Settings">
			 <table class="tab_new">
              <tr>
			     <td>
				 Телефон(мобильный):</td>
				 <td>
			      <input type="text" name="phone_mobile" value="+7927123455667"
               dojoType="dijit.form.ValidationTextBox"
                regExp="\d{11}"
                required="true"
                invalidMessage="вводите номер без тире, пример -  +7927123455667"/></td>
			   </tr>
			  <tr>
			     <td>
				 Телефон(домашний):</td>
				 <td>
			      <input type="text" name="phone_home" value="8(84564)123456"
                dojoType="dijit.form.ValidationTextBox"
                regExp="\d\(\d{2,4}\)\d{6,9}"
                required="true"
                invalidMessage="введите корретно домашний телефон с кодом. Пример  - 8(84564)123456"/></td>
			   </tr>
                <tr>
			     <td>
				 Номер ICQ:</td>
				 <td>
			      <input type="text" name="icq" value="235674344"
                   dojoType="dijit.form.ValidationTextBox"
                regExp="\d{5,11}"
                required="true"
                invalidMessage="введите корретно номер icq. Пример - 235674344"/></td>
			   </tr>
              <tr>
			     <td>
				  E-mail:</td>
				 <td>
			      <input type="text" name="email" value="student@mail.ru"
                dojoType="dijit.form.ValidationTextBox"
                regExp="[a-z0-9_\.\-]{1,20})@([a-z0-9\.\-]{1,20})\.([a-z]{2,4}"
                required="true"
                invalidMessage="введите корретно ваш e-mail. Пример - student@mail.ru"/></td>
			   </tr>
             </table>
            <p id="save_settings_success" style="display:none" class="scrit_blok2">Ваши данные успешно сохранены!</p>
			<p class="little_p" align=center>Данные, которые Вы внесете будут видны только администрации портала
            и необходимы, чтобы оперативно связаться с Вами. E-mail будет использоваться для автоматических рассылок и восстановления пароля. </p>
           
             <div class="virav_center stand_button_new">
                 <button disabled="true" dojoType="dijit.form.Button">
                     Изменить
                 </button>


                 <button dojoType="dijit.form.Button">
                     Убрать
                 </button>
             </div>
		</form>


        </div>



        <div dojoType="dijit.TitlePane" title="Изменить пароль">
           <form  method='post' id='change_password' name="change_password">
			<table class="tab_new">
			 <tr>
                 <td>Старый пароль:</td>
				 <td class="otstup"><input type="password"
                       name="old_password"
                       dojoType="dijit.form.ValidationTextBox"
                       required="true" /></td>
                 <td id="check_old_password_success" style="display:none"><img src="../images/action_check.gif"></td>
			 </tr>
			 <tr>
			     <td class="scrit_blok"></td>
			     <td id="old_password_error" style="display:none" class="scrit_blok">Неверный пароль</td>

			 </tr>
			 <tr>
			 	<td>Новый пароль:</td>
				<td><input  type="password"
                       name="new_password"
                       id="new_password"
                       regExp="[a-z0-9]{7,11}"
                       dojoType="dijit.form.ValidationTextBox"
                       required="true" disabled="true"
                       invalidMessage="Пароль должен быть не менее 7 символов и состять из латинских симоволов и цифр!"/></td>

			 </tr>
			
			 <tr>
				<td>Повторите пароль:</td>
				<td><input type="password"
                       name="confirm_password"
                       id="confirm_password"
                       dojoType="dijit.form.ValidationTextBox"
                       required="true" disabled="true"/></td>
                <td id="check_confirm_password_success" style="display:none"><img src="../images/action_check.gif"></td>
			</tr>
             <tr>
			     <td class="scrit_blok"></td>
			     <td id="confirm_password_error" style="display:none" class="scrit_blok">Пароли не совпадают!</td>
			 </tr>
			</table>
             <p id="save_password_success" style="display:none" class="scrit_blok2">Ваш пароль успешно изменен!</p>
				<div align="center">
					<div class="virav_left">
						<p class="little_p">* Пароль должен быть не менее 7 символов в длину</p>
						<p class="little_p">* Не используйте личные данные в качестве пароля</p>
						<p class="little_p">* Убедитесь, что не включен CAPS-Lock</p>
					</div>
				</div>
			<div class="virav_center stand_button_new">
			    <button disabled="true" id="button_change_password" dojoType="dijit.form.Button">
                    Изменить
                   </button>
			</div>
			 </form>

        </div>
</div>
</div>
