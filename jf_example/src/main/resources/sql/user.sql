#sql("pageList")
select id,nick_name, to_char(create_time,'yyyy-MM-dd') as create_time from t_user
where create_time<#para(0)
 order by create_time
#end


