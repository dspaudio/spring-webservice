insert into user (user_Name, password, created_date, modified_date) 
  values ('유저영', 'pass', now(), now());
insert into user (user_Name, password, created_date, modified_date) 
  values ('유저일', 'pass', now(), now());
insert into user (user_Name, password, created_date, modified_date) 
  values ('유저이', 'pass', now(), now());

insert into board (board_Name, category_Use_Flag, comment_Use_Flag, notice_Use_Flag, file_Use_Flag, del_Flag, created_By_id, created_date, modified_date) 
  values ('보드1', false, false, false, false, false, SELECT id FROM USER WHERE rownum=1, now(), now());
insert into board (board_Name, category_Use_Flag, comment_Use_Flag, notice_Use_Flag, file_Use_Flag, del_Flag, created_By_id, created_date, modified_date) 
  values ('보드2', false, false, false, false, false, SELECT id FROM USER WHERE rownum=1, now(), now());
 
insert into board_category (board_Id, order_No, board_Category_Name, del_Flag, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, 1, '카테고리1', false, now(), now());
 insert into Board_Category (board_Id, order_No, board_Category_Name, del_Flag, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, 2, '카테고리2', false, now(), now());
 insert into Board_Category (board_Id, order_No, board_Category_Name, del_Flag, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, 3, '카테고리3', false, now(), now());
 
insert into post (board_Id, board_Category_Id, title, content, created_By_id, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, SELECT id FROM Board_Category WHERE rownum=1, '제목1', '내용1', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into post (board_Id, board_Category_Id, title, content, created_By_id, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, SELECT id FROM Board_Category WHERE rownum=1, '제목2', '내용2', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into post (board_Id, board_Category_Id, title, content, created_By_id, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, SELECT id FROM Board_Category WHERE rownum=1, '제목3', '내용3', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into post (board_Id, board_Category_Id, title, content, created_By_id, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, SELECT id FROM Board_Category WHERE rownum=1, '제목4', '내용4', SELECT id FROM USER WHERE rownum=1, now(), now());
insert into post (board_Id, board_Category_Id, title, content, created_By_id, created_date, modified_date) 
  values (SELECT id FROM board WHERE rownum=1, SELECT id FROM Board_Category WHERE rownum=1, '제목5', '내용5', SELECT id FROM USER WHERE rownum=1, now(), now());
/*
insert into file (post_Id, id, file_Name, file_Url, file_Path, mime_Type, file_Size, created_date, modified_date) 
  values (1, 'ss', 'filename.jpg', '/2019/07/15/filename.jpg', '\2019\07\15\filename.jpg', 'image/jpeg', 1000, now(), now());
insert into file (post_Id, id, file_Name, file_Url, file_Path, mime_Type, file_Size, created_date, modified_date) 
  values (1, 'aa', 'filename2.jpg', '/2019/07/15/filename2.jpg', '\2019\07\15\filename2.jpg', 'image/jpeg', 2000, now(), now());
insert into file (post_Id, id, file_Name, file_Url, file_Path, mime_Type, file_Size, created_date, modified_date) 
  values (2, 'dd', 'filename3.jpg', '/2019/07/15/filename3.jpg', '\2019\07\15\filename3.jpg', 'image/jpeg', 3000, now(), now());*/
