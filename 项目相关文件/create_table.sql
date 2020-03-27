/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2020/3/27 21:31:21                           */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_COURSERE_REFERENCE_USER') then
    alter table courseRecord
       delete foreign key FK_COURSERE_REFERENCE_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_COURSERE_REFERENCE_COURSE') then
    alter table courseRecord
       delete foreign key FK_COURSERE_REFERENCE_COURSE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROLEMENU_REFERENCE_ROLE') then
    alter table roleMenu
       delete foreign key FK_ROLEMENU_REFERENCE_ROLE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ROLEMENU_REFERENCE_MENU') then
    alter table roleMenu
       delete foreign key FK_ROLEMENU_REFERENCE_MENU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_REFERENCE_ROLE') then
    alter table "user"
       delete foreign key FK_USER_REFERENCE_ROLE
end if;

drop table if exists course;

drop table if exists courseRecord;

drop table if exists dict;

drop table if exists menu;

drop table if exists role;

drop table if exists roleMenu;

drop table if exists "user";

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course 
(
   course_id            int                            not null,
   coursename           varchar                        null,
   teachername          varchar                        null,
   createtime           datetime                       null,
   intro                varchar                        null,
   info                 varchar                        null,
   status               int                            null,
   constraint PK_COURSE primary key clustered (course_id)
);

/*==============================================================*/
/* Table: courseRecord                                          */
/*==============================================================*/
create table courseRecord 
(
   id                   int                            not null,
   course_id            int                            null,
   user_id              int                            null,
   course_exp           varchar                        null,
   jointime             datetime                       null,
   score                decimal                        null,
   status               int                            null,
   constraint PK_COURSERECORD primary key clustered (id)
);

/*==============================================================*/
/* Table: dict                                                  */
/*==============================================================*/
create table dict 
(
   dict_id              int                            not null,
   "key"                varchar                        null,
   value                varchar                        null,
   dicName              varchar                        null,
   status               int                            null,
   constraint PK_DICT primary key clustered (dict_id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu 
(
   menu_id              int                            not null,
   name                 varchar                        null,
   path                 varchar                        null,
   pid                  int                            null,
   status               int                            null,
   constraint PK_MENU primary key clustered (menu_id)
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role 
(
   role_id              int                            not null,
   name                 varchar                        null,
   note                 varchar                        null,
   uniqeKey             varchar                        null,
   status               int                            null,
   constraint PK_ROLE primary key clustered (role_id)
);

/*==============================================================*/
/* Table: roleMenu                                              */
/*==============================================================*/
create table roleMenu 
(
   id                   int                            not null,
   role_id              int                            null,
   menu_id              int                            null,
   status               int                            null,
   constraint PK_ROLEMENU primary key clustered (id)
);

/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" 
(
   user_id              int                            not null,
   name                 varchar                        null,
   nickname             varchar                        null,
   userno               varchar                        null,
   email                varchar                        null,
   signature            varchar                        null,
   gender               varchar                        null,
   school               varchar                        null,
   education            varchar                        null,
   scholarship          varchar                        null,
   country              varchar                        null,
   address              varchar                        null,
   phone                varchar                        null,
   password             varchar                        null,
   role_id              int                            null,
   tags                 varchar                        null,
   status               int                            null,
   constraint PK_USER primary key clustered (user_id)
);

alter table courseRecord
   add constraint FK_COURSERE_REFERENCE_USER foreign key (user_id)
      references "user" (user_id)
      on update restrict
      on delete restrict;

alter table courseRecord
   add constraint FK_COURSERE_REFERENCE_COURSE foreign key (course_id)
      references course (course_id)
      on update restrict
      on delete restrict;

alter table roleMenu
   add constraint FK_ROLEMENU_REFERENCE_ROLE foreign key (role_id)
      references role (role_id)
      on update restrict
      on delete restrict;

alter table roleMenu
   add constraint FK_ROLEMENU_REFERENCE_MENU foreign key (menu_id)
      references menu (menu_id)
      on update restrict
      on delete restrict;

alter table "user"
   add constraint FK_USER_REFERENCE_ROLE foreign key (role_id)
      references role (role_id)
      on update restrict
      on delete restrict;

