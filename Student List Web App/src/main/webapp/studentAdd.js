/**
 * 
 */
function addStudent() 
{
	let url = "http://localhost:8080/WebAppAssignment/studentAdd";
	
 	var form = document.forms["formStudent"]; 
 	var requestParameters =      
 	"id=" + form["id"].value +     
 	"&fname=" + form["fname"].value +     
 	"&lname=" + form["lname"].value +    
 	"&address=" + form["address"].value +
 	"&postcode=" + form["postcode"].value +
 	"&postoffice=" + form["postoffice"].value;
 	
 	   // Adding basic input validation
    if (!form["id"].value.trim()) {
        alert("Please enter a student ID.");
        return;
    }
 	
 	let requestOptions = {
 	method: "POST", 
 	headers: {"Content-Type": "application/x-www-form-urlencoded"},
 	body: requestParameters
 	};
 
 	fetch(url, requestOptions, requestParameters) 
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
 	alert("Student data added.");
 	}	
	else if (status.errorCode === 1) {
 	alert("Cannot add the student. The id is already in use!");
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
	addStudent();
}

main();
 