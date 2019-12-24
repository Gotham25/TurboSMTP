package com.github.gotham25.turbosmtp.util

import java.lang.StringBuilder

/**
 * This is a JSON builder class to create mail body.
 *
 */
class JsonBuilder private constructor(
		private val authuser: String?,
		private val authpass: String?,
		private val from: String?,
		private val to: String?,
		private val subject: String? = null,
		private val cc: String? = null,
		private val bcc: String? = null,
		private val content: String? = null,
		private val html_content: String? = null,
		private val custom_headers: String? = null,
		private val mime_raw: String? = null 	
) {
	
	/**
	 * This is a Builder class for JsonBuilder which creates JSON string based on builder pattern
	 *
	 * @property authuser Email address of turboSMTP account.
	 *
	 * @property authpass Password of turboSMTP account.
	 *
	 * @property from Source email address from whom the mail is to be sent.
	 *
	 * @property to Comma-separated recipients list to whom the email is to be sent.
	 *
	 * @property subject Subject of the email.
	 *
	 * @property cc Comma-separated copy list to whom the email is to be sent.
	 *
	 * @property bcc Comma-separated hidden copy list to whom the email is to be sent.
	 *
	 * @property content The text content of the email.
	 *
	 * @property html_content The html content of the email.
	 *
	 * @property custom_headers Additional headers in JSON format.
	 *
	 * @property mime_raw MIME message which replaces content and html_content.
	 *
	 */
	data class Builder(
		private var authuser: String? = null,
		private var authpass: String? = null,
		private var from: String? = null,
		private var to: String? = null,
		private var subject: String? = null,
		private var cc: String? = null,
		private var bcc: String? = null,
		private var content: String? = null,
		private var html_content: String? = null,
		private var custom_headers: String? = null,
		private var mime_raw: String? = null 
	) {
		/**
		 * Sets the username for authentication purpose.
		 *
		 * @property authuser Email address of turboSMTP account.
		 *
		 */
		fun authUser(authuser: String?) = apply { this.authuser = authuser }
		
		/**
		 * Sets the password for authentication purpose.
		 *
		 * @property authpass Password of turboSMTP account.
		 *
		 */
		fun authPass(authpass: String?) = apply { this.authpass = authpass }
		
		/**
		 * Sets the from email address
		 *
		 * @property from Source email address from whom the mail is to be sent.
		 *
		 */
		fun from(from: String?) = apply { this.from = from }
		
		/**
		 * Sets the to email address
		 *
		 * @property to Comma-separated recipients list to whom the email is to be sent.
		 *
		 */
		fun to(to: String?) = apply { this.to = to }
		
		/**
		 * Sets the subject for email address
		 *
		 * @property subject Subject of the email.
		 *
		 */
		fun subject(subject: String?) = apply { this.subject = subject }
		
		/**
		 * Sets the cc field for email address
		 *
		 * @property cc Comma-separated copy list to whom the email is to be sent.
		 *
		 */
		fun cc(cc: String?) = apply { this.cc = cc }
		
		/**
		 * Sets the bcc field for email address
		 *
		 * @property bcc Comma-separated hidden copy list to whom the email is to be sent.
		 *
		 */
		fun bcc(bcc: String?) = apply { this.bcc = bcc }
		
		/**
		 * Sets the plain text content field for email address
		 *
		 * @property content The text content of the email.
		 *
		 */
		fun content(content: String?) = apply { this.content = content }
		
		/**
		 * Sets the html content field for email address
		 *
		 * @property html_content The html content of the email.
		 *
		 */
		fun htmlContent(html_content: String?) = apply { this.html_content = html_content }
		
		/**
		 * Sets custom headers for email address
		 *
		 * @property custom_headers Additional headers in JSON format.
		 *
		 */
		fun customHeaders(custom_headers: String?) = apply { this.custom_headers = custom_headers }
		
		/**
		 * Sets raw MIME content for email address
		 *
		 * @property mime_raw MIME message which replaces content and html_content.
		 *
		 */
		fun rawMime(mime_raw: String?) = apply { this.mime_raw = mime_raw }
		
		/**
		 * Returns a JSON string with provided properties set
		 *
		 */
		fun build() = JsonBuilder(authuser, authpass, from, to, subject, cc, bcc, content, html_content, custom_headers, mime_raw).build()
	}
	
	/**
	 * Builds a JSON string based on the required and optional parameters set in this class and resturns it
	 *
	 */
	fun build() : String {
		
		val jsonBuilder = StringBuilder()
		jsonBuilder.append("{")
		jsonBuilder.append("\"authuser\":\"$authuser\"")
		jsonBuilder.append(",\"authpass\":\"$authpass\"")
		jsonBuilder.append(",\"from\":\"$from\"")
		jsonBuilder.append(",\"to\":\"$to\"")
		if(subject != null)
			jsonBuilder.append(",\"subject\":\"$subject\"")
		if(cc != null)
			jsonBuilder.append(",\"cc\":\"$cc\"")
		if(bcc != null)
			jsonBuilder.append(",\"bcc\":\"$bcc\"")
		if(content != null)
			jsonBuilder.append(",\"content\":\"$content\"")
		if(html_content != null)
			jsonBuilder.append(",\"html_content\":\"$html_content\"")
		if(custom_headers != null)
			jsonBuilder.append(",\"custom_headers\":$custom_headers")
		if(mime_raw != null)
			jsonBuilder.append(",\"mime_raw\":\"$mime_raw\"")
		jsonBuilder.append("}")
		
		return jsonBuilder.toString()
	}
	
}