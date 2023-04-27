package com.tripmate.domain.accountbook.dao.mapper;

import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.dailyplans.dto.DailyPlanDTO;

import java.util.List;

public interface AccountBookDAOMapper {
    int insertAccountWithDailyPlanDTO(DailyPlanDTO dailyPlanDTO);
    int deleteAccount(List<String> acccountNoList);
    AccountBookVO searchAccountListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
    int insertAccountWithAccountBookDTO(AccountBookDTO accountBookDTO);
    int updateAccountAmount(UpdateAccountBookDTO updateAccountBookDTO);
    int updatePlanDayGroupAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO);
    int updateAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO);
}
