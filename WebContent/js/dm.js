// 送信者が自分かどうか保存する変数
// true:自分
// false:相手
let isMySelf = true;

// 送信ボタンの取得
let sendBtn = document.getElementById('sendBtn');

// 送信ボタンがクリックされたときの処理
sendBtn.addEventListener('click', function() {
  // 入力欄を取得
  let inputMessage = document.getElementById('inputMessage');

  // 入力値を取得
  let messageText = inputMessage.value;

  // 入力値が空かチェックする
  if (messageText == '') {
    // 入力値が空の場合、処理を中断する
    return;
  }

  // divを作成
  let messageBox = document.createElement('div');

  // 誰が送信者かチェック
  if (isMySelf) {
    // 自分が送信者の場合
    // 「box-right」というクラスをdivに追加
    messageBox.classList.add('box-right');
  } else {
    // 相手が送信者の場合
    // 「box-left」というクラスをdivに追加
    messageBox.classList.add('box-left');
  }

  // pタグを作成
  let message = document.createElement('p');

  // pタグのテキストに、画面の入力値を設定
  message.textContent = messageText;

  // pタグに「message-box」というクラスを追加
  message.classList.add('message-box');

  if (isMySelf) {
    // 自分が送信者の場合
    // 「green」というクラスをpに追加
    message.classList.add('green');
  } else {
    // 相手が送信者の場合
    // 「white」というクラスをpに追加
    message.classList.add('white');
  }

  // divにpタグを追加する
  messageBox.appendChild(message);

  // チャット画面である、divタグを取得する
  let room = document.getElementById('room');

  // チャット画面のdivに新規メッセージのdivを追加する
  room.appendChild(messageBox);

  // 入力欄の入力値をリセットする
  inputMessage.value = '';

  // 送信者を変更する
  if (isMySelf) {
    // 自分が送信者の場合
    // 次の送信者を相手にする
    isMySelf = false;
  } else {
    // 相手が送信者の場合
    // 次の送信者を自分にする
    isMySelf = true;
  }
})