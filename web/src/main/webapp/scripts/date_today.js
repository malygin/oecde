
function MakeArray(n) {
	this.length = n
	return this
}
monthNames = new MakeArray(12);
monthNames[0] = "Января"
monthNames[1] = "Февраля"
monthNames[2] = "Марта"
monthNames[3] = "Апреля"
monthNames[4] = "Мая"
monthNames[5] = "Июня"
monthNames[6] = "Июля"
monthNames[7] = "Августа"
monthNames[8] = "Сентября"
monthNames[9] = "Октября"
monthNames[10] = "Ноября"
monthNames[11] = "Декабря"

dayNames = new MakeArray(7)
dayNames[1] = "Понедельник"
dayNames[2] = "Вторник"
dayNames[3] = "Среда"
dayNames[4] = "Четверг"
dayNames[5] = "Пятница"
dayNames[6] = "Суббота"
dayNames[0] = "Воскресение";

function customDateString() {
	currentDate = new Date();
	var theDay = dayNames[currentDate.getDay()];
	var theMonth = monthNames[currentDate.getMonth()]
	msie4 = ((navigator.appName == "Microsoft Internet Explorer") && (parseInt(navigator.appVersion) >= 4 ));
	if (msie4) {
	    var theYear = currentDate.getYear()
	}
	else {
	     var theYear = currentDate.getYear() +1900
	}
	return  currentDate.getDate() + " " + theMonth + ", " + theDay
}