create table lesson_course (
	course_id int not null auto_increment primary key,
    name varchar(32) default null,
    description varchar(1024) default null,
    teacher_id int not null,
    create_time datetime default null,
    update_time datetime default null,
    index(teacher_id)
);

INSERT INTO lesson_course (name, description, teacher_id) VALUES ("course1", "The test course", 1);