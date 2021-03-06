--牧场信息表
--drop table customer;
--create table customer(cid int primary key,name varchar(100),city varchar (20),address varchar(100),
--cname varchar(20),cphone varchar(20),naicity varchar(20),naiaddress varchar(100),
--scale varchar(20),used int,left int ,road varchar(20),price int,longitude double,latitude double,staffid int,remarks varchar(200));
--insert into customer(cid,name,city,address,cname ,cphone,naicity,naiaddress,scale,used,left ,road,price,longitude,latitude,staffid,remarks) values ('HS00001','测试牧场','河北石家庄市','河北省衡水市某村','张三','13512345678','河北省衡水市','河北省衡水市某村',
--'20头高产奶牛10头猪',10,12,'高速',10,115.703188,37.764869,1,'备注');
--select * from customer;
--货车表
--drop table truck;
--create table truck(tid int primary key,license varchar(50),tname varchar(100),tphone varchar(20),ttype varchar(10),capacity int,citys varchar(100),remarks varchar(200));
--insert into truck(tid,license,tname,tphone,ttype,capacity,citys,remarks) values (1,'冀A0123','李四','13612345678','22轮半挂','30','衡水,石家庄,沧州等','备注');
--数据员表
--drop table staff;
--create table staff(staffid int primary key,sname varchar(50),sphone varchar(20),gender int,remarks varchar(200));
--insert into staff(staffid,sname,sphone,gender,remarks) values (1,'王五','13712345678','1','备注');
--order：订单表
--drop table orders;
--create table orders(oid int primary key ,cid varchar(50) ,tid int ,staffid int,volume int,price double,total int,createtime timestamp,remarks varchar(200));
--insert into orders (oid,cid,tid,staffid,volume,price,total,createtime,remarks) values (1,'HS001',1,1,10,21.5,215,'2017-06-22 21:06:05','');
--user:用户登陆信息表
--drop table user;
--create table user(id int primary key,username varchar(50),password varchar(50),power int);
--insert into user(id,username,password,power) values (1,'admin','admin',1);
--repoet:日常报表
--drop table report;
--create table report (rid int primary key,cid varchar,createdate date,total int,remark VARCHAR);
--insert into report (rid,cid,createdate,total,remark) values (1,'HS00001',date('now'),15,'无');
--数据库字段对照表
--customer：牧场信息表
--cid牧场id HS开头+000编号,name:牧场简称，city：牧场所在行政区划，address：牧场地址，cname：厂长姓名，cphone：厂长电话，naicity：交奶地的行政区划
--naiaddress：交奶地的具体地址，scale：牧场规模，used：月使用量（吨/月），left：库存剩余量，road：道路情况，price（元/吨）预计运费，longitude：经度，latitude：纬度，remarks备注
--truck货车信息表
--tid:货车id 主键唯一编码，license：车牌号 id，tname:车主姓名，tphone：车主电话，ttype：货车类型，capacity：容量(吨)，citys:车主中意城市（按英文,隔开）,remarks:备注
--staff：工作人员表
--staffid：id主键唯一表示，sname姓名，sphone：电话，gender：性别  1为男 2为女，remarks：备注
--order：订单表
--oid:订单id，cid：牧场id，tid:货车id,staffid：数据员id,volume：发货量（吨），price：单价（元），total：总价（元），createtime：创建时间，remarks：备注
--user:用户登陆信息表
--id:登陆用户id，username：用户名，password：密码，power：权限 1、超级管理员，2、普通管理员，3、只读权限
--select cid, name, city, address, cname, cphone, naicity, naiaddress, scale, used, left, road, longitude, latitude, t1.staffid ,remarks  from customer as t1,
--(select * from staff) as t2 where t1.staffid=t2.staffid order by cid desc limit 1 offset 0;
--delete from truck where tid =0
--delete from orders where oid=0
--delete from staff where staffid=0
--delete from customer where cid=HS0000
--insert into customer(cid) values ('HS0000');
--insert into truck(tid)values ('0');
--insert into staff(staffid) values ('0');
--insert into orders (oid)values ('0')