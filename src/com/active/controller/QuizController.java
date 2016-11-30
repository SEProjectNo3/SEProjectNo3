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

		if (cmd==null){ // Quiz.do 들어오면 교수가 추가한 퀴즈 리스트와 퀴즈 추가하기를 띄움
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
		else if (cmd.equals("quiz_write_form")) { //퀴즈 등록 버튼을 입력했을때 퀴즈 등록하는 페이지로 넘겨줌
			String url = "/a-quiz-write.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} 
		else if (cmd.equals("quiz_write")) { //퀴즈 등록 페이지에서 등록을 하면 post 방식으로 보냄
			insertQuiz(request, response);
		} 
		else if (cmd.equals("quiz_detail")) { //퀴즈 리스트에서 quizNo와 일치하는 퀴즈 상세보기
			searchQuiz(request, response);
		} 
		else if (cmd.equals("quiz_delete")) { //퀴즈 상세보기에서 삭제 버튼을 누를 경우 quizNo을 통해 삭제함
			deleteQuiz(request, response);
		}
		else if(cmd.equals("quiz_modify_form")){//퀴즈 상세보기에서 quizNo를 통해 퀴즈를 수정하기 위한 페이지로 넘겨줌
			modifyQuizForm(request, response);
		}
		else if (cmd.equals("quiz_update")) { //퀴즈 수정 처리하는 부분
			updateQuiz(request, response);
		}
	}

	/*
	 * 퀴즈를 등록하는 메소드
	 * 보기(1,2,3,4)를 추가해주기 위해 quizNo을 검색 조건으로
	 * QuizChoice 모델의 insert를 해줌
	 * 성공적으로 퀴즈가 등록되면 퀴즈 리스트 화면으로 넘겨줌
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

		insertQuizChoice(quizNo, request, response); //QuizChoice에서 보기(1,2,3,4)를 추가하는 부분

		searchQuizList(request, response);
		
		//QuizListAll(request, response); //퀴즈 리스트를 보여줌
	}

	/*
	 * 퀴즈 보기를 추가해주는 메소드
	 * insertQuiz 에서 quizNo를 받아오고 
	 * 각각의 보기에 따라 반복문을 통해 insert를 해줌
	 */
	public void insertQuizChoice(int quizNo, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		QuizChoiceDAO quizChoiceDAO = QuizChoiceDAO.getInstance();

		QuizChoice quizChoice = new QuizChoice();

		for (int i = 1; i <= 4; i++) { //각각의 보기 번호에 따라 insert를 실행
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
	 * 퀴즈를 삭제하는 부분
	 * 페이지에서 quizNo을 받아옴
	 * 성공적으로 삭제가 된다면 퀴즈 리스트를 보여주는 메소드를 호출함
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
	 * 퀴즈 수정하는 폼 페이지
	 * 페이지에서 quizNo 파라미터를 받아와
	 * serachQuiz를 함
	 * Quiz와 QuizChoice를 setAttribute하고
	 * 수정 페이지로 넘겨줌
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
	 * 퀴즈 업데이트를 처리하는 메소드로
	 * 보기 관련 부분인 QuizChoice를 먼저 update 해줌
	 * 모든 수정이 완료된 후에는 퀴즈 리스트를 보여줌
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
	 * 보기(1,2,3,4)를 수정해주는 메소드
	 * 파라미터의 값을 반복문을 통해 확인하여 설정하고
	 * QuizChoice를 update 함
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
	 * 하나의 강의에서 갖고 있는 퀴즈 리스트를 찾음
	 * lectureId와 일치하는지 찾고 페이지를 넘김
	 */
	// 특정 렉쳐 검색하여 리스트 확인
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
	 * 수정 이후 퀴즈 리스트를 보여줌
	 * lectureId와 일치하는지 찾고 페이지를 넘김
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
	 * DB에 저장되어 있는 모든 퀴즈 리스트를 가져옴
	 * quizDAO로 받아온 퀴즈 리스트를 quizList라는 이름으로 setAttribute해줌
	 * 처리된 후에는 퀴즈 리스트가 보이는 페이지로 이동함
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
	 * 페이지에서 받아 온 quizNo을 통해 
	 * 퀴즈 부분을 나타내는 quiz를 찾고
	 * 퀴즈 보기부분을 나타내는 quizChoice의 리스트를 찾음
	 * 처리된 후에 퀴즈 리스트의 화면으로 넘어감
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
