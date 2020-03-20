package com.tj.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDao;
import myutil.MyConstant;
import myutil.Paging;
import vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpSession session;
	
	
	
	
	BoardDao board_dao;

	public void setBoard_dao(BoardDao board_dao) {
		this.board_dao = board_dao;
	}
	
	
	
	
	@RequestMapping("/board/list.do")
	public String list(Integer page,String search,String search_text,Model model) {
		
		int nowPage = 1; //현재페이지
		if(page!=null)
			nowPage = page;
		
		//시작과 끝
		int start = (nowPage-1) * MyConstant.Board.BLOCKLIST + 1;
		int end	  = start 		+ MyConstant.Board.BLOCKLIST - 1;
		
		//검색페이지/조건을 담을 객체
		Map map = new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		
		//검색카데고릭 없으면 전체검색
		if(search==null) search = "all";
		
		if(!search.equals("all")) {
			
			if(search.equals("subject_name_content")) {
				
				map.put("subject", search_text);
				map.put("name", search_text);
				map.put("content", search_text);
			
			}else if(search.equals("subject")) {
				//제목
				map.put("subject", search_text);
			}else if(search.equals("name")) {
				//이름
				map.put("name", search_text);
			}else if(search.equals("content")) {
				//내용
				map.put("content", search_text);
			}
			
		}
		
		//게시물 총갯수 구하기
		int rowTotal = board_dao.selectRowTotal(map);
		
		List<BoardVo> list = board_dao.selectList(map);

		//검색필터
		String search_filter = String.format("list.do?search=%s&search_text=%s", search, search_text);
		
		//pagemenu생성
		String pagemenu	= Paging.getPaging(
								search_filter, 
								nowPage, 
								rowTotal, 
								MyConstant.Board.BLOCKLIST, 
								MyConstant.Board.BLOCKPAGE
								);
		
		//이전에 봤다는 정보를 지원
		session.removeAttribute("show");
		
		model.addAttribute("list",list);
		model.addAttribute("pagemenu", pagemenu);
	
		
		return "board/board_list";
	}
	
	@RequestMapping("/board/view.do")
	public String view(int idx, Model model) {
		
		BoardVo vo = board_dao.selectOne(idx);
		
		if(session.getAttribute("show")==null) {
				
		int res = board_dao.update_readhit(idx);
		
		//봤다라는 정보 기록
		session.setAttribute("show", true);
		
		}
		
		//결과적으로 request binding
		model.addAttribute("vo", vo);
		
		return "board/board_view";
		
	}
	
	//글쓰기 폼 띄우기
	@RequestMapping("/board/insert_form.do")
	public String insert_form() {
		
		return "board/board_insert_form";
	}
	
	//답글쓰기
	@RequestMapping("/board/reply_form.do")
	public String reply_form() {
		
		return "board/board_reply_form";
	}
	
	//글쓰기
	@RequestMapping("/board/insert.do")
	public String insert(BoardVo vo) {
		
		//작성자ip
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = board_dao.insert(vo);
		
		
		return "redirect:list.do";
	}
	
	//답글쓰기
	@RequestMapping("/board/replay.do")
	public String reply(BoardVo vo, int page, Model model) {
		
		BoardVo baseVo = board_dao.selectOne(vo.getIdx());
		
		int res = board_dao.update_step(baseVo);
		
		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		vo.setRef(baseVo.getRef());
		vo.setStep(baseVo.getStep());
		vo.setDepth(baseVo.getDepth());
		
		res = board_dao.reply(vo);
		
		model.addAttribute("page", page);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/board/modify_form.do")
	public String modify_form(int idx, Model model) {
		
		BoardVo vo = board_dao.selectOne(idx);
		
		model.addAttribute("vo", vo);
		
		return "board/board_modify_form";
		
	}
	
	@RequestMapping("/board/modify.do")
	public String modify(BoardVo vo, Model model, int page, String search, String search_text){

		String ip = request.getRemoteAddr();
		vo.setIp(ip);
		
		int res = board_dao.update(vo);
		
		model.addAttribute("idx", vo.getIdx());
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("search_text", search_text);
		
		return "redirct:view.do";
	}
	
	@RequestMapping("/board/delete.do")
	public String delete(int idx, Model model,int page, String search, String search_text) {
		
		int res = board_dao.delete(idx);
		
		
		
		
		return search_text;
		
	}
	
	
	
}
