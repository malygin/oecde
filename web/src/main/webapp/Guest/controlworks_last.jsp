<%-- 
    Document   : controlworks_last
    Created on : 08.07.2009, 11:46:01
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<script type="text/javascript">           
    function gggg(id1,id2){
    dojo.require("dojo.fx");

    var fout = dojo.fadeOut({node: id2, duration: 300});
    var fin = dojo.fadeIn({node: id2, duration: 300});
    var hide = dojo.fx.wipeOut({node: id1, duration: 300});
    var show = dojo.fx.wipeIn({node: id1, duration: 300});
    var animOut = dojo.fx.combine([fout,hide]);
    var animIn = dojo.fx.combine([show,fin]);
    if(dojo.byId(id1).style.display == "none"){
        animIn.play();
    }else{
        animOut.play();
    }
    }
</script>

<div class="head_main_math">
<p>Мои контрольные работы</p>
</div>

<div class="title">
    <table class="gray_words" cellpadding='0' cellspacing='0'>
        <tr>
            <td class="gray_words_1_2" style="width: 190px;">Преподаватель</td>
            <td class="gray_words_1_2" style="width: 145px;">&nbsp;</td>
            <td class="gray_words_3_4" style="text-align: right !important; ">попытки</td>
            <td class="gray_words_3_4" style="width: 73px; padding-left: 20px; text-align: left !important;">оценка</td>
        </tr>
    </table>
</div>

<div class="GrayTitle_big">
    <div dojoType="dijit.TitlePane" title="
        <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
            <tr>
                <td class='gray_words_1_2'><b>Механика    </b></td>
                <td class='gray_words_1_2'>Степанова О. А.</td>
                <td class='gray_words_3_4'>4 из 20</td>
                <td class='gray_words_3_4'></td>
                <td class='gray_words_5'></td>
            </tr>
        </table>
    ">
        <div>
            <div class="white_title">
               <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td class="gray_words_1_2"><a href="javascript:void(0)" onclick="gggg('table_for_razv0','pane0')">Контрольная №1</a></td>
                        <td class="gray_words_1_2"></td>
                        <td class="gray_words_3_4">4</td>
                        <td class="gray_words_3_4" class='n'>зачтено</td>
                        <td class="gray_words_5"><a style="display: none;" ><img src='../images/2ms.jpg'></a></td>
                    </tr>
               </table>
                <div id="table_for_razv0" style="display: none;">
                     <table class="pane" id="pane0" cellpadding='0' cellspacing='0'>
                        <tr class="gray_words">
                            <td  class="gray_words_1_2">попытка 1</td>
                            <td  class="gray_words_1_2">13.09.07</td>
                            <td  class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">загрузить</a></td>
                            <td  class="gray_words_3_4"></td>
                            <td  class="gray_words_5"></td>
                        </tr>
                        <tr class="gray_words">
                            <td  class="gray_words_1_2">попытка 2</td>
                            <td  class="gray_words_1_2">13.09.07</td>
                            <td  class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">загрузить</a></td>
                            <td  class="gray_words_3_4"></td>
                            <td  class="gray_words_5"></td>
                        </tr>
                        <tr class="gray_words">
                            <td  class="gray_words_1_2">попытка 3</td>
                            <td  class="gray_words_1_2">13.09.07</td>
                            <td  class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">загрузить</a></td>
                            <td  class="gray_words_3_4"></td>
                            <td  class="gray_words_5"></td>
                        </tr>
                        <tr class="gray_words">
                            <td class="gray_words_1_2">попытка 4</td>
                            <td class="gray_words_1_2">13.09.07</td>
                            <td class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;' href="javascript:alert('У вас недостаточно прав для использования данного функционала.')">загрузить</a></td>
                            <td class="gray_words_3_4"></td>
                            <td class="gray_words_5"></td>
                        </tr>
                    </table>
               </div>
            </div>
            
        </div>
    </div>
    <%--<div dojoType="dijit.TitlePane" title="
        <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
            <tr>
                <td class='gray_words_1_2'><b>Математическая статистика</b></td>
                <td class='gray_words_1_2'>Игнатьева А. А.</td>
                <td class='gray_words_3_4'>2 из 20</td>
                <td class='gray_words_3_4'></td>
                <td class='gray_words_5'></td>
            </tr>
        </table>
    ">
        <div style="border-bottom: 1px solid silver;">
            <div class="white_title">
               <table class='tab_contr_works' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td class="gray_words_1_2"><a href="javascript:void(0)" onclick="gggg('table_for_razv1','pane1')">Контрольная №1</a></td>
                        <td class="gray_words_1_2"></td>
                        <td class="gray_words_3_4">2</td>
                        <td class="gray_words_3_4" class='n'>зачтено</td>
                        <td class="gray_words_5"><a style="display: none;" href=''><img src='../images/2ms.jpg'></a></td>
                    </tr>
               </table>
                <div id="table_for_razv1" style="display: none;">
                     <table class="pane" id="pane1" cellpadding='0' cellspacing='0'>
                        <tr class="gray_words">
                            <td  class="gray_words_1_2">попытка 1</td>
                            <td  class="gray_words_1_2">13.09.07</td>
                            <td  class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;'>загрузить</a></td>
                            <td  class="gray_words_3_4"></td>
                            <td  class="gray_words_5"></td>
                        </tr>
                        <tr class="gray_words">
                            <td  class="gray_words_1_2">попытка 2</td>
                            <td  class="gray_words_1_2">13.09.07</td>
                            <td  class="gray_words_3_4"><a  style='color:#147BC4; font-size:10pt;'>загрузить</a></td>
                            <td  class="gray_words_3_4"></td>
                            <td  class="gray_words_5"></td>
                        </tr>
                    </table>
               </div>
            </div>
           
        </div>
    </div>--%>
</div>
 
