--Populate 
insert into Member values('student1@email.arizona.edu', 'Adam', 'I am student 1', 'Tucson', 2000-01-01, 'y', 'y', 'good');
insert into Member values('student2@email.arizona.edu', 'Barbara', 'I am student 2', 'Tucson', 2000-02-01, 'y', 'n', 'good');
insert into Member values('student3@email.arizona.edu', 'Charles', 'I am student 3', 'Phoenix', 2000-03-01, 'y', 'n', 'bad');
insert into Member values('student4@email.arizona.edu', 'Debby', 'I am student 4', 'Phoenix', 2000-04-01, 'n', 'y', null);
insert into Member values('student5@email.arizona.edu', 'Earl', 'I am student 5', 'Yuma', 2000-05-01, 'n', 'y', null);

insert into Pet values('student1@email.arizona.edu', 'Fiona', 2019-01-01, 'y', 'Dog', 3, null);
insert into Pet values('student2@email.arizona.edu', 'Garfield', 2019-02-01, 'y', 'Cat', null, null);
insert into Pet values('student3@email.arizona.edu', 'Harry', 2019-03-01, 'n', 'Cat', null, null);
insert into Pet values('student4@email.arizona.edu', 'Isabel', 2019-04-01,'y', 'Dog', 4, null);
insert into Pet values('student5@email.arizona.edu', 'Jack', 2019-05-01, 'n', 'Rabbit', null, 'y');

insert into Favorite values('student1@email.arizona.edu', 'student1@email.arizona.edu', 'Fiona', 2019-01-01);
insert into Favorite values('student2@email.arizona.edu', 'student2@email.arizona.edu', 'Garfield', 2019-02-01);
insert into Favorite values('student3@email.arizona.edu', 'student3@email.arizona.edu', 'Harry', 2019-03-01);
insert into Favorite values('student4@email.arizona.edu', 'student4@email.arizona.edu', 'Isabel', 2019-04-01);
insert into Favorite values('student5@email.arizona.edu', 'student5@email.arizona.edu', 'Jack', 2019-05-01);
