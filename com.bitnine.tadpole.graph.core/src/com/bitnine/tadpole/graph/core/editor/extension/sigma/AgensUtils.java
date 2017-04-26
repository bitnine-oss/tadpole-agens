package com.bitnine.tadpole.graph.core.editor.extension.sigma;

import java.util.Random;

import org.apache.log4j.Logger;

public class AgensUtils {

	/**
	 * Logger for this class
	 */
	private static final Random oRandom = new Random();

	public AgensUtils() {
	}

	/**
	 * 데이터베이스의 컬럼 사이즈에 따라서 입력가능한 가장 큰수를 정수범위 내에서 조회한다.
	 * 
	 * @param size
	 * @return
	 */
	public static int getMaxBySize(int size) {
		if (size > 9) {
			return Integer.MAX_VALUE;
		} else {
			return Integer.parseInt("999999999".substring(0, size));
		}
		// int -2147483648 ~ 2147483647
		// long -9223372036854775808 ~ 9223372036854775807
	}

	public static long getRandomNumber(int size) {
		int targetVal = 0;
		int max = getMaxBySize(size);
		int min = 3;

		try {
			targetVal = oRandom.nextInt(getMaxBySize(size));

			if (max < targetVal)
				targetVal = targetVal - (targetVal - max);
			
			if (min > targetVal)
				targetVal = min;

		} catch (Exception e) {
			targetVal = getMaxBySize(size);
		}

		//logger.debug("getRandomNumber:" + size + "=" + targetVal);

		return targetVal;
	}

	public static long getRandomNumberbyLimit(String limit, int size) {
		int userLimitVal = 0;
		int targetVal = 0;
		try {
			userLimitVal = Integer.parseInt(limit);
			targetVal = oRandom.nextInt(userLimitVal);

			if (targetVal > userLimitVal)
				targetVal = userLimitVal - (targetVal - userLimitVal);

			if (targetVal > getMaxBySize(size))
				targetVal = Math.abs(oRandom.nextInt(getMaxBySize(size)));

		} catch (Exception e) {
			targetVal = getMaxBySize(size);
		}

		//logger.debug("getRandomNumberbyLimit:" + limit + ", " + size + "=" + targetVal);

		return targetVal;
	}

	public static String getRandomRGB() {

		return "rgb(" + getRandomNumberbyLimit("255", 3) + "," + getRandomNumberbyLimit("255", 3) + "," + getRandomNumberbyLimit("255", 3) + ")";
	}
	
	public static String getRandomRGB(String max) {

		return "rgb(" + getRandomNumberbyLimit(max, 3) + "," + getRandomNumberbyLimit(max, 3) + "," + getRandomNumberbyLimit(max, 3) + ")";
	}
}
