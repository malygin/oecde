
<%--
    Document   : education
    Created on : 07.08.2009, 16:14:46
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    dojo.require("dojo.parser");
    dojo.require("dojox.layout.ContentPane");
    dojo.require("dijit.layout.TabContainer");
    function makeTabsVisible(){
        dojo.forEach( dijit.byId("mainTabContainer").getChildren(),function f32q(item){dojo.attr(item,"style","visibility:visible")});
    }
</script>


<div class="head_main_math">
    <p>6 шагов к успешному обучению</p>
</div>
<div class="centered_tabs_of_tabContainer">
	<div id="mainTabContainer" nested="true" doLayout dojoType="dijit.layout.TabContainer"  style="width:522px; height:1000px;">
		<div dojoType="dojox.layout.ContentPane"  href="education_info.jsp" title="Шаг 1" loadingMessage="" parseOnLoad selected>
		</div>

		<div id="umk" dojoType="dijit.layout.ContentPane" href="education_umk.jsp" title="Шаг 2" loadingMessage="" parseOnLoad refreshOnShow preload="false" style="visibility:hidden">

		</div>

		<div id="cw" dojoType="dijit.layout.ContentPane" href="education_contr_work.jsp" title="Шаг 3" loadingMessage="" parseOnLoad refreshOnShow preload="false" style="visibility:hidden">

		</div>

		<div id="test" dojoType="dijit.layout.ContentPane" href="education_test.jsp" title="Шаг 4" loadingMessage="" parseOnLoad refreshOnShow preload="false" style="visibility:hidden">

		</div>

		<div id="cTest" dojoType="dijit.layout.ContentPane" href="education_it_test.jsp" title="Шаг 5" loadingMessage="" parseOnLoad refreshOnShow preload="false" style="visibility:hidden">

		</div>

		<div id="misc" dojoType="dijit.layout.ContentPane"  href="education_pr.jsp" title="Шаг 6" loadingMessage="" parseOnLoad refreshOnShow preload="false" style="visibility:hidden">

		</div>


	</div>
</div>