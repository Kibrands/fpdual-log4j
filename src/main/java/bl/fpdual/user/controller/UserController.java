package bl.fpdual.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bl.fpdual.user.model.User;
import bl.fpdual.user.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/users")
	public void addNewUser(@RequestBody User user) {
		this.userService.addNewUser(user);
	}

	@GetMapping("/users")
	public List<User> getUserList() {
		return this.userService.getUserList();
	}

	@PutMapping("/users/{nickName}")
	public void modifyExistingUser(@PathVariable("nickName") String nickName, @RequestBody User user) {
		this.userService.modifyExistingUser(nickName, user);
	}

	@GetMapping("/users/{country}")
	public List<User> getUserListByCountry(@PathVariable("country") String country) {
		return this.userService.getUserListByCountry(country);
	}

	@DeleteMapping("/users/remove/{nickName}")
	public void removeUser(@PathVariable("nickName") String nickName) {
		this.userService.removeUser(nickName);
	}
}
