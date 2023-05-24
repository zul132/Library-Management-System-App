create database library;
use library;

create table books(bookId int auto_increment, bookName varchar(30), author varchar(30), publisherName varchar(30), edition varchar(5), primary key(bookId));

select * from books;

insert into books(bookName,author,publisherName,edition) values("ABC", "ABC", "ABC", "ABC"),
						("EFG","EFG","EFG","EFG"),
                        ("HIJ","HIJ","HIJ","HIJ");

truncate table books;