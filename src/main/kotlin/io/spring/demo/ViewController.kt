package io.spring.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/")
    fun render(model: Model): String {
        model.addAttribute("title", "Users")
        model.addAttribute("users", listOf(
                User("Juergen", "Hoeller"),
                User("Rossen", "Stoyanchev"),
                User("Brian", "Clozel"),
                User("Stéphane", "Nicoll"),
                User("Arjen", "Poutsma"),
                User("Sébastien", "Deleuze")
        ))
        return "index"
    }

}