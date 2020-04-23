create table lesson_user (
	user_id int not null auto_increment primary key,
    name varchar(32) default null,
    email varchar(1024) default null,
    phone varchar(32) default null,
    password varchar(1024) default null,
    create_time datetime default null,
    update_time datetime default null
);

INSERT INTO lesson_user (name, email, phone, password) VALUES ("xiaoming", "xiaoming@qq.com", "12322343321", "password");