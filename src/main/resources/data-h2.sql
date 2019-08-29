insert into user (user_Name, password, created_date, modified_date) 
  values ('유저영', 'pass', now(), now());
insert into user (user_Name, password, created_date, modified_date) 
  values ('유저일', 'pass', now(), now());
insert into user (user_Name, password, created_date, modified_date) 
  values ('유저이', 'pass', now(), now());
 
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('TEC', '제목1', '내용1', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('SOC', '제목2', '내용2', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목3', '내용3', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목4', '내용4', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into news (category, title, content, created_By_id, created_date, modified_date) 
  values ('CUL', '제목5', '내용5', SELECT id FROM USER WHERE rownum=1, now(), now());
