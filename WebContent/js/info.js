function weatherGet(url) {
    let areas, todayWeather, todayMinTemps, todayMaxTemps, tomorrowWeather, tomorrowMinTemps, tomorrowMaxTemps;
    let errMes = "取得できません";
    fetch(url)
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
        })
        .then(function (weather) {
            areas = weather[0].timeSeries[0].areas[0];
            fromArea = areas.area.name;
            places = weather[0].timeSeries[2].areas[0].area.name;
            todayWeather = areas.weathers[0];
            todayMinTemps = weather[0].timeSeries[2].areas[0].temps[0];
            todayMaxTemps = weather[0].timeSeries[2].areas[0].temps[1];
            tomorrowWeather = areas.weathers[1];
            tomorrowMinTemps = weather[0].timeSeries[2].areas[0].temps[2];
            tomorrowMaxTemps = weather[0].timeSeries[2].areas[0].temps[3];
            if (tomorrowMinTemps === undefined) {
                tomorrowMinTemps = "-";
            }
            if (tomorrowMaxTemps === undefined) {
                tomorrowMaxTemps = "-";
            }
            document.getElementById('fromWeather').textContent = fromArea;
            document.getElementById('todayWeather').textContent = todayWeather;
            document.getElementById('tomorrowWeather').textContent = tomorrowWeather;
            document.getElementById('placeWeather').textContent = places;
            document.getElementById('todayTemps').textContent = "最低 " + todayMinTemps + "℃ " + "最高 " + todayMaxTemps + "℃";
            document.getElementById('tomorrowTemps').textContent = "最低 " + tomorrowMinTemps + "℃ " + "最高 " + tomorrowMaxTemps + "℃";
        })
        .catch(function () {
            document.getElementById('fromWeather').textContent = errMes;
            document.getElementById('todayWeather').textContent = errMes;
            document.getElementById('tomorrowWeather').textContent = errMes;
            document.getElementById('placeWeather').textContent = errMes;
            document.getElementById('todayTemps').textContent = errMes;
            document.getElementById('tomorrowTemps').textContent = errMes;
        });
}

function timeGet() {
    let time = [];
    let now = new Date();
    time[0] = now.getFullYear();
    time[1] = now.getMonth() + 1;
    time[2] = now.getDate();
    time[3] = now.getDay();
    time[4] = now.getHours();
    time[5] = now.getMinutes();
    time[6] = now.getSeconds();
    return time;
}

function clockGet() {
    let body = new Image();
    let short = new Image();
    let long = new Image();
    let hand = new Image();
    let cover = new Image();
    body.src = './img/CSIRT_body.png';
    short.src = './img/CSIRT_short.png';
    long.src = './img/CSIRT_long.png';
    hand.src = './img/CSIRT_sec.png';
    cover.src = './img/CSIRT_cover.png';
    let time = [];
    time = timeGet();
    let canvas = document.getElementById('clock');
    let ctx = canvas.getContext('2d');
    let handHour = ((time[5] / 60) + time[4]) / 12 * 360;
    let handMin = ((time[6] / 60) + time[5]) / 60 * 360;
    let handSec = time[6] / 60 * 360;
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(body, 0, 0);
    ctx.save();
    ctx.translate(canvas.width / 2, canvas.height / 2);
    ctx.rotate(handHour * Math.PI / 180);
    ctx.drawImage(short, -canvas.width / 2, -canvas.height / 2);
    ctx.restore();
    ctx.save();
    ctx.translate(canvas.width / 2, canvas.height / 2);
    ctx.rotate(handMin * Math.PI / 180);
    ctx.drawImage(long, -canvas.width / 2, -canvas.height / 2);
    ctx.restore();
    ctx.save();
    ctx.translate(canvas.width / 2, canvas.height / 2);
    ctx.rotate(handSec * Math.PI / 180);
    ctx.drawImage(hand, -canvas.width / 2, -canvas.height / 2);
    ctx.restore();
    ctx.drawImage(cover, 0, 0);
}

