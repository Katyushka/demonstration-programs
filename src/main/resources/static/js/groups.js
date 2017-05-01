/**
 * Created by ekaterina on 30.04.2017.
 */
function addGroup() {
    $('#groupDialog').dialog("option", "title", 'Add Group');
    $('#groupDialog').dialog('open');
}

function editGroup(id) {

    $.get("/groups/get/" + id, function(result) {

        $("#groupDialog").html(result);

        $('#groupDialog').dialog("option", "title", 'Edit Group');

        $("#groupDialog").dialog('open');
    });
}

function deleteGroup(id) {

    $( "#dialog-confirm" ).dialog({
        resizable: false,
        height:200,
        width: 300,
        modal: true,
        buttons: {
            "Delete group": function() {
                $.ajax({
                    url: "/groups/delete/" + id,
                    type: "POST",
                    success:function(response) {
                        $('#group-'+id).hide()
                    }
                });
                $( this ).dialog( "close" );
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });
}

function resetDialog(form) {

    form.find("input").val("");
}

$(document).ready(function() {

    $('#groupDialog').dialog({

        autoOpen : false,
        position : 'center',
        modal : true,
        resizable : false,
        width : 440,
        buttons : {
            "Save" : function() {
                $('#groupForm').submit();
            },
            "Cancel" : function() {
                $(this).dialog('close');
            }
        },
        close : function() {

            resetDialog($('#groupForm'));

            $(this).dialog('close');
        }
    });
});

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});