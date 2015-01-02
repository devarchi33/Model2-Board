------------------------------------------------------------------
--  TABLE BOARD
------------------------------------------------------------------

CREATE TABLE BOARD
(
   BOARD_NUM         NUMBER,
   BOARD_NAME        VARCHAR2 (20),
   BOARD_PASS        VARCHAR2 (15),
   BOARD_SUBJECT     VARCHAR2 (50),
   BOARD_FILE        VARCHAR2 (50),
   BOARD_RE_REF      NUMBER,
   BOARD_RE_LEV      NUMBER,
   BOARD_RE_SEQ      NUMBER,
   BOARD_DATE        DATE,
   BOARD_READCOUNT   NUMBER,
   BOARD_CONTENT     VARCHAR2 (2000)
)
NOCACHE
LOGGING;