CREATE TABLE  users ( 
employeeId VARCHAR(5), 
password  VARCHAR(32) NOT NULL, 
username VARCHAR(50) NOT NULL,
icon VARCHAR(1024),
birth TIMESTAMP,
comment VARCHAR(2000),
point int(1000),
PRIMARY KEY  (employeeId) 
); 


INSERT INTO users VALUES ( 
'0001', 
'0001', 
'田中', 
'iconnn',  
CURRENT_TIMESTAMP, 
'こんにちは', 
2
);

INSERT INTO users VALUES ( 
'0002', 
'0002', 
'鈴木', 
'iconnn',  
CURRENT_TIMESTAMP, 
'おはようございます。', 
7
);

CREATE TABLE langs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employeeId VARCHAR(5) NOT NULL,
    langName VARCHAR(20) NOT NULL,
    FOREIGN KEY (employeeId ) REFERENCES users(employeeId )
);

INSERT INTO langs VALUES ( 
NULL, 
'0001', 
'English'
);


SELECT u.employeeId, l.langName
FROM users u
LEFT JOIN langs l ON u.employeeId= l.employeeId;

SELECT u.employeeId, u.username, u.icon, l.langName, u.birth,u.comment,u.point 
FROM users u 
LEFT JOIN langs l ON u.employeeId= l.employeeId WHERE u.employeeId=?;

SELECT u.employeeId, l.langName FROM users u LEFT JOIN langs l ON u.employeeId= l.employeeId WHERE u.employeeId='0003';


SELECT u.employeeId, u.username, u.icon, l.langName, u.birth,u.comment,u.point FROM users u LEFT JOIN langs l ON u.employeeId= l.employeeId WHERE u.employeeId=?;

