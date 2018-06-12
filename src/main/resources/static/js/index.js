	var flock = require("flock-apps-fe/src/flock")
	var userData;
	var baseUrl;
	window.onload = function(){
	    var urlParams = new URLSearchParams(window.location.href);
        baseUrl =  window.location.origin;
	    userData = JSON.parse(urlParams.get('flockEvent'));
	    var button = document.getElementById("button");
	    button.onclick =  function(){
	        var element = document.getElementById("loading");
	        element.classList.add("show");
	        element = document.getElementById("button");
	        element.classList.add("pointer-none");
	        sendPdfFilesToServer();
	    }
	}

	function sendPdfFilesToServer(){
	    var firstPdf = document.getElementById('first-pdf');
	    var secondPdf = document.getElementById('second-pdf');
	    const file1 = firstPdf.files[0];
	    const file2 = secondPdf.files[0];
	    var formData =  new FormData();
	    formData.append('firstPdf', file1, file1.name);
	    formData.append('secondPdf', file2, file2.name);
	    postRequest(formData);
	}

	function postRequest(formData){
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	         flock.close();
	    }
	    };
	    xhttp.open("POST", baseUrl + "/post" + "?userId=" + userData.userId);
	    xhttp.send(formData);
	}
