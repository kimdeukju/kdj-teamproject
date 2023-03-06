let filters = document.querySelectorAll(".filters");

function changeSelect(event) {
	// console.log(event.target.value);
	let s1;
	let s2;
	let s3;

	filters.forEach((el, idx) => {
		if (idx == 0) s1 = el.children[0].value;
		if (idx == 1) s2 = el.children[0].value;
		if (idx == 2) s3 = el.children[0].value;
	});
}
//function searchTracksAjax() {
//	const listNumber = document.querySelector("#listNumber").value;
//
//	$.ajax({
//		type: "get", // GET 방식으로 요청한다.
//		url: `/trackList?page=${listNumber}`, // 해당 URL에 요청을 보낸다.
//		data: {}, // 요청하면서 함께 줄 데이터 (GET 요청시엔 비워둔다)
//
//		success: function (res) {
//			// 성공하면 서버에서 준 결과를 response라는 변수에 담는다.
//			// response가 왔을 때 실행될 함수를 작성해준다.
//			console.log("성공");
//		},
//		// res -> 서버에서 전송 Data
//		error: function () {
//			// 실패
//			console.log("실패");
//		},
//	});
//}
//
//document.querySelectorAll(".pagingNum").forEach((el, idx) => {
//	el.addEventListener("click", searchTracksAjax);
//});
