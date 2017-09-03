SELECT ep.*, eb.s_event_banner_position_cd,er1.s_event_recom_position_cd FROM hs_event.t_event_page as ep left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id left outer join hs_event.s_event_recom_1 as er1 on er1.h_event_id=ep.h_event_id;

SELECT ep.*, eb.s_event_banner_position_cd,er1.s_event_recom_position_cd FROM hs_event.t_event_page as ep  left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id left outer join hs_event.s_event_recom_1 as er1 on er1.h_event_id=ep.h_event_id order by er1.s_event_recom_position_cd asc, ep.h_event_id desc limit 6;

SELECT ep.*, eb.s_event_banner_position_cd,er1.s_event_recom_position_cd FROM (select * FROM hs_event.t_event_page where s_event_type_cd=1) as ep  left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id left outer join hs_event.s_event_recom_1 as er1 on er1.h_event_id=ep.h_event_id order by er1.s_event_recom_position_cd asc, ep.h_event_id desc limit 6;

SELECT ep.*, eb.s_event_banner_position_cd,er2.s_event_recom_position_cd FROM (select * FROM hs_event.t_event_page where s_event_type_cd=2) as ep  left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id left outer join hs_event.s_event_recom_2 as er2 on er2.h_event_id=ep.h_event_id order by er2.s_event_recom_position_cd asc, ep.h_event_id desc limit 6;

SELECT ep.*, eb.s_event_banner_position_cd,er3.s_event_recom_position_cd FROM (select * FROM hs_event.t_event_page where s_event_type_cd=3) as ep  left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id left outer join hs_event.s_event_recom_3 as er3 on er3.h_event_id=ep.h_event_id order by er3.s_event_recom_position_cd asc, ep.h_event_id desc limit 6;

---------------------------------------------------------------------------------------------------------------------------------------------------
SELECT * FROM hs_event.t_event_page order by h_event_id desc limit #{start}, #{end}

select count(1) from (SELECT h_event_id FROM hs_event.t_event_page order by h_event_id desc limit #{start}, #{end}) as t

SELECT ep.*, er1.s_event_recom_position_cd FROM (select * FROM hs_event.t_event_page where s_event_type_cd=1) as ep left outer join hs_event.s_event_recom_1 as er1 on er1.h_event_id=ep.h_event_id order by er1.s_event_recom_position_cd asc, ep.h_event_id desc limit #{start}, #{end}

SELECT COUNT(1) FROM (SELECT ep.h_event_id FROM (select h_event_id FROM hs_event.t_event_page where s_event_type_cd=1) as ep left outer join hs_event.s_event_recom_1 as er1 on er1.h_event_id=ep.h_event_id order by er1.s_event_recom_position_cd asc, ep.h_event_id desc limit #{start}, #{end}) as t

select * from hs_event.t_event_page where s_event_category_cd=#{sEventCategoryCd} and s_event_active_ind=0 order by h_event_id desc limit #{start}, #{end}

select count(1) from (select * from hs_event.t_event_page where s_event_category_cd=#{sEventCategoryCd} and s_event_active_ind=0 order by h_event_id desc limit #{start}, #{end}) as t;

select ep.* from (select * from hs_event.t_event_page where s_event_active_ind=0) as ep left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id limit #{start}, #{end}

select count(1) from (select ep.h_event_id from (select h_event_id from hs_event.t_event_page where s_event_active_ind=0) as ep left outer join hs_event.s_event_banner as eb on eb.h_event_id=ep.h_event_id limit #{start}, #{end}) as t


