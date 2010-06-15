<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "student.model.*" %>
<%@ page import = "java.util.*" %>

    <script type="text/javascript">
        dojo.require("dojo.parser");
        dojo.require("dijit.TitlePane");
        dojo.require("dijit.form.Button");
        dojo.require("dijit.form.Form");
                dojo.require("dijit.form.TextBox");
                dojo.require("dijit.Tree");
                dojo.require("dojo.data.ItemFileReadStore");
        document.title = "ЦОО СГУ. Список групп"
    </script>

                    <div class=head_main_math>
                        <p> Список студентов  </p>
                    </div>

                    <div align="centr"><div class="otdelitel"></div></div>

                    <div id=listtr>
                        <div dojoType="dojo.data.ItemFileReadStore" jsId="groupeStore" url="serviceStGroups.jsp"></div>
                                                <div class='stud_tree'><div dojoType="dijit.Tree" id="navigationTree" class="w3tree"
                                                        openOnClick="true" showRoot="false" store="groupeStore" width=auto>
                                                     <script type="dojo/method" event="onClick" args="item">
                                                            if(groupeStore.getValue(item, "type") == "groupe")
                                                            {
                                                                    window.location = "main.jsp#students/gr="+groupeStore.getValue(item, "name")+"&sp="+groupeStore.getValue(item, "speciality");
                                                            }
                                                    </script>
                                                </div></div>
                    </div>

