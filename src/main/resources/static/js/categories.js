/**
 * Created by ekaterina on 02.05.2017.
 */

$(document).ready(function () {

    $('#categories').DataTable({
        info: false,
        "aoColumns":[
            null,
            {"bSortable": false}
        ]
    });
});


function deleteCategory(id) {

    if (confirm("Confirm delete?")) {
        $.ajax({
            url: "/categories/delete/" + id,
            type: "POST",
            success: function (response) {
                $('#category-' + id).hide()
            }
        }).fail(function () {
            alert("Error");
        });
    }
}

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
