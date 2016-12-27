package io.spring.demo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun viewRedering() {
		val content = """<html>
<body>
<title>Users</title>
<h1>Title : Users</h1>
<ul>
<li>User Juergen Hoeller</li>
<li>User Rossen Stoyanchev</li>
<li>User Brian Clozel</li>
<li>User Stéphane Nicoll</li>
<li>User Arjen Poutsma</li>
<li>User Sébastien Deleuze</li>
</ul>
</body>
</html>"""
		assertEquals(content, restTemplate.getForEntity("/", String::class.java).body)
	}

}
