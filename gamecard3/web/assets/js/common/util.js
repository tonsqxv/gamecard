
function trim(str){ //删除左右两端的空格
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function formatDate(fdate,formatStr)
{
	 var fTime, fStr = 'ymdhis';
	 if (!formatStr)
	  formatStr= "y-m-d h:i:s";
	 if (fdate)
	  fTime = new Date(fdate);
	 else
	  fTime = new Date();
	 var formatArr = [
	 fTime.getFullYear().toString(),
	 (fTime.getMonth()+1)<10?("0"+(fTime.getMonth()+1)):(fTime.getMonth()+1).toString(),
	 (fTime.getDate())<10?("0"+(fTime.getDate())):(fTime.getDate()).toString(),
	 (fTime.getHours())<10?("0"+(fTime.getHours())):(fTime.getHours()).toString(),
	 (fTime.getMinutes())<10?("0"+(fTime.getMinutes())):(fTime.getMinutes()).toString(),
	 (fTime.getSeconds())<10?("0"+(fTime.getSeconds())):(fTime.getSeconds()).toString()
	 ]
	 for (var i=0; i<formatArr.length; i++)
	 {
	  formatStr = formatStr.replace(fStr.charAt(i), formatArr[i]);
	 }
	 return formatStr;
}
