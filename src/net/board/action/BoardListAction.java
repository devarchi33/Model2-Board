package net.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BoardDAO boardDao = new BoardDAO();
		List<BoardBean> boardList = new ArrayList<BoardBean>();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listCount = boardDao.getListCount(); //총 리스트 수를 받아옴.
		boardList = boardDao.getBoardList(page, limit); //리스트를 받아옴.
		
		//총 페이지수
		int maxPage = (int)((double)listCount/limit+0.95); //095를 더해서 올림 처리.
		
		//현재 페이지에 보여줄 시작 페이지 수.
		int startPage = (((int)((double)page /10 +0.9))-1)*10+1;
		
		//현재 페이지에서 보여줄 마지막 페이지 수
		int endPage = startPage + 10 -1;
		
		if(endPage>startPage) endPage = maxPage;
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("boardList", boardList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/qna_board_list.jsp");

		return forward;
	}

}
