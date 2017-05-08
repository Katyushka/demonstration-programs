/**
 * Created by ekaterina on 30.04.2017.
 */

$(document).ready(function () {

    $('#groups').DataTable({
        info: false,
        "aoColumns":[
            null,
            {"bSortable": false}
        ]
    });
});

function deleteGroup(id) {

    if (confirm("Confirm delete?")) {
        $.ajax({
            url: "/groups/delete/" + id,
            type: "POST",
            success: function (response) {
                $('#group-' + id).hide()
            }
        }).fail(function () {
            alert("Error");
        });
    }
}

