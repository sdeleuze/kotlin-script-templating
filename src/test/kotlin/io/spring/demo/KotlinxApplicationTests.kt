package io.spring.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("kotlinx")
class KotlinxApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	val englishContent = """<html><head><title>Users</title></head><body><p>Locale: <a href="/?locale=fr">FR</a> | <a href="/?locale=en">EN</a></p><h1>Kotlinx Users</h1><ul><li>User Juergen Hoeller</li><li>User Rossen Stoyanchev</li><li>User Brian Clozel</li><li>User Stéphane Nicoll</li><li>User Arjen Poutsma</li><li>User Sébastien Deleuze</li></ul></body></html>"""

	val frenchContent = """<html><head><title>Utilisateurs</title></head><body><p>Locale: <a href="/?locale=fr">FR</a> | <a href="/?locale=en">EN</a></p><h1>Kotlinx Utilisateurs</h1><ul><li>Utilisateur Juergen Hoeller</li><li>Utilisateur Rossen Stoyanchev</li><li>Utilisateur Brian Clozel</li><li>Utilisateur Stéphane Nicoll</li><li>Utilisateur Arjen Poutsma</li><li>Utilisateur Sébastien Deleuze</li></ul></body></html>"""

	@Test
	fun viewRenderingWithDefaultLocale() {
        assertEquals(englishContent, restTemplate.getForObject<String>("/"))
	}

	@Test
	fun viewRenderingWithEnglishLocale() {
        assertEquals(englishContent, restTemplate.getForObject<String>("/?locale=en"))
	}

	@Test
	fun viewRenderingWithFrenchLocale() {
        assertEquals(frenchContent, restTemplate.getForObject<String>("/?locale=fr"))
	}

}

