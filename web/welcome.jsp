<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type='text/javascript' src='/tc-timer/dwr/engine.js'></script>
<script type='text/javascript' src='/tc-timer/dwr/interface/Timer.js'></script>
<script type='text/javascript' src='/tc-timer/dwr/util.js'></script>

<title>Insert title here</title>
<style>
#remainingTime{
	  font-family: arial;
	  font-size: 24px;
  }
</style>
<script>

function attach(){
	Timer.attach();	
}

/*
var hour = 0;
var mins = 0;
var secs = 0;

startTimer = function(remainingTimeInSeconds) {
	hour = Math.floor(Math.max(remainingTimeInSeconds/(60*60), 0));
	var secsLeft = remainingTimeInSeconds % (60*60);
	mins = Math.floor(Math.max(secsLeft/60, 0));
	secsLeft = secsLeft % 60;
	secs = secsLeft;
	displayRemainingTime();
	setTimeout("updateRemainingTime()", 1000);
}

function updateRemainingTime() {
	var isClosed = false;
	if (secs > 0) secs--;
	else {
		if (mins > 0) {
			secs = 59;
			mins--;
		}
		else {
			if (hour > 0) {
				secs = 59;
				mins = 59;
				hour--;
			}
			else {
				hour = min = sec = 0;
				roundClosed();
				isClosed = true;				
			}
		}
	}
	displayRemainingTime();
	if (!isClosed) setTimeout("updateRemainingTime()", 1000);
}

function displayRemainingTime() {
	var hourStr = hour < 10? "0" + hour: hour;
	var minsStr = mins < 10? "0" + mins: mins;
	var secsStr = secs < 10? "0" + secs: secs;
	var time = hourStr + ":" + minsStr + ":" + secsStr;
	var remainingTimeSpan = document.getElementById('remainingTime');
	remainingTimeSpan.innerHTML = time;
}

function roundClosed() {	
	//TODO: hookup to server side process to do anything that needs to be 
	//done when a round has finished
	//this.document.examForm.submit();
}
*/
</script>
</head>
<body onload="attach()">
<span id="remainingTime">01:57:06</span>
</body>
</html>