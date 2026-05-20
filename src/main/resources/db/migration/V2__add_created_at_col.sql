alter table todos
    add created_at date default (CURRENT_DATE) null;

