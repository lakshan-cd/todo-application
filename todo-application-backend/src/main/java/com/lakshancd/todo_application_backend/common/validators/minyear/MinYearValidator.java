/*
 * Copyright(C) 2023 ICP TECHNOLOGIES (PVT) LTD.
 * All rights reserved.
 *
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
 * ICP TECHNOLOGIES (PVT) LTD
 *
 * This copy of the Source Code is intended for ICP TECHNOLOGIES (PVT) LTD 's internal use only
 * and is intended for viewing  by personals duly authorized by the management of ICP TECHNOLOGIES (PVT) LTD.
 * No part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of ICP TECHNOLOGIES (PVT) LTD.
 *
 * Author: Pasindu Uduwela
 */

package com.lakshancd.todo_application_backend.common.validators.minyear;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MinYearValidator implements ConstraintValidator<MinYear, Year> {
    private int minYear;

    @Override
    public void initialize(MinYear constraintAnnotation) {
        this.minYear = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Year value, ConstraintValidatorContext context) {

        int currentYearUTC = ZonedDateTime.now(ZoneOffset.UTC).getYear();

        if (value == null) {
            return true;
        }

        return value.getValue() >= Math.max(minYear, currentYearUTC);
    }
}
