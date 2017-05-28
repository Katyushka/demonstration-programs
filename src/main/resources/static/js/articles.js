/**
 * Created by ekaterina on 28.05.2017.
 */

$(document).ready(function () {

    $('#articles').DataTable({
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


function deleteArticle(id) {
    if (confirm("Confirm delete?")) {
        $.ajax({
            url: "/articles/delete/" + id,
            type: "POST",
            success: function (response) {
                $('#article-' + id).hide()
            }
        }).fail(function () {
            alert("Error");
        });
    }

}
