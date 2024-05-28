if not exists (select name from sys.schemas where name = N'DB_SCHEDULER')
    EXEC ('CREATE SCHEMA [DB_SCHEDULER] AUTHORIZATION [dbo]')
go

if not exists (select * from sysobjects where name='application_user' and xtype='U')
    CREATE TABLE DB_SCHEDULER.application_user (
    	application_user_id bigint IDENTITY(1,1) NOT NULL,
    	application_user_creation_date datetime2(6) NOT NULL,
    	application_user_email varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    	application_user_last_login_date datetime2(6) NULL,
    	application_user_last_update_date datetime2(6) NOT NULL,
    	application_user_name varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    	application_user_phone varchar(11) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
    	application_user_photo varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
    	CONSTRAINT PK__applicat__E7546C9FEF06963D PRIMARY KEY (application_user_id)
    );
go

-- DB_001_DEV.DB_SCHEDULER.schedule definition

if not exists (select * from sysobjects where name='schedule' and xtype='U')
    CREATE TABLE DB_SCHEDULER.schedule (
        schedule_id bigint IDENTITY(1,1) NOT NULL,
        schedule_creation_date datetime2(6) NOT NULL,
        schedule_date datetime2(6) NOT NULL,
        schedule_last_update_date datetime2(6) NOT NULL,
        schedule_status smallint NOT NULL,
        schedule_student_id bigint NULL,
        schedule_teacher_id bigint NOT NULL,
        CONSTRAINT PK__schedule__C46A8A6F00307C93 PRIMARY KEY (schedule_id)
    );
    ALTER TABLE DB_SCHEDULER.schedule WITH NOCHECK ADD CONSTRAINT CK__schedule__schedu__76969D2E CHECK (([schedule_status]>=(0) AND [schedule_status]<=(2)));
go

-- DB_001_DEV.DB_SCHEDULER.student definition

if not exists (select * from sysobjects where name='student' and xtype='U')
    CREATE TABLE DB_SCHEDULER.student (
        student_class_type varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
        student_reschedules int NULL,
        application_user_id bigint NOT NULL,
        student_level_id bigint NULL,
        student_teacher_id bigint NOT NULL,
        CONSTRAINT PK__student__E7546C9FAEF59463 PRIMARY KEY (application_user_id)
    );
    ALTER TABLE DB_SCHEDULER.student WITH NOCHECK ADD CONSTRAINT CK__student__student__797309D9 CHECK (([student_class_type]='IN_PERSON' OR [student_class_type]='ONLINE'));
go

-- DB_001_DEV.DB_SCHEDULER.student_level definition

if not exists (select * from sysobjects where name='student_level' and xtype='U')
    CREATE TABLE DB_SCHEDULER.student_level (
        student_level_id bigint IDENTITY(1,1) NOT NULL,
        student_level_code varchar(2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        student_level_description varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
        student_level_name varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
        CONSTRAINT PK__student___978BBA5ACF2EB494 PRIMARY KEY (student_level_id)
    );
go

-- DB_001_DEV.DB_SCHEDULER.teacher definition

if not exists (select * from sysobjects where name='teacher' and xtype='U')
    CREATE TABLE DB_SCHEDULER.teacher (
        teacher_school_subject varchar(10) NOT NULL,
        application_user_id bigint NOT NULL,
        CONSTRAINT PK__teacher__E7546C9FE7DA03D4 PRIMARY KEY (application_user_id)
    );
go

-- DB_SCHEDULER.schedule foreign keys

ALTER TABLE DB_SCHEDULER.schedule ADD CONSTRAINT FK6q6k23m1g0jlja6wjqx59lvy4 FOREIGN KEY (schedule_student_id) REFERENCES DB_SCHEDULER.student(application_user_id);
ALTER TABLE DB_SCHEDULER.schedule ADD CONSTRAINT FK96k9h8i4hoe9b1c6llkccwuon FOREIGN KEY (schedule_teacher_id) REFERENCES DB_SCHEDULER.teacher(application_user_id);

-- DB_001_DEV.DB_SCHEDULER.student foreign keys

ALTER TABLE DB_SCHEDULER.student ADD CONSTRAINT FK53q9xbb6l974fh1u4vnq0wwp FOREIGN KEY (student_teacher_id) REFERENCES DB_SCHEDULER.teacher(application_user_id);
ALTER TABLE DB_SCHEDULER.student ADD CONSTRAINT FKkk5yli3vhkgsn1pf7usywbkh0 FOREIGN KEY (application_user_id) REFERENCES DB_SCHEDULER.application_user(application_user_id);
ALTER TABLE DB_SCHEDULER.student ADD CONSTRAINT FKtbu12l0m51ac8csesmnma9oga FOREIGN KEY (student_level_id) REFERENCES DB_SCHEDULER.student_level(student_level_id);

-- DB_001_DEV.DB_SCHEDULER.teacher foreign keys

ALTER TABLE DB_SCHEDULER.teacher ADD CONSTRAINT FK4q6yek898evdobywiri9j5p5h FOREIGN KEY (application_user_id) REFERENCES DB_SCHEDULER.application_user(application_user_id);
