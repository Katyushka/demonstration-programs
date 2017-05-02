/**
 * Created by ekaterina on 02.05.2017.
 */
function addCategory() {
    $('#categoryDialog').dialog("option", "title", 'Добавить категорию');
    $('#categoryDialog').dialog('open');
}

function editCategory(id) {

    $.get("/categories/get/" + id, function (result) {

        $("#categoryDialog").html(result);

        $('#categoryDialog').dialog("option", "title", 'Редактировать');

        $("#categoryDialog").dialog('open');
    });
}

function deleteCategory(id) {

    $("#dialog-confirm").dialog({
        resizable: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "Удалить категорию": function () {
                $.ajax({
                    url: "/categories/delete/" + id,
                    type: "POST",
                    success: function (response) {
                        $('#category-' + id).hide()
                    }
                });
                $(this).dialog("close");
            },
            "Отменить": function () {
                $(this).dialog("close");
            }
        }
    });
}

function resetDialog(form) {

    form.find("input").val("");
}

$(document).ready(function () {

    $('#categoryDialog').dialog({

        autoOpen: false,
        position: 'center',
        modal: true,
        resizable: false,
        width: 440,
        buttons: {
            "Сохранить": function () {
                $('#categoryForm').submit();
            },
            "Отменить": function () {
                $(this).dialog('close');
            }
        },
        close: function () {

            resetDialog($('#categoryForm'));

            $(this).dialog('close');
        }
    });
});

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
