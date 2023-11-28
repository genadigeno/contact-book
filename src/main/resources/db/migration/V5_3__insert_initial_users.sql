insert into public.users (username, "password", "enabled", expired, "locked", firstname, lastname)
    values ('admin', '$2a$10$p75iJtjOUxUwV.HczbL2N.zGejxnVNKNyJBEAYqeMtRP.8nef8fOi', true, false, false, 'admin', 'admin');

insert into public.user_roles (role_id, user_id)
                select r.id as ROLE_ID, u.id as USER_ID from roles as r
                cross join users as u where u.username = 'admin' and r.name = 'ROLE_ADMIN'
;