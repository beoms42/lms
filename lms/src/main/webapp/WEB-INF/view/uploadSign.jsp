<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	    <canvas id="jsCanvas" class="canvas" style="border:1px solid black"></canvas>
	    <div class="controls">
		<form action="${pageContext.request.contextPath}/saveImage" method="post" enctype="multipart/form-data">
	        <div class="controls__btns" >
	            <button id="jsSave" >Save</button>
	        </div>
	    </form>
	    </div>

</body>
<script>   
const canvas = document.getElementById("jsCanvas");
const ctx = canvas.getContext("2d");
const colors = document.getElementsByClassName("jsColor");
const range = document.getElementById("jsRange");
const mode = document.getElementById("jsMode");
const saveBtn = document.getElementById("jsSave");

const INITIAL_COLOR = "#2c2c2c";
const CANVAS_SIZE = 700;


//컨버스 크기
canvas.width = CANVAS_SIZE;
canvas.height = CANVAS_SIZE;


ctx.fillStyle = "white";
ctx.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
ctx.strokeStyle = INITIAL_COLOR; // 우리가 그릴 선들은 모두 이 색을 갖는다
ctx.fillStyle = INITIAL_COLOR;
ctx.lineWidth = 5; // 라인의 너비가 2.5

let painting = false; 
let filling = false; // 기본값은 안채워져있음

function stopPainting() {
    painting = false;
  }

function startPainting() {
    painting = true;
}

function onMouseMove(event){
    const x = event.offsetX;
    const y = event.offsetY;
    if (!painting) {
        ctx.beginPath(); // path는 선이다, path를 만들면 마우스의 x,y, 좌표로 path를 옮긴다
        ctx.moveTo(x, y);
      } else {
        ctx.lineTo(x, y); // lineTo는 path의 이전 위치에서 지금 위치까지 선을 만드는 것
        ctx.stroke();
      } // lineTo()와 stroke()는 마우스를 움직이는 내내 발생한다! 마우스를 움직이는 동안 계속 발생한다!
}



function onMouseUp(event){
    painting = false;
}

function onMouseLeave(event){
    painting = false;
}

function handleRangeChange(event) {
    const size = event.target.value;
    ctx.lineWidth = size;
  }

function handleColorClick(event) {
    const color = event.target.style.backgroundColor;
    ctx.strokeStyle = color; // strokeStyle이 target에 있는 색상이 된다!
    ctx.fillStyle = color;
  }

  function handleCanvasClick() {
    if (filling) {
      ctx.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
    }
  }

  function handleCM(event) {
	  
    event.preventDefault();
  } 


  function uploadCanvasToServer(){
	  const canvas = document.getElementById('jsCanvas');
	  const imgBase64 = canvas.toDataURL('image/PNG');
	  const decodImg = atob(imgBase64.split(',')[1]);
	  

	  let array = [];
	  for (let i = 0; i < decodImg .length; i++) {
	    array.push(decodImg .charCodeAt(i));
	
	    
	  }

	  const file = new Blob([new Uint8Array(array)], {type: 'image/PNG'});
	  const fileName = 'canvas_img_' + new Date().getMilliseconds() + '.PNG';
	  let formData = new FormData();
	  formData.append('file', file, fileName);
	  
	  $.ajax({
		    type: 'post',
		    url: '/lms/saveImage',
		    cache: false,
		    data: formData,
		    processData: false,
		    contentType: false,
		    success: function (data) {
		      alert('Uploaded !!')
		    }
		  })
	}
  
	if(canvas){
	    canvas.addEventListener("mousemove", onMouseMove);
	    canvas.addEventListener("mousedown", startPainting);
	    canvas.addEventListener("mouseup", stopPainting);
	    canvas.addEventListener("mouseleave", stopPainting);
	    canvas.addEventListener("click", handleCanvasClick);
	    canvas.addEventListener("contextmenu", handleCM);
	}
	
	Array.from(colors).forEach(color =>
	    color.addEventListener("click", handleColorClick)
	  );
	  
	if (range) {
	    range.addEventListener("input", handleRangeChange);
	}
	
	if (mode) {
	    mode.addEventListener("click", handleModeClick);
	  }
	
	if (saveBtn) {
		saveBtn.addEventListener("click",uploadCanvasToServer);
	}
	

	</script>
</html>