var paragraphURL = "http://localhost:8181/searchie_interface/rest/api/paragraph";
var accessURL = "http://localhost:8181/searchie_interface/rest/api/access";
var queryURL = "http://localhost:8181/searchie_interface/rest/api/query";
//var rootURL = "http://brooloo.cs.umass.edu:8080/searchie_interface/rest/api"
//var paragraphURL = "http://brooloo.cs.umass.edu:8080/searchie_interface/rest/api/paragraph";
//var accessURL = "http://brooloo.cs.umass.edu:8080/searchie_interface/rest/api/access";
//var queryURL = "http://brooloo.cs.umass.edu:8080/searchie_interface/rest/api/query";

	
$('#passcode_id').val('');

$('#myTags').tagit();

$("#postBtn").on("click", function() {
	var passcode = $('#passcode_id').val();
	if (!passcode) {
		refreshPage('Please enter passcode');
	} 
	$('#passcode_id').val('');
	accessCheck(passcode);
});

function refreshPage(message) {
	if (message) {
		alert(message);
	}
	window.location.reload(false);
}

function accessCheck(passcode) {
	console.log('accessCheck');
	if (!passcode) {
		refreshPage('Please enter passcode');
	}
	
	$('#passcode_hidden_id').val(passcode);
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : accessURL,
		dataType : "json",
		data : passcode,
		success : function(data, textStatus, jqXHR) {
			if (data) {
				showParagraph(passcode);
			} else {
				refreshPage('Cannot recognize given passcode. Please enter a valid passcode.');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			refreshPage('Error while checking passcode: ' + textStatus);
		}
	});
}

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

function showParagraph(passcode) {
	console.log('showParagraph');
	if (!passcode) {
		refreshPage('Invalid passcode. Please enter a valid passcode.');
	}
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : paragraphURL,
		//dataType : "json", // data type of response
		data : passcode,
		success : function(data, textStatus, jqXHR) {
			renderList(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			refreshPage('Error while fetching paragraph: ' + textStatus);
		}
	});
}

function searchQuery() {
	console.log('searchQuery');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : queryURL,
		//dataType : "json",
		data : prepareQuery(),
		success : function(data, textStatus, jqXHR) {
			if (!data || !data.description) {
				refreshPage('No pragraph content found. Please try again');
			} else if (!data.passcode) {
				refreshPage('No passcode found. Please try again.');
			} else {
				$('#myTags li:not(:last)').remove();
			}
			showParagraph(data.passcode);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			refreshPage('Query error: ' + textStatus);
		}
	});
}

var paragraph_id;
function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$.each(list, function(index, paragraph) {
		if (!paragraph) {
			refreshPage('Cannot fetch any paragraph. Please try again.');
		}
		if (!paragraph.passcode) {
			refreshPage('Invalid request. Please enter passcode and start new query');
		}
		if (!paragraph.description) {
			refreshPage('No paragraph content found. Please try again.');
		}
		if (paragraph.passcode != $("#passcode_hidden_id").val()) {
			refreshPage('Invalid passcode. Please provide valid passcode and try again');
		}
		$('p.clickable').text(paragraph.description);
		$('.clickable').lettering('words');
		$('p.instructions').text(paragraph.instructions);
		paragraph_id = paragraph.id;
	});
}

// Helper function to serialize all the form fields into a JSON string
function prepareQuery() {
	var selections = [paragraph_id];
	$(".tagit-label").each(function() { selections.push($(this).text()) });
	var json_data = JSON.stringify({
		"param": selections.join("\t"),
		"passcode" : $('#passcode_hidden_id').val()
	});
	
	return json_data;
}

$("#myTags").on("click", "span.text-icon", function() {
	$(this).closest('.tagit-choice').remove();
});

var p = $('.clickable');
p.css({ cursor: 'pointer' });

function getExistingTags() {
    var listElement =document.getElementsByClassName("the-word-here");
    var tempArray = [];
    for(var i=0;i<listElement.length; i++){
        tempArray.push(listElement[i].childNodes[0].data);
    }
    return tempArray;
}

/*p.on('click', function(e) {
    var range = window.getSelection() || document.getSelection() || document.selection.createRange();
    var word = $.trim(range.toString());
    
    if(word != '') {
        word = '<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable"><span class="tagit-label">' + word + '</span><a class="tagit-close"><span class="text-icon">\xd7</span><span class="ui-icon ui-icon-close"></span></a><input value="' + word + '" name="tags" class="tagit-hidden-field" type="hidden"></li>';
    	var targetChar = ' ';
    	var tkeyCode = targetChar.charCodeAt(0);
    	
    	$('#myTags').trigger({type: 'keypress', which: tkeyCode, keyCode: tkeyCode}).prepend(word);
    }
    
    e.stopPropagation();
    
});*/

/*p.on('click', function (e) {
    var range = window.getSelection() || document.getSelection() || document.selection.createRange();
    var word = $.trim(range.toString());
    if (word != '') {
        var words = getExistingTags();
        words.push(word);
        words.sort();
        console.log(words);
        $('#myTags').empty();
        for(var i=0;i<words.length;i++){
            var wordElm = '<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable"><span class="tagit-label the-word-here">' + words[i] + '</span><a class="tagit-close"><span class="text-icon">\xd7</span><span class="ui-icon ui-icon-close"></span></a><input value="' + words[i] + '" name="tags" class="tagit-hidden-field" type="hidden"></li>';
            var targetChar = ' ';
            var tkeyCode = targetChar.charCodeAt(0);

            $('#myTags').trigger({type: 'keypress', which: tkeyCode, keyCode: tkeyCode}).prepend(wordElm);
        }

    }

    e.stopPropagation();

});*/


p.on('click', function (e) {
    var range = window.getSelection() || document.getSelection() || document.selection.createRange();
    var word = $.trim(range.toString());
    if (word != '') {
        var words = getExistingTags();
        words.push(word);
        words.sort();
        $('#myTags').empty();
        var prevWord;
        for (var i = 0; i < words.length; i++) {
            var wordElm = '<span class="tagit-choice ui-widget-content ui-state-default ui-corner-all tagit-choice-editable">' +
                '<button class="tagit-label the-word-here">' + words[i] + '</button><a class="tagit-close"><span class="text-icon">\xd7</span><span class="ui-icon ui-icon-close" style="cursor: pointer"></span></a><input value="' + words[i] + '" name="tags" class="tagit-hidden-field" type="hidden"></span>';
            var targetChar = ' ';
            var tkeyCode = targetChar.charCodeAt(0);
            if (prevWord != words[i]) {
                $('#myTags').prepend('<br/>');
            }
            $('#myTags').trigger({type: 'keypress', which: tkeyCode, keyCode: tkeyCode}).prepend(wordElm);

            prevWord = words[i];
        }

    }

    e.stopPropagation();

});