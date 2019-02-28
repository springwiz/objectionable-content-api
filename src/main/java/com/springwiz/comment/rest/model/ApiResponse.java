package com.springwiz.comment.rest.model;

/**
 * The Interface ApiResponse.
 *
 * @author sumit
 */
public interface ApiResponse {

	/**
	 * The Enum ResponseStatus.
	 */
	public enum ResponseStatus {

		/** The success. */
		SUCCESS("success"),

		/** The error. */
		ERROR("error"),

		/** The dberror. */
		DB_ERROR("dberror"),

		/** The deleted. */
		DELETED("deleted"),

		/** The no entry exists. */
		NO_ENTRY_EXISTS("no_entry_exists");

		/** The str value. */
		private final String strValue;

		/**
		 * Instantiates a new response status.
		 *
		 * @param strValue
		 *            the str value
		 */
		private ResponseStatus(String strValue) {
			this.strValue = strValue;
		}

		@Override
		public String toString() {
			return strValue;
		}
	}

	/**
	 * get the Response Code.
	 *
	 * @return String
	 */
	public String getCode();

	/**
	 * get the Response Message.
	 *
	 * @return String
	 */
	public String getMessage();

	/**
	 * get the Response Status.
	 *
	 * @return ResponseStatus
	 */
	public ResponseStatus getStatus();
}