function weatherView() {
    let from = document.getElementById("from").value;
    if (from === "0") {
        return 0;
    }
    let url = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + from + ".json";
    weatherGet(url);
}

function calendarView(year, month) {
    let start = new Date(year, month - 1, 1);
    let end = new Date(year, month,  0);
    let lastEnd = new Date(year, month - 1, 0);
    let startDay = start.getDay();
    let endDate = end.getDate();
    let lastEndDate = lastEnd.getDate();
    let calendarHtml = '';
    let dayCount = 1;
    calendarHtml += '<h1>' + year  + '/' + month + '</h1>';
    calendarHtml += '<input type="hidden" id="year" value="' + year + '"></input>' + '<input type="hidden" id="month" value="' + month + '"></input>';
    calendarHtml += '<table>';
    for (let i = 0; i < days.length; i++) {
        calendarHtml += '<td>' + days[i] + '</td>';
    }

    for (let i = 0; i < 6; i++) {
        calendarHtml += '<tr>';
        for (let j = 0; j < 7; j++) {
            if (i == 0 && j < startDay) {
                let num = lastEndDate - startDay + j + 1;
                calendarHtml += '<td class="is-disabled">' + num + '</td>';
            } else if (dayCount > endDate) {
                let num = dayCount - endDate;
                calendarHtml += '<td class="is-disabled">' + num + '</td>';
                dayCount++;
            } else {
                calendarHtml += `<td class="calendar_td" data-date="${year}/${month}/${dayCount}">${dayCount}</td>`;
                dayCount++;
            }
        }
        calendarHtml += '</tr>';
    }
    calendarHtml += '</table>'

    document.querySelector('#calendar').innerHTML = calendarHtml
}

function lastCalendar() {
    let year = document.getElementById("year").value;
    let month = document.getElementById("month").value;
    document.querySelector('#calendar').innerHTML = '';
    month--;
        if (month < 1) {
            year--;
            month = 12;
        }
    calendarView(year, month);
}

function postForm(value) {

    var form = document.createElement('form');
    var request = document.createElement('input');

    form.method = 'POST';
    form.action = '/C4/HomeServlet';

    request.type = 'hidden';
    request.name = 'date';
    request.value = value;

    form.appendChild(request);
    document.body.appendChild(form);

    form.submit();

};

function nextCalendar() {
    let year = document.getElementById("year").value;
    let month = document.getElementById("month").value;
    document.querySelector('#calendar').innerHTML = '';
    month++;
        if (month > 12) {
            year++;
            month = 1;
        }
    calendarView(year, month);
}

window.onload = function () {
    let time = [];
    /*let from, url;

    for (let i = 0; i < places.length; i++) {
        let opt = document.createElement("option");
        opt.value = places[i].val;
        opt.text = places[i].txt;
        if (places[i].val === "0") {
            opt.text = "群馬県" + "(デフォルト)";
            opt.setAttribute("selected", true);
        }
        document.getElementById("from").appendChild(opt);
    }*/

    window.setInterval(function () {
        time = timeGet();
        document.getElementById('date').textContent = time[0] + "/" + time[1].toString().padStart(2, " ") + "/" + time[2].toString().padStart(2, " ") + "(" + days[time[3]] + ")" + time[4].toString().padStart(2, "0") + ":" + time[5].toString().padStart(2, "0") + ":" + time[6].toString().padStart(2, "0");
        /*if (time[5] === 0 && time[6] === 0) {
            weatherView();
        }
        clockGet();*/
    }, 1000);

    /*from = "100000";
    url = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + from + ".json";
    weatherGet(url);*/
    time = timeGet();
    calendarView(time[0], time[1]);
};

/*document.getElementById("from").onchange = function () {
    weatherView();
};*/

document.addEventListener("click", function(e) {
    if(e.target.classList.contains("calendar_td")) {
        alert('クリックした日付は' + e.target.dataset.date + 'です');
        postForm(e.target.dataset.date);
    }
});