--Create
create table Member (
	EmailAddress  	varchar2(50) primary key,
	Name	    	varchar2(20) not null,
	SelfIntroduction varchar2(50),
	Location		varchar2(50) not null,
	Birthday		date not null,
	IsAdopter		varchar2(1) check(IsAdopter in ('y', 'n')) not null,
	IsSender		varchar2(1) check(IsSender in ('y', 'n')) not null,
	AdoptingExperience varchar2(50),
	constraint adoptExp check(IsAdopter = 'n' or AdoptingExperience is not null)
);

create table Pet (
	EmailAddress  	varchar2(50) references Member(EmailAddress),
	Name			varchar2(10),
	Birthday		date,
	IsSterilized	varchar2(1) check(IsSterilized in ('y', 'n')),
	Breed			varchar2(7) check(Breed in ('Cat', 'Dog', 'Rabbit')) not null,
	DaysWalk		number(3) check (DaysWalk > 0),
	BiteWire		varchar2(1) check(BiteWire in ('y', 'n')),
	constraint PK_Pet primary key(EmailAddress, Name, Birthday),
	constraint dogWalks check(Breed != 'Dog' or DaysWalk is not null),
    	constraint rabbitBites check(Breed != 'Rabbit' or BiteWire is not null)
);

create table Favorite (
	EmailAddressMember	varchar2(50),
	EmailAddressPet		varchar2(50),
	PetName				varchar2(10),
	Birthday			date,
	constraint PK_Favorite primary key(EmailAddressMember, EmailAddressPet, PetName, Birthday),
	constraint FK1_Favorite foreign key(EmailAddressMember) references Member(EmailAddress),
	constraint FK2_Favorite	foreign key(EmailAddressPet, PetName, Birthday) references Pet(EmailAddress, Name, Birthday)
);
