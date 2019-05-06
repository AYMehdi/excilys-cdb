$(document).ready(function() {
	$("#errComputerName").hide();
	$("#errIntroduced").hide(); // Initially hiding the error spans
	$("#errDiscontinued").hide();
	$("#btnEditOrAddComputer").click(function() {
		var name = $("#computerName").val();
		var introduced = $("#introduced").val();
		var discontinued = $("#discontinued").val();

		var dateFormat = /(^$|[0-9]{4}[/][0-9]{2}[/][0-9]{2}$)/;

		if (name == null || name == "") {
			$("#errComputerName").show();
			return false;
		}

		if (introduced == null || !(introduced.match(dateFormat))) {
			$("#errIntroduced").show();
			return false;
		}

		if (discontinued == null || !(discontinued.match(dateFormat))) {
			$("#errDiscontinued").show();
			return false;
		}

		return true;
	});

	$("#computerName").change(function() {
		$("#errComputerName").hide();
		return true;
	});

	$("#introduced").change(function() {
		$("#errIntroduced").hide();
		return true;
	});

	$("#discontinued").change(function() {
		$("#errDiscontinued").hide();
		return true;
	});

});