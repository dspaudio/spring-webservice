insert into user (username, password, created_date, modified_date) 
  values ('유저영', '{noop}password', now(), now());
insert into user (username, password, created_date, modified_date) 
  values ('유저일', '{noop}password', now(), now());
insert into user (username, password, created_date, modified_date) 
  values ('유저이', '{noop}password', now(), now());
insert into user (username, password, created_date, modified_date) 
  values ('유저삼', '{noop}password', now(), now());
  
insert into user_authority (user_Id, authority) 
  values (SELECT id FROM USER WHERE username='유저영', 'ROLE_USER');
insert into user_authority (user_Id, authority) 
  values (SELECT id FROM USER WHERE username='유저일', 'ROLE_USER');
insert into user_authority (user_Id, authority) 
  values (SELECT id FROM USER WHERE username='유저이', 'ROLE_USER');
insert into user_authority (user_Id, authority) 
  values (SELECT id FROM USER WHERE username='유저삼', 'ROLE_USER');
 
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목1', '내용1', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목2', '내용2', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목3', '내용3', SELECT id FROM USER WHERE username='유저삼', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목4', '내용4', SELECT id FROM USER WHERE username='유저이', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목5', '내용5', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목6', '내용6', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목7', '내용7', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목8', '내용8', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목9', '내용9', SELECT id FROM USER WHERE username='유저이', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목10', '내용10', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목11', '내용11', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목12', '내용12', SELECT id FROM USER WHERE username='유저삼', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목13', '내용13', SELECT id FROM USER WHERE username='유저이', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목14', '내용14', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목15', '내용15', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목16', '내용16', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목17', '내용17', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목18', '내용18', SELECT id FROM USER WHERE username='유저영', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목19', '내용19', SELECT id FROM USER WHERE username='유저이', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목20', '내용20', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목21', '내용21', SELECT id FROM USER WHERE username='유저일', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목22', '내용22', SELECT id FROM USER WHERE username='유저삼', now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목23', '내용23', SELECT id FROM USER WHERE username='유저일', now(), now());
