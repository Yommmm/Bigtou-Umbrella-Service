package com.bigtou.umbrella.util;

public abstract class GlobalConstants {
	
	// 伞机状态
	public static final String SJ_FLAG_0 = "0";//机器出伞状态
	public static final String SJ_FLAG_1 = "1";//出伞成功
	public static final String SJ_FLAG_2 = "2";//出伞失败
	public static final String SJ_FLAG_3 = "3";//还伞成功
	public static final String SJ_FLAG_4 = "4";//还伞失败
	
	// 出伞状态
	public static final String CS_FLAG_0 = "0";//
	public static final String CS_FLAG_1 = "1";//
	
	/**
	 * 订单状态
	 * 开始状态，下单支付，未出伞 | start
	 * 出伞成功   | finish
	 * 出伞中 | doing
	 * 已还伞 | return
	 */
	public static final String ORDER_STATUS_START = "start";
	public static final String ORDER_STATUS_FINISH = "finish";
	public static final String ORDER_STATUS_DOING = "doing";
	public static final String ORDER_STATUS_RETURN = "return";
	
	/**
	 * 操作类型
	 * 工作人员操作 | Operate
	 * 出伞                | Out
	 * 还伞                | In
	 */
	public static final String OPERATE_TYPE_OPERATE = "operate";
	public static final String OPERATE_TYPE_OUT = "out";
	public static final String OPERATE_TYPE_IN = "in";
	
}
