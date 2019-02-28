# import-excel
### <p> Oracle query to created Table:<p>  
````
CREATE TABLE ifo_steel_import_export(  
  RECORD_ID number(20,0) NOT NULL,  
  TAX_CODE varchar(15),  
  TRADER_NAME varchar2(300),  
  TRADER_ID int,  
  TRADER_TYPE varchar2(20),  
  TRADING_DATE date,  
  TRADING_TYPE varchar2(20),  
  PARTNER_NAME varchar2(200),  
  PARTNER_COUNTRY varchar2(200),  
  PRODUCT_TYPE varchar2(100),  
  PRODUCT_DETAIL varchar2(500),  
  VOLUME number(30, 3),  
  UNIT varchar2(30),  
  UNIT_PRICE number(30, 3),  
  EXCHANGE_RATE number(30, 3),  
  CURRENCY_CODE varchar2(20),  
  VALUE number(30,3),  
  CREATED_DATE date default SYSDATE,  
  CREATED_BY varchar2(50),  
  MODIFIED_DATE date  default sysdate,  
  MODIFIED_BY varchar2(50)  
  );  
ALTER TABLE ifo_steel_import_export ADD(  
  CONSTRAINT steel_pk PRIMARY KEY(RECORD_ID)
);  
  CREATE SEQUENCE seq_ifo_steel_import_export  
  MINVALUE 1  
  START WITH 1  
  INCREMENT BY 1  
  CACHE 10;  
  CREATE OR REPLACE TRIGGER steel_on_insert  
  BEFORE INSERT ON IPA.ifo_steel_import_export  
  FOR EACH ROW  
    BEGIN  
      SELECT seq_ifo_steel_import_export.nextval  
      into :new.RECORD_ID  
      from dual;  
    END;    
````
