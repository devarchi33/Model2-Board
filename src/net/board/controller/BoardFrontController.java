package net.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.BoardAddAction;
import net.board.action.BoardDeleteAction;
import net.board.action.BoardDetailAction;
import net.board.action.BoardListAction;
import net.board.action.BoardModifyAction;
import net.board.action.BoardModifyView;
import net.board.action.BoardReplyAction;
import net.board.action.BoardReplyView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.board.action.*;

/**
 * Servlet implementation class BoardFrontController
 */

@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(BoardFrontController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFrontController() {
		super();
	}

	public void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;

		if (command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		}  else if (command.equals("/BoardDelete.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");

		} else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardAddAction 에러 : ", e);
			}
		}else if (command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardReplyAction 에러 : ", e);
			}
		} else if (command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardReplyView 에러 : ", e);
			}
		} else if (command.equals("/BoardModifyAction.bo")) {
			action = new BoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardModifyAction 에러 : ", e);
			}
		} else if (command.equals("/BoardModify.bo")) {
			action = new BoardModifyView();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardModify 에러 : ", e);
			}
		} else if (command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardDeleteAction 에러 : ", e);
			}
		} else if (command.equals("/BoardListAction.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardListAction 에러 : ", e);
			}
		} else if (command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				logger.info("BoardDetailAction 에러 : ", e);
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
				logger.info("Redirect : "+forward.getPath());
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				logger.info("Forwading : "+forward.getPath());
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
