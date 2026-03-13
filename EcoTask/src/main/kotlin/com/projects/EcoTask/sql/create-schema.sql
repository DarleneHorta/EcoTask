create schema EcoTasks.dbo;

create table if not exists dbo.users(
    id int generated always as identity primary key,
    username VARCHAR(64) unique not null,
    password_validation VARCHAR(256) not null
);

create table if not exists dbo.tasks(
    id int generated always as identity primary key,
    taskName varchar(64) not null,
    taskDesc varchar(256) ,
    taskTypeId int not null,
    taskDueDate bigint not null,
    taskCreatedAt bigint not null,

);

create table if not exists dbo.reward(
    id int generated always as identity primary key,
    userId int not null,
    rewardDate bigint not null,
    rewardCategoryId int not null,
);

create table if not exists dbo.rewardCategory(
    id int generated always as identity primary key,
    rewardCategory VARCHAR(256) unique not null,
);

create table if not exists dbo.taskType(
     id int generated always as identity primary key,
     taskType VARCHAR(256) unique not null,
);





