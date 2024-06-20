SELECT * FROM NOTICES 

CREATE TABLE NOTICES (
    notices_id  INTEGER AUTO_INCREMENT,
    employee_id VARCHAR(5) NOT NULL,
    content VARCHAR(20) NOT NULL,
    notice_status BOOLEAN,
    notice_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (notices_id)
);

/* テーブルにテスト用のデータを登録する SQL 文 */

INSERT INTO NOTICES (
    employee_id,
    content,
    notice_status,
    notice_date
) VALUES (
    '12345',           
    'hello',                 
    TRUE,       
    CURRENT_TIMESTAMP            
);


/*削除*/
DROP TABLE NOTICES;

/*INNER JOIN*/
SELECT notices_id, employee_id,content,notice_status,notice_date
FROM USERS
INNER JOIN NOTICES
ON USERS.employee_id = NOTICES.employee_id;

/**/


