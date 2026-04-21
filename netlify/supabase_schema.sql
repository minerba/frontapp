-- credit_scores 테이블: KCB/NICE 점수 저장
create table if not exists credit_scores (
  id serial primary key,
  agency text not null unique check (agency in ('KCB', 'NICE')),
  score integer not null check (score between 0 and 1000),
  updated_at timestamptz default now()
);

-- 초기 데이터
insert into credit_scores (agency, score) values
  ('KCB', 757),
  ('NICE', 876)
on conflict (agency) do nothing;

-- credit_history 테이블: 조회·변동 내역
create table if not exists credit_history (
  id serial primary key,
  agency text not null check (agency in ('KCB', 'NICE')),
  type text not null check (type in ('loan', 'score')),
  year text not null,
  date text not null,
  -- loan 타입 필드
  title text,
  description text,
  has_card boolean default false,
  -- score 타입 필드
  change_amount integer,
  score_from integer,
  score_to integer,
  created_at timestamptz default now()
);

-- 초기 KCB 히스토리
insert into credit_history (agency, type, year, date, title, description, has_card) values
  ('KCB', 'loan', '2026년', '3. 27.', '대출 잔액 변동', '한국스탠다드차타드 대출잔액이 변동됐어요', true),
  ('KCB', 'loan', '2026년', '2. 27.', '대출 잔액 변동', '한국스탠다드차타드 대출잔액이 변동됐어요', false),
  ('KCB', 'loan', '2026년', '1. 28.', '대출 잔액 변동', '한국스탠다드차타드 대출잔액이 변동됐어요', false);

-- 초기 NICE 히스토리
insert into credit_history (agency, type, year, date, title, description, has_card) values
  ('NICE', 'loan', '2026년', '3. 26.', '대출 잔액 변동', '한국스탠다드차타드은행 대출잔액이 변동됐어요', true);

insert into credit_history (agency, type, year, date, change_amount, score_from, score_to) values
  ('NICE', 'score', '2026년', '3. 3.', -8, 884, 876),
  ('NICE', 'score', '2025년', '11. 12.', 5, 879, 884),
  ('NICE', 'score', '2025년', '9. 4.', -3, 882, 879);

insert into credit_history (agency, type, year, date, title, description, has_card) values
  ('NICE', 'loan', '2025년', '12. 26.', '대출 잔액 변동', '한국스탠다드차타드은행 대출잔액이 변동됐어요', false),
  ('NICE', 'loan', '2025년', '11. 26.', '대출 잔액 변동', '한국스탠다드차타드은행 대출잔액이 변동됐어요', false);

-- RLS 비활성화 (개발용 - 프로덕션에서는 적절한 정책 설정 필요)
alter table credit_scores disable row level security;
alter table credit_history disable row level security;
