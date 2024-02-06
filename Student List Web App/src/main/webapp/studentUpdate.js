/**
 * 
 */
var form = document.forms["formStudent"];
form["id"].disabled = true;
var id = parseInt((window.location.href).split('=')[1], 10);
form["id"].value = id;

function updateStudent() 
{
	let url = "http://localhost:8080/WebAppAssignment/updateStudent";
	
	form["id"].disabled = true;
	form["id"].value = updateStudentTable.id;
		
	var fname = String((window.location.href).split('=')[1], 10);
	form["fname"].value = fname;
	
	form["fname"].value = updateStudentTable.lname;
	form["address"].value = updateStudentTable.address;
 	form["postcode"].value = updateStudentTable.postcode;
	form["postoffice"].value = updateStudentTable.postoffice;

 	
 	let requestOptions = {
 	method: "POST", 
 	headers: {"Content-Type": "application/x-www-form-urlencoded"},
 	body: form
 	};
 
 	fetch(url, requestOptions) 
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
			 	throw "HTTP status code is " + response.status;
			}
		})
 		.then(status => processResponseStatus(status))
 		.catch(errorText => alert("Fetch failed: " + errorText));
}


function processResponseStatus(status) { 
 	if (status.errorCode === 0) {
 	alert("Student data updated.");
 	}	
 	else {
	alert("The database is temporarily unavailable. Please try again later.");
 	} 
 }
 
function cancel() {
      window.location.href = 'studentList.html';
    }
 
 
function main()
{
	updateStudent();
}
main();
 