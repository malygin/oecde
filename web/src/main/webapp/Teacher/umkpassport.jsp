<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ЦОО СГУ</title>
        <link href="../style/style.css" rel="stylesheet" type="text/css">
        <link href="../style/text.css" rel="stylesheet" type="text/css">       
        <!--<link href="../style/test_text_umk.css" rel="stylesheet" type="text/css">-->
        <link rel="icon" href="../favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="favicon.ico">
    </head>
    <style type="text/css">
        @import "../dojoroot/dijit/themes/tundra/test_tundra.css";
        @import "../dojoroot/dojo/resources/dojo.css";
        @import "../style/style.css";
        @import "../style/DropDownSelect.css";
    </style>
    <script type="text/javascript" src="../dojoroot/dojo/dojo.js" djConfig="parseOnLoad: true" djConfig="preventBackButtonFix: false"></script>
    <script type="text/javascript">
        dojo.require("dojo.parser");
		dojo.require("dojox.data.dom");
		dojo.require("dijit.TitlePane");
		dojo.require("dojox.layout.ContentPane");
        dojo.require("dojo.back");
        dojo.require("dijit.form.Slider");		
		dojo.require("dijit.form.Button");
		dojo.require("dijit.Dialog");
		dojo.require("dijit.form.ComboBox");
		dojo.require("dijit.form.CheckBox");	
		dojo.require("dojo.data.ItemFileWriteStore");
        dojo.require("dijit.InlineEditBox");
        dojo.require("dijit.form.Form");
		//dojo.require("dojo.fx");
       // dojo.connect();
       dojo.addOnLoad(function fhref(){          
           dijit.byId("contentPane").setHref("task.jsp?umk=<%=request.getParameter("umk")!= null?request.getParameter("umk").replaceAll("[^0-9]", ""):""%>");
         
       })
    </script>
    <script type="text/javascript" src="../scripts/date_today.js" djConfig="parseOnLoad: true"></script>
    <script type="text/javascript" src="../scripts/umkpassport.js" djConfig="parseOnLoad: true"></script>
    
    <script>
       function hide_menu(menu){
            dojo.byId(menu).style.visibility = "hidden";          
          
       }
       function show_menu(menu){          
            dojo.byId(menu).style.visibility = "visible";      
       }

    </script>
<body class="tundra">
    <div align="center">
        <div class="main_page">
            <div class="top_shadow">
                <div class="bot_shadow">
                    <div class="l_t_angle">
                        <div class="r_t_angle">
                            <div class="r_b_angle">
                                <div class="l_b_angle">
                                    <div class="inf_pole">
                                        <jsp:include page="jspf/l_menu.jsp"></jsp:include>
                                        <div class="right">
                                            <jsp:include page="jspf/t_menu.jsp"></jsp:include>
                                            <div class="right_inside">
                                                <div>
                                                    <div class="inf">
                                                        <jsp:include page="jspf/fio.jsp"></jsp:include>
                                                        <div class="under_fio_line"></div>
<div class="main_inf" id="contentPane" executeScripts="true" dojoType="dojox.layout.ContentPane" parseOnLoad preventCache refreshOnShow loadingMessage="">
</div>
                                                    </div>
                                                    <jsp:include page="jspf/r_menu.jsp"></jsp:include>
                                                    <div align="centr"><div class="otdelitel"></div></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div align="centr"><div class="otdelitel"></div></div>
                                    </div>
                                    <jsp:include page="jspf/b_menu.jsp"></jsp:include>
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
