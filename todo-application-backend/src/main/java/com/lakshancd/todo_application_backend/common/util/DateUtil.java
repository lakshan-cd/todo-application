/*
 *   Copyright(C) 2023 ICP TECHNOLOGIES (PVT) LTD.
 *   All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
 *   ICP TECHNOLOGIES (PVT) LTD
 *
 *   This copy of the Source Code is intended for ICP TECHNOLOGIES (PVT) LTD 's internal use only
 *   and is intended for viewing  by personals duly authorized by the management of ICP TECHNOLOGIES (PVT) LTD.
 *   No part of this file may be reproduced or distributed in any form or by any
 *   means without the written approval of the Management of ICP TECHNOLOGIES (PVT) LTD.
 *
 *   Author: Oshada Eranga
 */

package com.lakshancd.todo_application_backend.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    // Format - yyyy_MM_dd_HH_mm_ss (2023-10-01 14:34:15)
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    // Format - ddMMyyyy (02102023)
    public static String ddMMyyyy = "ddMMyyyy";

    // Format - HHmmss (132200)
    public static String HHmmss = "mmssSS";

    /**
     * Returns time in HHmmss Format
     * Current Time : 15:09:57 ----> Returns 150957
     * @return Formated Time
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat(HHmmss);
        String time = df.format(date);
        return time;
    }

    /**
     * Returns Date for given Format
     * @param date Current Date
     * @param dateFormat Date Format
     * @return Formtted Date
     */
    public static String format(final Date date, final String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }
}
