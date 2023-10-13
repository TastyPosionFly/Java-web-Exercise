const imageArray = ["image1.jpg","image2.jpg","image3.jpg"];
let currentIndex = 0;

const imageElement = document.querySelector("#image");
const prevButton = document.querySelector("#prev-button");
const nextButton = document.querySelector("#next-button");

imageElement.src = imageArray[currentIndex];

prevButton.addEventListener("click", () => {
    currentIndex = (currentIndex - 1 + imageArray.length) % imageArray.length;
    imageElement.src = imageArray[currentIndex];
});


nextButton.addEventListener("click", () => {
    currentIndex = (currentIndex + 1) % imageArray.length;
    imageElement.src = imageArray[currentIndex];
});
