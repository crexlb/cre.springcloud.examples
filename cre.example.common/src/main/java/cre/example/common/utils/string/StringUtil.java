package cre.example.common.utils.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.function.Consumer;

public class StringUtil {
	/**
	 * @判断字符串对象是否为空
	 */
	public static boolean isEmpty(String temp) {
		boolean b = true;
		if (temp != null) {
			temp = temp.toLowerCase();
			b = temp == " " || temp.equals(" ") || temp == "null" || temp.equals("null") || temp == "undefined"
					|| temp.equals("undefined") || temp.length() <= 0;
		}
		return b;
	}

	public static void isNotEmpty(String temp, Consumer<String> consumer) {
		if (!isEmpty(temp)) {
			consumer.accept(temp);
		}
	}

	public static void isEmpty(String temp, Consumer<String> consumer) {
		if (isEmpty(temp)) {
			consumer.accept(temp);
		}
	}
	/**
	 * @获取UUID
	 */
	public static String toUuId() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}
	/**
	 * @把当前字符串转换成日期
	 */
	public static Date toDate(String dateString) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date toNowDate() {
		return new Date();
	}

	public static Date toAddDate(int calender, int temp) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());
		rightNow.add(calender, temp);
		return rightNow.getTime();
	}

	public static Date toAddYearDate(int temp) {
		return toAddDate(Calendar.YEAR, temp);
	}

	public static Date toAddMonthDate(int temp) {
		return toAddDate(Calendar.MONTH, temp);
	}

	public static Date toAddDayDate(int temp) {
		return toAddDate(Calendar.DAY_OF_YEAR, temp);
	}

	public static Date toAddHourDate(int temp) {
		return toAddDate(Calendar.HOUR, temp);
	}

	public static Date toAddMinuteDate(int temp) {
		return toAddDate(Calendar.MINUTE, temp);
	}

	public static Date toAddSecondDate(int temp) {
		return toAddDate(Calendar.SECOND, temp);
	}

	/**
	 * @把当前日期转换成字符串
	 */
	public static String toDateString(Date date) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String toDateString() {
		return toDateString(new Date());
	}

	public static String toDateString(Date date, String format) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat(format).format(date);
	}

	public static String toTimeString(Date date) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/**
	 * @将一个字符串的首字母改为大写或者小写
	 * @param srcString
	 *            源字符串
	 * @param大小写标识，ture小写，false大些
	 */
	public static String toLowerCaseInitial(String srcString, boolean flag) {
		StringBuilder sb = new StringBuilder();
		if (flag) {
			sb.append(Character.toLowerCase(srcString.charAt(0)));
		} else {
			sb.append(Character.toUpperCase(srcString.charAt(0)));
		}
		sb.append(srcString.substring(1));
		return sb.toString();
	}

	/**
	 * @日期差天数、小时、分钟、秒数组
	 */
	public static long[] getDisTime(Date startDate, Date endDate) {
		long timesDis = Math.abs(startDate.getTime() - endDate.getTime());
		long day = timesDis / (1000 * 60 * 60 * 24);
		long hour = timesDis / (1000 * 60 * 60) - day * 24;
		long min = timesDis / (1000 * 60) - day * 24 * 60 - hour * 60;
		long sec = timesDis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
		return new long[] { day, hour, min, sec };
	}

	/**
	 * @日期差天数、小时、分钟、秒
	 */
	public static String getDisTimeStr(Date startDate, Date endDate) {
		long[] dis = getDisTime(startDate, endDate);
		StringBuilder descString = new StringBuilder();
		if (dis[0] > 0) {
			descString.append(dis[0]).append("天 ");
		}
		if (dis[1] > 0) {
			descString.append(dis[1]).append("小时 ");
		}
		if (dis[2] > 0) {
			descString.append(dis[2]).append("分钟 ");
		}
		if (dis[3] > 0) {
			descString.append(dis[3]).append("秒钟");
		}
		return descString.toString();
	}
}
