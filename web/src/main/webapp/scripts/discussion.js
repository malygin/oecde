/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    function create_text_area(n, text, cName, id) {
        var ta = new dijit.form.Textarea({
            name: n,
            value: text,
            class: cName
        }, id);
    }
    function show_commF(name, id) {
        create_text_area("ta" + name, "", "coment_ta", name + id + id);
        dojo.attr(name + id, "style", "display: block; border: 1px solid silver; margin: 5px ; padding: 3px; text-align: center;");
    }
    function hidme(name, id) {
        dojo.attr(name + id, "style", "display: none;");
        dijit.byId(name + id + id).destroy();
        div = document.createElement("div");
        div.id = name + id + id;
        dojo.byId(name + id + id + id).appendChild(div);
    }
    function redact_comm(name, id, old_value) {
        create_text_area("ta" + name, old_value, "coment_ta", name + id);
        dojo.byId("coment_old_text" + id).style.display = "none";
        dojo.attr("paren_c" + id, "style", 'display: block;');

    }
    function com_changing_cancel(id, type) {
        if (type == false) {
            new_val = dijit.byId("container_for_ta" + id).value;
            dojo.byId("coment_old_text" + id).innerHTML = new_val;
        }
        dijit.byId("container_for_ta" + id).destroy();
        dojo.byId("paren_c" + id).style.display = "none";
        dojo.byId("coment_old_text" + id).style.display = "block";
        div = document.createElement("div");
        div.id = "container_for_ta" + id;
        dojo.byId("ccont" + id).appendChild(div);
    }
    function commet_this_event() {
        create_text_area("new_root_comment", "", "coment_ta", "for_ta_news_com");
        dojo.attr("new_com_form", "style", "display: block; margin: 5px 0 0 0; padding: 5px 5px 0 5px; border: none; border-top: 1px solid silver;");
    }
    function cancel_root_com() {
        dojo.byId("new_com_form").style.display = "none";
        dijit.byId("for_ta_news_com").destroy();
        div = document.createElement("div");
        div.id = "for_ta_news_com";
        dojo.byId("com_form_container").appendChild(div);
    }
    function actionResult(name, id, actCont) {
        hidme(name, id);
        dojo.byId(actCont + id).style.display = "block";
        var g = setTimeout(function res() {
            dojo.byId(actCont + id).style.display = "none"
        }, 1000);
    }
    //Единица добавлена, чтобы функция не перегружалась в child.tag
    function submit_com1(id,typeObject) {
        val = dojo.byId("for_ta_news_com").value;
        dojo.xhrPost({
            url:'../spring/discussion/addComment',
            content:{idObject:id,
                message : val,
                typeObject : typeObject
            },
            load:function f4qw() {
                dijit.byId("contentPane").refresh()
            }
        });
    }

    function submitNews() {
        document.getElementById("fullText").value = dijit.byId("main_text").getValue(false);
        dojo.xhrPost({
            form:dojo.doc.editNews,
            url: "../spring/news/newsAdd.jsp",
            load:function f4qw() {
                dijit.byId("contentPane").refresh()
            },
            error:function ff423(e) {
                console.debug(e)
            }
        });
    }


    function showPrevNews(){
        dojo.byId("prevNewsText").innerHTML = dijit.byId("main_text").editNode.innerHTML;
        dojo.byId("prevNewsHeader").innerHTML = dojo.byId("header").value;
        dijit.byId("preview").show();
        dojo.byId("divCont").style.height = dojo.byId("divCont").offsetParent.offsetHeight - 37 + "px";
    }

    function discardChanges(){
        dijit.byId("preview").hide();
    }