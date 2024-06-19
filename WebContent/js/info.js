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

function timeDateGet() {
    let time = [];
    let now = new Date();
    time[0] = now.getFullYear();
    time[1] = now.getMonth() + 1;
    time[2] = now.getDate();
    time[3] = now.getDay();
    time[4] = now.getHours();
    time[5] = now.getMinutes();
    time[6] = now.getSeconds();
    let start = new Date(time[0], time[1] - 1, 1);
    let end = new Date(time[0], time[1],  0);
    let lastEnd = new Date(time[0], time[1] - 1, 0);
    time[7] = start.getDay();
    time[8] = end.getDate();
    time[9] = lastEnd.getDate();
    return time;
}

function clockGet() {
    let body = new Image();
    let short = new Image();
    let long = new Image();
    let hand = new Image();
    let cover = new Image();
    body.src = 'img/CSIRT_body.png';
    short.src = 'img/CSIRT_short.png';
    long.src = 'img/CSIRT_long.png';
    hand.src = 'img/CSIRT_sec.png';
    cover.src = 'img/CSIRT_cover.png';
    let time = [];
    time = timeDateGet();
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

function calendarView() {
    let calendarHtml = '';
    let dayCount = 1;
    let date = [];
    date = timeDateGet();
    calendarHtml += '<h1>' + date[0]  + '/' + date[1] + '</h1>';
    calendarHtml += '<table>';
    for (let i = 0; i < days.length; i++) {
        calendarHtml += '<td>' + days[i] + '</td>';
    }
    
    for (let i = 0; i < 6; i++) {
        calendarHtml += '<tr>';
        for (let j = 0; j < 7; j++) {
            if (i == 0 && j < date[7]) {
                let num = date[9] - date[7] + j + 1;
                calendarHtml += '<td class="is-disabled">' + num + '</td>';
            } else if (dayCount > date[8]) {
                let num = dayCount - date[8];
                calendarHtml += '<td class="is-disabled">' + num + '</td>';
                dayCount++;
            } else {
                calendarHtml += '<td>' + dayCount + '</td>';
                dayCount++;
            }
        }
        calendarHtml += '</tr>';
    }
    calendarHtml += '</table>'
    
    document.querySelector('#calendar').innerHTML = calendarHtml
}

window.onload = function () {
    let time = [];
    let from, url;

    for (let i = 0; i < places.length; i++) {
        let opt = document.createElement("option");
        opt.value = places[i].val;
        opt.text = places[i].txt;
        if (places[i].val === "0") {
            opt.text = "群馬県" + "(デフォルト)";
            opt.setAttribute("selected", true);
        }
        document.getElementById("from").appendChild(opt);
    }

    window.setInterval(function () {
        time = timeDateGet();
        document.getElementById('date').textContent = time[0] + "/" + time[1].toString().padStart(2, " ") + "/" + time[2].toString().padStart(2, " ") + "(" + days[time[3]] + ")" + time[4].toString().padStart(2, "0") + ":" + time[5].toString().padStart(2, "0") + ":" + time[6].toString().padStart(2, "0");
        if (time[5] === 0 && time[6] === 0) {
            weatherView();
        }
        clockGet();
    }, 1000);

    from = "100000";
    url = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + from + ".json";
    timeDateGet();
    weatherGet(url);

    calendarView();
};

document.getElementById("from").onchange = function () {
    weatherView();
};
