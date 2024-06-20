CREATE TABLE messages(
message_id INTEGER NOT NULL AUTO_INCREMENT(1),
conversations_id INTEGER NOT NULL,
sender_id VARCHAR(5) NOT NULL,
receiver_id VARCHAR(5) NOT NULL,
message_content VARCHAR(200) NOT NULL,
created_at TIMESTAMP NOT NULL,
PRIMARY KEY (message_id)
);

INSERT INTO messages VALUES(
NULL,
'1',
'0001',
'0002',
'おはようございます！',
CURRENT_TIMESTAMP
)
INSERT INTO messages VALUES(
NULL,
'1',
'0002',
'0001',
'おはよう！',
CURRENT_TIMESTAMP
)









CREATE TABLE conversations(
conversations_id INTEGER AUTO_INCREMENT(1),
user1_id VARCHAR(5) NOT NULL,
user2_id VARCHAR(5) NOT NULL,
PRIMARY KEY (conversations_id)
);

INSERT INTO conversations(
NULL,
'0001',
'0002'
)


