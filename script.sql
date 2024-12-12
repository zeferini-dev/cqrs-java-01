DECLARE
    v_column_name VARCHAR2(100) := 'NOME_DA_COLUNA'; -- Substitua pelo nome da coluna desejada
BEGIN
    FOR rec IN (
        SELECT 
            OWNER,
            TABLE_NAME,
            COLUMN_NAME
        FROM 
            ALL_TAB_COLUMNS
        WHERE 
            COLUMN_NAME = UPPER(v_column_name)
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Owner: ' || rec.OWNER || ', Table: ' || rec.TABLE_NAME || ', Column: ' || rec.COLUMN_NAME);
    END LOOP;
END;
/
