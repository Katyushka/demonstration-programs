/**
 * Created by ekaterina on 03.05.2017.
 */
$(document).ready(function () {
    $('#users-multiselect').multiselect({
        enableFiltering: true,
        includeSelectAllOption: true,
        selectAllText: 'Выбрать всех',
        buttonWidth: '530px',
        buttonText: function(options, select) {
            if (options.length === 0) {
                return 'Соавтор не выбран ...';
            }
            else if (options.length > 3) {
                return 'Выбрано более 3 соавторов!';
            }
            else {
                var labels = [];
                options.each(function() {
                    if ($(this).attr('label') !== undefined) {
                        labels.push($(this).attr('label'));
                    }
                    else {
                        labels.push($(this).html());
                    }
                });
                return labels.join(', ') + '';
            }
        }
    })
});