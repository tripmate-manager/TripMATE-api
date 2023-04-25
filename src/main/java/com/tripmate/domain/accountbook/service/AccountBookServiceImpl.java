package com.tripmate.domain.accountbook.service;

import com.tripmate.domain.accountbook.dao.AccountBookDAO;
import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.UpdateAccountBookDTO;
import com.tripmate.domain.accountbook.vo.AccountBookVO;
import com.tripmate.domain.dailyplans.dto.DailyPlanByDayDTO;
import com.tripmate.domain.plans.dao.PlanDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountBookServiceImpl implements AccountBookService {
    private final AccountBookDAO accountBookDAO;
    private final PlanDAO planDAO;

    @Override
    public AccountBookVO searchAccountListByDay(DailyPlanByDayDTO dailyPlanByDayDTO) {
        int planMateCnt = planDAO.getPlanMateCnt(dailyPlanByDayDTO.getPlanNo());

        AccountBookVO accountBookVOS = accountBookDAO.searchAccountListByDay(dailyPlanByDayDTO);

        AccountBookVO accountBookVOS2 = AccountBookVO.builder()
                .planNo(accountBookVOS.getPlanNo())
                .dayGroup(dailyPlanByDayDTO.getDayGroup())
                .tripTerm(accountBookVOS.getTripTerm())
                .dayAmountSum(accountBookVOS.getDayAmountSum())
                .planAmountSum(accountBookVOS.getPlanAmountSum())
                .planMateAmountSum(accountBookVOS.getPlanAmountSum() / planMateCnt)
                .planAmountSum(accountBookVOS.getPlanAmountSum())
                .accountList(accountBookVOS.getAccountList())
                .build();

        return accountBookVOS2;
    }

    @Override
    public boolean insertAccountWithAccountBookDTO(String planNo, AccountBookDTO accountBookDTO) {
        return accountBookDAO.insertAccountWithAccountBookDTO(accountBookDTO) == 1;
    }

    @Override
    public boolean updateAccountAmount(String planNo, UpdateAccountBookDTO updateAccountBookDTO) {
        return accountBookDAO.updateAccountAmount(updateAccountBookDTO) == 1;
    }

    @Override
    public boolean updateAccountSortSequence(String planNo, UpdateAccountBookDTO updateAccountBookDTO) {
        accountBookDAO.updatePlanDayGroupAccountSortSequence(updateAccountBookDTO);
        accountBookDAO.updateAccountSortSequence(updateAccountBookDTO);
        return true;
    }
}
