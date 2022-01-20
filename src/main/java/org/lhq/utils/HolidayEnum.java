package org.lhq.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HolidayEnum {
	//农历节日
	ChineseNewYear("春节",1,1,true),
	//TombSweepingDay("清明节",1,1),
	DragonBoatFestival("端午节",5,5,true),
	MidAutumnFestival("中秋节",8,15,true),
	//公历节日
	NewYear("元旦",1,1,false),
	InternationalLaborDay("五一",5,1,false),
	NationalDay("国庆节",10,1,false);

	private String holidayName;
	private int holidayMonth;
	private int holidayDay;
	private boolean isLunar;
}
