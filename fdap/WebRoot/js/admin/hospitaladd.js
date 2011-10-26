
/**
*	check submit valid for add or edit.
*   @author zhaoyou
**/
function checkSubmit() {

	if ($('#hospitalName').val() =="") {
		alert('医院信息必填 ！');
		return ;
	}
	
	if ($('#projectId').val() =="") {
		alert('工程信息必填 ！');
		return ;
	}
	
	if ($('#latitude').val() =="") {
		alert('纬度信息必填 ！');
		return ;
	} else {
		if (isNaN($('#latitude').val())) {
			alert("纬度信息必须是个数字");
			return ;
		}
	}
	
	if ($('#longitude').val() =="") {
		alert('经度信息必填 ！');
		return ;
	} else {
		if (isNaN($('#longitude').val())) {
			alert("经度信息必须是个数字");
			return ;
		}
	}
	
	$('#myform').submit();
}

$(function(){
	$('#saveButton').click(checkSubmit);
});