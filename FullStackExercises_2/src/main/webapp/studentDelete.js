/**
 * 
 */
function deleteStudent() 
{
	let url = "http://localhost:8080/FullStackExercises_2/deleteStudent";
	
	var form = document.forms["formStudent"]; 
 	var requestParameters = "id=" + form["id"].value;    
 	
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
 	alert("Student data deleted!");
 	}	
	else if (status.errorCode === 1) {
 	alert("Student data not deleted. Unknown student id!");
 	} 
 	else {
	alert("The database is temporarily unavailable. Please try again later.");
 	} 
 }
 
function main()
{
	deleteStudent();
}
main();