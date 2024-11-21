create table users(
	
	id bigint not null auto_increment,
	name varchar(100) not null,
	cpf varchar(50) not null,
	birth varchar(15) not null,
	phone varchar(15)not null,
	email varchar(100)not null,
	
	primary key(id)
	
);