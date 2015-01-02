package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReplyAction implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardReplyAction.class);

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("euc-kr");
		ActionForward forward = new ActionForward();
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		int result = 0;
		
		boardBean.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boardBean.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boardBean.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boardBean.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boardBean.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		boardBean.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		boardBean.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
		
		result = boardDao.boardReply(boardBean);
		
		if(result == 0) {
			logger.info("답장 실패");
			return null;
		}
		
		logger.info("답장 성공");
		
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+result);
		
		return forward;
	}

}
