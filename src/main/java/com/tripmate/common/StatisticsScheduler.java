package com.tripmate.common;

import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.statistics.dao.StatisticsDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatisticsScheduler {
    private final StatisticsDAO statisticsDAO;

    @Scheduled(cron = "0 0 0 * * *")
    public void planLikeStatisticsScheduler() {
        statisticsDAO.insertPlanLikeStatistics(ConstCode.STATISTICS_TERM_CODE_1_MONTH);
        statisticsDAO.insertPlanLikeStatistics(ConstCode.STATISTICS_TERM_CODE_3_MONTH);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void searchKeywordStatisticsScheduler() {
        statisticsDAO.insertSearchKeywordStatistics(ConstCode.STATISTICS_TERM_CODE_1_MONTH);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void planAttributeStatisticsScheduler() {
        statisticsDAO.insertPlanAttributeStatistics(ConstCode.STATISTICS_TERM_CODE_1_MONTH);
    }
}
