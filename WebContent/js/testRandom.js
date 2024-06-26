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

window.onload = function () {
	let time = [];
    window.setInterval(function () {
        time = timeGet();
        if (time[4] === 0 && time[5] === 0 && time[6] === 0) {
            //;
        }
        clockGet();
    }, 1000);
};