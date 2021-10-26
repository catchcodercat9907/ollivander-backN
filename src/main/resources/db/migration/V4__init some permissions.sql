insert ignore into `feature` (feature_key, feature_label, `group`, version)
select 'COMMODITY_KEY', 'Commodity', 'Sales', 'ollivander_prod' from dual
where not exists(select id from `feature` where feature_key = 'COMMODITY_KEY');

insert ignore into `feature` (feature_key, feature_label, `group`, version)
select 'CUSTOMER_CARE_KEY', 'Customer Care', 'Cares', 'ollivander_prod' from dual
where not exists(select id from `feature` where feature_key = 'CUSTOMER_CARE_KEY');