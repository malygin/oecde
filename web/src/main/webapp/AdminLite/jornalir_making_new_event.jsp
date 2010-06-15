<%-- 
    Document   : jornalir_making_new_event
    Created on : 07.07.2009, 14:00:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 <script type="text/javascript">
		dojo.require("dijit.form.FilteringSelect");
        dojo.require("dijit.form.Button");
 </script>

	<div class=tree>
        <div class=GrayTitle>
        <div dojoType="dijit.TitlePane" title="Обновление УМК" open="false">
        <div class="for_select_name">
            <form action="#" method="GET">
            <p class="name_ymk_select">Выберите название УМК</p>
            <div class="for_form_selection">
            <select  dojoType="dijit.form.FilteringSelect" id="setvaluetest2" name="state1" autoComplete="false" invalidMessage="Invalid state name" onChange="dojo.byId('oc1').value=arguments[0]" >
                <option >Название УМК 1</option>
                <option >Название УМК 2</option>
                <option >Название УМК 3</option>
                <option >Название УМК 4</option>
                <option >Название УМК 5</option>
                <option >Название УМК 6</option>
                <option >Название УМК 7</option>
                <option >Название УМК 8</option>
                <option >Название УМК 9</option>
                <option >Название УМК 10</option>
                <option >Название УМК 11</option>
                <option >Название УМК 12</option>
                <option >Название УМК 13</option>
                <option >Название УМК 14</option>
                <option >Название УМК 15</option>
                <option >Название УМК 16</option>
                <option >Название УМК 17</option>
                <option >Название УМК 18</option>
                <option >Название УМК 19</option>
                <option >Название УМК 20</option>
                <option >Название УМК 21</option>
                <option >Название УМК 130</option>
                <option >Название УМК 151</option>
                <option >Название УМК 162</option>
                <option >Название УМК 133</option>
                <option >Название УМК 164</option>
                <option >Название УМК 135</option>
                <option >Название УМК 166</option>
                <option >Название УМК 127</option>
                <option >Название УМК 1861</option>
                <option >Название УМК 149</option>
                <option >Название УМК 270</option>
                <option >Название УМК 211</option>
            </select>
            </div>
            </form>
            
		<p class="name_ymk_comment">Введите комментарий:</p>
		<textarea class="for_comments_new"></textarea>
	</div>
	<button class="button_for_dobavl_inf" dojoType="dijit.form.Button" iconClass="plusIcon">Сохранить</button>
	</div>
	</div>
    </div>

	<div class=tree>
	<div class=GrayTitle>
	<div dojoType="dijit.TitlePane" title="Обновление теста" open="false">
	<div class="for_select_name">
		<form action="#" method="GET">
		<p class="name_ymk_select">Выберите название УМК</p>
		<div class="for_form_selection">
		<select  dojoType="dijit.form.FilteringSelect" id="setvaluetest3" name="state2" autoComplete="false" invalidMessage="Invalid state name" onChange="dojo.byId('oc1').value=arguments[0]" >
		    <option >Название УМК 1</option>
		    <option >Название УМК 2</option>
		    <option >Название УМК 3</option>
		    <option >Название УМК 4</option>
		    <option >Название УМК 5</option>
		    <option >Название УМК 6</option>
		    <option >Название УМК 7</option>
		    <option >Название УМК 8</option>
		    <option >Название УМК 9</option>
		    <option >Название УМК 10</option>
		    <option >Название УМК 11</option>
		    <option >Название УМК 12</option>
		    <option >Название УМК 13</option>
		    <option >Название УМК 14</option>
		    <option >Название УМК 15</option>
		    <option >Название УМК 16</option>
		    <option >Название УМК 17</option>
		    <option >Название УМК 18</option>
		    <option >Название УМК 19</option>
		    <option >Название УМК 20</option>
		    <option >Название УМК 21</option>
			<option >Название УМК 130</option>
		    <option >Название УМК 151</option>
		    <option >Название УМК 162</option>
		    <option >Название УМК 133</option>
		    <option >Название УМК 164</option>
		    <option >Название УМК 135</option>
		    <option >Название УМК 166</option>
		    <option >Название УМК 127</option>
		    <option >Название УМК 1861</option>
		    <option >Название УМК 149</option>
		    <option >Название УМК 270</option>
		    <option >Название УМК 211</option>
	    </select>
        </div>
		</form>
        

		<form action="#" method="GET">
		<p class="name_ymk_select">Выберите название теста</p>
		<div class="for_form_selection">
		<select  dojoType="dijit.form.FilteringSelect" id="setvaluetest4" name="state3" autoComplete="false" invalidMessage="Invalid state name" onChange="dojo.byId('oc1').value=arguments[0]" >
		    <option >Название теста 1</option>
		    <option >Название теста 2</option>
		    <option >Название теста 3</option>
		    <option >Название теста 4</option>
		    <option >Название теста 5</option>
		    <option >Название теста 6</option>
		    <option >Название теста 7</option>
		    <option >Название теста 8</option>
		    <option >Название теста 9</option>
		    <option >Название теста 10</option>
		    <option >Название теста 11</option>
		    <option >Название теста 12</option>
		    <option >Название теста 13</option>
		    <option >Название теста 14</option>
		    <option >Название теста 15</option>
	    </select>
        </div>
		</form>

		<p class="name_ymk_comment">Введите комментарий:</p>
		<textarea class="for_comments_new"></textarea>
        </div>
	<button class="button_for_dobavl_inf" dojoType="dijit.form.Button" iconClass="plusIcon">Сохранить</button>
    </div>
    </div>
    </div>

	<div class=tree>
	<div class=GrayTitle>
	<div dojoType="dijit.TitlePane" title="Усовершенствование портала" open="false">
	<div class="for_select_name">
		<p class="name_ymk_comment">Введите комментарий:</p>
		<textarea class="for_comments_new"></textarea>
	</div>
	<button class="button_for_dobavl_inf" dojoType="dijit.form.Button" iconClass="plusIcon">Сохранить</button>
	</div>
	</div>
	</div>

	<div class=tree>
	<div class=GrayTitle>
	<div dojoType="dijit.TitlePane" title="Обновление учебного плана" open="false">
	<div class="for_select_name">
		<form action="#" method="GET">
		<p class="name_ymk_select_nomber_of_group">Выберите номер группы</p>
		<div class="for_form_selection">
		<select  dojoType="dijit.form.FilteringSelect" id="setvaluetest5" name="state4" autoComplete="false" invalidMessage="Invalid state name" onChange="dojo.byId('oc1').value=arguments[0]" >
		    <option >1</option>
		    <option >2</option>
		    <option >3</option>
		    <option >4</option>
		    <option >5</option>
		    <option >6</option>
		    <option >7</option>
		    <option >8</option>
		    <option >9</option>
		    <option >10</option>
		    <option >11</option>
		    <option >12</option>
		    <option >13</option>
		    <option >14</option>
		    <option >15</option>
		    <option >16</option>
		    <option >17</option>
		    <option >18</option>
		    <option >19</option>
		    <option >20</option>
		    <option >21</option>
			<option >130</option>
		    <option >151</option>
		    <option >162</option>
		    <option >133</option>
		    <option >164</option>
		    <option >135</option>
		    <option >166</option>
		    <option >127</option>
		    <option >861</option>
		    <option >149</option>
		    <option >270</option>
		    <option >211</option>
	    </select>
        </div>
		</form>
		
		<form action="#" method="GET">
		<p class="name_ymk_select">Выберите специальность</p>
		<div class="for_form_selection">
		<select  dojoType="dijit.form.FilteringSelect" id="setvaluetest6" name="state5" autoComplete="false" invalidMessage="Invalid state name" onChange="dojo.byId('oc1').value=arguments[0]" >
		    <option >Специальность 1</option>
		    <option >Специальность 2</option>
		    <option >Специальность 3</option>
		    <option >Специальность 4</option>
		    <option >Специальность 5</option>
		    <option >Специальность 6</option>
		    <option >Специальность 7</option>
		    <option >Специальность 8</option>
		    <option >Специальность 9</option>
		    <option >Специальность 10</option>
		    <option >Специальность 11</option>
		    <option >Специальность 12</option>
		    <option >Специальность 13</option>
		    <option >Специальность 14</option>
		    <option >Специальность 15</option>
	    </select>
        </div>
		</form>
        </div>
		<button class="button_for_dobavl_inf" dojoType="dijit.form.Button" iconClass="plusIcon">Сохранить</button>
	</div>
    </div>
    </div>



