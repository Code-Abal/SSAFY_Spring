SET SQL_SAFE_UPDATES = 0;

-- 서울
UPDATE venues
SET region = '서울'
WHERE sidonm IN ('서울', '서울특별시');

-- 경기 / 인천
UPDATE venues
SET region = '경기/인천'
WHERE sidonm IN ('경기', '경기도', '인천', '인천광역시');

-- 충청 / 강원
UPDATE venues
SET region = '충청/강원'
WHERE sidonm IN (
  '대전', '대전광역시',
  '세종',
  '충북', '충청북도',
  '충남', '충청남도',
  '강원', '강원도', '강원특별자치도'
);

-- 대구 / 경북
UPDATE venues
SET region = '대구/경북'
WHERE sidonm IN ('대구', '대구광역시', '경북', '경상북도');

-- 부산 / 경남
UPDATE venues
SET region = '부산/경남'
WHERE sidonm IN ('부산', '부산광역시', '울산', '울산광역시', '경남', '경상남도');

-- 광주 / 전라
UPDATE venues
SET region = '광주/전라'
WHERE sidonm IN (
  '광주', '광주광역시',
  '전북', '전라북도', '전북특별자치도',
  '전남', '전라남도'
);

-- 제주
UPDATE venues
SET region = '제주'
WHERE sidonm = '제주'
   OR sidonm LIKE '제주%';

-- 나머지
UPDATE venues
SET region = '기타'
WHERE region IS NULL OR region = '';

SET SQL_SAFE_UPDATES = 1;
