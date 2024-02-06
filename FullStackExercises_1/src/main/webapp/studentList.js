/**
 * Full Stack Exercise 1
 */

 function studentList() {
	fetch("http://localhost:8080/FullStackExercises_1/students")
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
        </tr>`
	}).join('');
}

 
 function main() { // Define the main function
    studentList();
}
 
 main();
 