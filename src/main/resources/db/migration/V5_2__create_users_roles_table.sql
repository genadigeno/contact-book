create table public.user_roles (
    role_id int,
    user_id int,
    constraint fk_ur_users foreign key (user_id) references public.users(id) ON DELETE CASCADE,
    constraint fk_ur_roles foreign key (role_id) references public.roles(id) ON DELETE CASCADE
);