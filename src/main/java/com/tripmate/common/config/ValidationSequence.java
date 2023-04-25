package com.tripmate.common.config;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import static com.tripmate.common.config.ValidationGroups.NotBlankGroup;
import static com.tripmate.common.config.ValidationGroups.NumberTypeGroup;
import static com.tripmate.common.config.ValidationGroups.PatternCheckGroup;

@GroupSequence({NotBlankGroup.class,
                NumberTypeGroup.class,
                PatternCheckGroup.class,
                Default.class})
public interface ValidationSequence {
}
