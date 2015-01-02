package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardReplyView implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardReplyView.class);
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boardBean = boardDao.getDetail(num);
		
		if(boardBean == null) {
			logger.info("답장 페이지 이동 실패.");
			return null;
		}
		logger.info("답장 페이지 이동 완료.");
		
		request.setAttribute("boardBean", boardBean);
		
		forward.setRedirect(false);
		forward.setPath("./board/qna_board_reply.jsp");
		
		return forward;
	}

}
