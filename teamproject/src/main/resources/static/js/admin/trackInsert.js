const imgInput = document.querySelector(".file input");
const imgOutput = document.querySelector(".img");
let imagesArray = [];

imgInput.addEventListener("change", function () {
	const file = imgInput.files;
	imagesArray.push(file[0]);
	displayImages();
	function displayImages() {
		let images = "";
		imagesArray.forEach((image, index) => {
			images =
				images +
				`<img src="${URL.createObjectURL(
					image
				)}" alt="image" style="width:125px; hegiht:125px;">`;
		});
		imgOutput.innerHTML = images;
	}

	deleteImage();
});

function deleteImage(index) {
	imagesArray.splice(index, 1);
	displayImages();
}
