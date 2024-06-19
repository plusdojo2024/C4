function RanUseNnm() {
    //参加人数
    let user = 5
    let nums = [user];

    let i = 0;
    check : while (i < user) {
        let ran = (Math.floor(Math.random() * 1000)) + 1;
        if (ran > 500) {
            continue;
        } 
        //確認用
        console.log(ran);
        for (let j = 0; j < i; j++) {
            if (ran === nums[j]) {
                continue check;
            }
        }
        nums[i] = ran;
        i++;
    }
    //確認用
    console.log(nums);
    return nums;
}

//自分で作った入れたい配列に代入する
//以下一例
let test = [];
console.log(test);
test = RanUseNnm();
console.log(test);