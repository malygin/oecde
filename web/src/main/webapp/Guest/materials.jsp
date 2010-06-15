<%--
    Document   : st_mat_content
    Created on : 03.03.2009, 12:26:41
    Author     : KorgovVD
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script>
    document.title = "ЦОО СГУ. Изучаемые материалы";
    dojo.require("dijit.ProgressBar");
</script>

<div class="head_main_math">
    <p>Изучаемые материалы</p>
</div>
<div>
    <div class="inf_math">
        <div class="inf_math">
            <a class="material_name" style="color:#75CFF5; font-size:12pt;"><b>Mатематика</b></a>
            <p class="teacher_name"><b>Автор:</b> Степанова О. А.</p>
            <div class="dop_inf">
                <p class="finished_tests">Пройденные тесты: 0 из 2 <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">перейти к тестам</a></p>
                <p class="test_results">Результаты тестирования: <b class="grade_points_in_materials"></b>170 баллов <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">подробнее</a></p>
                <p class="final_test">Итоговый тест: <b class="grade_points_in_materials"></b>80 баллов</p>
            </div>
            <br>
            <div class="materials_page">
                <div dojoType="dijit.ProgressBar" progress="80"></div>
            </div>
        </div>
    </div>
    <div class="inf_math">
        <div class="inf_math">
            <a class="material_name" style="color:#75CFF5; font-size:12pt;"><b>Mеханика</b></a>
            <p class="teacher_name"><b>Автор:</b> Игнатьева А. A. </p>
            <div class="dop_inf">
                <p class="finished_tests">Пройденные тесты: 0 из 4 <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">перейти к тестам</a></p>
                <p class="test_results">Результаты тестирования: <b class="grade_points_in_materials"></b>370 баллов <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">подробнее</a></p>
                <p class="final_test">Итоговый тест: <b class="grade_points_in_materials"></b>90 баллов</p>
            </div>
            <br>
            <div class="materials_page">
                <div dojoType="dijit.ProgressBar" progress="90"></div>
            </div>
        </div>
    </div>
    <div class="inf_math">
        <div class="inf_math">
            <a class="material_name" style="color:#75CFF5; font-size:12pt;"><b>Информационные технологии</b></a>
            <p class="teacher_name"><b>Автор:</b> Позднева И. П. </p>
            <div class="dop_inf">
                <p class="finished_tests">Пройденные тесты: 0 из 4 <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">перейти к тестам</a></p>
                <p class="test_results">Результаты тестирования: <b class="grade_points_in_materials"></b>270 баллов <a href="javascript:alert('У Вас недостаточно прав, для использования данного функционала!');" class="more_inf_link" style="color:#959595;font-size:10pt; text-decoration: underline;">подробнее</a></p>
                <p class="final_test">Итоговый тест: <b class="grade_points_in_materials"></b>85 баллов</p>
            </div>
            <br>
            <div class="materials_page">
                <div dojoType="dijit.ProgressBar" progress="85"></div>
            </div>
        </div>
    </div>
</div>
