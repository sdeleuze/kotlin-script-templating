package io.spring.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/")
    fun render(model: Model): String {
        model.addAttribute("title", "Layout example")
        return "foo"
    }

}