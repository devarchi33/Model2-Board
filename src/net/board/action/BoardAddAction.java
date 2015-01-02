package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardAddAction implements Action{
	static Logger logger = LoggerFactory.getLogger(BoardAddAction.class);

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardData = new BoardBean();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "boardUpload";
		
		int fileSize = 5*1024*1024;
		
		realFolder  = request.getRealPath(saveFolder);
		
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request,
									     realFolder,
									     fileSize,
									     "euc-kr",
									     new DefaultFileRenamePolicy());
			
			boardData.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
			boardData.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
			boardData.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
			boardData.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
			boardData.setBOARD_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement()));

			result = boardDao.boardInsert(boardData);
			
			if(result == false) {
				logger.info("게시판 등록 실패.");
			}
			logger.info("게시판 등록 완료.");
			
			forward.setRedirect(true);
			forward.setPath("./BoardListAction.bo");
			return forward;
			
		}catch(Exception e) {
			logger.info("BoardAddAction 에러 : ",e);
		}
		
		return null;
	}

}
