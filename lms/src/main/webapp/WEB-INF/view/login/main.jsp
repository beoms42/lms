<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>LMS-TFT</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/tftace.jpg" />
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <jsp:include page="/inc/topbar.jsp"/>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <jsp:include page="/inc/sidebar.jsp"/>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-5 grid-margin stretch-card">
              <div class="card tale-bg">
                <div class="card-people mt-auto">
                  <img src="${pageContext.request.contextPath}/images/dashboard/people.svg" alt="people">
                  <div class="weather-info">
                    <div class="d-flex">
                      <div>
                         <h4>${year}년 ${month}월 ${day}일 ${dayOfWeek}</h4><br>
                         <div style="display: flex;">
                            <h2 class="mb-0 font-weight-normal"><span id="weather"></span><span id="tmp"></span><sup>ºC</sup></h2>
                            <div style="display: block;">
                              <h4 class="location font-weight-normal float-right">금천구</h4>
                              <br>
                              <h4 class="font-weight-normal float-right">가산동</h4>
                           </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
               <div class="job-info">
             <button type="button" id="button">Button</button>
      		
		                        <div id="job" ></div>
                    <a href="" id="list"></a>
            </div>
 

        
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2021.  Premium <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap admin template</a> from BootstrapDash. All rights reserved.</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="ti-heart text-danger ml-1"></i></span>
          </div>
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Distributed by <a href="https://www.themewagon.com/" target="_blank">Themewagon</a></span> 
          </div>
        </footer> 
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>   
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="${pageContext.request.contextPath}/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="${pageContext.request.contextPath}/vendors/chart.js/Chart.min.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="${pageContext.request.contextPath}/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="${pageContext.request.contextPath}/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="${pageContext.request.contextPath}/js/off-canvas.js"></script>
  <script src="${pageContext.request.contextPath}/js/hoverable-collapse.js"></script>
  <script src="${pageContext.request.contextPath}/js/template.js"></script>
  <script src="${pageContext.request.contextPath}/js/settings.js"></script>
  <script src="${pageContext.request.contextPath}/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
  <script src="${pageContext.request.contextPath}/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
  <script type="text/javascript">
        $.ajax({
         url : '/lms/weather'
         , type : 'get'
         , timeout : 30000
         , contentType : 'application/json'
         , dataType : 'json'
         , success : function(data, status, xhr) {
            let dataHeader = data.result.response.header.resultCode;
            if(dataHeader == '00') {
               console.log('success ==>');
               console.log(data);
               let arr = data.result.response.body.items.item;
               
               
               if(arr[7].fcstValue >= 80) { // 습도가 80퍼 이상일 때
                  if(arr[6].fcstValue == 0) { // 비가 아닐 때
                     if(arr[5].fcstValue == 1) { // 맑음 상태일 때
                        $('#weather').append('<i class="icon-sun mr-2" width="20"></i>');
                     } else { // 흐림 상태일 때
                        $('#weather').append('<i class="icon-cloud mr-2" width="20"></i>');
                     }
                  } else if(arr[6].fcstValue == 1) { // 비 상태일 때
                     $('#weather').append('<i class="icon-umbrella mr-2" width="20"></i>');
                  } else if(arr[6].fsctValue == 2 || arr[6].fsctValue == 3) { // 비/눈 or 눈/비 상태일 때
                     if(arr[0].fcstValue < -5) { // 기온이 -5도 이하일 때
                        $('#weather').append('<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-snow" viewBox="0 0 16 16"><path d="M8 16a.5.5 0 0 1-.5-.5v-1.293l-.646.647a.5.5 0 0 1-.707-.708L7.5 12.793V8.866l-3.4 1.963-.496 1.85a.5.5 0 1 1-.966-.26l.237-.882-1.12.646a.5.5 0 0 1-.5-.866l1.12-.646-.884-.237a.5.5 0 1 1 .26-.966l1.848.495L7 8 3.6 6.037l-1.85.495a.5.5 0 0 1-.258-.966l.883-.237-1.12-.646a.5.5 0 1 1 .5-.866l1.12.646-.237-.883a.5.5 0 1 1 .966-.258l.495 1.849L7.5 7.134V3.207L6.147 1.854a.5.5 0 1 1 .707-.708l.646.647V.5a.5.5 0 1 1 1 0v1.293l.647-.647a.5.5 0 1 1 .707.708L8.5 3.207v3.927l3.4-1.963.496-1.85a.5.5 0 1 1 .966.26l-.236.882 1.12-.646a.5.5 0 0 1 .5.866l-1.12.646.883.237a.5.5 0 1 1-.26.966l-1.848-.495L9 8l3.4 1.963 1.849-.495a.5.5 0 0 1 .259.966l-.883.237 1.12.646a.5.5 0 0 1-.5.866l-1.12-.646.236.883a.5.5 0 1 1-.966.258l-.495-1.849-3.4-1.963v3.927l1.353 1.353a.5.5 0 0 1-.707.708l-.647-.647V15.5a.5.5 0 0 1-.5.5z"/></svg>');
                     } else { // 기온이 -5도 이상일 때
                        $('#weather').append('<i class="icon-umbrella mr-2" width="20"></i>');
                     }
                  } else { // 눈 상태일때
                     $('#weather').append('<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-snow" viewBox="0 0 16 16"><path d="M8 16a.5.5 0 0 1-.5-.5v-1.293l-.646.647a.5.5 0 0 1-.707-.708L7.5 12.793V8.866l-3.4 1.963-.496 1.85a.5.5 0 1 1-.966-.26l.237-.882-1.12.646a.5.5 0 0 1-.5-.866l1.12-.646-.884-.237a.5.5 0 1 1 .26-.966l1.848.495L7 8 3.6 6.037l-1.85.495a.5.5 0 0 1-.258-.966l.883-.237-1.12-.646a.5.5 0 1 1 .5-.866l1.12.646-.237-.883a.5.5 0 1 1 .966-.258l.495 1.849L7.5 7.134V3.207L6.147 1.854a.5.5 0 1 1 .707-.708l.646.647V.5a.5.5 0 1 1 1 0v1.293l.647-.647a.5.5 0 1 1 .707.708L8.5 3.207v3.927l3.4-1.963.496-1.85a.5.5 0 1 1 .966.26l-.236.882 1.12-.646a.5.5 0 0 1 .5.866l-1.12.646.883.237a.5.5 0 1 1-.26.966l-1.848-.495L9 8l3.4 1.963 1.849-.495a.5.5 0 0 1 .259.966l-.883.237 1.12.646a.5.5 0 0 1-.5.866l-1.12-.646.236.883a.5.5 0 1 1-.966.258l-.495-1.849-3.4-1.963v3.927l1.353 1.353a.5.5 0 0 1-.707.708l-.647-.647V15.5a.5.5 0 0 1-.5.5z"/></svg>');
                  }
               } else { // 습도가 80퍼 미만일 때
                  if(arr[5].fcstValue == 1) { // 맑음 상태일 때
                     $('#weather').append('<i class="icon-sun mr-2" width="20"></i>');
                  } else { // 흐림 상태일 때
                     $('#weather').append('<i class="icon-cloud mr-2" width="20"></i>');
                  }
               }
               
               
               for(let i=0; i<arr.length; i++) {
                  if(arr[i].category == 'TMP') {
                     $('#tmp').append(arr[i].fcstValue);
                  }
               }
            } else {
               console.log('fail ==>');
               console.log(data);
            }
         }
         , error : function(e, status, xhr, data) {
            console.log('error ==>');
            console.log(e);
         }
      });
    	$('#button').click(function() {
    		$('#job').empty();
    		  var xhr = new XMLHttpRequest();
    		  var url = '${pageContext.request.contextPath}'; /*URL*/
    		  xhr.open('GET', url);
    		  xhr.onreadystatechange = function () {
    		  if (this.readyState == xhr.DONE) { // <== 정상적으로 준비되었을때
    		  if(xhr.status == 200||xhr.status == 201){ // <== 호출 상태가 정상적일때
    		  alert('Status: '+this.status+
    		  '\nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+
    		  '\nBody: '+this.responseText);
    		   }
    		}
    	}

    		  
    		  $.ajax({
  				type:'get'
  				, url : url+'/adRestController'
  				, success:function(a){
  					console.log(typeof(a));
  					console.log(a);
  						var a2 = JSON.parse(a);
  						console.log(a2);
  						  var arr = a2.GetJobInfo.row; 
  						  
  						//let arr = a2.results.JO_REGIST_NO;
  						console.log(arr);
  						for(let i=0; i<arr.length; i++) {
  							// console.log(arr[i].CMPNY_NM);
  						     // $('#list').append('<div>'+arr[i].RCRIT_JSSFC_CMMN_CODE_SE+'</div>'); 
  						     
  						     if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE >= 130000 && arr[i].RCRIT_JSSFC_CMMN_CODE_SE < 140000) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  								}
  							/* 
  							if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE == 133201) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  							} else if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE == 132000) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  							} else if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE == 415504) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  							} else if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE == 131203) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  							} else if(arr[i].RCRIT_JSSFC_CMMN_CODE_SE == 134102 ) {
  								$('#job').append('<div>'+arr[i].CMPNY_NM+'</div>');
  							} */
  						}
  				}

    			})
    		  //xhr.send('')
    	  	});
        
  </script>
</body>

</html>
