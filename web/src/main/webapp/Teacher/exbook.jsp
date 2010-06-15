<%-- 
    Document   : task
    Created on : 14.08.2009, 13:36:12
    Author     : ShihovMY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

﻿<html>
	<head>
		<link rel="stylesheet" href="../dojoroot/dojo/resources/dojo.css" type="text/css">
		<link rel="stylesheet" href="../dojoroot/dijit/themes/tundra/tundra.css" type="text/css">
		<link rel="stylesheet" href="../style/umkStyle.css" type="text/css"/>
	</head>
<script type="text/javascript" src="../dojoroot/dojo/dojo.js" djConfig="parseOnLoad: true" djConfig="preventBackButtonFix: false"></script>
<script type="text/javascript">
	dojo.require("dojo.parser");
	dojo.require("dojo.fx");
	dojo.require("dijit.form.Button");
	dojo.require("dojox.layout.ContentPane");
	dojo.require("dijit.Dialog");
</script>
<script type="text/javascript" src="../scripts/exbook.js"></script>
<script type="text/javascript" src="../textbooks/history_best/dialog.js"></script>
<body style="padding: 0px; margin: 0px; //overflow-y: hidden;">
	<div class="top_menu" id="fixed_block">
		<div class="verh">
		</div>

		<div class="zakladka">
			<div class="smeshenie">
				<div class="block_top_menu">
					<a class="link_top_menu" href="main.jsp">Главная</a>
				</div>
				<div class="activpage">
					<div class="left_part">
						<div class="right_part">
							<a class="link_top_menu" href="main.jsp#news">Новости</a>
						</div>
					</div>
				</div>
				<div class="block_top_menu">
					<a class="link_top_menu" href="main.jsp#mat">Материалы</a>
				</div>
				<div class="block_top_menu_back">
					<a class="link_top_menu" href="../index.jsp">Выход</a>
				</div>
			</div>
		</div>

	<div class="title">
		<p id="title">  </p>
	</div>
	<div class="pages">
		<div class="smeshenie_pages">
			<div class="pages_block">
                <p><a class="page_link" href="javascript:void(0)" id="currentPage"></a> </p>
			</div>
			<div class="pages_block">
				<p id="previous"></p>
			</div>
            <div id="pages">
            </div>
			<div class="pages_block">
				<p id="next"></p>
			</div>
			<div class="pages_block">
				<p><a class="page_link" href="" id="last">Последняя</a></p>
			</div>
			<div class="pages_block">
                <a class="page_link" id="back" href=""><img  src="../images/book.jpg"></a>
			</div>
			<div class="pages_block">
                <p></p>
			</div>
			<div class="author" id="moduleName" style="visibility:hidden">
			</div>
		</div>
	</div>
</div>

<div>
<div align="center" id="move_block" class="move_block">
	<div class="info_block" >
					<div style="position: relative; left: -46px;" id="toolbarPane">
						<div class="toolbar">
							<div class="panel_open" id="green" onclick="sh_hi_bar(2, 'red', this);">
								<input type="image" src="../images/open_panel2.png" name="button">
							</div>
							<div class="panel_close" id="red" onclick="sh_hi_bar(1, 'green', this);">
								<input type="image" src="../images/close_panel.png" name="button">
							</div>
							<div id="tools" class="tools">
								<input type="image" src="../images/plus.png" name="button" onclick="text_size('infopart')">
								<input type="image" src="../images/minus.png" name="button" onclick="text_size_min('infopart')">
                                <!--<input type="image" src="../images/text.png" name="button" onclick="defolt_text('infopart')">
								<input type="image"src="../images/question.png" name="button">
								<input type="image" src="../images/tools.png" name="button">-->
							</div>
						</div>
					</div>

			<div  id="infopart"  executeScripts="true" dojoType="dojox.layout.ContentPane" preload parseOnLoad preventCache refreshOnShow
            loadingMessage='<div align="center" id="loader" style="z-index: 100; position: relative; margin-top:110px;"><img src="../images/loader1.gif"></div>'
            class="info" style="font-size: 10pt; cursor: default;">
				</div>
		</div>
	</div>
</div>
</body>
</html>