package com.github.gotham25.turboapi.utiltest

import kotlin.test.Test
import kotlin.test.assertEquals
import com.github.gotham25.turbosmtp.util.JsonBuilder

/**
 * This is a test class for JsonBuilder class.
 *
 */
class JsonBuilderTest {
	@Test fun testBuild() {
		var classUnderTest = JsonBuilder.Builder()
								.authUser("DummyUser")
								.authPass("DummyPass@123")
								.from("sample123@gmail.com")
								.to("sample456@gmail.com")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\"}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.subject("test subject")
		
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\"}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.cc("sample789@gmail.com")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\"}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.bcc("test123@gmail.com")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\",\"bcc\":\"test123@gmail.com\"}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.content("Sample plain text content")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\",\"bcc\":\"test123@gmail.com\",\"content\":\"Sample plain text content\"}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.htmlContent("<body>Sample html content</body>")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\",\"bcc\":\"test123@gmail.com\",\"content\":\"Sample plain text content\",\"html_content\":\"<body>Sample html content</body>\"}", classUnderTest.build())

		classUnderTest = classUnderTest
							.customHeaders("{\"X-key1\":\"value1\", \"X-key2\":\"value2\"}")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\",\"bcc\":\"test123@gmail.com\",\"content\":\"Sample plain text content\",\"html_content\":\"<body>Sample html content</body>\",\"custom_headers\":{\"X-key1\":\"value1\", \"X-key2\":\"value2\"}}", classUnderTest.build())
		
		classUnderTest = classUnderTest
							.rawMime("Sample raw mime message")
		
		assertEquals("{\"authuser\":\"DummyUser\",\"authpass\":\"DummyPass@123\",\"from\":\"sample123@gmail.com\",\"to\":\"sample456@gmail.com\",\"subject\":\"test subject\",\"cc\":\"sample789@gmail.com\",\"bcc\":\"test123@gmail.com\",\"content\":\"Sample plain text content\",\"html_content\":\"<body>Sample html content</body>\",\"custom_headers\":{\"X-key1\":\"value1\", \"X-key2\":\"value2\"},\"mime_raw\":\"Sample raw mime message\"}", classUnderTest.build())
		 
	}
}