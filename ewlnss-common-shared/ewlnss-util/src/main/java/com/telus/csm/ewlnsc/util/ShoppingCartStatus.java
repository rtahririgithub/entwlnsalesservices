package com.telus.csm.ewlnsc.util;

public interface ShoppingCartStatus {
	
	public static final String PREPARED ="PREPARED";
	public static final String NEW ="NEW";
	public static final String IN_PROCESS ="IN_PROCESS";
	public static final String INTERMEDIATE_COMPLETED ="INTERMEDIATE_COMPLETED";
	public static final String COMPLETED ="COMPLETED";
	public static final String CANCELLED ="CANCELLED";
	public static final String FAILED ="FAILED";
	
	
	//these status from Wireless code, and used in ESDB sales order
	public enum CartItemStatus {
		//@formatter:off
		PREPARED("prepared"),
		NEW("new"), // there is no corresponding SRPDS status type id for this order status
		IN_PROCESS("in-process"), // there is no corresponding SRPDS status type id for this order status
		FAILED("failed"),
		INTERMEDIATE_ORDER_COMPLETED("intermediate order completed"),
		CANCELLED("cancelled"),
		RESERVED("reserved"),
		PENDING("pending"),
		ABANDON("abandon"),
		COMPLETED("completed"),
		RETURNED("returned"), // there is no corresponding SRPDS status type id for this order status

		;
		//@formatter:on

		private final String code;

		private CartItemStatus(String code) {
			this.code = code;
		}

		public boolean isEqualTo(String str) {
			if( str == null )
				return false;
			return code.equalsIgnoreCase(str);
		}

		public String getCode() {
			return code;
		}

		@Override
		public String toString() {
			return code;
		}

		public static CartItemStatus getStatusByCode(String statusCode) {
			for( CartItemStatus s : CartItemStatus.values() ) {
				if( s.getCode() != null && s.getCode().equalsIgnoreCase(statusCode))
					return s;
			}
			return null;
		}		
	
	}
	
}