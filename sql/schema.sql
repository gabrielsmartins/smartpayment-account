create table tbl_account(account_id varchar(36) not null,
						 account_customer_id varchar(36) not null,
						 account_type varchar(256) not null,
						 account_status varchar(256) not null,
						 account_balance numeric(17,2) not null,
constraint pk_account primary key(account_id));

create table tbl_financial_transaction(financial_transaction_account_id varchar(36) not null,
                                       financial_transaction_identifier varchar(36) not null,
									   financial_transaction_created_at datetime not null,
									   financial_transaction_description varchar(256) not null,
									   financial_transaction_amount numeric(17,2) not null,
									   financial_transaction_account_balance numeric(17,2) not null,
									   financial_transaction_status varchar(256) not null,
									   financial_transaction_account_target_id varchar(36) default null,
constraint pk_financial_transaction primary key(financial_transaction_account_id ,financial_transaction_identifier),
constraint fk_source_account foreign key(financial_transaction_account_id) references tbl_account(account_id),
constraint fk_target_account foreign key(financial_transaction_account_target_id) references tbl_account(account_id));