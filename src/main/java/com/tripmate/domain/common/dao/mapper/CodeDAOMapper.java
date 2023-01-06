package com.tripmate.domain.common.dao.mapper;

import com.tripmate.domain.common.dto.CodeDTO;

import java.util.List;
import java.util.Map;

public interface CodeDAOMapper {
    List<CodeDTO> selectCommonCodeDetailList(String commonCode);
    CodeDTO selectCommonCodeDetail(Map<String, Object> map);
}