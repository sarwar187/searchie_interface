// The root URL for the RESTful services
var rootURL = "http://localhost:8181/searchie_interface/rest/api";

// Retrieve paragraph when application starts
//application start howar shathe shathe paragraph dekhailo. showParagraph() er definition ta niche likha ase. 
showParagraph();

// -------------------------------
// Minimal
// -------------------------------
$('#myTags').tagit();

// Trigger search when pressing 'Return' on search key input field
$('#myTags').keypress(function(e) {
	if (e.which == 13) {
		search($('#myTags').val());
		e.preventDefault();
		return false;
	}
});

var form = document.getElementById("paragraph_form");

document.getElementById("query").addEventListener("click", function () {
  searchQuery();
});

//showParagraph() function ta successful hoile renderList() function ke call dibe. rootURL a showParagraph() function
//ta ase, ebong oitar against a GET method ta ase. so type hoilo GET and dataType hoilo json. 
function showParagraph() {
	console.log('showParagraph');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", // data type of response
		success : renderList
	});
}

function searchQuery() {
	console.log('searchQuery');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL,
		dataType : "json",
		data : prepareQuery(),
		success : function(data, textStatus, jqXHR) {
			alert('Query executed successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('Query error: ' + textStatus);
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, paragraph) {
		$('.clickable').append(paragraph.description);
		$('.clickable').lettering('words');
	});
//	$.each(list, function(index, paragraph) {
//		$.append(paragraph.description);
//		$.lettering('words');
//	});
	
}

// Helper function to serialize all the form fields into a JSON string
function prepareQuery() {
	var selections = [];
	$(".tagit-label").each(function() { selections.push($(this).text()) });
	var json_data = JSON.stringify({
		"param": selections.join("<$@4M@4>")
	});
	alert("query params: [" + json_data + "]");
	
	return json_data;
}

$("#myTags").on("click", "span.text-icon", function() {
	$(this).closest('.tagit-choice').remove();
});

var p = $('.clickable');
p.css({ cursor: 'pointer' });

p.on('click', function(e) {
    var range = window.getSelection() || document.getSelection() || document.selection.createRange();
    var word = $.trim(range.toString());
    
    if(word != '') {
        word = '<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable"><span class="tagit-label">' + word + '</span><a class="tagit-close"><span class="text-icon">\xd7</span><span class="ui-icon ui-icon-close"></span></a><input value="' + word + '" name="tags" class="tagit-hidden-field" type="hidden"></li>';
    	var targetChar = ' ';
    	var tkeyCode = targetChar.charCodeAt(0);
    	
    	$('#myTags').trigger({type: 'keypress', which: tkeyCode, keyCode: tkeyCode}).prepend(word);
    }
    
    e.stopPropagation();
    
});
