package com.tripmate.domain.accountbook.service;

import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.DeleteAccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;

public interface AccountBookService {
    AccountBookVO searchAccountListByDay(DailyPlanByDayDTO dailyPlanByDayDTO);
    boolean deleteAccount(DeleteAccountBookDTO deleteAccountBookDTO);
    boolean insertAccountWithAccountBookDTO(String planNo, String dayGroup, AccountBookDTO accountBookDTO);
    boolean updateAccountAmount(UpdateAccountBookDTO updateAccountBookDTO);
    boolean updateAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO);
}
