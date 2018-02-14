alter table PRODUCT drop constraint FK66rtd2on6u46jc2lj0as98lxk;
alter table PRODUCT_SALE drop constraint FKje544hl9ot9ut4kincy0ckej9;
alter table PRODUCT_SALE drop constraint FKlssa8hgrm82e7eelnrqoxn3yp;
alter table PRODUCT_SALES_BATCH drop constraint FKj0qtuv6u3dmntltefak2lcjn0;
alter table PRODUCT_SALES_BATCH drop constraint FK47kf8kip6696ilh10mk09iaan;
alter table PRODUCT_SALES_BATCH_STATUS drop constraint FKod498y5o5tjy2388mk34i3g8j;
drop table if exists PRODUCT cascade;
drop table if exists PRODUCT_CATEGORY cascade;
drop table if exists PRODUCT_SALE cascade;
drop table if exists PRODUCT_SALES_BATCH cascade;
drop table if exists PRODUCT_SALES_BATCH_STATUS cascade;
drop sequence PRODUCT_CATEGORY_ID_SEQ;
drop sequence PRODUCT_SALE_ID_SEQ;
drop sequence PRODUCT_SALES_BATCH_ID_SEQ;