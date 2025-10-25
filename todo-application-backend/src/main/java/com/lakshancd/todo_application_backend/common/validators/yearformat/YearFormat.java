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

package com.lakshancd.todo_application_backend.common.validators.yearformat;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearFormatValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearFormat{
        String message() default "Year must be in the format 'yyyy'";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
