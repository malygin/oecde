<%-- 
    Document   : allTests
    Created on : 21.07.2009, 16:35:09
    Author     : KorgovVD
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:set var="s" value="${(param[\"s\"] eq 1)}"/>

<script>
    dojo.require("dijit.ProgressBar");
    function hide_tr(id,link_id){
        if(dojo.byId(id).style.display != 'none')
            dojo.fx.wipeOut({node: id,duration: 250}).play();        
        else
            dojo.fx.wipeIn({node: id,duration: 250}).play();
        dojo.byId(link_id).setAttribute("class","show_hide_link_in_otch");
    }
</script>
<div class="head_main_math">
    <p>Результаты тестов</p>
</div>

<div>

    <table class="otch_main_tab" cellpadding="0" cellspacing="0">
        <tr>
            <td class="first_stolb_hat_main_tab">
                <p>Название</p>
            </td>
            <td class="second_stolb_hat_main_tab">
                <p>Попыток</p>
            </td>
            <td class="third_stolb_hat_main_tab">
                <p>Правильных ответов</p>
            </td>
            <td class="fourth_stolb_hat_main_tab">
                <p>Результат</p>
            </td>
            <td class="fiveth_stolb_hat_main_tab">
                <p>Способ оценивания</p>
            </td>
        </tr>
        <tr  style="margin-left: 10px;" class="">
            <td class="not_last_td_otch_page" >
                <a href="javascript:void(0)" onclick="hide_tr('int_table0',this)" class="show_hide_link_in_otch">
                    Итоговый тест номер 1
                </a>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    2
                </p>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    &nbsp;-&nbsp;
                </p>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    97
                </p>
            </td>
            <td class="last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    Зачтено
                </p>
            </td>
        </tr>
        <tbody id="int_table0" style="margin: 0px; display: none;">
            <tr>
                <td class="td_otch_page1">Попытка 1</td>
                <td class="td_otch_page1"></td>
                <td class="td_otch_page1">93 из 100</td>
                <td class="td_otch_page1">12-12-2009</td>
                <td class="td_otch_page1">&nbsp;</td>
            </tr>
            <tr>
                <td class="td_otch_page1">Попытка 2</td>
                <td class="td_otch_page1"></td>
                <td class="td_otch_page1">97 из 100</td>
                <td class="td_otch_page1">21-12-2009</td>
                <td class="td_otch_page1">&nbsp;</td>
            </tr>
        </tbody>
        <tr  style="margin-left: 10px;" class="">
            <td class="not_last_td_otch_page" >
                <a href="javascript:void(0)" onclick="hide_tr('int_table1',this)" class="show_hide_link_in_otch">
                    Итоговый тест номер 2
                </a>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    1
                </p>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    &nbsp;-&nbsp;
                </p>
            </td>
            <td class="not_last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    97
                </p>
            </td>
            <td class="last_td_otch_page">
                <p class="p_in_td_tab_otch">
                    Зачтено
                </p>
            </td>
        </tr>
        <tbody id="int_table1" style="margin: 0px; display: none;">
            <tr>
                <td class="td_otch_page1">Попытка 1</td>
                <td class="td_otch_page1"></td>
                <td class="td_otch_page1">93 из 100</td>
                <td class="td_otch_page1">12-12-2009</td>
                <td class="td_otch_page1">&nbsp;</td>
            </tr>
        </tbody>
    </table>
</div>
