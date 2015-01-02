package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.board.db.util.DbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDAO {
	static Logger logger = LoggerFactory.getLogger(BoardDAO.class);

	DbUtil util = new DbUtil();
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAO() {

		conn = util.getconnection();
	}

	// 글의 개수 구하기
	public int getListCount() {
		int x = 0;

		try {
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.info("getListCount 에러 : " + e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}
		return x;
	}

	// 글의 목록 구하기
	public List<BoardBean> getBoardList(int page, int limit) {
		String board_list_sql = "select * from (select rownum rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
				+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from"
				+ "(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc))"
				+ "where rnum>=? and rnum<=?";

		List<BoardBean> list = new ArrayList<BoardBean>();

		int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row 번호.
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호.

		try {
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}

			return list;
		} catch (Exception e) {
			logger.info("getBoardList 에러 : ", e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}
		return null;

	}

	// 글 내용 보기.
	public BoardBean getDetail(int num) throws Exception {
		BoardBean board = null;

		try {
			pstmt = conn
					.prepareStatement("select * from board where BOARD_NUM = ?");

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();

				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));

				return board;
			}
		} catch (Exception e) {
			logger.info("getDetail 에러 : " + e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}
		return null;
	}

	// 글 등록
	public boolean boardInsert(BoardBean board) {
		int num = 0;
		String sql = "";
		int result = 0;

		try {
			pstmt = conn.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			sql = "insert into board (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, "
					+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV,"
					+ "BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			result = pstmt.executeUpdate();

			if (result == 0)
				return false;

			return true;
		} catch (Exception e) {
			logger.info("Board Insert 에러 : " + e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}
		return false;
	}

	// 글 답변
	public int boardReply(BoardBean board) {
		String board_max_sql = "select max(board_num) from board";
		String sql = "";
		int num = 0;
		int result = 0;

		int re_ref = board.getBOARD_RE_REF();
		int re_lev = board.getBOARD_RE_LEV();
		int re_seq = board.getBOARD_RE_SEQ();

		try {
			pstmt = conn.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;

			sql = "update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1"
					+ "where BOARD_RE_REF=? and BOARD_RE_SEQ>?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);

			result = pstmt.executeUpdate();
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;

			sql = "insert into board (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, "
					+ "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV,"
					+ "BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, "");
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);

			pstmt.executeUpdate();

			return num;
		} catch (SQLException e) {
			logger.info("boardReply 에러 : ", e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}

		return 0;
	}

	// 글 수정
	public boolean boardModify(BoardBean modifyBoard) throws Exception {
		String sql = "update board set BOARD_SUBJECT=?, BOARD_CONTENT=?"
				+ "where BOARD_NUM=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, modifyBoard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyBoard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyBoard.getBOARD_NUM());
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			logger.info("boardModify 에러 : ", e);
		} finally {
			util.close(rs);
			util.close(pstmt);
		}
		return false;
	}

	// 글 삭제
	public boolean boardDelete(int num) {
		String board_delete_sql = "delete from board where BOARD_NUM=?";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception e) {
			logger.info("boardDelete 에러", e);
		} finally {
			try {
				util.close(pstmt);
			} catch (Exception e) {

			}
		}
		return false;
	}

	// 조회수 업데이트
	public void setReadCountUpdate(int num) throws Exception {
		String sql = "update board set BOARD_READCOUNT = BOARD_READCOUNT +1 "
				+ "where BOARD_NUM=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("setReadCOuntUpdate 에러 : ", e);
		} finally {
			try {
				util.close(pstmt);
			} catch (Exception e) {

			}
		}
	}

	// 글쓴이 인지 확인
	public boolean isBoardWriter(int num, String pass) {
		String board_sql = "select * from board where BOARD_NUM=?";

		try {
			pstmt = conn.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();

			if (pass.equals(rs.getString("BOARD_PASS"))) {
				return true;
			}
		} catch (SQLException e) {
			logger.info("isBoardWriter : ", e);
		}

		return false;
	}
}