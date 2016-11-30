package com.active.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.active.dao.QuizChoiceDAO;
import com.active.dao.QuizDAO;
import com.active.model.Quiz;
import com.active.model.QuizChoice;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/Quiz.do")
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuizController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("get in quizController");
		actionDo(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("post in quizController");
		actionDo(request, response);

	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("actionDo");
		System.out.println("Quiz controller");
		request.setCharacterEncoding("UTF-8");

		String cmd = request.getParameter("cmd");

		if (cmd==null){ // Quiz.do ������ ������ �߰��� ���� ����Ʈ�� ���� �߰��ϱ⸦ ���
			String url = "/quiz-professor.jsp";
			
			searchQuizList(request, response);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else if(cmd.equals("studying")){
			String url = "/a-quiz-write.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		else if (cmd.equals("quiz_write_form")) { //���� ��� ��ư�� �Է������� ���� ����ϴ� �������� �Ѱ���
			String url = "/a-quiz-write.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} 
		else if (cmd.equals("quiz_write")) { //���� ��� ���������� ����� �ϸ� post ������� ����
			insertQuiz(request, response);
		} 
		else if (cmd.equals("quiz_detail")) { //���� ����Ʈ���� quizNo�� ��ġ�ϴ� ���� �󼼺���
			searchQuiz(request, response);
		} 
		else if (cmd.equals("quiz_delete")) { //���� �󼼺��⿡�� ���� ��ư�� ���� ��� quizNo�� ���� ������
			deleteQuiz(request, response);
		}
		else if(cmd.equals("quiz_modify_form")){//���� �󼼺��⿡�� quizNo�� ���� ��� �����ϱ� ���� �������� �Ѱ���
			modifyQuizForm(request, response);
		}
		else if (cmd.equals("quiz_update")) { //���� ���� ó���ϴ� �κ�
			updateQuiz(request, response);
		}
	}

	/*
	 * ��� ����ϴ� �޼ҵ�
	 * ����(1,2,3,4)�� �߰����ֱ� ���� quizNo�� �˻� ��������
	 * QuizChoice ���� insert�� ����
	 * ���������� ��� ��ϵǸ� ���� ����Ʈ ȭ������ �Ѱ���
	 */
	public void insertQuiz(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int quizNo;

		QuizDAO quizDAO = QuizDAO.getInstance();

		Quiz quiz = new Quiz();

		System.out.println(request.getParameter("answer"));

		
		
		quiz.setQuestion(request.getParameter("question"));
		quiz.setQuizTime(request.getParameter("quizTime"));
		quiz.setAnswer(Integer.parseInt(request.getParameter("answer")));
		//quiz.setAnswer(request.getParameter("answer"));
		quiz.setExplanation(request.getParameter("explanation"));
		quiz.setLectureId(request.getParameter("lectureId"));

		quizNo = quizDAO.insertQuiz(quiz);

		insertQuizChoice(quizNo, request, response); //QuizChoice���� ����(1,2,3,4)�� �߰��ϴ� �κ�

		searchQuizList(request, response);
		
		//QuizListAll(request, response); //���� ����Ʈ�� ������
	}

	/*
	 * ���� ���⸦ �߰����ִ� �޼ҵ�
	 * insertQuiz ���� quizNo�� �޾ƿ��� 
	 * ������ ���⿡ ���� �ݺ����� ���� insert�� ����
	 */
	public void insertQuizChoice(int quizNo, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();

		QuizChoice quizChoice = new QuizChoice();

		for (int i = 1; i <= 4; i++) { //������ ���� ��ȣ�� ���� insert�� ����
			switch (i) {
			case 1:
				quizChoice.setAnswer(request.getParameter("choiceNumber1"));
				break;
			case 2:
				quizChoice.setAnswer(request.getParameter("choiceNumber2"));
				break;
			case 3:
				quizChoice.setAnswer(request.getParameter("choiceNumber3"));
				break;
			case 4:
				quizChoice.setAnswer(request.getParameter("choiceNumber4"));
				break;
			}
			quizChoice.setChoiceNumber(i);
			quizChoice.setQuizNo(quizNo);

			quizChoiceDAO.insertQuizChoice(quizChoice);
		}
	}

	/*
	 * ��� �����ϴ� �κ�
	 * ���������� quizNo�� �޾ƿ�
	 * ���������� ������ �ȴٸ� ���� ����Ʈ�� �����ִ� �޼ҵ带 ȣ����
	 */
	public boolean deleteQuiz(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int quizNo = Integer.parseInt(request.getParameter("quizNo"));
		
		QuizDAO quizDAO = QuizDAO.getInstance();

		boolean res = quizDAO.deleteQuiz(quizNo);

		if (res) {
			System.out.println("delete quiz success");
			
			searchQuizList(request, response);
			
			return true;
		} else {
			System.out.println("delete quiz failed");
			return false;
		}
	}

	/*
	 * ���� �����ϴ� �� ������
	 * ���������� quizNo �Ķ���͸� �޾ƿ�
	 * serachQuiz�� ��
	 * Quiz�� QuizChoice�� setAttribute�ϰ�
	 * ���� �������� �Ѱ���
	 */
	public void modifyQuizForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String url = "/a-quiz-update.jsp";
		String url = "/quiz-modify.jsp";
		int quizNo = Integer.parseInt(request.getParameter("quizNo"));
		
		QuizDAO quizDAO = QuizDAO.getInstance();
		Quiz quiz = quizDAO.searchQuiz(quizNo);
		request.setAttribute("quiz", quiz);
		
		ArrayList<Quiz> quizList = quizDAO.searchQuizList("cse4036-01-01");
		request.setAttribute("quizList", quizList);
		
		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();
		ArrayList<QuizChoice> quizChoiceList = quizChoiceDAO.searchQuizChoiceList(quizNo);
		request.setAttribute("quizChoiceList", quizChoiceList);	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	/*
	 * ���� ������Ʈ�� ó���ϴ� �޼ҵ��
	 * ���� ���� �κ��� QuizChoice�� ���� update ����
	 * ��� ������ �Ϸ�� �Ŀ��� ���� ����Ʈ�� ������
	 */
	public void updateQuiz(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int quizNo = Integer.parseInt(request.getParameter("quizNo"));
		
		updateQuizChoice(quizNo, request, response);
		
		Quiz quiz = new Quiz();

		quiz.setQuizNo(Integer.parseInt(request.getParameter("quizNo")));
		quiz.setQuestion(request.getParameter("question"));
		quiz.setQuizTime(request.getParameter("quizTime"));
		quiz.setAnswer(Integer.parseInt(request.getParameter("answer")));
		quiz.setExplanation(request.getParameter("explanation"));
		quiz.setLectureId(request.getParameter("lectureId"));

		QuizDAO quizDAO = QuizDAO.getInstance();
		quizDAO.updateQuiz(quiz);
		
		//QuizListAll(request, response);
		//searchQuizListAfterUpdate(request, response);
		searchQuizList(request, response);
	}

	/*
	 * ����(1,2,3,4)�� �������ִ� �޼ҵ�
	 * �Ķ������ ���� �ݺ����� ���� Ȯ���Ͽ� �����ϰ�
	 * QuizChoice�� update ��
	 */
	public void updateQuizChoice(int quizNo, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();

		QuizChoice quizChoice = new QuizChoice();

		for (int i = 1; i <= 4; i++) {
			switch (i) {
			case 1:
				quizChoice.setAnswer(request.getParameter("choiceNumber1"));
				break;
			case 2:
				quizChoice.setAnswer(request.getParameter("choiceNumber2"));
				break;
			case 3:
				quizChoice.setAnswer(request.getParameter("choiceNumber3"));
				break;
			case 4:
				quizChoice.setAnswer(request.getParameter("choiceNumber4"));
				break;
			}
			quizChoice.setChoiceNumber(i);
			quizChoice.setQuizNo(quizNo);

			quizChoiceDAO.updateQuizChoice(quizChoice);
		}
	}
	
	/*
	 * �ϳ��� ���ǿ��� ���� �ִ� ���� ����Ʈ�� ã��
	 * lectureId�� ��ġ�ϴ��� ã�� �������� �ѱ�
	 */
	// Ư�� ���� �˻��Ͽ� ����Ʈ Ȯ��
	public void searchQuizList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String url = "/Quiz.do";
		String url = "/quiz-professor.jsp";
		
		System.out.println("get in searchQuizList");

		QuizDAO quizDAO = QuizDAO.getInstance();

		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();
		
		ArrayList<Quiz> quizList = quizDAO.searchQuizList("cse4036-01-01");
		request.setAttribute("quizList", quizList);

		ArrayList<Integer> quizNoList = quizDAO.getQuizNoList("cse4036-01-01");
		
		request.setAttribute("quizNoList", quizNoList);
		
		int i=1;
		for(int quizNo : quizNoList){
			ArrayList<QuizChoice> quizChoiceList = quizChoiceDAO.searchQuizChoiceList(quizNo);
			
			request.setAttribute("quizChoiceList"+"i", quizChoiceList);
		}
		

		//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		//dispatcher.forward(request, response);
	}
	
	/*
	 * ���� ���� ���� ����Ʈ�� ������
	 * lectureId�� ��ġ�ϴ��� ã�� �������� �ѱ�
	 */
	public void searchQuizListAfterUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/Quiz.do";
		//String url = "/quiz-professor.jsp";
		
		System.out.println("get in searchQuizList");

		QuizDAO quizDAO = QuizDAO.getInstance();

		ArrayList<Quiz> quizList = quizDAO.searchQuizList("cse4036-01-01");

		request.setAttribute("quizList", quizList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/*
	 * DB�� ����Ǿ� �ִ� ��� ���� ����Ʈ�� ������
	 * quizDAO�� �޾ƿ� ���� ����Ʈ�� quizList��� �̸����� setAttribute����
	 * ó���� �Ŀ��� ���� ����Ʈ�� ���̴� �������� �̵���
	 */
	public void QuizListAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/quiz-professor.jsp";

		System.out.println("get in QuizListAll");

		QuizDAO quizDAO = QuizDAO.getInstance();

		ArrayList<Quiz> quizList = quizDAO.searchQuizListAll(); 

		request.setAttribute("quizList", quizList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	/*
	 * ���������� �޾� �� quizNo�� ���� 
	 * ���� �κ��� ��Ÿ���� quiz�� ã��
	 * ���� ����κ��� ��Ÿ���� quizChoice�� ����Ʈ�� ã��
	 * ó���� �Ŀ� ���� ����Ʈ�� ȭ������ �Ѿ
	 */
	public void searchQuiz(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "a-quiz-detail.jsp";

		String quizNo = request.getParameter("quizNo");

		QuizDAO quizDAO = QuizDAO.getInstance();
		Quiz quiz = quizDAO.searchQuiz(Integer.parseInt(quizNo));

		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();
		ArrayList<QuizChoice> quizChoiceList = quizChoiceDAO.searchQuizChoiceList(Integer.parseInt(quizNo));

		request.setAttribute("quiz", quiz); 
		request.setAttribute("quizChoiceList", quizChoiceList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
