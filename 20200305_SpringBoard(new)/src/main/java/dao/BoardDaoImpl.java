package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVo;

public class BoardDaoImpl implements BoardDao {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	public List<BoardVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.board_list");
	}


	@Override
	public int selectRowTotal(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_row_total", map);
	}


	@Override
	public List<BoardVo> selectList(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.board_condition_list", map);
	}


	@Override
	public BoardVo selectOne(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.board_one", idx);
	}


	@Override
	public int update_readhit(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_readhit", idx);
	}


	@Override
	public int insert(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_insert", vo);
	}


	@Override
	public int update_step(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update_step", vo);
	}


	@Override
	public int reply(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.board_reply", vo);
	}


	@Override
	public int update(BoardVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("board.board_update", vo);
	}


	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("board.board_delete", idx);
	}

	
}
