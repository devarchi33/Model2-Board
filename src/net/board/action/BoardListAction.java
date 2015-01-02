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
		
		int listCount = boardDao.getListCount(); //�� ����Ʈ ���� �޾ƿ�.
		boardList = boardDao.getBoardList(page, limit); //����Ʈ�� �޾ƿ�.
		
		//�� ��������
		int maxPage = (int)((double)listCount/limit+0.95); //095�� ���ؼ� �ø� ó��.
		
		//���� �������� ������ ���� ������ ��.
		int startPage = (((int)((double)page /10 +0.9))-1)*10+1;
		
		//���� ���������� ������ ������ ������ ��
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
