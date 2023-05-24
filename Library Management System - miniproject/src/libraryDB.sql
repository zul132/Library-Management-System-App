create database library;
use library;

create table books(bookId int auto_increment, bookName varchar(30), author varchar(30), publisherName varchar(30), edition varchar(5), primary key(bookId));

select * from books;

insert into books(bookName,author,publisherName,edition) values("Secret Garden", "Enid Blyton", "Oxford Publishers", "2"),
						("Little Princess","Frances H. Burnett","Puffin Classics","8"),
                        ("Harry Potter","J.K.Rowling","Bloomsbury Publishing","1"),
                        ("Percy Jackson","Rick Riordan","Disney Hyperion","1"),
                        ("Mary Poppins","P.L.Travers","HarpenCollins","5");

truncate table books;