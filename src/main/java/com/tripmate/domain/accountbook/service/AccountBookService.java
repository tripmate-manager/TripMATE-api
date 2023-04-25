package com.tripmate.domain.accountbook.service;

import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;

public interface AccountBookService {
    AccountBookVO searchAccountListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
    boolean insertAccountWithAccountBookDTO(String planNo, AccountBookDTO accountBookDTO);
    boolean updateAccountAmount(String planNo, UpdateAccountBookDTO updateAccountBookDTO);
    boolean updateAccountSortSequence(String planNo, UpdateAccountBookDTO updateAccountBookDTO);
}
