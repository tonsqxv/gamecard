package com.macower.basedata.util;

import com.paypal.sdk.util.Util;

public class DoubleUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Double format2Point(Double totalPrice) {
		// 先四舍五入
		float f_totalPrice = Util.round(
				Float.parseFloat(Double.toString(totalPrice)), 2);
		totalPrice = Double.parseDouble(String.valueOf(f_totalPrice));
		// 判断小数点位数，如果大于两位，截取前面两位小数点
		String[] arr = String.valueOf(totalPrice).split("\\.");
		if (arr.length == 2 && arr[1].length() > 2) {
			totalPrice = Double.parseDouble(arr[0] + "."
					+ arr[1].substring(0, 2));
		}

		return totalPrice;

	}

}
