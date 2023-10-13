const button = document.querySelector("#MoreCourse");
const courseLists = document.querySelector("#CoursesLists");

button.addEventListener("click",()=>{
    let xhr = new XMLHttpRequest();

    xhr.open("GET","http://localhost:8080/Exercise_war_exploded/MoreCourses",true);

    xhr.onreadystatechange = function (){
        if (xhr.readyState === 4 && xhr.status === 200){
            let course = JSON.parse(xhr.responseText);
            display(course)
        }
    }

    xhr.send();
});

function display(courses){
    let courseList = document.querySelector("#CoursesLists");
    courseList.innerHTML = ""; // 清空课程列表

    for (let i = 0; i < courses.length; i++) {
        let course = courses[i];
        let courseElement = document.createElement("p");
        courseElement.textContent = course;
        courseList.appendChild(courseElement);
    }
}