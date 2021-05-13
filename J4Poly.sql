create database FpolyJ4_pro;
use FpolyJ4;
go
create table Users(
	Username varchar(30) not null primary key,
	Passwords nvarchar(50) not null,
	Email nvarchar(50) null,
	Fullname nvarchar(50) null,
	Admin bit null
);
create table Videos(
	VideoId varchar(50) not null  primary key,
	Title nvarchar(100) null,
	Poster nvarchar(100) null,
	Views int null,
	Description nvarchar(1500) null,
	Active bit null,
);
create table Favorites(
	FavoriteId int IDENTITY(1,1) primary key,
	Username varchar(30) not null foreign key (Username) references Users(Username),
	VideoId varchar(50) not null foreign key (VideoId) references Videos(VideoId),
	LikedDate date null
);
create table Shares(
	ShareId int IDENTITY(1,1) primary key,
	Username varchar(30) not null foreign key (Username) references Users(Username),
	VideoId varchar(50) not null foreign key (VideoId) references Videos(VideoId),
	Emails nvarchar(250) null,
	SharedDate date null
)
select * from Users