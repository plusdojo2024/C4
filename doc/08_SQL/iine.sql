CREATE TABLE iine ( 
    iineId INT AUTO_INCREMENT PRIMARY KEY, 
    employeeId VARCHAR(5) NOT NULL,
    username VARCHAR(50) NOT NULL,
    post_id INT ,

    FOREIGN KEY(employeeId) -- 外部キーemployeeId
    REFERENCES users(employeeId) ,
    FOREIGN KEY(username) -- 外部キーusername
    REFERENCES users(username),
    FOREIGN KEY(post_id) -- 外部キーpost_id
    REFERENCES posts(post_id)
);

INSERT INTO iine (employeeId, username, post_id)
SELECT '0001', '田中', post_id
FROM posts
WHERE post_id = '4'
ORDER BY created_at DESC
LIMIT 1;


INSERT INTO iine VALUES(
    '鈴木',
    '0002'
    );


