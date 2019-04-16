//On load

$(function() {
    // Default: hide edit and delete modes
    $(".editMode").hide();
    $("#deleteComputer").hide();
    
    // Click on "selectall" box
    $("#selectall").click(function () {
        $('.cb-display').prop('checked', this.checked);
        console.log($('.cb-display').length);
    });

    // Click on a checkbox
    $(".cb").click(function() {
        if ($(".cb").length == $(".cb:checked").length) {
            $("#selectall").prop("checked", true);
        } else {
            $("#selectall").prop("checked", false);
        }
        if ($(".cb:checked").length != 0) {
            $("#deleteSelected").enable();
        } else {
            $("#deleteSelected").disable();
        }
    });

});


// Function setCheckboxValues
(function ( $ ) {

    $.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

        var str = $('.' + checkboxFieldName + ':checked').map(function() {
            return this.value;
        }).get().join();
        
        $(this).attr('value',str);
        
        return this;
    };

}( jQuery ));

// Function toggleEditMode
(function ( $ ) {

    $.fn.toggleEditMode = function() {
        if($(".editMode").is(":visible")) {
            $(".editMode").hide();
            $("#editComputer").text("Edit");
            $("#deleteComputer").hide();
        }
        else {
            $(".editMode").show();
            $("#editComputer").text("View");
            $("#deleteComputer").show();
        }
        return this;
    };

}( jQuery ));


// Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ( $ ) {
    $.fn.deleteSelected = function() {
        if (confirm("Are you sure you want to delete the selected computers?")) { 
            $('#deleteForm input[name=selection]').setCheckboxValues('selection','cb');
            $('#deleteForm').submit();
        }
    };
}( jQuery ));



//Event handling
//Onkeydown
$(document).keydown(function(e) {

    switch (e.keyCode) {
        //DEL key
        case 46:
            if($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
                displayDeleteAlert();
            }   
            break;
        //E key (CTRL+E will switch to edit mode)
        case 69:
            if(e.ctrlKey) {
                $.fn.toggleEditMode();
            }
            break;
    }
});

// Delete selected computers
function postDeleteForm() {
	
	$("#deleteForm").submit();
	
}

//Hide delete alert and edit mode
function hideDeleteAlert() {
	
    $("#deleteAlert").hide();
    $.fn.toggleEditMode();

}

// Display delete alert
function displayDeleteAlert() {
	
	var checkboxes = document.getElementsByClassName("cb");
	var count = 0;
	
	for (var i = 0; i < checkboxes.length; i++) {	
		if (checkboxes[i].checked == true){
			count++;
		}
	}
	
	var numbberOfSelectedComputers = count;
	
	$("#numberOfSelectedComputers").text(count);
	
	if (count > 0){
		$("#deleteAlert").show();
		$('html, body').animate({ scrollTop: 0 }, 'fast');
	}

}


