-- 코드를 입력하세요
WITH RECURSIVE TIME AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1 FROM TIME
    WHERE HOUR < 23
)
SELECT HOUR 'HOUR', count(ANIMAL_ID) 'COUNT'
FROM ANIMAL_OUTS RIGHT JOIN TIME
ON HOUR(ANIMAL_OUTS.datetime) = TIME.HOUR
GROUP BY HOUR
ORDER BY HOUR;