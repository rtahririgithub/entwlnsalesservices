select s.service_id
, translate(e.service_des,chr(49810)||chr(49792)||chr(49801)||chr(49814),''' '||chr(50089)||'-') service_des
, translate(e.service_des_french,chr(49810)||chr(49792)||chr(49801)||chr(49814),''' '||chr(50089)||'-') service_des_french
, s.kb_service_package_cd, s.kb_service_package_type_cd, s.product_type_cd 
       , nvl((select min(price)
                  from catalogue_item i, catalogue_item_price x, service sf
                  where i.sub_type_id = s.service_id
                  and i.catalogue_item_type_cd = 'SERVICE'
                  and sysdate between i.effective_dt and i.expiry_dt 
                  and sysdate between x.effective_dt and x.expiry_dt 
                  and x.catalogue_item_id = i.catalogue_item_id 
                  and x.price_class_id = 1 
                  and x.catalogue_item_id = i.catalogue_item_id
                  and sf.service_id = x.service_feature_service_id
                  and upper(trim(sf.service_des)) <> 'TERMINATION CHARGE'
                  and x.rating_lvl_grp_id is null
              ),0) base_price_amt 
      , case when trim(kb_service_package_cd) in ($$UnlimitedSMSList) or service_allowance_type_txt = 'Event' then 'SMS'
             when service_allowance_type_txt in ('KB','MB','GB') then 'Data'
             else service_allowance_type_txt end service_allowance_type
      , case when trim(kb_service_package_cd) in ($$UnlimitedSMSList) 
                then 99999
             when service_allowance_type_txt in ('KB') and service_allowance_qty / (1024*1024) = round(service_allowance_qty / (1024*1024),0)
                then service_allowance_qty / (1024*1024)
             when service_allowance_type_txt in ('KB','MB') and service_allowance_qty / 1024 = round(service_allowance_qty / 1024,0) 
                then service_allowance_qty / 1024
	 	when service_allowance_type_txt in ('GB')
		   then service_allowance_qty
             else round(service_allowance_qty,0) end service_allowance_qty
      , case when trim(kb_service_package_cd) in ($$UnlimitedSMSList) then 'Event'
             when service_allowance_type_txt = 'KB' and service_allowance_qty / (1024*1024) = round(service_allowance_qty / (1024*1024),0) then 'GB'
             when service_allowance_type_txt = 'KB' and service_allowance_qty / 1024 = round(service_allowance_qty / 1024,0) then 'MB'
             when service_allowance_type_txt = 'MB' and service_allowance_qty / 1024 = round(service_allowance_qty / 1024,0) then 'GB'
             else service_allowance_type_txt end service_allowance_type_txt
from service e, service_package s
,(select srvc_pkg_service_id, max(service_allowance_qty) service_allowance_qty, max(service_allowance_type_txt) service_allowance_type_txt   
  from service_allowance x, service_allowance_type t
  where x.service_allowance_type_id = t.service_allowance_type_id
    and systimestamp between x.effective_start_ts and x.effective_end_ts
    group by srvc_pkg_service_id ) v
where e.service_id = s.service_id
and e.service_id = v.srvc_pkg_service_id (+)
and s.for_sale_ind = 'Y' 
and s.kb_service_package_type_cd <> 'P'
and s.kb_service_package_status_cd = 'A'
and s.sale_effective_dt < sysdate 
and s.product_type_cd in ('C','I')
order by service_id