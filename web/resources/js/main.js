// displays nice curtain when user is on idle
var idleCurtain = {
    fadeIn: function() {
        $('.ui-dialog').hide();
        $('.ui-widget-overlay').hide().fadeIn('slow');
    }
}

// removal confirmation dialog
var removeDialog = {
    show: function() {
        if (clientTable.selection.length > 0) {
            confirmationDialog.show();
        } else {
            growlMessages.renderMessage({
                summary: 'No client selected',
                detail: 'Please select clients to be removed'
            });
        }
    }
}

// masks all postal-code fields
var postalCodeMasker = {
    mask: function() {
        $(".postal-code").mask("99-999");
    }
}