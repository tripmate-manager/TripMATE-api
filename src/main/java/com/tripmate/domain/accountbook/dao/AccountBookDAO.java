package com.tripmate.domain.accountbook.dao;

import com.tripmate.domain.accountbook.dao.mapper.AccountBookDAOMapper;
import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountBookDAO {
    private final SqlSession sqlSession;

    public int insertAccountWithDailyPlanDTO(DailyPlanDTO dailyPlanDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).insertAccountWithDailyPlanDTO(dailyPlanDTO);
    }

    public int deleteAccount(String dailyPlanNo) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).deleteAccount(dailyPlanNo);
    }

    public AccountBookVO searchAccountListByDay(DailyPlanByDayDTO dailyPlanByDayDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).searchAccountListByDay(dailyPlanByDayDTO);
    }

    public int insertAccountWithAccountBookDTO(AccountBookDTO accountBookDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).insertAccountWithAccountBookDTO(accountBookDTO);
    }

    public int updateAccountAmount(UpdateAccountBookDTO updateAccountBookDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).updateAccountAmount(updateAccountBookDTO);
    }

    public int updatePlanDayGroupAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).updatePlanDayGroupAccountSortSequence(updateAccountBookDTO);
    }

    public int updateAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO) {
        return sqlSession.getMapper(AccountBookDAOMapper.class).updateAccountSortSequence(updateAccountBookDTO);
    }
}
