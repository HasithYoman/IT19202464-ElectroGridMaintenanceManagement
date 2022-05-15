//Hide the alters on page load
$(document).ready(function()
{

	$("#alertSuccess").hide();

 	$("#alertError").hide();

}); 

$(document).on("click", "#btnSave", function(event)
{
	console.log($("#hidMaintenanceIDSave").val());
	
	// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();

// Form validation-------------------
var status = validateMaintenanceForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
 var type = ($("#hidMaintenanceIDSave").val() == "") ? "POST" : "PUT";
console.log(type); 
 $.ajax(
 {
 url : "MaintenanceAPI",
 type : type,
 data : $("#formMaintenance").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onInquirySaveComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateMaintenanceForm()
{
// name
if ($("#name").val().trim() == "")
 {
 return "Insert name.";
 }
// area
if ($("#area").val().trim() == "")
 {
 return "Insert area.";
 }
// place
if ($("#place").val().trim() == "")
 {
 return "Insert place.";
 }

// time
if ($("#time").val().trim() == "")
 {
 return "Insert time.";
 }
// status
if ($("#status").val().trim() == "")
 {
 return "Insert status.";
 }
return true;
}

$(document).on("click", ".btnUpdate", function()
{
 $("#hidMaintenanceIDSave").val($(this).data("maintenanceID"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#area").val($(this).closest("tr").find('td:eq(1)').text());
 $("#place").val($(this).closest("tr").find('td:eq(2)').text());
 $("#time").val($(this).closest("tr").find('td:eq(3)').text());
 $("#status").val($(this).closest("tr").find('td:eq(4)').text());
});

function onMaintenanceSaveComplete(response, status)
{
	if (status == "success")
 	{
 		var resultSet = JSON.parse(response);
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
			
 			$("#divItemsGrid").html(resultSet.data);
			
 		} else if (resultSet.status.trim() == "error")
 			{
			 	$("#alertError").text(resultSet.data);
 			 	$("#alertError").show();
 			}	
 	} else if (status == "error")
 		{
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 		} else
 			{
 				$("#alertError").text("Unknown error while saving..");
 				$("#alertError").show();
 			} 
		
 $("#hidMaintenanceIDSave").val("");
 $("#formMaintenance")[0].reset();
}


$(document).on("click", ".btnRemove", function()
{
	var id = $(this).data("maintenanceid");
	console.log("id is :"+id)
 $.ajax(
 {
 url : "MaintenanceAPI",
 type : "DELETE",
 data : "maintenanceID=" + id,
 dataType : "text",
 complete : function(response, status)
 {
	console.log(id)
 onMaintenanceDeleteComplete(response.responseText, status);
 }
 });
});

function onMaintenanceDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divMaintenanceGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
	$("#hidMaintenanceIDSave").val(""); 
	$("#formMaintenance")[0].reset(); 
}









