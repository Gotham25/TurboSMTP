package com.github.gotham25.turbosmtp

import khttp.get
import khttp.post
import khttp.responses.Response
import org.json.JSONObject
import com.github.gotham25.turbosmtp.util.JsonBuilder

class TurboMailer private constructor(
	private val authUser: String? = null,
	private val authPass: String? = null
){
	
	private val BASE_URL = "https://dashboard.serversmtp.com/api"
	private val AUTHORIZATION = "Authorization"
	private val CONTENT_TYPE = "Content-Type"
	private val APPLICATION_JSON = "application/json"
	private val ERROR = "error"
	private val ERROR_CODE = "errorCode"
	
	/**
	 * This is a Builder class for TurboMailer which performs mail actions
	 *
	 * @property authuser Email address of turboSMTP account.
	 *
	 * @property authpass Password of turboSMTP account.
	 *
	 */
	data class Builder(
		private var authUser: String? = null,
		private var authPass: String? = null
	) {
		
		/**
		 * Sets the username for authentication purpose.
		 *
		 * @property user Email address of turboSMTP account.
		 *
		 */
		fun AuthUser(user: String?) = apply { this.authUser = user }
		
		/**
		 * Sets the password for authentication purpose.
		 *
		 * @property pass Password of turboSMTP account.
		 *
		 */
		fun AuthPass(pass: String?) = apply { this.authPass = pass }
		
		/**
		 * Returns a TurboMailer instance with username & password properties set
		 *
		 */
		fun build() = TurboMailer(authUser, authPass)
	}
	
	/**
	 * Authenticates the user with user & pass provided and returns JSON response containing authentication cookie
	 *
	 */
	private fun authenticate() : JSONObject? {
		val authResponse = get(
			url = "$BASE_URL/authorize/${this.authUser}/${this.authPass}"/*,
			headers = mapOf(
				CONTENT_TYPE to APPLICATION_JSON
			)*/
		)
		return when(authResponse.statusCode) {
			200 -> authResponse.jsonObject
			else -> return JSONObject()
					.put(ERROR, authResponse.text)
					.put(ERROR_CODE, authResponse.statusCode)
			
		}
	}
	
	/**
	 * Sends a mail to desired recipient(s) using turbo SMTP mailer API
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
	fun sendMail(
		from: String,
		to: String,
		subject: String? = null,
		cc: String? = null,
		bcc: String? = null,
		content: String? = null,
		html_content: String? = null,
		custom_headers: String? = null,
		mime_raw: String? = null
	) : String {
		
		val authCookie = authenticate()
		
		if(authCookie != null && !authCookie.has(ERROR)){
			val mailBody = JsonBuilder.Builder()
							.authUser(authUser)
							.authPass(authPass)
							.from(from)
							.to(to)
							.subject(subject)
							.cc(cc)
							.bcc(bcc)
							.content(content)
							.htmlContent(html_content)
							.customHeaders(custom_headers)
							.rawMime(mime_raw)
							.build()
			
			println("\nauthCookie: $authCookie\n\nmailBody: $mailBody\n\n")
		
			val mailResponse = post(
				url = "https://api.turbo-smtp.com/api/mail/send",
				headers = mapOf(
					AUTHORIZATION to authCookie.getString("auth"),
					CONTENT_TYPE to APPLICATION_JSON
				),
				data = mailBody
			)
			
			return when(mailResponse.statusCode) {
				200 -> mailResponse.text
				else -> "{\"error\": \"${mailResponse.text}\", \"errorCode\": ${mailResponse.statusCode}}" 
			}
		}

		return "{\"error\": \"authCookie is null/error. ${authCookie?.getString(ERROR)}\", \"errorCode\": ${authCookie?.getInt(ERROR_CODE)}}"
		
	}
	
	/**
	 * Returns account info as JSON string
	 *
	 */
	fun accountInfo() : String {
		val authCookie = authenticate()
		
		if(authCookie != null && !authCookie.has(ERROR)){
			
			val response = get(
				url = "https://dashboard.serversmtp.com/api/user/info",
				headers = mapOf(
					AUTHORIZATION to authCookie.getString("auth")
				)
			)
			
			return when(response.statusCode) {
				200 -> response.text
				else -> "{\"error\": \"${response.text}\", \"errorCode\": ${response.statusCode}}" 
			}
			
		}

		return "{\"error\": \"authCookie is null/error. ${authCookie?.getString(ERROR)}\", \"errorCode\": ${authCookie?.getInt(ERROR_CODE)}}"
	}
	
	/**
	 * Returns active plans as JSON string
	 *
	 */
	fun activePlans() : String {
		val authCookie = authenticate()
		
		if(authCookie != null && !authCookie.has(ERROR)){
			
			val response = get(
				url = "https://dashboard.serversmtp.com/api/plans",
				headers = mapOf(
					AUTHORIZATION to authCookie.getString("auth")
				)
			)
			
			return when(response.statusCode) {
				200 -> response.text
				else -> "{\"error\": \"${response.text}\", \"errorCode\": ${response.statusCode}}" 
			}
			
		}

		return "{\"error\": \"authCookie is null/error. ${authCookie?.getString(ERROR)}\", \"errorCode\": ${authCookie?.getInt(ERROR_CODE)}}"
	}
	
	/**
	 * Returns sub-account(s) info as JSON string
	 *
	 */
	fun subAccounts() : String {
		val authCookie = authenticate()
		
		if(authCookie != null && !authCookie.has(ERROR)){
			
			val response = get(
				url = "https://dashboard.serversmtp.com/api/useraccounts/summary",
				headers = mapOf(
					AUTHORIZATION to authCookie.getString("auth")
				)
			)
			
			return when(response.statusCode) {
				200 -> response.text
				else -> "{\"error\": \"${response.text}\", \"errorCode\": ${response.statusCode}}" 
			}
			
		}

		return "{\"error\": \"authCookie is null/error. ${authCookie?.getString(ERROR)}\", \"errorCode\": ${authCookie?.getInt(ERROR_CODE)}}"
	}
	
	/**
	 * Returns last email sent statistics for given recordCount
	 *
	 * @property resultCount Max count of records to fetch
	 *
	 */
	fun lastEmailSentStatistics(resultCount: String) : String {
		val authCookie = authenticate()
		
		if(authCookie != null && !authCookie.has(ERROR)){
			
			val response = get(
				url = "https://dashboard.serversmtp.com/api/stats/email-feed-last/$resultCount",
				headers = mapOf(
					AUTHORIZATION to authCookie.getString("auth")
				)
			)
			
			return when(response.statusCode) {
				200 -> response.text
				else -> "{\"error\": \"${response.text}\", \"errorCode\": ${response.statusCode}}" 
			}
			
		}

		return "{\"error\": \"authCookie is null/error. ${authCookie?.getString(ERROR)}\", \"errorCode\": ${authCookie?.getInt(ERROR_CODE)}}"
	}
}