<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/path/to/jquery.signaturepad.css" rel="stylesheet" />
<pre class="brush:xml;" style="width: 1709.53px;">
<form method="post" action="" class="sigPad">
  <label for="name">Type your name</label>
  <input type="text" name="name" id="name" class="name">
  <p class="typeItDesc">Review your signature</p>
  <p class="drawItDesc">Draw your signature</p>
  <ul class="sigNav">
    <li class="typeIt"><a href="#type-it" class="current">Type It</a></li>
    <li class="drawIt"><a href="#draw-it" >Draw It</a></li>
    <li class="clearButton"><a href="#clear">Clear</a></li>
  </ul>
  <div class="sig sigWrapper">
    <div class="typed"></div>
    <canvas class="pad" width="198" height="55"></canvas>
    <input type="hidden" name="output" class="output">
  </div>
  <button type="submit">I accept the terms of this agreement.</button>
</form>
</pre>
<!-- jQuery -->
<script src="/path/to/cdn/jquery.min.js"></script>

<!-- json2.js -->
<script src="/path/to/json2.min.js"></script>

<!-- signature pad -->
<script src="/path/to/jquery.signaturepad.js"></script>

<!-- for IE -->
<!--[if lt IE 9]>
  <script src="/path/to/flashcanvas.js"></script>
<![endif]-->

<script>
	$(function(){
	  $('.sigPad').signaturePad();
	});
	// signature data
	var sig = [
	    {"lx":20,"ly":34,"mx":20,"my":34},
	    {"lx":21,"ly":33,"mx":20,"my":34},
	    {"lx":22,"ly":31,"mx":21,"my":33},
	    {"lx":23,"ly":30,"mx":22,"my":31},
	    {"lx":25,"ly":27,"mx":23,"my":30},
	    {"lx":27,"ly":25,"mx":25,"my":27},
	    {"lx":29,"ly":23,"mx":27,"my":25},
	    ...
	]
</script>