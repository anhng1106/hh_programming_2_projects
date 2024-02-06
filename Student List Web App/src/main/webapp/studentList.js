/**
 * WebAppAssignment
 */

 function studentList() {
	fetch("http://localhost:8080/WebAppAssignment/students")
		.then(response => {
			if (response.ok)
			{
				return response.json();
			} 
			else 
			{
				throw "HTTP status code is " + response.status;
			}
		})
		
        .then(studentList => 
        {
			console.log(studentList); // Add this line to check the structure
			updateStudentTable(studentList) // Updated to call updateStudentTable
		})
        
		.catch(errorText => console.error("Fetch failed: " + errorText));
 }
 
 function updateStudentTable(studentList) {
    let tbodyStudents = document.getElementById('tbodyStudents');
    tbodyStudents.innerHTML = studentList.map(student => 
    {
		 // Ensure the postcode is treated as a string and retains leading zeros
        let formattedPostcode = student.postcode.toString().padStart(5, '0');
        return `<tr>
            <td>${student.id}</td>
            <td>${student.firstname}</td>
            <td>${student.lastname}</td>
            <td>${student.streetaddress}</td>
            <td>${formattedPostcode}</td>
            <td>${student.postoffice}</td>
            <td>${createUpdateDeleteLinks(student.id)}</td>
        </tr>`
	}).join('');
}

function createUpdateDeleteLinks(id) {
 	return "<span class='link' onclick='updateStudent(" + id + ");'>Update</span>" + 
 			"&nbsp;&nbsp;" + 
			"<span class='link' onclick='deleteStudent(" + id + ");'>Delete</span>";
}

function updateStudent(id) {
	location.replace("studentUpdate.html?id=" + id);
}

function deleteStudent(id) 
{
  const confirmDelete = confirm("Delete student " + id + "?");
  
  if (confirmDelete==true) 
  {
	    let url = "http://localhost:8080/WebAppAssignment/deleteStudent";
	    
	    let requestParameters = "id=" + id;

	    let requestOptions = 
	    {
	        method: "POST",
	        headers: {"Content-Type": "application/x-www-form-urlencoded"},
	        body: requestParameters
	    };

		fetch(url, requestOptions) 
		.then(response => {
			if (response.ok) {	
        		alert("Student " + id + " deleted successfully.");
        		studentList(); // Refresh the list after deletion
      		} else {
        		alert("Error deleting student: " + data.message);
      		}	
   		})
      	.catch((error) => {
        	console.error("Fetch error: " + error); // Proper error logging
  		});
  }
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

 function main() { // Define the main function
    studentList();
}
 
 main();
 
 