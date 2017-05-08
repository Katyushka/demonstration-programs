/**
 * Created by ekaterina on 12.10.2016.
 */

$(document).ready(function () {

    $('#users').DataTable({
        info: false,
        "aoColumns":[
            null,
            null,
            null,
            null,
            null,
            {"bSortable": false}
        ]
    });
});


function deleteUser(id) {
    if (confirm("Confirm delete?")) {
        $.ajax({
            url: "/users/delete/" + id,
            type: "POST",
            success: function (response) {
                $('#user-' + id).hide()
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