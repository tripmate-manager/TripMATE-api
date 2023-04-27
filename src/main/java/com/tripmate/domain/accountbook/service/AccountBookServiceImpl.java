package com.tripmate.domain.accountbook.service;

import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.accountbook.dao.AccountBookDAO;
import com.tripmate.domain.accountbook.dto.AccountBookDTO;
import com.tripmate.domain.accountbook.dto.DeleteAccountBookDTO;
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

        return AccountBookVO.builder()
                .planNo(accountBookVOS.getPlanNo())
                .dayGroup(dailyPlanByDayDTO.getDayGroup())
                .tripTerm(accountBookVOS.getTripTerm())
                .dayAmountSum(accountBookVOS.getDayAmountSum())
                .planAmountSum(accountBookVOS.getPlanAmountSum())
                .planMateAmountSum(accountBookVOS.getPlanAmountSum() / planMateCnt)
                .planAmountSum(accountBookVOS.getPlanAmountSum())
                .accountList(accountBookVOS.getAccountList())
                .build();
    }

    @Override
    public boolean deleteAccount(DeleteAccountBookDTO deleteAccountBookDTO) {
        return accountBookDAO.deleteAccount(deleteAccountBookDTO.getAccountNoList()) == deleteAccountBookDTO.getAccountNoList().size();
    }

    @Override
    public boolean insertAccountWithAccountBookDTO(String planNo, String dayGroup, AccountBookDTO accountBookDTO) {
        if (!planNo.equals(accountBookDTO.getPlanNo()) || !dayGroup.equals(accountBookDTO.getDayGroup())) {
            throw new WrongParameterException("여행 가계부 항목 생성 처리 중 오류가 발생하였습니다.");
        }

        return accountBookDAO.insertAccountWithAccountBookDTO(accountBookDTO) == 1;
    }

    @Override
    public boolean updateAccountAmount(UpdateAccountBookDTO updateAccountBookDTO) {
        return accountBookDAO.updateAccountAmount(updateAccountBookDTO) == 1;
    }

    @Override
    public boolean updateAccountSortSequence(UpdateAccountBookDTO updateAccountBookDTO) {
        return accountBookDAO.updatePlanDayGroupAccountSortSequence(updateAccountBookDTO) > 0;
    }
}
